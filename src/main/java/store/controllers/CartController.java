package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import store.entities.*;
import store.services.interfaces.OrderService;
import store.services.interfaces.ProductService;
import store.services.interfaces.UserService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Controller("CartController")
public class CartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(HttpServletRequest req, Model model) {
        boolean isEmpty = true;
        HashMap<Product, Integer> cartProducts = ((HashMap) req.getSession().getAttribute("cartProducts"));
        List<String> maxStockItems = new ArrayList<>();
        boolean stockIssued = false;
        double sum = 0.0;
        String stockIssuedMessage = "";
        if (!((HashMap) req.getSession().getAttribute("cartProducts")).isEmpty()){
            isEmpty = false;
            for (Map.Entry<Product, Integer> entry : cartProducts.entrySet())
            {
                //Update all data in stock
                Product product = productService.getEntityById(entry.getKey().getProductId());
                if (product.getStockQuantity() < entry.getValue()){
                    maxStockItems.add(product.getName());
                    cartProducts.put(product, product.getStockQuantity());
                    sum += product.getPrice() * product.getStockQuantity();
                    stockIssued = true;
                    continue;
                }
                sum += product.getPrice() * entry.getValue();
            }
            stockIssuedMessage = String.join(", ", maxStockItems);
        }

        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("stockissued", stockIssued);
        model.addAttribute("isempty", isEmpty);
        model.addAttribute("sum", sum);
        model.addAttribute("cartproducts", cartProducts);
        model.addAttribute("stockissuedmessage", "Unfortunately, we havent got enough quintity this item's: ".concat(stockIssuedMessage));
        return "all/cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    @Scope("session")
    public String UpdateCart(HttpServletRequest req, Model model) {
        boolean isEmpty = true;
        String[] productsId = req.getParameterValues("products");
        String[] productQuintities = req.getParameterValues("product_quantities");
        Map<Product, Integer> cartProducts = new HashMap<>();
        for (int i = 0; i < productsId.length; i++){
            if (productQuintities[i].isEmpty() || !productQuintities[i].equals("0")){
                cartProducts.put(productService.getEntityById(Integer.parseInt(productsId[i])), Integer.parseInt(productQuintities[i]));
            }
        }
        req.getSession().setAttribute("cartProducts", cartProducts);

        if (req.getParameter("is-update").equals("true")){
            return cart(req, model);
        }
        return "redirect:user/checkout";
    }

    @RequestMapping(value = "/user/checkout", method = RequestMethod.GET)
    public String checkout(HttpServletRequest req, Model model) {
        boolean isEmpty = true;
        HashMap<Product, Integer> cartProducts = ((HashMap) req.getSession().getAttribute("cartProducts"));
        List<String> maxStockItems = new ArrayList<>();
        boolean stockIssued = false;
        double sum = 0.0;
        String stockIssuedMessage = "";
        if (!((HashMap) req.getSession().getAttribute("cartProducts")).isEmpty()){
            isEmpty = false;
            for (Map.Entry<Product, Integer> entry : cartProducts.entrySet())
            {
                //Update all data in stock
                Product product = productService.getEntityById(entry.getKey().getProductId());
                if (product.getStockQuantity() < entry.getValue()){
                    maxStockItems.add(product.getName());
                    cartProducts.put(product, product.getStockQuantity());
                    sum += product.getPrice() * product.getStockQuantity();
                    stockIssued = true;
                    continue;
                }
                sum += product.getPrice() * entry.getValue();
            }
            stockIssuedMessage = String.join(", ", maxStockItems);
        }
        model.addAttribute("shippingMethods", orderService.getAllShippingMethods());
        model.addAttribute("paymentMethods", orderService.getAllPaymentMethods());
        model.addAttribute("currentUser", ((User) req.getSession().getAttribute("currentUser")));
        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        model.addAttribute("thumbprefix", "../assets/img/thumbs/");
        model.addAttribute("stockissued", stockIssued);
        model.addAttribute("isempty", isEmpty);
        model.addAttribute("sum", sum);
        model.addAttribute("cartproducts", cartProducts);
        model.addAttribute("stockissuedmessage", "Unfortunately, we havent got enough quintity this item's: ".concat(stockIssuedMessage));
        return "user/checkout";
    }

    @RequestMapping(value = "/user/cardpayment", method = RequestMethod.GET)
    public String cardpayment(HttpServletRequest req, Model model) {


        model.addAttribute("productList", productService.getAll());
        model.addAttribute("imgprefix", "../assets/img/products/");
        return "user/cardpayment";
    }

    @RequestMapping(value = "/user/checkout", method = RequestMethod.POST)
    public String confirmCheckout(HttpServletRequest req, Model model,
                                  @RequestParam(value = "first_name", required = false) String firstName,
                                  @RequestParam(value = "second_name", required = false) String secondName,
                                  @RequestParam(value = "email", required = false) String email,
                                  @RequestParam(value = "phone_number", required = false) String phoneNumber,
                                  @RequestParam(value = "shipping_method", required = false) String shippingMethod,
                                  @RequestParam(value = "country", required = false) String country,
                                  @RequestParam(value = "city", required = false) String city,
                                  @RequestParam(value = "street", required = false) String street,
                                  @RequestParam(value = "home", required = false) String home,
                                  @RequestParam(value = "room", required = false) String room,
                                  @RequestParam(value = "zip_code", required = false) String zipCode,
                                  @RequestParam(value = "payment_method", required = false) String paymentMethod) {

        User currentUser = (User) req.getSession().getAttribute("currentUser");

        currentUser.setFirstName(firstName);
        currentUser.setSecondName(secondName);
        currentUser.setEmail(email);
        currentUser.setPhoneNumber(phoneNumber);

        //Updating Address fields
        if (!shippingMethod.equals("Customer Pickup")){
            UserAdress currentUserAdress = (currentUser.getUserAdress() != null) ? currentUser.getUserAdress() : new UserAdress();
            currentUserAdress.setCountry(country);
            currentUserAdress.setCity(city);
            currentUserAdress.setStreet(street);
            currentUserAdress.setHome(home);
            currentUserAdress.setRoom(room);
            currentUserAdress.setZipCode(zipCode);
            currentUser.setUserAdress(currentUserAdress);
        }
        userService.updateEntity(currentUser);

        if (paymentMethod.equals("Credit Card")){
            return "redirect:cardpayment";
        } else {
            HashMap<Product, Integer> cartProducts = ((HashMap) req.getSession().getAttribute("cartProducts"));
            double sum = 0;
            if (!((HashMap) req.getSession().getAttribute("cartProducts")).isEmpty()){
                for (Map.Entry<Product, Integer> entry : cartProducts.entrySet())
                {
                    //Update all data in stock
                    Product product = productService.getEntityById(entry.getKey().getProductId());
                    if (product.getStockQuantity() < entry.getValue()){
                        cartProducts.put(product, product.getStockQuantity());
                        sum += product.getPrice() * product.getStockQuantity();
                        continue;
                    }
                    sum += product.getPrice() * entry.getValue();
                }
            }
            orderService.createOrder(currentUser, paymentMethod, shippingMethod, cartProducts);
        }

        req.getSession().setAttribute("cartProducts", new HashMap<Product, Integer>());
        req.getSession().setAttribute("currentUser", userService.getUserByeMail(currentUser.getEmail()));

        return "redirect:previous-orders";
    }

}
