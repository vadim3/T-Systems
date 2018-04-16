package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.entities.User;
import store.exceptions.UserNotFoundException;
import store.services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Controller("LoginController")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * Method for dispatching requests to login page
     *
     * @param model model for page view
     * @return page for view login
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("userData", true);
        return "all/login";
    }

    /**
     * Method for logout
     *
     * @param model model for page view
     * @return page for logout
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(Model model, HttpServletRequest req) throws ServletException {
        req.getSession().invalidate();
        req.logout();
        model.addAttribute("userData", true);
        return "index";
    }


    @RequestMapping(value = "/rememberPassword", method = RequestMethod.GET)
    public String rememberMe() {
        return "all/rememberpassword";
    }

    /**
     * Method for dispatching requests to rememberMe
     *
     * @param model model;
     * @param email user's email
     * @return remember me page
     */
    @RequestMapping(value = "/rememberPassword", method = RequestMethod.POST)
    public String rememberMePost(Model model, HttpServletRequest req, @RequestParam(value = "email") String email) {
//        try {
            User user = userService.getUserByeMail(email);
            model.addAttribute("remindCheck", true);
//        } catch (UserNotFoundException ex) {
//            model.addAttribute("remindCheck", false);
//        }
        return "all/rememberpassword";
    }

}
