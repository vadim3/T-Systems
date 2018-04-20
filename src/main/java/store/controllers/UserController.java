package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.entities.Product;
import store.entities.User;
import store.services.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Controller("UserController")
public class UserController {

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

    @RequestMapping(value = "/user/personal-details", method = RequestMethod.GET)
    public String personalDetails(HttpServletRequest req, Model model) {
        boolean isNewUser = false;

        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", isNewUser);
        return "user/personaldetails";
    }

    @RequestMapping(value = "/user/update-password", method = RequestMethod.GET)
    public String cart(HttpServletRequest req, Model model) {
        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        return "user/changepassword";
    }

    @RequestMapping(value = "/user/shipping-address", method = RequestMethod.GET)
    public String shippingAddress(HttpServletRequest req, Model model) {
        boolean isNewUser = false;
        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", isNewUser);
        return "user/shippingaddress";
    }

    @RequestMapping(value = "/user/previous-orders", method = RequestMethod.GET)
    public String previousOrders(HttpServletRequest req, Model model) {

        User currentUser = ((User) req.getSession().getAttribute("currentUser"));
        boolean noOrders = (currentUser.getOrders() == null || currentUser.getOrders().isEmpty());
        model.addAttribute("allOrders", orderService.getAllOrdersByUser(currentUser.getUserId()));
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", noOrders);
        return "user/previousorders";
    }

}
