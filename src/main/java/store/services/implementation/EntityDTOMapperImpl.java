package store.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.dao.interfaces.*;
import store.dto.*;
import store.entities.*;
import store.services.interfaces.EntityDTOMapper;

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
    public void mapProductCategoryFromDTO(ProductCategory productCategory, ProductCategoryDTO productCategoryDTO) {
        productCategory.setName(productCategoryDTO.getName());
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
    public void mapProductVendorFromDTO(ProductVendor productVendor ,ProductVendorDTO productVendorDTO) {
        productVendor.setName(productVendorDTO.getName());
    }

    @Override
    @Transactional
    public ProductDTO mapDTOFromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(product.getProductId());
        productDTO.setName(product.getName());
        productDTO.setStockQuantity(product.getStockQuantity());
        productDTO.setDescription(product.getDescription());
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
    public void mapProductFromDTO(Product product, ProductDTO productDTO) {
        product.setName(productDTO.getName());
        if (productDTO.getPower() != null)
            product.setPower(productDTO.getPower());
        product.setColor(productDTO.getColor());
        if (productDTO.getVolume() != null)
            product.setVolume(productDTO.getVolume());
        if (productDTO.getWeight() != null)
            product.setWeight(productDTO.getWeight());
        product.setImagePath(productDTO.getImagePath());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
    }

    @Override
    @Transactional
    public void mapOrderStatusFromDTO(OrderStatus orderStatus, OrderStatusDTO orderStatusDTO) {
        orderStatus.setOrderStatusId(orderStatusDTO.getOrderStatusId());
        orderStatus.setStatus(orderStatusDTO.getStatus());
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
    public void mapShippingMethodFromDTO(ShippingMethod shippingMethod, ShippingMethodDTO shippingMethodDTO){
        shippingMethod.setShippingMethodId(shippingMethodDTO.getShippingMethodId());
        shippingMethod.setStatus(shippingMethodDTO.getStatus());
    }

    @Override
    @Transactional
    public PaymentMethodDTO mapDTOFromPaymentMethod(PaymentMethod paymentMethod){
        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();
        paymentMethodDTO.setPaymentMethodId(paymentMethod.getPaymentMethodId());
        paymentMethodDTO.setStatus(paymentMethod.getStatus());
        return paymentMethodDTO;
    }

    @Override
    @Transactional
    public void mapPaymentMethodFromDTO(PaymentMethodDTO paymentMethodDTO){
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId(paymentMethodDTO.getPaymentMethodId());
        paymentMethod.setStatus(paymentMethodDTO.getStatus());
    }

    @Override
    @Transactional
    public UserAdressDTO mapDTOFromUserAdress(UserAdress userAdress) {
        if (userAdress == null) return null;
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
    public OrderDTO mapDTOFromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
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
    public void mapOrderFromDTO(Order order, OrderDTO orderDTO){
        if (orderDTO.getDateTime() != null) order.setDateTime(orderDTO.getDateTime());
    }

    @Override
    @Transactional
    public Map<ProductDTO, Integer> transformListToMap(List<Product> orders) {
        Map<ProductDTO, Integer> productMap = new HashMap<>();

        for (Product product: orders) {
            ProductDTO productDTO = mapDTOFromProduct(product);
            if (productMap.containsKey(productDTO)){
                productMap.put(productDTO, productMap.get(productDTO) + 1);
            } else {
                productMap.put(productDTO, 1);
            }
        }
        return productMap;
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
        userDTO.setAccessLevel(String.valueOf(user.getAccessLevel().getAccessLevelId()));
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : user.getOrders()){
            orderDTOList.add(mapDTOFromOrder(order));
        }
        return userDTO;
    }

    @Override
    @Transactional
    public void mapUserAdressFromDTO(UserAdress userAdress ,UserAdressDTO userAdressDTO){
        if (userAdressDTO.getCountry() != null) userAdress.setCountry(userAdressDTO.getCountry());
        if (userAdressDTO.getCity() != null) userAdress.setCity(userAdressDTO.getCity());
        if (userAdressDTO.getStreet() != null) userAdress.setStreet(userAdressDTO.getStreet());
        if (userAdressDTO.getHome() != null) userAdress.setHome(userAdressDTO.getHome());
        if (userAdressDTO.getRoom() != null) userAdress.setRoom(userAdressDTO.getRoom());
        if (userAdressDTO.getZipCode() != null) userAdress.setZipCode(userAdressDTO.getZipCode());
    }

    @Override
    @Transactional
    public void mapUserFromDTO(User user, UserDTO userDTO){
        if (userDTO.getFirstName() != null) user.setFirstName(userDTO.getFirstName());
        if (userDTO.getSecondName() != null) user.setSecondName(userDTO.getSecondName());
        if (userDTO.getPhoneNumber() != null) user.setPhoneNumber(userDTO.getPhoneNumber());
        if (userDTO.getEmail() != null) user.setEmail(userDTO.getEmail());
        if (userDTO.getBirthdayData() != null) user.setBirthdayData(userDTO.getBirthdayData());
    }
}
