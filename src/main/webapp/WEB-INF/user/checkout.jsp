<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PowerTrade Store</title>

    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet'
          type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="../assets/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="../assets/css/owl.carousel.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/responsive.css">

</head>

<%@ include file="header.jsp" %>

<div class="promo-area">
    <div class="container">
        <div class="row"></div>
    </div>
</div>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Checkout</h2>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <%@ include file="leftside.jsp" %>
            <div class="col-md-1"></div>
            <div class="col-md-7">

                <div class="woocommerce">

                    <c:choose>
                        <c:when test="${isempty}">
                            <h2>Your cart is empty, nothing to checkout</h2>
                        </c:when>

                        <c:otherwise>

                            <form action="#" class="checkout" method="post" name="checkout">

                                <div id="customer_details" class="col2-set">

                                    <h3>Billing Details</h3>

                                    <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                        <label for="billing_first_name">First Name <abbr title="required"
                                                                                         class="required">*</abbr>
                                        </label>
                                        <input type="text" value="${currentUser.firstName}" placeholder=""
                                               id="billing_first_name"
                                               name="first_name" class="input-text ">
                                    </p>

                                    <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                        <label for="billing_last_name">Last Name <abbr title="required"
                                                                                       class="required">*</abbr>
                                        </label>
                                        <input type="text" value="${currentUser.secondName}" placeholder=""
                                               id="billing_last_name"
                                               name="second_name" class="input-text ">
                                    </p>
                                    <div class="clear"></div>

                                    <p id="billing_email_field"
                                       class="form-row form-row-first validate-required validate-email">
                                        <label for="billing_email">Email Address <abbr title="required"
                                                                                       class="required">*</abbr>
                                        </label>
                                        <input type="text" value="${currentUser.email}" placeholder=""
                                               id="billing_email"
                                               name="email"
                                               class="input-text ">
                                    </p>

                                    <p id="billing_phone_field"
                                       class="form-row form-row-last validate-required validate-phone">
                                        <label for="billing_phone">Phone <abbr title="required"
                                                                               class="required">*</abbr>
                                        </label>
                                        <input type="text" value="${currentUser.phoneNumber}" placeholder=""
                                               id="billing_phone"
                                               name="phone_number"
                                               class="input-text ">
                                    </p>

                                    <label for="shipping_method">Shipping Method <abbr title="required"
                                                                                       class="required">*</abbr>
                                    </label>
                                    <select id="shipping_method" name="shipping_method" onchange="function choiceShipping() {
                                if (document.getElementById('shipping_method').selectedIndex == '0'){
                                    document.getElementById('shipping_address').style.display = 'none';
                                } else {
                                    document.getElementById('shipping_address').style.display = 'block';
                                }
                            } choiceShipping()">
                                        <c:forEach var="prvendor" items="${shippingMethods}">
                                            <option value="${prvendor.status}">
                                                    ${prvendor.status}
                                            </option>
                                        </c:forEach>
                                    </select>

                                    <div id="shipping_address" style="display: none">
                                        <h3>Address Details</h3>
                                        <p id="country_field"
                                           class="form-row form-row-first address-field validate-state"
                                           data-o_class="form-row form-row-first address-field validate-state">
                                            <label class="" for="country">County</label>
                                            <input type="text" id="country" name="country"
                                                   placeholder="State / County"
                                                   value="${currentUser.userAdress.country}"
                                                   class="input-text ">
                                        </p>

                                        <p id="city_field"
                                           class="form-row form-row-wide address-field validate-required"
                                           data-o_class="form-row form-row-wide address-field validate-required">
                                            <label class="" for="city">Town / City <abbr title="required"
                                                                                         class="required">*</abbr>
                                            </label>
                                            <input type="text" value="${currentUser.userAdress.city}"
                                                   placeholder="Town / City"
                                                   id="city"
                                                   name="city" class="input-text ">
                                        </p>

                                        <p id="street_field"
                                           class="form-row form-row-wide address-field validate-required">
                                            <label class="" for="billing_address_1">Adress <abbr title="required"
                                                                                                 class="required">*</abbr>
                                            </label>
                                            <input type="text" value="${currentUser.userAdress.street}"
                                                   placeholder="Street address"
                                                   id="billing_address_1"
                                                   name="street" class="input-text ">
                                        </p>

                                        <p id="home_field" class="form-row form-row-wide address-field">
                                            <input type="text" value="${currentUser.userAdress.home}"
                                                   placeholder="Home, Building"
                                                   id="home" name="home" class="input-text ">
                                        </p>

                                        <p id="room_field" class="form-row form-row-wide address-field">
                                            <input type="text" value="${currentUser.userAdress.room}"
                                                   placeholder="Apartment, suite, unit etc."
                                                   id="room" name="room" class="input-text ">
                                        </p>

                                        <p id="zipcode_field"
                                           class="form-row form-row-last address-field validate-required validate-postcode"
                                           data-o_class="form-row form-row-last address-field validate-required validate-postcode">
                                            <label class="" for="zipcode">Postcode <abbr title="required"
                                                                                         class="required">*</abbr>
                                            </label>
                                            <input type="text" value="${currentUser.userAdress.zipCode}"
                                                   placeholder="Postcode / Zip" id="zipcode"
                                                   name="zip_code" class="input-text ">
                                        </p>
                                    </div>

                                    <div id="order_review" style="position: relative;">

                                        <table class="shop_table">
                                            <thead>
                                            <tr>
                                                <th class="product-name">Product</th>
                                                <th class="product-total">Total</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="customentry" items="${cartproducts}">
                                                <tr class="cart_item">
                                                    <td class="product-name">
                                                            ${customentry.key.name} <strong
                                                            class="product-quantity">x ${customentry.value}</strong></td>
                                                    <td class="product-total">
                                                        <span class="amount">$${customentry.key.price * customentry.value}</span></td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                            <tfoot>

                                            <tr class="cart-subtotal">
                                                <th>Cart Subtotal</th>
                                                <td><span class="amount">$${sum}</span>
                                                </td>
                                            </tr>

                                            <tr class="shipping">
                                                <th>Shipping and Handling</th>
                                                <td>

                                                    Free Shipping
                                                    <input type="hidden" class="shipping_method" value="free_shipping"
                                                           id="shipping_method_0" data-index="0"
                                                           name="shipping_method[0]">
                                                </td>
                                            </tr>


                                            <tr class="order-total">
                                                <th>Order Total</th>
                                                <td><strong><span class="amount">$${sum}</span></strong></td>
                                            </tr>

                                            </tfoot>
                                        </table>


                                        <div id="payment">


                                            <label class="" for="payment_method">Payment Method <abbr title="required"
                                                                                                      class="required">*</abbr>
                                            </label>
                                            <select id="payment_method" name="payment_method">
                                                <c:forEach var="pmethod" items="${paymentMethods}">
                                                    <option value="${pmethod.status}">
                                                            ${pmethod.status}
                                                    </option>
                                                </c:forEach>
                                            </select>


                                            <div class="form-row place-order">

                                                <input type="submit" data-value="Place order" value="Place order"
                                                       id="place_order" name="woocommerce_checkout_place_order"
                                                       class="button alt">
                                            </div>
                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>
        </div>
    </div>
</div>

<div class="promo-area">
    <div class="container">
        <div class="row"></div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</html>

