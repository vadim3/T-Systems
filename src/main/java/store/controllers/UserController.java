package store.controllers;

import lombok.extern.slf4j.Slf4j;
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
import store.dto.OrderDTO;
import store.dto.ProductDTO;
import store.dto.UserAdressDTO;
import store.dto.UserDTO;
import store.exceptions.DAOException;
import store.exceptions.DuplicateUserException;
import store.services.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

@Slf4j
@Controller("UserController")
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAdressService userAdressService;

    @RequestMapping(value = "/user/personal-details", method = RequestMethod.GET)
    public String personalDetails(HttpServletRequest req, Model model, String notification) {
        initSession(req);

        model.addAttribute("currentUser", ((UserDTO) req.getSession().getAttribute("currentUser")));
        model.addAttribute("notification", notification);
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return "user/personaldetails";
    }

    @RequestMapping(value = "/user/personal-details", method = RequestMethod.POST)
    public String updatePersonalDetails(@ModelAttribute("currentUser") @Valid UserDTO currentUser,
                                        BindingResult bindingResult, HttpServletRequest req, Model model) {
        if (bindingResult.hasErrors()) {
            return "user/personaldetails";
        }
        try {
            userService.updateEntity(currentUser);
        } catch (DuplicateUserException ex) {
            model.addAttribute("error", ex.getMessage());
            return "user/personaldetails";
        }
        req.setAttribute("currentUser", currentUser);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return personalDetails(req, model, "Personal details successfully updated");
    }


    @RequestMapping(value = "/user/update-password", method = RequestMethod.GET)
    public String password(HttpServletRequest req, Model model) {
        initSession(req);
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return "user/changepassword";
    }

    @RequestMapping(value = "/user/update-password", method = RequestMethod.POST)
    public String updatePassword(HttpServletRequest req, Model model,
                                 @RequestParam(value = "old_password", required = false) String oldPassword,
                                 @RequestParam(value = "new_password", required = false) String newPassword,
                                 @RequestParam(value = "confirm_password", required = false) String confirmPassword) {
        UserDTO currentUser = (UserDTO) req.getSession().getAttribute("currentUser");
        String error = null;
        String notification = null;
        if (newPassword.equals(confirmPassword)) {
            if (newPassword.length() >= 5){
                try {
                    userService.updatePassword(currentUser, oldPassword, newPassword);
                    notification = "The password is successfully updated!";
                } catch (RuntimeException ex){
                    error = ex.getLocalizedMessage();
                }
            } else {
                error = "Password must be over 5 characters.";
            }
        } else {
            error = "New amd Confirm password fields are not identically!";
        }
        model.addAttribute("error", error);
        model.addAttribute("notification", notification);
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return "user/changepassword";
    }

    @RequestMapping(value = "/user/shipping-address", method = RequestMethod.GET)
    public String shippingAddress(HttpServletRequest req, Model model) {
        initSession(req);
        UserDTO currentUser = (UserDTO) req.getSession().getAttribute("currentUser");
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentUserAdress", userAdressService.getUserAdressByUserId(currentUser.getUserId()));
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return "user/shippingaddress";
    }


    @RequestMapping(value = "/user/shipping-address", method = RequestMethod.POST)
    public String updateShippingAddress(@ModelAttribute("currentUserAdress") @Valid UserAdressDTO currentUserAdress,
                                        BindingResult bindingResult, HttpServletRequest req, Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "user/shippingaddress";
        }
        UserDTO currentUser = (UserDTO) req.getSession().getAttribute("currentUser");
        if ((currentUserAdress.getAdressId() == 0)) {
            userAdressService.createEntity(currentUser, currentUserAdress);
        } else {
            userAdressService.updateEntity(currentUser, currentUserAdress);
        }


        model.addAttribute("currentUser", (req.getSession().getAttribute("currentUser")));
        model.addAttribute("currentUserAdress", userAdressService.getUserAdressByUserId(currentUser.getUserId()));
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        return "user/shippingaddress";
    }


    @RequestMapping(value = "/user/previous-orders", method = RequestMethod.GET)
    public String previousOrders(HttpServletRequest req, Model model, String notification) {
        initSession(req);
        try {
            validateUser(req);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        UserDTO currentUser = ((UserDTO) req.getSession().getAttribute("currentUser"));

        boolean noOrders = (orderService.getAllOrdersByUser(Integer.parseInt(currentUser.getUserId())) == null ||
                orderService.getAllOrdersByUser(Integer.parseInt(currentUser.getUserId())).isEmpty());
        List<Map<ProductDTO, Integer>> allOrdersMap = new ArrayList<>();
        List<OrderDTO> orders = orderService.getAllOrdersByUser(Integer.parseInt(currentUser.getUserId()));
        for (OrderDTO order : orders) {
            allOrdersMap.add(order.getProducts());
        }
        Collections.reverse(allOrdersMap);
        Collections.reverse(orders);

        model.addAttribute("notification", notification);
        model.addAttribute("allorders", allOrdersMap);
        model.addAttribute("orders", orders);
        model.addAttribute("imgprefix", "/img/products/");
        model.addAttribute("thumbprefix", "/img/thumbs/");
        model.addAttribute("isempty", noOrders);
        return "user/previousorders";
    }

    @RequestMapping(value = "/user/previous-orders", method = RequestMethod.POST)
    public String repeateOrder(HttpServletRequest req, Model model,
                               @RequestParam(value = "order_id", required = false) String orderId) {

        req.getSession().setAttribute("cartProducts", orderService.getEntityById(Integer.parseInt(orderId)).getProducts());
        return "redirect:../cart";
    }

    private void initSession(HttpServletRequest req) {
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDTO == null || userDTO.getEmail() == null) {
            req.getSession().setAttribute("currentUser", userService.getUserByeMail(user.getUsername()));
        }
    }

    private void validateUser(HttpServletRequest req) throws IllegalAccessException {
        IllegalAccessException e = new IllegalAccessException("The userDTO object in session or argument isn't authentic");
        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("currentUser");
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userService.getUserPassword(userDTO).equals(user.getPassword())) {
            log.error("", e);
            throw e;
        }
    }
}
