package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.dto.UserDTO;
import store.exceptions.UserNotFoundException;
import store.security.UserDetailsServiceDAO;
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

    @Autowired
    private UserDetailsServiceDAO userDetailsServiceDAO;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("dao-auth")
    private AuthenticationManager authenticationManager;

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


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(Model model, HttpServletRequest req) throws ServletException {
        req.getSession().invalidate();
        req.logout();
        return "redirect:/home";
    }


    @RequestMapping(value = "/rememberPassword", method = RequestMethod.GET)
    public String rememberMe() {
        return "all/rememberpassword";
    }


    @RequestMapping(value = "/rememberPassword", method = RequestMethod.POST)
    public String rememberMePost(Model model, HttpServletRequest req, @RequestParam(value = "email") String email) {
        try {
            UserDTO user = userService.getUserByeMail(email);
            model.addAttribute("remindCheck", true);
            model.addAttribute("email", user.getEmail());
        } catch (UserNotFoundException ex) {
            model.addAttribute("remindCheck", false);
        }
        return "all/rememberpassword";
    }

    /**
     * Method for dispatching requests to register page
     *
     * @param model model for page view
     * @return page for view register
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        model.addAttribute("userData", true);
        return "all/register";
    }

    /**
     * Method for dispatching requests to register page
     *
     * @param model model for page view
     * @return page for view register
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPagePost(Model model, HttpServletRequest req,
                                   @RequestParam(value = "email") String email,
                                   @RequestParam(value = "phone") String phone,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "confirm-password") String confirmPassword) {

            if (password.equals(confirmPassword)){
                userService.createUser(email, phone, password);
                model.addAttribute("remindCheck", true);
//                model.addAttribute("email", user.getEmail());

                req.getSession().setAttribute("currentUser", userService.getUserByeMail(email));
                autoLogin(email, password);
            }
        return "all/index";
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPagePost(Model model, HttpServletRequest req,
                                      @RequestParam(value = "email") String email,
                                      @RequestParam(value = "password") String password) {
        req.getSession().setAttribute("currentUser", userService.getUserByeMail(email));
        return "redirect:/main";
    }

    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String deniedPage(Model model) {
        return "all/403";
    }

    @RequestMapping(value = "/login-denied", method = RequestMethod.GET)
    public String loginDeniedPage(Model model) {
        return "all/logindenied";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest req, Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO currentUser = userService.getUserByeMail(user.getUsername());
        req.getSession().setAttribute("currentUser", currentUser);
        if (userService.getAccessLevelIdByUser(currentUser) == 1) {
            return "redirect:user/previous-orders";
        } else if (userService.getAccessLevelIdByUser(currentUser) == 2) {
            return "redirect:admin/all-products";
        } else return "all/index";
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

}
