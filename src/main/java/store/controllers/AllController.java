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
import store.services.interfaces.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Controller("AllController")
public class AllController {

    @Autowired
    private ProductService productService;

    /**
     * Method for dispatching requests to user's contracts
     *
     * @param req   request from page
     * @param model model for page view
     * @return page for view user's contract
     */
    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String catalog(HttpServletRequest req, Model model) {
        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "all/allproducts";
    }

//    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
//    public String catalogCategory(@RequestParam("category") String category, HttpServletRequest req, Model model) {
//        //User user = (User) req.getSession().getAttribute("currentUser");
//        model.addAttribute("productByCategoryList", productService.getProductByCategory(category));
//        model.addAttribute("imgprefix", "../assets/img/products/");
//        return "all/allproducts";
//    }
//
//    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
//    public String catalogVendor(@RequestParam("vendor") String vendor, HttpServletRequest req, Model model) {
//        //User user = (User) req.getSession().getAttribute("currentUser");
//        model.addAttribute("productByCategoryList", productService.getProductByCategory(vendor));
//        model.addAttribute("imgprefix", "../assets/img/products/");
//        return "all/allproducts";
//    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String contact(HttpServletRequest req, Model model) {
        return "all/contacts";
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String product(@RequestParam("id") int id, HttpServletRequest req, Model model) {
        //User user = (User) req.getSession().getAttribute("currentUser");
        model.addAttribute("product", productService.getEntityById(id));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        return "all/singleproduct";
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.POST)
    @Scope("session")
    public String addtoCart(HttpServletRequest req, Model model,
                            @RequestParam(value = "data-product-id") int productId, @RequestParam(value = "data-quantity") int quantity)
    {
        try{
            ArrayList<Product> products = (ArrayList<Product>) req.getSession().getAttribute("cartProducts");
            for (int i = 0; i < quantity; i++){
                products.add(productService.getEntityById(productId));
            }
            req.getSession().setAttribute("cartProducts", products);
            System.out.println(1);
        } catch (Exception e){
            req.getSession().setAttribute("cartProducts", new ArrayList<Product>());
            ArrayList<Product> products = (ArrayList<Product>) req.getSession().getAttribute("cartProducts");
            for (int i = 0; i < quantity; i++){
                products.add(productService.getEntityById(productId));
            }
            req.getSession().setAttribute("cartProducts", products);
            System.out.println(2);
        }

        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "all/allproducts";
    }


}
