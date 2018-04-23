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
                    <h2>Change Password</h2>
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
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
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

                            <h3>Update Password</h3>

                            <p id="old_password_field" class="form-row form-row-first validate-required">
                                <label class="" for="old_password">Previous password <abbr title="required"
                                                                                           class="required">*</abbr></label>
                                <input type="password" value="" placeholder=""
                                       id="old_password"
                                       name="old_password" class="input-text">
                            </p>
                            <div class="clear"></div>

                            <p id="new_password_field" class="form-row form-row-first validate-required">
                                <label class="" for="new_password">New password <abbr title="required"
                                                                                      class="required">*</abbr></label>
                                <input type="password" value="" placeholder=""
                                       id="new_password"
                                       name="new_password" class="input-text">
                            </p>
                            <div class="clear"></div>

                            <p id="confirm_password_field" class="form-row form-row-first validate-required">
                                <label class="" for="confirm_password">Confirm password <abbr title="required"
                                                                                              class="required">*</abbr></label>
                                <input type="password" value="" placeholder=""
                                       id="confirm_password"
                                       name="confirm_password" class="input-text">
                            </p>
                            <div class="clear"></div>

                            <div class="form-row place-order">
                                <input type="submit" data-value="Update" value="Update"
                                       id="change_password" name="change"
                                       class="button alt">
                            </div>

                            <div class="form-row place-order">
                                <input type="button" class="btn btn-success btn-midnight" onclick="generatePassword()" data-value="Generate Password" value="Generate Password"
                                       id="generate_password" name="generate"
                                       >
                            </div>

                            <c:if test="${not empty message}">
                                <h3>${message}</h3>
                            </c:if>


                        </div>
                    </form>
                    <script>
                        function generatePassword() {
                            var length = 8,
                                charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
                                retVal = "";
                            for (var i = 0, n = charset.length; i < length; ++i) {
                                retVal += charset.charAt(Math.floor(Math.random() * n));
                            }
                            document.getElementById('new_password').value = retVal;
                            document.getElementById('confirm_password').value = retVal;
                        }
                    </script>
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



