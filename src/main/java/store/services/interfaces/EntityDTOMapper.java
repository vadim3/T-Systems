package store.services.interfaces;

import store.dto.*;
import store.entities.*;

import java.util.List;
import java.util.Map;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public interface EntityDTOMapper {
    public ProductCategoryDTO mapDTOFromProductCategory(ProductCategory productCategory);

    public void mapProductCategoryFromDTO(ProductCategory productCategory, ProductCategoryDTO productCategoryDTO);

    public ProductVendorDTO mapDTOFromProductVendor(ProductVendor productVendor);

    public void mapProductVendorFromDTO(ProductVendor productVendor ,ProductVendorDTO productVendorDTO);

    public ProductDTO mapDTOFromProduct(Product product);

    public Product mapProductFromDTO(ProductDTO productDTO);

    public OrderStatus mapOrderStatusFromDTO(OrderStatusDTO orderStatusDTO);

    public OrderStatusDTO mapDTOFromOrderStatus(OrderStatus orderStatus);

    public ShippingMethodDTO mapDTOFromShippingMethod(ShippingMethod shippingMethod);

    public ShippingMethod mapShippingMethodFromDTO(ShippingMethodDTO shippingMethodDTO);

    public PaymentMethodDTO mapDTOFromPaymentMethod(PaymentMethod paymentMethod);

    public PaymentMethod mapPaymentMethodFromDTO(PaymentMethodDTO paymentMethodDTO);

    public UserAdressDTO mapDTOFromUserAdress(UserAdress userAdress);

    public OrderDTO mapDTOFromOrder(Order order);

    public Order mapOrderFromDTO(OrderDTO orderDTO);

    public Map<ProductDTO, Integer> transformListToMap(List<Product> orders);

    public List<Product> transformMapToList(Map<ProductDTO, Integer> orders);

    public UserDTO mapDTOFromUser(User user);

    public void mapUserAdressFromDTO(UserAdress userAdress ,UserAdressDTO userAdressDTO);

    public void mapUserFromDTO(User user, UserDTO userDTO);

}
