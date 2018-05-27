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
    ProductCategoryDTO mapDTOFromProductCategory(ProductCategory productCategory);

    void mapProductCategoryFromDTO(ProductCategory productCategory, ProductCategoryDTO productCategoryDTO);

    public ProductVendorDTO mapDTOFromProductVendor(ProductVendor productVendor);

    public void mapProductVendorFromDTO(ProductVendor productVendor ,ProductVendorDTO productVendorDTO);

    public ProductDTO mapDTOFromProduct(Product product);

    public void mapProductFromDTO(Product product, ProductDTO productDTO);

    public void mapOrderStatusFromDTO(OrderStatus orderStatus, OrderStatusDTO orderStatusDTO);

    public OrderStatusDTO mapDTOFromOrderStatus(OrderStatus orderStatus);

    public ShippingMethodDTO mapDTOFromShippingMethod(ShippingMethod shippingMethod);

    public void mapShippingMethodFromDTO(ShippingMethod shippingMethod, ShippingMethodDTO shippingMethodDTO);

    public PaymentMethodDTO mapDTOFromPaymentMethod(PaymentMethod paymentMethod);

    public void mapPaymentMethodFromDTO(PaymentMethodDTO paymentMethodDTO);

    public UserAdressDTO mapDTOFromUserAdress(UserAdress userAdress);

    public OrderDTO mapDTOFromOrder(Order order);

    public void mapOrderFromDTO(Order order, OrderDTO orderDTO);

    public Map<ProductDTO, Integer> transformListToMap(List<Product> orders);

    public UserDTO mapDTOFromUser(User user);

    public void mapUserAdressFromDTO(UserAdress userAdress ,UserAdressDTO userAdressDTO);

    public void mapUserFromDTO(User user, UserDTO userDTO);

}
