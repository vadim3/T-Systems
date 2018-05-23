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
                    <li class="active"><a href="/">Home</a></li>
                    <li><a href="/catalog">Shop page</a></li>
                    <li><a href="/cart">Cart</a></li>
                    <li><a href="/user/checkout">Checkout</a></li>
                    <li><a href="/contacts">Contact</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End mainmenu area -->

<div class="slider-area">
    <!-- Slider -->
    <div class="block-slider block-slider4">
        <ul class="" id="bxslider-home4">
            <li>
                <img src="../assets/img/h4-slide.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        iPhone <span class="primary">6 <strong>Plus</strong></span>
                    </h2>
                    <h4 class="caption subtitle">Dual SIM</h4>
                    <a class="caption button-radius" href="/product?id=3"><span class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="../assets/img/h4-slide2.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        All BIG TV* <span class="primary">20% <strong>off</strong></span>
                    </h2>
                    <h4 class="caption subtitle">view better*</h4>
                    <a class="caption button-radius" href="/catalog?category=TV"><span class="icon"></span>View all</a>
                </div>
            </li>
            <li><img src="../assets/img/h4-slide3.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        Apple <span class="primary">Store <strong>Apple iMac retina 27</strong></span>
                    </h2>
                    <h4 class="caption subtitle">Select Item</h4>
                    <a class="caption button-radius" href="product?id=19"><span class="icon"></span>Shop now</a>
                </div>
            </li>
            <li><img src="../assets/img/h4-slide4.png" alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        New Beats <span class="primary">by <strong>Dr. Dre</strong></span>
                    </h2>
                    <h4 class="caption subtitle">& lighting</h4>
                    <a class="caption button-radius" href="/product?id=30"><span class="icon"></span>Shop now</a>
                </div>
            </li>
        </ul>
    </div>
    <!-- ./Slider -->
</div> <!-- End slider area -->

<div class="promo-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-3 col-sm-6">
                <div class="single-promo promo1">
                    <i class="fa fa-refresh"></i>
                    <p>30 Days return</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="single-promo promo2">
                    <i class="fa fa-truck"></i>
                    <p>Free shipping</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="single-promo promo3">
                    <i class="fa fa-lock"></i>
                    <p>Secure payments</p>
                </div>
            </div>
            <div class="col-md-3 col-sm-6">
                <div class="single-promo promo4">
                    <i class="fa fa-gift"></i>
                    <p>New products</p>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End promo area -->

<div class="maincontent-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="latest-product">
                    <h2 class="section-title">The Most Popular Products</h2>
                    <div class="product-carousel">
                        <c:forEach var="product" items="${popularProducts}">
                            <div class="single-product">
                                <div class="product-f-image">
                                    <img src="${imgprefix}${product.imagePath}" alt="${product.name}">
                                    <div class="product-hover">
                                        <a href="#" onclick="function addingToCart() {
                                                $.ajax({
                                                type: 'POST',
                                                url: '/catalog',
                                                data: {'item': '${product.productId}'}});
                                                var cartAmount = parseFloat(document.getElementById('cart-amount').innerText);
                                                cartAmount += parseFloat('${product.price}');
                                                document.getElementById('cart-amount').innerText = cartAmount.toString();
                                                var productCount = parseInt(document.getElementById('product-count').innerText);
                                                productCount++;
                                                document.getElementById('product-count').innerText = productCount.toString();
                                                }addingToCart()" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to
                                            cart</a>
                                        <a href="/product?id=${product.productId}" class="view-details-link"><i
                                                class="fa fa-link"></i> See details</a>
                                    </div>
                                </div>

                                <h2><a href="/product?id=${product.productId}">${product.name}</a></h2>

                                <div class="product-carousel-price">
                                    <ins>$${product.price}</ins>
                                    <del>$${product.price}</del>
                                </div>
                            </div>
                        </c:forEach>
                        <%--<div class="single-product">--%>
                            <%--<div class="product-f-image">--%>
                                <%--<img src="../assets/img/product-2.jpg" alt="">--%>
                                <%--<div class="product-hover">--%>
                                    <%--<a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                    <%--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i>--%>
                                        <%--See details</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<h2>Nokia Lumia 1320</h2>--%>
                            <%--<div class="product-carousel-price">--%>
                                <%--<ins>$899.00</ins>--%>
                                <%--<del>$999.00</del>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="single-product">--%>
                            <%--<div class="product-f-image">--%>
                                <%--<img src="../assets/img/product-3.jpg" alt="">--%>
                                <%--<div class="product-hover">--%>
                                    <%--<a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                    <%--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i>--%>
                                        <%--See details</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<h2>LG Leon 2015</h2>--%>

                            <%--<div class="product-carousel-price">--%>
                                <%--<ins>$400.00</ins>--%>
                                <%--<del>$425.00</del>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="single-product">--%>
                            <%--<div class="product-f-image">--%>
                                <%--<img src="../assets/img/product-4.jpg" alt="">--%>
                                <%--<div class="product-hover">--%>
                                    <%--<a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                    <%--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i>--%>
                                        <%--See details</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<h2><a href="single-product.html">Sony microsoft</a></h2>--%>

                            <%--<div class="product-carousel-price">--%>
                                <%--<ins>$200.00</ins>--%>
                                <%--<del>$225.00</del>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="single-product">--%>
                            <%--<div class="product-f-image">--%>
                                <%--<img src="../assets/img/product-5.jpg" alt="">--%>
                                <%--<div class="product-hover">--%>
                                    <%--<a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                    <%--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i>--%>
                                        <%--See details</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<h2>iPhone 6</h2>--%>

                            <%--<div class="product-carousel-price">--%>
                                <%--<ins>$1200.00</ins>--%>
                                <%--<del>$1355.00</del>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="single-product">--%>
                            <%--<div class="product-f-image">--%>
                                <%--<img src="../assets/img/product-6.jpg" alt="">--%>
                                <%--<div class="product-hover">--%>
                                    <%--<a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>--%>
                                    <%--<a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i>--%>
                                        <%--See details</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<h2><a href="single-product.html">Samsung gallaxy note 4</a></h2>--%>

                            <%--<div class="product-carousel-price">--%>
                                <%--<ins>$400.00</ins>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End main content area -->

<div class="brands-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="brand-wrapper">
                    <div class="brand-list">
                        <img src="../assets/img/brand1.png" alt="">
                        <img src="../assets/img/brand2.png" alt="">
                        <img src="../assets/img/brand3.png" alt="">
                        <img src="../assets/img/brand4.png" alt="">
                        <img src="../assets/img/brand5.png" alt="">
                        <img src="../assets/img/brand6.png" alt="">
                        <img src="../assets/img/brand1.png" alt="">
                        <img src="../assets/img/brand2.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End brands area -->

<div class="product-widget-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="single-product-widget">
                    <h2 class="product-wid-title">The Most Popular</h2>
                    <a href="" class="wid-view-more">View All</a>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-1.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-2.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Apple new mac book 2015</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-3.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Apple new i phone 6</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="single-product-widget">
                    <h2 class="product-wid-title">Recently Viewed</h2>
                    <a href="#" class="wid-view-more">View All</a>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-4.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Sony playstation microsoft</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-1.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Sony Smart Air Condtion</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-2.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Samsung gallaxy note 4</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="single-product-widget">
                    <h2 class="product-wid-title">Top New</h2>
                    <a href="#" class="wid-view-more">View All</a>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-3.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Apple new i phone 6</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-4.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Samsung gallaxy note 4</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                    <div class="single-wid-product">
                        <a href="single-product.html"><img src="../assets/img/product-thumb-1.jpg" alt=""
                                                           class="product-thumb"></a>
                        <h2><a href="single-product.html">Sony playstation microsoft</a></h2>
                        <div class="product-wid-rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product-wid-price">
                            <ins>$400.00</ins>
                            <del>$425.00</del>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End product widget area -->

<%@ include file="footer.jsp" %>
</html>

