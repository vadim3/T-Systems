package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.*;
import store.dto.*;
import store.entities.*;
import store.services.interfaces.EntityDTOMapper;
import store.services.interfaces.OrderService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/

/**
 * The Mapper Object is responsible ONLY for mapping DTO to Entity and Reverse mapping
 */
@Service("EntityDTOMapper")
public class EntityDTOMapperImpl implements EntityDTOMapper {

    @Autowired
    UserDAO userDAO;

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderService orderService;

    @Override
    @Transactional
    public ProductCategoryDTO mapDTOFromProductCategory(ProductCategory productCategory) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setName(productCategory.getName());
        productCategoryDTO.setProductCategoryId(productCategory.getProductCategoryId());
        return productCategoryDTO;
    }

    @Override
    @Transactional
    public ProductCategory mapProductCategoryFromDTO(ProductCategoryDTO productCategoryDTO) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(productCategoryDTO.getProductCategoryId());
        productCategory.setName(productCategoryDTO.getName());
        return productCategory;
    }

    @Override
    @Transactional
    public ProductVendorDTO mapDTOFromProductVendor(ProductVendor productVendor) {
        ProductVendorDTO productVendorDTO = new ProductVendorDTO();
        productVendorDTO.setName(productVendor.getName());
        productVendorDTO.setProductVendorId(productVendor.getProductVendorId());
        return productVendorDTO;
    }
    @Override
    @Transactional
    public ProductVendor mapProductVendorFromDTO(ProductVendorDTO productVendorDTO) {
        ProductVendor productVendor = new ProductVendor();
        productVendor.setProductVendorId(productVendorDTO.getProductVendorId());
        productVendor.setName(productVendorDTO.getName());
        return productVendor;
    }

    @Override
    @Transactional
    public ProductDTO mapDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setColor(product.getColor());
        productDTO.setVolume(product.getVolume());
        productDTO.setPower(product.getPower());
        productDTO.setImagePath(product.getImagePath());
        productDTO.setProductCategoryDTO(mapDTOFromProductCategory(product.getProductCategory()));
        productDTO.setProductVendorDTO(mapDTOFromProductVendor(product.getProductVendor()));
        return productDTO;
    }

    @Override
    @Transactional
    public Product mapProductFromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPower(productDTO.getPower());
        product.setColor(productDTO.getColor());
        product.setVolume(productDTO.getVolume());
        product.setWeight(productDTO.getWeight());
        product.setImagePath(product.getImagePath());
        product.setDescription(product.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setProductCategory(mapProductCategoryFromDTO(productDTO.getProductCategoryDTO()));
        product.setProductVendor(mapProductVendorFromDTO(productDTO.getProductVendorDTO()));
        return product;
    }

    @Override
    @Transactional
    public OrderStatus mapOrderStatusFromDTO(OrderStatusDTO orderStatusDTO) {
        OrderStatus orderStatus = orderService.getOrderStatusByStatus(orderStatusDTO.getStatus());
        orderStatus.setOrderStatusId(orderStatusDTO.getOrderStatusId());
        orderStatus.setStatus(orderStatusDTO.getStatus());
        return orderStatus;
    }

    @Override
    @Transactional
    public OrderStatusDTO mapDTOFromOrderStatus(OrderStatus orderStatus) {
        OrderStatusDTO orderStatusDTO = new OrderStatusDTO();
        orderStatusDTO.setOrderStatusId(orderStatus.getOrderStatusId());
        orderStatusDTO.setStatus(orderStatus.getStatus());
        return orderStatusDTO;
    }

    @Override
    @Transactional
    public ShippingMethodDTO mapDTOFromShippingMethod(ShippingMethod shippingMethod){
        ShippingMethodDTO shippingMethodDTO = new ShippingMethodDTO();
        shippingMethodDTO.setShippingMethodId(shippingMethod.getShippingMethodId());
        shippingMethodDTO.setStatus(shippingMethod.getStatus());
        return shippingMethodDTO;
    }

    @Override
    @Transactional
    public ShippingMethod mapShippingMethodFromDTO(ShippingMethodDTO shippingMethodDTO){
        ShippingMethod shippingMethod = orderService.getShippingMethodByStatus(shippingMethodDTO.getStatus());
        shippingMethod.setShippingMethodId(shippingMethodDTO.getShippingMethodId());
        shippingMethod.setStatus(shippingMethodDTO.getStatus());
        return shippingMethod;
    }

    @Override
    @Transactional
    public PaymentMethodDTO mapDTOFromPaymentMethod(PaymentMethod paymentMethod){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethod.setPaymentMethodId(paymentMethodDTO.getPaymentMethodId());
        paymentMethod.setStatus(paymentMethodDTO.getStatus());
        return paymentMethodDTO;
    }

    @Override
    @Transactional
    public PaymentMethod mapPaymentMethodFromDTO(PaymentMethodDTO paymentMethodDTO){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethodDTO.setPaymentMethodId(paymentMethod.getPaymentMethodId());
        paymentMethodDTO.setStatus(paymentMethod.getStatus());
        return paymentMethod;
    }

    @Override
    @Transactional
    public UserAdressDTO mapDTOFromUserAdress(UserAdress userAdress) {
        UserAdressDTO userAdressDTO = new UserAdressDTO();
        userAdressDTO.setAdressId(userAdress.getAdressId());
        userAdressDTO.setCountry(userAdress.getCountry());
        userAdressDTO.setCity(userAdress.getCity());
        userAdressDTO.setZipCode(userAdress.getZipCode());
        userAdressDTO.setStreet(userAdress.getStreet());
        userAdressDTO.setHome(userAdress.getHome());
        userAdressDTO.setRoom(userAdress.getRoom());
        return userAdressDTO;
    }

    @Override
    @Transactional
    public UserAdress mapUserAdressFromDTO(UserAdressDTO userAdressDTO) {

        UserAdress userAdress = (userAdressDTO.getAdressId() == 0) new UserAdress(): ;
        userAdress.setAdressId(userAdressDTO.getAdressId());
        userAdress.setCountry(userAdressDTO.getCountry());
        userAdress.setCity(userAdressDTO.getCity());
        userAdress.setZipCode(userAdressDTO.getZipCode());
        userAdress.setStreet(userAdressDTO.getStreet());
        userAdress.setHome(userAdressDTO.getHome());
        userAdress.setRoom(userAdressDTO.getRoom());
        return userAdress;
    }

    @Override
    @Transactional
    public OrderDTO mapDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        UserDTO userDTO = new UserDTO();
        UserAdressDTO userAdressDTO = mapDTOFromUserAdress(order.getUser().getUserAdress());
        userDTO.setUserAdressDTO(userAdressDTO);
        orderDTO.setUser(userDTO);
        orderDTO.setDateTime(order.getDateTime());
        OrderStatusDTO orderStatusDTO = mapDTOFromOrderStatus(order.getOrderStatus());
        orderDTO.setOrderStatus(orderStatusDTO);

        ShippingMethodDTO shippingMethodDTO = mapDTOFromShippingMethod(order.getShippingMethod());
        orderDTO.setShippingMethod(shippingMethodDTO);

        PaymentMethodDTO paymentMethodDTO = mapDTOFromPaymentMethod(order.getPaymentMethod());
        orderDTO.setPaymentMethod(paymentMethodDTO);
        orderDTO.setProducts(transformListToMap(order.getProducts()));
        return orderDTO;
    }

    @Override
    @Transactional
    public Order mapOrderFromDTO(OrderDTO orderDTO){
        Order order = (orderDTO.getOrderId() == 0) ? new Order() : orderDAO.read(orderDTO.getOrderId());

        if (orderDTO.getUser() != null && orderDTO.getUser().getUserId() != null)
            order.setUser(userDAO.read(Integer.valueOf(orderDTO.getUser().getUserId())));

        if (orderDTO.getShippingMethod() != null)
            order.setShippingMethod(orderService.getShippingMethodByStatus(orderDTO.getShippingMethod().getStatus()));

        if (orderDTO.getPaymentMethod() != null)
            order.setPaymentMethod(orderService.getPaymentMethodByStatus(orderDTO.getPaymentMethod().getStatus()));

        if (orderDTO.getOrderStatus() != null)
            order.setOrderStatus(orderService.getOrderStatusByStatus(orderDTO.getOrderStatus().getStatus()));

        if (orderDTO.getDateTime() != null) order.setDateTime(orderDTO.getDateTime());

        return order;
    }

    @Override
    @Transactional
    public Map<ProductDTO, Integer> transformListToMap(List<Product> orders) {
        Map<ProductDTO, Integer> productMap = new HashMap<>();

        for (Product product: orders) {
            ProductDTO productDTO = mapDTOFromProduct(product);
            if (productMap.containsKey(productDTO)){
                productMap.put(productDTO, productMap.get(product) + 1);
            } else {
                productMap.put(productDTO, 1);
            }
        }
        return productMap;
    }


    @Override
    @Transactional
    public List<Product> transformMapToList(Map<ProductDTO, Integer> orders) {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<ProductDTO, Integer> entry : orders.entrySet())
        {
            for (int i = 0; i < entry.getValue(); i++){
                Product product = mapProductFromDTO(entry.getKey());
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    @Transactional
    public UserDTO mapDTOFromUser(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserId(String.valueOf(user.getUserId()));
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setSecondName(user.getSecondName());
        userDTO.setBirthdayData(user.getBirthdayData());
        userDTO.setPhoneNumber(user.getPhoneNumber());

        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : user.getOrders()){
            orderDTOList.add(mapDTOFromOrder(order));
        }

        userDTO.setOrdersDTO(orderDTOList);

        UserAdressDTO userAdressDTO = mapDTOFromUserAdress(user.getUserAdress());
        userDTO.setUserAdressDTO(userAdressDTO);

        return userDTO;
    }

    @Override
    @Transactional
    public User mapUserFromDTO(UserDTO userDTO){
        User user;
        if (userDTO.getUserId() == null){
            user = new User();
        } else {
            user = userDAO.read(Integer.valueOf(userDTO.getUserId()));
        }
        if (userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if (userDTO.getSecondName() != null) user.setSecondName(userDTO.getSecondName());
        if (userDTO.getPhoneNumber() != null) user.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getBirthdayData() != null) user.setBirthdayData(userDTO.getBirthdayData());
        if (userDTO.getUserAdressDTO() != null) {
            UserAdress userAdress = mapUserAdressFromDTO(userDTO.getUserAdressDTO());
            user.setUserAdress(userAdress);
        }
        return user;
    }
}
