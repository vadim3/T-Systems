package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import store.dto.*;
import store.entities.ProductCategory;
import store.exceptions.DAOException;
import store.exceptions.DuplicateProductCategoryException;
import store.exceptions.DuplicateProductVendorException;
import store.services.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Controller("AdminController")
public class AdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAdressService userAdressService;

    @Autowired
    private ProductVendorService productVendorService;

    @Autowired
    private ProductCategoryService productCategoryService;


    @RequestMapping(value = "/admin/user-management", method = RequestMethod.GET)
    public String controllUsers(HttpServletRequest req, Model model) {
        initSession(req);
        model.addAttribute("currentUser", ((UserDTO) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        model.addAttribute("allUserList", userService.getAll());
        return "admin/adminmanagmentuser";
    }


    @RequestMapping(value = "/admin/order-history", method = RequestMethod.GET)
    public String orderHistory(HttpServletRequest req, Model model, String notification) {
        initSession(req);
        List<Map<ProductDTO, Integer>> allOrdersMap = new ArrayList<>();
        List<OrderDTO> orders = orderService.getAll();
        List<UserDTO> users = new ArrayList<>();
        List<UserAdressDTO> userAddresses = new ArrayList<>();
        Collections.reverse(orders);
        for (OrderDTO order : orders) {
            allOrdersMap.add(order.getProducts());
            UserDTO userDTO = userService.getUserByOrder(order);
            users.add(userDTO);
            userAddresses.add(userAdressService.getUserAdressByUserId(userDTO.getUserId()));
        }

        Collections.reverse(allOrdersMap);

        model.addAttribute("allorders", allOrdersMap);
        model.addAttribute("orders", orders);
        model.addAttribute("users", users);
        model.addAttribute("useradresses", userAddresses);
        model.addAttribute("orderstatuses", orderService.getAllOrderStatuses());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        model.addAttribute("notification", notification);
        return "admin/adminallorders";
    }

    @RequestMapping(value = "/admin/order-history", method = RequestMethod.POST)
    public String updateOrderStatus(HttpServletRequest req, Model model,
                                    @RequestParam(value = "order_status", required = false) String orderStatus,
                                    @RequestParam(value = "order_id", required = false) String orderId) {
        initSession(req);
        OrderDTO order = orderService.getEntityById(Integer.parseInt(orderId));
        order.setOrderStatus(orderService.getOrderStatusByStatus(orderStatus));
        orderService.updateEntity(order);
        return orderHistory(req, model, "Order status successfully changed");
    }


    @RequestMapping(value = "/admin/all-products", method = RequestMethod.GET)
    public String catalog(HttpServletRequest req, Model model,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "vendor", required = false) String vendor,
                          @RequestParam(value = "minprice", required = false) String minprice,
                          @RequestParam(value = "maxprice", required = false) String maxprice,
                          @RequestParam(value = "page", required = false) String page
    ) {
        int items = 0;
        for (Object i : ((HashMap) req.getSession().getAttribute("cartProducts")).values()) {
            items += (Integer) i;
        }

        model.addAttribute("items", items);
        model.addAttribute("productList", productService.getProductByComplex(category, vendor, minprice, maxprice, page));
        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchVendor", vendor);
        model.addAttribute("minprice", minprice);
        model.addAttribute("maxprice", maxprice);
        model.addAttribute("page", (page == null) ? 1 : Integer.parseInt(page));
        model.addAttribute("pageQuantity", productService.paginationPages(category, vendor, minprice, maxprice, page));
        model.addAttribute("itemsQuantity", productService.itemsQuintity(category, vendor, minprice, maxprice, page));
        return "admin/adminallproducts";
    }

    @RequestMapping(value = "/admin/add-product", method = RequestMethod.GET)
    public String addProduct(HttpServletRequest req, Model model, String notification) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("allvendors", productVendorService.getAll());
        model.addAttribute("allcategories", productCategoryService.getAll());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("notification", notification);
        return "admin/adminaddproduct";
    }

    @RequestMapping(value = "/admin/add-product", method = RequestMethod.POST)
    public String confirmAddProduct(@ModelAttribute("product") @Valid ProductDTO productDTO,
                                    BindingResult bindingResult, HttpServletRequest req, Model model,
                                    @RequestParam(value = "product_category", required = false) String productCategory,
                                    @RequestParam(value = "product_vendor", required = false) String productVendor,
                                    @RequestParam(value = "image_file", required = false) MultipartFile image,
                                    @RequestParam(value = "thumb_file", required = false) MultipartFile imageth
    ) throws IOException {
        productDTO.getProductCategoryDTO().setName(productCategory);
        productDTO.getProductVendorDTO().setName(productVendor);
        if (bindingResult.hasErrors()) {
            model.addAttribute("allvendors", productVendorService.getAll());
            model.addAttribute("allcategories", productCategoryService.getAll());
            return "admin/adminaddproduct";
        }
        if (image.getSize() != 0){
            productDTO.setImagePath(productDTO.getProductCategoryDTO().getName().replaceAll(" ", "-").toLowerCase()
                    + "/" + productDTO.getName() + ".jpg");

            File dataDir = new File(System.getProperty("jboss.server.data.dir") + "/img/products/"
                    + productDTO.getProductCategoryDTO().getName().replaceAll(" ", "-").toLowerCase());
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            File convFile = new File(dataDir, productDTO.getName().replaceAll(" ", "-").toLowerCase() + ".jpg");
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(image.getBytes());
            fos.close();
        } else {
            productDTO.setImagePath("no-image-item.jpg");
        }

        if (imageth.getSize() != 0){
            File dataDir = new File(System.getProperty("jboss.server.data.dir") + "/thumbs/products/"
                    + productDTO.getProductCategoryDTO().getName().replaceAll(" ", "-").toLowerCase());
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            File convFile = new File(dataDir, productDTO.getName().replaceAll(" ", "-").toLowerCase() + ".jpg");
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(image.getBytes());
            fos.close();
        }
        productService.createEntity(productDTO);
        return addProduct(req, model, "The product " + productDTO.getName() + " successfully added");
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.GET)
    public String changeProduct(HttpServletRequest req, Model model, String notification,
                                @RequestParam(value = "item", required = false) String item) {
        ProductDTO product = productService.getEntityById(Integer.parseInt(item));

        model.addAttribute("product", product);
        model.addAttribute("allvendors", productVendorService.getAll());
        model.addAttribute("allcategories", productCategoryService.getAll());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("notification", notification);
        return "admin/adminchangeproduct";
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.POST)
    public String confirmChangeProduct(@ModelAttribute("product") @Valid ProductDTO productDTO,
                                       BindingResult bindingResult, HttpServletRequest req, Model model,
                                       @RequestParam(value = "product_category", required = false) String productCategory,
                                       @RequestParam(value = "product_vendor", required = false) String productVendor,
                                       @RequestParam(value = "image_file", required = false) MultipartFile image,
                                       @RequestParam(value = "thumb_file", required = false) MultipartFile imageth
    ) throws IOException {
        productDTO.getProductCategoryDTO().setName(productCategory);
        productDTO.getProductVendorDTO().setName(productVendor);

        if (bindingResult.hasErrors()) {
            model.addAttribute("allvendors", productVendorService.getAll());
            model.addAttribute("allcategories", productCategoryService.getAll());
            return "admin/adminaddproduct";
        }
        if (image.getSize() != 0){
            productDTO.setImagePath(productDTO.getProductCategoryDTO().getName().replaceAll(" ", "-").toLowerCase()
                    + "/" + productDTO.getName() + ".jpg");

            File dataDir = new File(System.getProperty("jboss.server.data.dir") + "/img/products/"
                    + productDTO.getProductCategoryDTO().getName().replaceAll(" ", "-").toLowerCase());
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            File convFile = new File(dataDir, productDTO.getName().replaceAll(" ", "-").toLowerCase() + ".jpg");
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(image.getBytes());
            fos.close();
        } else {
            productDTO.setImagePath("no-image-item.jpg");
        }

        if (imageth.getSize() != 0){
            File dataDir = new File(System.getProperty("jboss.server.data.dir") + "/thumbs/products/"
                    + productDTO.getProductCategoryDTO().getName().replaceAll(" ", "-").toLowerCase());
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            File convFile = new File(dataDir, productDTO.getName().replaceAll(" ", "-").toLowerCase() + ".jpg");
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(image.getBytes());
            fos.close();
        }

        productService.updateEntity(productDTO);

        return changeProduct(req, model, "The product" + productDTO.getName() + "successfully changed",
                String.valueOf(productDTO.getProductId()));
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.DELETE)
    public String deleteProduct(HttpServletRequest req, Model model,
                                @RequestParam(value = "product_id", required = false) String productId) {

        if (productId.equals("0")) {

        } else {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(Integer.parseInt(productId));
            productService.deleteEntity(productDTO);
        }
        return "redirect:all-products";
    }

    @RequestMapping(value = "/admin/add-category", method = RequestMethod.GET)
    public String addCategory(HttpServletRequest req, Model model, String notification) {
        model.addAttribute("category", new ProductCategoryDTO());
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("notification", notification);
        return "admin/adminaddproductcategory";
    }

    @RequestMapping(value = "/admin/add-category", method = RequestMethod.POST)
    public String confirmAddCategory(@ModelAttribute("category") @Valid ProductCategoryDTO productCategoryDTO,
                                     BindingResult bindingResult, HttpServletRequest req, Model model) {
        if (bindingResult.hasErrors()) {

            return "admin/adminaddproductcategory";
        }
        try {
            productCategoryService.createEntity(productCategoryDTO);
        } catch (DAOException e) {
            model.addAttribute("error", e.getLocalizedMessage());
            model.addAttribute("imgprefix", "/img/products/");
            return "admin/adminaddproductcategory";
        }
        return addCategory(req, model, "The category " + productCategoryDTO.getName() + " successfully added");
    }

    @RequestMapping(value = "/admin/change-category", method = RequestMethod.GET)
    public String changeCategory(HttpServletRequest req, Model model, String notification,
                                 @RequestParam(value = "category", required = false) String category) {
        if (category == null) {
            return "redirect:add-category";
        }
        model.addAttribute("notification", notification);
        model.addAttribute("category", productCategoryService.getEntityById(Integer.parseInt(category)));
        model.addAttribute("imgprefix", "/img/products/");
        return "admin/adminchangeproductcategory";
    }

    @RequestMapping(value = "/admin/change-category", method = RequestMethod.POST)
    public String confirmChangeCategory(@ModelAttribute("category") @Valid ProductCategoryDTO productCategoryDTO,
                                        BindingResult bindingResult, HttpServletRequest req, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/adminchangeproductcategory";
        }
        try {
            productCategoryService.updateEntity(productCategoryDTO);
        } catch (DuplicateProductCategoryException e) {
            model.addAttribute("error", e.getLocalizedMessage());
            model.addAttribute("imgprefix", "/img/products/");
            return "admin/adminchangeproductcategory";
        }
        return changeCategory(req, model,"The category " + productCategoryDTO.getName() + " successfully changed",
                String.valueOf(productCategoryDTO.getProductCategoryId()));
    }

    @RequestMapping(value = "/admin/change-category", method = RequestMethod.DELETE)
    public String deleteCategory(HttpServletRequest req, Model model,
                                 @RequestParam(value = "category_id", required = false) String categoryId) {

        if (categoryId.equals("0")) {

        } else {
            ProductCategoryDTO productCategoryDTO = productCategoryService.getEntityById(Integer.valueOf(categoryId));
            productCategoryService.deleteEntity(productCategoryDTO);
        }
        return "redirect:all-products";
    }

    @RequestMapping(value = "/admin/add-vendor", method = RequestMethod.GET)
    public String addVendor(HttpServletRequest req, Model model, String notification) {
        model.addAttribute("notification", notification);
        model.addAttribute("vendor", new ProductVendorDTO());
        model.addAttribute("imgprefix", "/img/products/");
        return "admin/adminaddproductvendor";
    }

    @RequestMapping(value = "/admin/add-vendor", method = RequestMethod.POST)
    public String confirmAddVendor(@ModelAttribute("vendor") @Valid ProductVendorDTO productVendorDTO,
                                   BindingResult bindingResult, HttpServletRequest req, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/adminaddproductvendor";
        }
        try {
            productVendorService.createEntity(productVendorDTO);
        } catch (DuplicateProductVendorException e) {
            model.addAttribute("error", e.getLocalizedMessage());
            model.addAttribute("imgprefix", "/img/products/");
            return "admin/adminaddproductvendor";
        }
        return addVendor(req, model,"The vendor " + productVendorDTO.getName() + " successfully added");
    }

    @RequestMapping(value = "/admin/change-vendor", method = RequestMethod.GET)
    public String changeVendor(HttpServletRequest req, Model model, String notification,
                               @RequestParam(value = "vendor", required = false) String vendor) {
        if (vendor == null) {
            return "redirect:add-vendor";
        }
        model.addAttribute("notification", notification);
        model.addAttribute("vendor", productVendorService.getEntityById(Integer.parseInt(vendor)));
        model.addAttribute("imgprefix", "/img/products/");
        return "admin/adminchangeproductvendor";
    }

    @RequestMapping(value = "/admin/change-vendor", method = RequestMethod.POST)
    public String confirmChangeVendor(@ModelAttribute("vendor") @Valid ProductVendorDTO productVendorDTO,
                                      BindingResult bindingResult, HttpServletRequest req, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/adminchangeproductvendor";
        }
        try {
            productVendorService.updateEntity(productVendorDTO);
        } catch (DAOException e) {
            model.addAttribute("error", e.getLocalizedMessage());
            model.addAttribute("imgprefix", "/img/products/");
            return "admin/adminchangeproductvendor";
        }
        return changeVendor(req, model,"The vendor " + productVendorDTO.getName() + " successfully changed",
                String.valueOf(productVendorDTO.getProductVendorId()));
    }

    @RequestMapping(value = "/admin/top-customers", method = RequestMethod.GET)
    public String viewTopUsers(HttpServletRequest req, Model model) {
        model.addAttribute("topcustomers", userService.getTopTenUsers());
        model.addAttribute("imgprefix", "/img/products/");
        return "admin/adminclientstatistic";
    }

    @RequestMapping(value = "/admin/top-products", method = RequestMethod.GET)
    public String viewTopProducts(HttpServletRequest req, Model model) {

        model.addAttribute("topproducts", productService.getTenBestSellersProduct());
        model.addAttribute("thumbprefix", "/img/thumbs/");
        model.addAttribute("imgprefix", "/img/products/");
        return "admin/adminproductstatistic";
    }

    @RequestMapping(value = "/admin/income-statistic", method = RequestMethod.GET)
    public String incomeStatistic(HttpServletRequest req, Model model) {

        model.addAttribute("imgprefix", "/img/products/");
        return "admin/adminincomestatistic";
    }

    @RequestMapping(value = "/admin/income-statistic", method = RequestMethod.POST)
    public String confirmIncomeStatistic(HttpServletRequest req, Model model,
                                         @RequestParam(value = "datefrom", required = false) String dateFrom,
                                         @RequestParam(value = "dateto", required = false) String dateTo) {
        try {
            Double income = orderService.getIncomeInPeriod(dateFrom, dateTo);
            String datemessage = "The income between " + dateFrom + " and " + dateTo + " is ";
            String incomeValue = String.format("%.2f", income);
            model.addAttribute("datemessage", datemessage);
            model.addAttribute("incomevalue", incomeValue);
            return "admin/adminincomestatistic";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "admin/adminincomestatistic";
    }

    private void initSession(HttpServletRequest req) {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");
        if (userDTO == null || userDTO.getEmail() == null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            req.getSession().setAttribute("currentUser", userService.getUserByeMail(user.getUsername()));
        }
    }

}
