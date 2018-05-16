package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import store.dto.*;
import store.services.interfaces.*;

import javax.servlet.http.HttpServletRequest;
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
    private ProductVendorService productVendorService;

    @Autowired
    private ProductCategoryService productCategoryService;


    @RequestMapping(value = "/admin/user-management", method = RequestMethod.GET)
    public String controllUsers(HttpServletRequest req, Model model) {

        model.addAttribute("currentUser", ((UserDTO) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("allUserList", userService.getAll());
        return "admin/adminmanagmentuser";
    }


    @RequestMapping(value = "/admin/order-history", method = RequestMethod.GET)
    public String orderHistory(HttpServletRequest req, Model model) {
        List<Map<ProductDTO, Integer>> allOrdersMap = new ArrayList<>();
        List<OrderDTO> orders = orderService.getAll();
        Collections.reverse(orders);
        for (OrderDTO order : orders){
            allOrdersMap.add(order.getProducts());
        }
        Collections.reverse(allOrdersMap);
        model.addAttribute("allorders", allOrdersMap);
        model.addAttribute("orders", orders);
        model.addAttribute("orderstatuses", orderService.getAllOrderStatuses());
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
//        model.addAttribute("allUserList", userService.getAll());
        return "admin/adminallorders";
    }

    @RequestMapping(value = "/admin/order-history", method = RequestMethod.POST)
    public String udpdateOrderStatus(HttpServletRequest req, Model model,
                               @RequestParam(value = "order_status", required = false) String orderStatus,
                               @RequestParam(value = "order_id", required = false) String orderId){
        OrderDTO order =  orderService.getEntityById(Integer.parseInt(orderId));
        order.setOrderStatus(orderService.getOrderStatusByStatus(orderStatus));
        orderService.updateEntity(order);
        return orderHistory(req, model);
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
        for ( Object i :((HashMap) req.getSession().getAttribute("cartProducts")).values()){
            items += (Integer) i;
        }

        model.addAttribute("items", items);
        model.addAttribute("productList", productService.getProductByComplex(category, vendor, minprice, maxprice, page));
        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("searchCategory", category);
        model.addAttribute("searchVendor", vendor);
        model.addAttribute("minprice", minprice);
        model.addAttribute("maxprice", maxprice);
        model.addAttribute("page", (page == null) ? 1 : Integer.parseInt(page));
        model.addAttribute("pageQuantity", productService.paginationPages(category, vendor, minprice, maxprice, page));
        model.addAttribute("itemsQuantity", productService.itemsQuintity(category, vendor, minprice, maxprice, page));
        return "admin/adminallproducts";
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.GET)
    public String changeProduct(HttpServletRequest req, Model model,
                            @RequestParam(value = "item", required = false) String item) {
        boolean isNewProduct = false;
        if (item != null){
            model.addAttribute("product", productService.getEntityById(Integer.parseInt(item)));
        } else {
            model.addAttribute("product", new ProductDTO());
            isNewProduct = true;
        }
        model.addAttribute("isnewproduct", isNewProduct);
        model.addAttribute("allvendors", productVendorService.getAll());
        model.addAttribute("allcategories", productCategoryService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "admin/adminchangeproduct";
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.POST)
    public String confirmChangeProduct(HttpServletRequest req, Model model,
                                       @RequestParam(value = "product_id", required = false) String productId,
                                       @RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "price", required = false) String price,
                                       @RequestParam(value = "stock_quintity", required = false) String stockQuintity,
                                       @RequestParam(value = "product_category", required = false) String productCategory,
                                       @RequestParam(value = "product_vendor", required = false) String productVendor,
                                       @RequestParam(value = "description", required = false) String description,
                                       @RequestParam(value = "image_path", required = false) MultipartFile image,
                                       @RequestParam(value = "weight", required = false) String weight,
                                       @RequestParam(value = "volume", required = false) String volume,
                                       @RequestParam(value = "color", required = false) String color,
                                       @RequestParam(value = "power", required = false) String power) throws IOException {


        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setPrice(Double.parseDouble(price));
        productDTO.setStockQuantity(Integer.parseInt(stockQuintity));
        productDTO.setDescription(description);
        productDTO.setImagePath(productCategory.replaceAll(" ","-").toLowerCase() + "/" + name);
        productDTO.setWeight(Double.parseDouble(weight));
        productDTO.setVolume(Double.parseDouble(volume));
        productDTO.setColor(color);
        productDTO.setPower(Double.parseDouble(power));

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setName(productCategory);

        ProductVendorDTO productVendorDTO = new ProductVendorDTO();
        productVendorDTO.setName(productVendor);

        productDTO.setProductCategoryDTO(productCategoryDTO);
        productDTO.setProductVendorDTO(productVendorDTO);

        if (productId.equals("0")){
            File convFile = new File(name);
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(image.getBytes());
            fos.close();
            String imagePath = productCategory + "/" + name;

            productService.createEntity(productDTO);
        } else {
            productDTO.setProductId(Integer.parseInt(productId));
            productService.updateEntity(productDTO);
        }
        return "redirect:all-products";
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.DELETE)
    public String deleteProduct(HttpServletRequest req, Model model,
                                       @RequestParam(value = "product_id", required = false) String productId) {

        if (productId.equals("0")){
            
        } else {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(Integer.parseInt(productId));
            productService.deleteEntity(productDTO);
        }
        return "redirect:all-products";
    }



    @RequestMapping(value = "/admin/change-category", method = RequestMethod.GET)
    public String changeCategory(HttpServletRequest req, Model model,
                                @RequestParam(value = "category", required = false) String category) {
        boolean isNewCategory = false;
        if (category != null){
            model.addAttribute("category", productCategoryService.getEntityById(Integer.parseInt(category)));
        } else {
            model.addAttribute("category", new ProductCategoryDTO());
            isNewCategory = true;
        }
        model.addAttribute("isnewcategory", isNewCategory);
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "admin/adminchangeproductcategory";
    }

    @RequestMapping(value = "/admin/change-category", method = RequestMethod.POST)
    public String confirmChangeCategory(HttpServletRequest req, Model model,
                                       @RequestParam(value = "category_id", required = false) String categoryId,
                                       @RequestParam(value = "name", required = false) String name) {
        ProductCategoryDTO productCategory;
        if (categoryId.equals("0")){
            productCategory = new ProductCategoryDTO();
            productCategory.setName(name);
            productCategoryService.createEntity(productCategory);
        } else {
            productCategory = productCategoryService.getEntityById(Integer.parseInt(categoryId));
            productCategory.setName(name);
            productCategoryService.updateEntity(productCategory);
        }
        return "redirect:all-products";
    }

    @RequestMapping(value = "/admin/change-vendor", method = RequestMethod.GET)
    public String changeVendor(HttpServletRequest req, Model model,
                                 @RequestParam(value = "vendor", required = false) String vendor) {
        boolean isNewVendor = false;
        if (vendor != null){
            model.addAttribute("vendor", productVendorService.getEntityById(Integer.parseInt(vendor)));
        } else {
            model.addAttribute("vendor", new ProductVendorDTO());
            isNewVendor = true;
        }
        model.addAttribute("isnewvendor", isNewVendor);
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "admin/adminchangeproductvendor";
    }

    @RequestMapping(value = "/admin/change-vendor", method = RequestMethod.POST)
    public String confirmChangeVendor(HttpServletRequest req, Model model,
                                        @RequestParam(value = "vendor_id", required = false) String vendorId,
                                        @RequestParam(value = "name", required = false) String name) {
        ProductVendorDTO productVendor;
        if (vendorId.equals("0")){
            productVendor = new ProductVendorDTO();
            productVendor.setName(name);
            productVendorService.createEntity(productVendor);
        } else {
            productVendor = productVendorService.getEntityById(Integer.parseInt(vendorId));
            productVendor.setName(name);
            productVendorService.updateEntity(productVendor);
        }
        return "redirect:all-products";
    }

    @RequestMapping(value = "/admin/top-customers", method = RequestMethod.GET)
    public String viewTopUsers(HttpServletRequest req, Model model) {
        model.addAttribute("topcustomers", userService.getTopTenUsers());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "admin/adminclientstatistic";
    }

    @RequestMapping(value = "/admin/top-products", method = RequestMethod.GET)
    public String viewTopProducts(HttpServletRequest req, Model model) {

        model.addAttribute("topproducts", productService.getTenBestSellersProduct());
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "admin/adminproductstatistic";
    }

    @RequestMapping(value = "/admin/income-statistic", method = RequestMethod.GET)
    public String incomeStatistic(HttpServletRequest req, Model model) {

        model.addAttribute("imgprefix", "../assets/img/products/");
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



}
