package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.entities.User;
import store.services.interfaces.*;

import javax.servlet.http.HttpServletRequest;

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


    @RequestMapping(value = "/admin/user-managment", method = RequestMethod.GET)
    public String personalDetails(HttpServletRequest req, Model model) {

        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("allUserList", userService.getAll());
        return "admin/adminmanagmentuser";
    }

}
