package store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import store.entities.Product;
import store.entities.User;
import store.services.interfaces.ProductService;

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
}
