package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.dto.OrderDTO;
import store.dto.ProductDTO;
import store.dto.UserAdressDTO;
import store.dto.UserDTO;
import store.services.interfaces.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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

        model.addAttribute("currentUser", ((UserDTO) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", isNewUser);
        return "user/personaldetails";
    }

    @RequestMapping(value = "/user/personal-details", method = RequestMethod.POST)
    public String updatePersonalDetails(HttpServletRequest req, Model model,
                                        @RequestParam(value = "first_name", required = false) String firstName,
                                        @RequestParam(value = "second_name", required = false) String secondName,
                                        @RequestParam(value = "birthday", required = false) String birthday,
                                        @RequestParam(value = "email", required = false) String email,
                                        @RequestParam(value = "phone_number", required = false) String phoneNumber
    ) {
        UserDTO currentUser = (UserDTO) req.getSession().getAttribute("currentUser");
        currentUser.setFirstName(firstName);
        currentUser.setBirthdayData(birthday);
        currentUser.setSecondName(secondName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);
        userService.updateEntity(currentUser);
        req.setAttribute("currentUser", currentUser);

        boolean isNewUser = false;
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", isNewUser);
        return "user/personaldetails";
    }


    @RequestMapping(value = "/user/update-password", method = RequestMethod.GET)
    public String password(Model model) {

        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        return "user/changepassword";
    }

    @RequestMapping(value = "/user/update-password", method = RequestMethod.POST)
    public String updatePassword(HttpServletRequest req, Model model,
                                 @RequestParam(value = "old_password", required = false) String oldPassword,
                                 @RequestParam(value = "new_password", required = false) String newPassword,
                                 @RequestParam(value = "confirm_password", required = false) String confirmPassword) {
        UserDTO currentUser = (UserDTO) req.getSession().getAttribute("currentUser");
        String message;
        if (newPassword.equals(confirmPassword)){
            if (oldPassword.equals(userService.getUserPassword(currentUser))){
                userService.updatePassword(currentUser, newPassword);
                message = "Successfull update!";
            } else {
                message = "Wrong old password!";
            }
        } else {
            message = "New amd Confirm password fields not identically!";
        }
        model.addAttribute("message", message);
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        return "user/changepassword";
    }

    @RequestMapping(value = "/user/shipping-address", method = RequestMethod.GET)
    public String shippingAddress(HttpServletRequest req, Model model) {
        boolean isNewUser = false;
        model.addAttribute("currentUser", ((UserDTO) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", isNewUser);
        return "user/shippingaddress";
    }




    @RequestMapping(value = "/user/shipping-address", method = RequestMethod.POST)
    public String updateShippingAddress(HttpServletRequest req, Model model,
                                        @RequestParam(value = "country", required = false) String country,
                                        @RequestParam(value = "city", required = false) String city,
                                        @RequestParam(value = "street", required = false) String street,
                                        @RequestParam(value = "home", required = false) String home,
                                        @RequestParam(value = "room", required = false) String room,
                                        @RequestParam(value = "zip_code", required = false) String zipCode
    ) {
        UserDTO currentUser = (UserDTO) req.getSession().getAttribute("currentUser");

        UserAdressDTO currentUserAdress = (currentUser.getUserAdressDTO() != null) ? currentUser.getUserAdressDTO() : new UserAdressDTO();
        currentUserAdress.setCountry(country);
        currentUserAdress.setCity(city);
        currentUserAdress.setStreet(street);
        currentUserAdress.setHome(home);
        currentUserAdress.setRoom(room);
        currentUserAdress.setZipCode(zipCode);
        currentUser.setUserAdressDTO(currentUserAdress);
        userService.updateEntity(currentUser);
        boolean isNewUser = false;
        model.addAttribute("currentUser", ((UserDTO) req.getSession().getAttribute("currentUser")));
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", isNewUser);
        return "user/shippingaddress";
    }


    @RequestMapping(value = "/user/previous-orders", method = RequestMethod.GET)
    public String previousOrders(HttpServletRequest req, Model model) {

        UserDTO currentUser = ((UserDTO) req.getSession().getAttribute("currentUser"));
        boolean noOrders = (currentUser.getOrdersDTO() == null || currentUser.getOrdersDTO().isEmpty());
        List<Map<ProductDTO, Integer>> allOrdersMap = new ArrayList<>();
        List<OrderDTO> orders = orderService.getAllOrdersByUser(Integer.parseInt(currentUser.getUserId()));
        for (OrderDTO order : orders){
            allOrdersMap.add(order.getProducts());
        }
        Collections.reverse(allOrdersMap);

        model.addAttribute("allorders", allOrdersMap);
        model.addAttribute("orders", orders);
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("isempty", noOrders);
        return "user/previousorders";
    }

    @RequestMapping(value = "/user/previous-orders", method = RequestMethod.POST)
    public String repeateOrder(HttpServletRequest req, Model model,
                               @RequestParam(value = "order_id", required = false) String orderId) {

        req.getSession().setAttribute("cartProducts", orderService.getEntityById(Integer.parseInt(orderId)).getProducts());
        return "redirect:../cart";
    }


}
