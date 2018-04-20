package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.entities.Product;
import store.entities.User;
import store.services.interfaces.ProductCategoryService;
import store.services.interfaces.ProductService;
import store.services.interfaces.ProductVendorService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Controller("AllController")

public class AllController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductVendorService productVendorService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * Method for dispatching requests to user's contracts
     *
     * @param req   request from page
     * @param model model for page view
     * @return page for view user's contract
     */
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalog(HttpServletRequest req, Model model,
                          @RequestParam(value = "category", required = false) String category,
                          @RequestParam(value = "vendor", required = false) String vendor,
                          @RequestParam(value = "minprice", required = false) String minprice,
                          @RequestParam(value = "maxprice", required = false) String maxprice
    ) {

        model.addAttribute("productList", productService.getProductByComplex(category, vendor, minprice, maxprice));
        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");

        int items = 0;
        for ( Object i :((HashMap) req.getSession().getAttribute("cartProducts")).values()){
            items += (Integer) i;
        }
        model.addAttribute("items", items);

        return "all/allproducts";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(HttpServletRequest req, Model model) {
        try {
            if (req.getSession().getAttribute("cartProducts").equals(null)) {
                req.getSession().setAttribute("cartProducts", new HashMap<Product, Integer>());
            }
        } catch (Exception e) {
            req.getSession().setAttribute("cartProducts", new HashMap<Product, Integer>());
        }

        try {
            if (req.getSession().getAttribute("currentUser").equals(null)) {
                req.getSession().setAttribute("currentUser", new User());
            }
        } catch (Exception e) {
            req.getSession().setAttribute("currentUser", new User());
        }

        int items = 0;
        for ( Object i :((HashMap) req.getSession().getAttribute("cartProducts")).values()){
            items += (Integer) i;
        }
        model.addAttribute("items", items);
        return "all/index";
    }


    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contact(HttpServletRequest req, Model model) {
        return "all/contacts";
    }




    @RequestMapping(value = "/catalog", method = RequestMethod.POST)
    @Scope("session")
    public String addtoCart(HttpServletRequest req, Model model) {
        int productId = Integer.parseInt(req.getParameter("item"));
        HashMap<Product, Integer> cartProducts = (HashMap<Product, Integer>) req.getSession().getAttribute("cartProducts");
        Product product = productService.getEntityById(productId);
        System.out.println(productId);

        if (cartProducts.containsKey(product)) {
            cartProducts.put(product, cartProducts.get(product) + 1);
            System.out.println("Increment " + product);
        } else {
            cartProducts.put(product, 1);
            System.out.println("Adding " + product);
        }
        int items = 0;
        for ( Object i :((HashMap) req.getSession().getAttribute("cartProducts")).values()){
            items += (Integer) i;
        }
        model.addAttribute("items", items);

        model.addAttribute("allCategories", productCategoryService.getAll());
        model.addAttribute("allVendors", productVendorService.getAll());
        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "all/allproducts";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(@RequestParam("id") int id, HttpServletRequest req, Model model) {
        model.addAttribute("product", productService.getEntityById(id));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        return "all/singleproduct";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @Scope("session")
    public String addProductToCart(HttpServletRequest req, Model model) {
        int productId = Integer.parseInt(req.getParameter("product"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        HashMap<Product, Integer> cartProducts = (HashMap<Product, Integer>) req.getSession().getAttribute("cartProducts");
        Product product = productService.getEntityById(productId);
        System.out.println(productId);

        if (cartProducts.containsKey(product)) {
            cartProducts.put(product, cartProducts.get(product) + quantity);
            System.out.println("Increment " + product);
        } else {
            cartProducts.put(product, quantity);
            System.out.println("Adding " + product);
        }
        int items = 0;
        for ( Object i :((HashMap) req.getSession().getAttribute("cartProducts")).values()){
            items += (Integer) i;
        }
        model.addAttribute("items", items);


        model.addAttribute("product", productService.getEntityById(productId));
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "all/singleproduct";
    }

}
