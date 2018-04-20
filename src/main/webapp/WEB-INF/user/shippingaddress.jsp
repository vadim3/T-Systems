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

                            <h3>Update Shipping Address</h3>

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
                            <div class="form-row place-order">
                                <input type="submit" data-value="Update" value="Update"
                                       id="place_order" name="change"
                                       class="button alt">
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
