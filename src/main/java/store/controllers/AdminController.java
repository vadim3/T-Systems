package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.entities.*;
import store.services.interfaces.*;

import javax.servlet.http.HttpServletRequest;
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

        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("allUserList", userService.getAll());
        return "admin/adminmanagmentuser";
    }


    @RequestMapping(value = "/admin/order-history", method = RequestMethod.GET)
    public String orderHistory(HttpServletRequest req, Model model) {
        List<Map<Product, Integer>> allOrdersMap = new ArrayList<>();
        List<Order> orders = orderService.getAll();
        Collections.reverse(orders);
        for (Order order : orders){
            allOrdersMap.add(orderService.transformListToMap(order.getProducts()));
        }
        Collections.reverse(allOrdersMap);
        model.addAttribute("allorders", allOrdersMap);
        model.addAttribute("orders", orders);
        model.addAttribute("orderstatuses", orderService.getAllOrderStatuses());
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("allUserList", userService.getAll());
        return "admin/adminallorders";
    }

    @RequestMapping(value = "/admin/order-history", method = RequestMethod.POST)
    public String udpdateOrderStatus(HttpServletRequest req, Model model,
                               @RequestParam(value = "order_status", required = false) String orderStatus,
                               @RequestParam(value = "order_id", required = false) String orderId){
        Order order =  orderService.getEntityById(Integer.parseInt(orderId));
        order.setOrderStatus(orderService.getOrderStatusByStatus(orderStatus));
        orderService.updateEntity(order);
        return orderHistory(req, model);
    }


    @RequestMapping(value = "/admin/all-products", method = RequestMethod.GET)
    public String catalog(HttpServletRequest req, Model model,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "vendor", required = false) String vendor,
                          @RequestParam(value = "minprice", required = false) String minprice,
                          @RequestParam(value = "maxprice", required = false) String maxprice
    ) {
        int items = 0;
        for ( Object i :((HashMap) req.getSession().getAttribute("cartProducts")).values()){
            items += (Integer) i;
        }

        model.addAttribute("items", items);
        model.addAttribute("productList", productService.getProductByComplex(category, vendor, minprice, maxprice));
        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "admin/adminallproducts";
    }

    @RequestMapping(value = "/admin/change-product", method = RequestMethod.GET)
    public String changeProduct(HttpServletRequest req, Model model,
                            @RequestParam(value = "item", required = false) String item) {
        boolean isNewProduct = false;
        if (item != null){
            model.addAttribute("product", productService.getEntityById(Integer.parseInt(item)));
        } else {
            model.addAttribute("product", new Product());
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
                                  @RequestParam(value = "image_path", required = false) String imagePath,
                                  @RequestParam(value = "weight", required = false) String weight,
                                  @RequestParam(value = "volume", required = false) String volume,
                                  @RequestParam(value = "color", required = false) String color,
                                  @RequestParam(value = "power", required = false) String power) {

        if (productId.equals("0")){
            productService.createNewProduct(name, price,stockQuintity, productCategory, productVendor,
                    description, imagePath, weight, volume, color, power);
        } else {
            productService.updateAllFieldsProduct(productId, name, price,stockQuintity, productCategory, productVendor,
                    description, imagePath, weight, volume, color, power);
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
            model.addAttribute("category", new ProductCategory());
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
        ProductCategory productCategory;
        if (categoryId.equals("0")){
            productCategory = new ProductCategory();
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
            model.addAttribute("vendor", new ProductVendor());
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
        ProductVendor productVendor;
        if (vendorId.equals("0")){
            productVendor = new ProductVendor();
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
            String incomevalue = String.format("%.2f", income);
            model.addAttribute("datemessage", datemessage);
            model.addAttribute("incomevalue", incomevalue);
            return "admin/adminincomestatistic";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "admin/adminincomestatistic";
    }

}
