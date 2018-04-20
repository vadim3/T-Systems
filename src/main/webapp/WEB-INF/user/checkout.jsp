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

<jsp:include page="header.jsp"/>

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
                    <h2>Shopping Cart</h2>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="single-sidebar">
                    <h2 class="sidebar-title">Search Products</h2>
                    <form action="">
                        <input type="text" placeholder="Search products...">
                        <input type="submit" value="Search">
                    </form>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Products</h2>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Recent Posts</h2>
                    <ul>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-md-1"></div>
            <div class="col-md-7">

                <div class="woocommerce">
                    <form action="#" class="checkout" method="post" name="checkout">

                        <div id="customer_details" class="col2-set">

                            <h3>Billing Details</h3>

                            <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                <label class="" for="billing_first_name">First Name <abbr title="required"
                                                                                          class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.firstName}" placeholder=""
                                       id="billing_first_name"
                                       name="billing_first_name" class="input-text ">
                            </p>

                            <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                <label class="" for="billing_last_name">Last Name <abbr title="required"
                                                                                        class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.secondName}" placeholder=""
                                       id="billing_last_name"
                                       name="billing_last_name" class="input-text ">
                            </p>
                            <div class="clear"></div>

                            <p id="billing_address_1_field"
                               class="form-row form-row-wide address-field validate-required">
                                <label class="" for="billing_address_1">Address <abbr title="required"
                                                                                      class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.userAdress.street}" placeholder="Street address"
                                       id="billing_address_1"
                                       name="billing_address_1" class="input-text ">
                            </p>

                            <p id="billing_address_2_field" class="form-row form-row-wide address-field">
                                <input type="text" value="${currentUser.userAdress.room}"
                                       placeholder="Apartment, suite, unit etc. (optional)"
                                       id="billing_address_2" name="billing_address_2" class="input-text ">
                            </p>

                            <p id="billing_city_field"
                               class="form-row form-row-wide address-field validate-required"
                               data-o_class="form-row form-row-wide address-field validate-required">
                                <label class="" for="billing_city">Town / City <abbr title="required"
                                                                                     class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.userAdress.city}" placeholder="Town / City"
                                       id="billing_city"
                                       name="billing_city" class="input-text ">
                            </p>

                            <p id="billing_state_field" class="form-row form-row-first address-field validate-state"
                               data-o_class="form-row form-row-first address-field validate-state">
                                <label class="" for="billing_state">County</label>
                                <input type="text" id="billing_state" name="billing_state"
                                       placeholder="State / County" value="${currentUser.userAdress.country}"
                                       class="input-text ">
                            </p>
                            <p id="billing_postcode_field"
                               class="form-row form-row-last address-field validate-required validate-postcode"
                               data-o_class="form-row form-row-last address-field validate-required validate-postcode">
                                <label class="" for="billing_postcode">Postcode <abbr title="required"
                                                                                      class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.userAdress.zipCode}"
                                       placeholder="Postcode / Zip" id="billing_postcode"
                                       name="billing_postcode" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="billing_email_field"
                               class="form-row form-row-first validate-required validate-email">
                                <label class="" for="billing_email">Email Address <abbr title="required"
                                                                                        class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.email}" placeholder="" id="billing_email"
                                       name="billing_email"
                                       class="input-text ">
                            </p>

                            <p id="billing_phone_field"
                               class="form-row form-row-last validate-required validate-phone">
                                <label class="" for="billing_phone">Phone <abbr title="required"
                                                                                class="required">*</abbr>
                                </label>
                                <input type="text" value="${currentUser.phoneNumber}" placeholder="" id="billing_phone"
                                       name="billing_phone"
                                       class="input-text ">
                            </p>
                            <div class="clear"></div>


                            <div class="create-account">
                                <p>Create an account by entering the information below. If you are a returning
                                    customer please login at the top of the page.</p>
                                <p id="account_password_field" class="form-row validate-required">
                                    <label class="" for="account_password">Account password <abbr title="required"
                                                                                                  class="required">*</abbr>
                                    </label>
                                    <input type="password" value="" placeholder="Password" id="account_password"
                                           name="account_password" class="input-text">
                                </p>
                                <div class="clear"></div>
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
                                    <c:forEach var="type" items="${cartproducts}">
                                        <tr class="cart_item">
                                            <td class="product-name">
                                                    ${type.key.name} <strong class="product-quantity">x ${type.value}</strong></td>
                                            <td class="product-total">
                                                <span class="amount">$${type.key.price * type.value}</span></td>
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
                                                   id="shipping_method_0" data-index="0" name="shipping_method[0]">
                                        </td>
                                    </tr>


                                    <tr class="order-total">
                                        <th>Order Total</th>
                                        <td><strong><span class="amount">$${sum}</span></strong></td>
                                    </tr>

                                    </tfoot>
                                </table>


                                <div id="payment">
                                    <ul class="payment_methods methods">
                                        <li class="payment_method_bacs">
                                            <input type="radio" data-order_button_text="" checked="checked" value="bacs"
                                                   name="payment_method" class="input-radio" id="payment_method_bacs">
                                            <label for="payment_method_bacs">Direct Bank Transfer </label>
                                            <div class="payment_box payment_method_bacs">
                                                <p>Make your payment directly into our bank account. Please use your
                                                    Order ID as the payment reference. Your order won’t be shipped until
                                                    the funds have cleared in our account.</p>
                                            </div>
                                        </li>
                                        <li class="payment_method_cheque">
                                            <input type="radio" data-order_button_text="" value="cheque"
                                                   name="payment_method" class="input-radio" id="payment_method_cheque">
                                            <label for="payment_method_cheque">Cheque Payment </label>
                                            <div style="display:none;" class="payment_box payment_method_cheque">
                                                <p>Please send your cheque to Store Name, Store Street, Store Town,
                                                    Store State / County, Store Postcode.</p>
                                            </div>
                                        </li>
                                        <li class="payment_method_paypal">
                                            <input type="radio" data-order_button_text="Proceed to PayPal"
                                                   value="paypal" name="payment_method" class="input-radio"
                                                   id="payment_method_paypal">
                                            <label for="payment_method_paypal">PayPal <img alt="PayPal Acceptance Mark"
                                                                                           src="https://www.paypalobjects.com/webstatic/mktg/Logo/AM_mc_vs_ms_ae_UK.png"><a
                                                    title="What is PayPal?"
                                                    onclick="javascript:window.open('https://www.paypal.com/gb/webapps/mpp/paypal-popup','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"
                                                    class="about_paypal"
                                                    href="https://www.paypal.com/gb/webapps/mpp/paypal-popup">What is
                                                PayPal?</a>
                                            </label>
                                            <div style="display:none;" class="payment_box payment_method_paypal">
                                                <p>Pay via PayPal; you can pay with your credit card if you don’t have a
                                                    PayPal account.</p>
                                            </div>
                                        </li>
                                    </ul>

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

<jsp:include page="footer.jsp"/>
</html>

