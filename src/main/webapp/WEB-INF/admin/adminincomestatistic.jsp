<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Income Statistic</title>

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
    <link rel="stylesheet" href="../assets/css/bootstrap-datetimepicker.min.css" />

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
                    <li><a href="/admin/order-history">Orders Management</a></li>
                    <li><a href="/admin/all-products">Product Management</a></li>
                    <li class="active"><a href="/admin/income-statistic">Income Statistic</a></li>
                    <li><a href="/admin/top-customers">Top 10 Customers</a></li>
                    <li><a href="/admin/top-products">Top 10 Products</a></li>
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
                    <h2>Income Statistic</h2>
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
                    <form action="#" class="checkout" method="post" name="checkout">

                        <h3>Calculate Income</h3>
                            <label class="" for="datefrom">Date from<abbr title="required"
                                                                          class="required">*</abbr></label>
                        <div class="form-group">
                            <div class="input-group date" id="datetimepicker1">
                                <input type="text" class="form-control" id="datefrom" name="datefrom"/>
                                <span class="input-group-addon">
				                    <span class="glyphicon-calendar glyphicon"></span>
			                    </span>
                            </div>
                        </div>

                            <label class="" for="dateto">Date to<abbr title="required"
                                                                          class="required">*</abbr></label>
                            <div class="form-group">
                                <div class="input-group date" id="datetimepicker2">
                                    <input type="text" class="form-control" id="dateto" name="dateto"/>
                                    <span class="input-group-addon">
				                    <span class="glyphicon-calendar glyphicon"></span>
			                    </span>
                                </div>
                            </div>

                            <div class="form-row place-order">
                                <input type="submit" data-value="Calculate" value="Calculate" name="change" class="button alt">
                            </div>
                    </form>
                    <div style="margin: 30px">
                        <c:if test="${not empty incomevalue}">
                            <h4 style="display: inline">${datemessage}</h4>
                            <h3 style="color: green; display: inline"><u> $${incomevalue}</u><h3>
                        </c:if>
                    </div>
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

<script src="../assets/js/jquery-3.2.1.min.js"></script>
<script src="../assets/js/moment-with-locales.min.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript">
    $(function () {
        $('#datetimepicker1').datetimepicker({
            locale: 'en',
            stepping: 10,
            format: 'DD/MM/YYYY',
            defaultDate: moment('10.04.2018').format('DD.MM.YYYY')

        });
        $('#datetimepicker2').datetimepicker({
            locale: 'en',
            stepping: 10,
            format: 'DD/MM/YYYY',
            defaultDate: moment('10.04.2018').format('DD.MM.YYYY')
        });

    });
</script>

<div class="footer-top-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="footer-about-us">
                    <h2>Power<span>Trade</span></h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis sunt id doloribus vero quam laborum quas alias dolores blanditiis iusto consequatur, modi aliquid eveniet eligendi iure eaque ipsam iste, pariatur omnis sint! Suscipit, debitis, quisquam. Laborum commodi veritatis magni at?</p>
                    <div class="footer-social">
                        <a href="#" target="_blank"><i class="fa fa-facebook"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-twitter"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-youtube"></i></a>
                        <a href="#" target="_blank"><i class="fa fa-linkedin"></i></a>
                    </div>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="footer-menu">
                    <h2 class="footer-wid-title">User Navigation </h2>
                    <ul>
                        <li><a href="/cart">My cart</a></li>
                        <li><a href="/user/previous-orders">Order history</a></li>
                        <li><a href="/user/personal-details">Personal Details</a></li>
                        <li><a href="/user/shipping-address">Shipping Adress</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="footer-menu">
                    <h2 class="footer-wid-title">Categories</h2>
                    <ul>
                        <li><a href="/catalog?category=Mobile Phone">Mobile Phone</a></li>
                        <li><a href="/catalog?category=Accessories">Home accesseries</a></li>
                        <li><a href="/catalog?category=TV">LED TV</a></li>
                        <li><a href="/catalog?category=Computer">Computer</a></li>
                        <li><a href="/catalog?category=Notebook">Notebooks</a></li>
                    </ul>
                </div>
            </div>

            <div class="col-md-3 col-sm-6">
                <div class="footer-newsletter">
                    <h2 class="footer-wid-title">Newsletter</h2>
                    <p>Sign up to our newsletter and get exclusive deals you wont find anywhere else straight to your inbox!</p>
                    <div class="newsletter-form">
                        <form action="#">
                            <input type="email" placeholder="Type your email">
                            <input type="submit" value="Subscribe">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End footer top area -->

<div class="footer-bottom-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="copyright">
                    <p>&copy; 2018. All Rights Reserved. <a href="/" target="_blank">PowerTrade</a></p>
                </div>
            </div>

            <div class="col-md-4">
                <div class="footer-card-icon">
                    <i class="fa fa-cc-discover"></i>
                    <i class="fa fa-cc-mastercard"></i>
                    <i class="fa fa-cc-paypal"></i>
                    <i class="fa fa-cc-visa"></i>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End footer bottom area -->


<!-- Bootstrap JS form CDN -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<!-- jQuery sticky menu -->
<script src="../assets/js/owl.carousel.min.js"></script>
<script src="../assets/js/jquery.sticky.js"></script>

<!-- jQuery easing -->
<script src="../assets/js/jquery.easing.1.3.min.js"></script>

<!-- Main Script -->
<script src="../assets/js/main.js"></script>

<!-- Slider -->
<script type="text/javascript" src="../assets/js/bxslider.min.js"></script>
<script type="text/javascript" src="../assets/js/script.slider.js"></script>
</body>
</html>



