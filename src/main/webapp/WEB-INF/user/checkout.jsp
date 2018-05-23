<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<div class="mainmenu-area">
    <div class="container">
        <div class="row">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/">Home</a></li>
                    <li><a href="/user/previous-orders">Order History</a></li>
                    <li><a href="/user/personal-details">Personal Details</a></li>
                    <li><a href="/user/shipping-address">Shipping Adress</a></li>
                    <li class="active"><a href="/user/checkout">Checkout</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->

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

                                    <spring:bind path="currentUser.userId">
                                        <input type="hidden" name="${status.expression}" value="${status.value}">
                                    </spring:bind>

                                    <spring:bind path="currentUser.birthdayData">
                                        <input type="hidden" name="${status.expression}" value="${status.value}">
                                    </spring:bind>

                                    <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                        <label for="billing_first_name">First Name <abbr title="required"
                                                                                         class="required">*</abbr>
                                        </label>
                                        <spring:bind path="currentUser.firstName">
                                            <input type="text" value="${status.value}" placeholder="required"
                                                   id="billing_first_name" name="${status.expression}" class="input-text">
                                            <form:errors path="currentUser.firstName" cssStyle="color: red"/>
                                        </spring:bind>
                                    </p>

                                    <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                        <label for="billing_last_name">Last Name <abbr title="required"
                                                                                       class="required">*</abbr>
                                        </label>

                                        <spring:bind path="currentUser.secondName">
                                            <input type="text" value="${status.value}" placeholder="required"
                                                   id="billing_last_name" name="${status.expression}" class="input-text">
                                            <form:errors path="currentUser.secondName" cssStyle="color: red"/>
                                        </spring:bind>
                                    </p>
                                    <div class="clear"></div>

                                    <p id="billing_email_field"
                                       class="form-row form-row-first validate-required validate-email">
                                        <label for="billing_email">Email Address <abbr title="required"
                                                                                       class="required">*</abbr>
                                        </label>
                                        <spring:bind path="currentUser.email">
                                            <input type="text" value="${status.value}" placeholder="required"
                                                   id="billing_email" name="${status.expression}" class="input-text">
                                            <form:errors path="currentUser.email" cssStyle="color: red"/>
                                        </spring:bind>
                                    </p>

                                    <p id="billing_phone_field"
                                       class="form-row form-row-last validate-required validate-phone">
                                        <label for="billing_phone">Phone <abbr title="required"
                                                                               class="required">*</abbr>
                                        </label>
                                        <spring:bind path="currentUser.phoneNumber">
                                            <input type="text" value="${status.value}" placeholder="required"
                                                   id="billing_phone" name="${status.expression}" class="input-text">
                                            <form:errors path="currentUser.phoneNumber" cssStyle="color: red"/>
                                        </spring:bind>
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
                                        <c:forEach var="shippingstatus" items="${shippingMethods}">
                                            <option value="${shippingstatus.status}">
                                                    ${shippingstatus.status}
                                            </option>
                                        </c:forEach>
                                    </select>

                                    <div id="shipping_address" style="display: none">
                                        <h3>Address Details</h3>
                                        <p id="country_field"
                                           class="form-row form-row-first address-field validate-state"
                                           data-o_class="form-row form-row-first address-field validate-state">

                                            <spring:bind path="userAdress.adressId">
                                                <input type="hidden" name="${status.expression}" value="${status.value}">
                                            </spring:bind>

                                            <label class="" for="country">Country</label>

                                            <spring:bind path="userAdress.country">
                                                <input type="text" value="${status.value}" placeholder="State / County"
                                                       id="country" name="${status.expression}" class="input-text">
                                                <form:errors path="userAdress.country" cssStyle="color: red"/>
                                            </spring:bind>
                                        </p>

                                        <p id="city_field"
                                           class="form-row form-row-wide address-field validate-required"
                                           data-o_class="form-row form-row-wide address-field validate-required">
                                            <label class="" for="city">Town / City <abbr title="required"
                                                                                         class="required">*</abbr>
                                            </label>

                                            <spring:bind path="userAdress.city">
                                                <input type="text" value="${status.value}" placeholder="Town / City"
                                                       id="city" name="${status.expression}" class="input-text">
                                                <form:errors path="userAdress.city" cssStyle="color: red"/>
                                            </spring:bind>
                                        </p>

                                        <p id="street_field"
                                           class="form-row form-row-wide address-field validate-required">
                                            <label class="" for="billing_address_1">Adress <abbr title="required"
                                                                                                 class="required">*</abbr>
                                            </label>
                                            <spring:bind path="userAdress.street">
                                                <input type="text" value="${status.value}" placeholder="Street address"
                                                       id="billing_address_1" name="${status.expression}" class="input-text">
                                                <form:errors path="userAdress.street" cssStyle="color: red"/>
                                            </spring:bind>
                                        </p>

                                        <p id="home_field" class="form-row form-row-wide address-field">
                                            <spring:bind path="userAdress.home">
                                                <input type="text" value="${status.value}" placeholder="Home, Building"
                                                       id="home" name="${status.expression}" class="input-text">
                                                <form:errors path="userAdress.home" cssStyle="color: red"/>
                                            </spring:bind>
                                        </p>

                                        <p id="room_field" class="form-row form-row-wide address-field">
                                            <spring:bind path="userAdress.room">
                                                <input type="text" value="${status.value}" placeholder="Apartment, suite, unit etc."
                                                       id="room" name="${status.expression}" class="input-text">
                                                <form:errors path="userAdress.room" cssStyle="color: red"/>
                                            </spring:bind>
                                        </p>

                                        <p id="zipcode_field"
                                           class="form-row form-row-last address-field validate-required validate-postcode"
                                           data-o_class="form-row form-row-last address-field validate-required validate-postcode">
                                            <label class="" for="zipcode">Postcode <abbr title="required"
                                                                                         class="required">*</abbr>
                                            </label>
                                            <spring:bind path="userAdress.zipCode">
                                                <input type="text" value="${status.value}" placeholder="Postcode / Zip"
                                                       id="zipcode" name="${status.expression}" class="input-text">
                                                <form:errors path="userAdress.zipCode" cssStyle="color: red"/>
                                            </spring:bind>
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
</body>
</html>

