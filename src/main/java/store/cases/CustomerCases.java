package store.cases;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.dto.OrderDTO;
import store.dto.ProductDTO;
import store.dto.UserDTO;
import store.exceptions.DuplicateUserException;
import store.services.interfaces.OrderService;
import store.services.interfaces.ProductService;
import store.services.interfaces.UserAdressService;
import store.services.interfaces.UserService;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
@Slf4j
@Service("Customer Service")
public class CustomerCases {

//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserAdressService userAdressService;
//
//    public List<OrderDTO> getAllOrdersByUser(int id){
//        return orderService.getAllOrdersByUser(id);
//    }
//
//    public void createOrder(UserDTO userDTO, String paymentMethod, String shippingMethod, Map<ProductDTO, Integer> products){
//        orderService.createOrder(userDTO, paymentMethod, shippingMethod, products);
//    }
//
//    public void registrationNewUser(String email, String phone,String password){
//        try{
//            userService.createUser(email, phone, password);
//        } catch (DuplicateUserException e){
//            log.error("error", e.getMessage());
//        }
//    }
//
//    public Map<ProductDTO, Integer> repeateOrder(String orderId){
//        return orderService.getEntityById(Integer.parseInt(orderId)).getProducts();
//    }

}
