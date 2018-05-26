package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.dto.UserDTO;
import store.exceptions.DuplicateUserException;
import store.exceptions.UserNotFoundException;
import store.security.UserDetailsServiceDAO;
import store.services.interfaces.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

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
    public String loginPage(HttpServletRequest req, Model model) {
        if (req.getHeader("referer") == null) return "all/login";
        if (req.getHeader("referer").equals("http://localhost:8080/login"))
            model.addAttribute("error", "The username or password incorrect");
        return "all/login";
    }

    /**
     * Method for dispatching requests to logout page
     *
     * @param model model for page view
     * @param req   request to page
     * @return page for view login
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(Model model, HttpServletRequest req) throws ServletException {
        req.getSession().invalidate();
        req.logout();
        return "redirect:/home";
    }

    /**
     * Method for dispatching requests to remember password page
     *
     * @return page for view login
     */
    @RequestMapping(value = "/rememberPassword", method = RequestMethod.GET)
    public String rememberMe() {
        return "all/rememberpassword";
    }


    @RequestMapping(value = "/rememberPassword", method = RequestMethod.POST)
    public String rememberMePost(Model model, HttpServletRequest req, @RequestParam(value = "email") String email) {
        String regexpEmail="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (!Pattern.matches(regexpEmail , email)){
            model.addAttribute("error", "Email field is required in right format xxx@xx.xx");
            return "all/rememberpassword";
        }
        try {
            UserDTO user = userService.getUserByeMail(email);
            model.addAttribute("notification", "Your password sent on your e-mail:" + user.getEmail());
        } catch (UserNotFoundException ex) {
            model.addAttribute("error", "User doesn't exist. Try again!");
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

        String error = null;
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);

        String regexpEmail="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if (!Pattern.matches(regexpEmail , email)){
            error = "Email field is required in right format xxx@xx.xx";
            model.addAttribute("error", error);
            return "all/register";
        }
        if (!Pattern.matches("^79[0-9]{9}$" , phone)){
            error = "Phone Number must be in right format 79XXXXXXXXX";
            model.addAttribute("error", error);
            return "all/register";
        }
        if (password.length() < 5){
            error = "Password must be over 5 characters.";
            model.addAttribute("error", error);
            return "all/register";
        }
        if (password.equals(confirmPassword)) {
            try{
                userService.createUser(email, phone, password);
                req.getSession().setAttribute("currentUser", userService.getUserByeMail(email));
                autoLogin(email, password);
                return "all/index";
            } catch (DuplicateUserException e){
                error = e.getLocalizedMessage();
            }
        } else {
            error = "Password and confirmation are not equal";
        }

        model.addAttribute("error", error);
        return "all/register";
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
    public String loginDeniedPage(HttpServletRequest req, Model model) {
        return "redirect:/login";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(HttpServletRequest req, Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO currentUser = userService.getUserByeMail(user.getUsername());
        req.getSession().setAttribute("currentUser", currentUser);
        if (userService.getAccessLevelIdByUser(currentUser) == 1) {
            return "redirect:user/previous-orders";
        } else if (userService.getAccessLevelIdByUser(currentUser) == 2) {
            return "redirect:admin/all-products";
        } else return "all/index";
    }

    private void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

}
