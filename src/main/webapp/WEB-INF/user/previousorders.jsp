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


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <div class="single-sidebar">
                    <h2 class="sidebar-title">Search Products</h2>
                    <form action="#">
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
                            <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$800.00</del>
                        </div>
                    </div>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Recent Posts</h2>
                    <ul>
                        <li><a href="#">Sony Smart TV - 2015</a></li>
                        <li><a href="#">Sony Smart TV - 2015</a></li>
                        <li><a href="#">Sony Smart TV - 2015</a></li>
                        <li><a href="#">Sony Smart TV - 2015</a></li>
                        <li><a href="#">Sony Smart TV - 2015</a></li>
                    </ul>
                </div>
            </div>


            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">

                        <c:choose>
                            <c:when test="${isempty}">
                                <h2>You Don't Have Any Orders</h2>
                            </c:when>

                            <c:otherwise>

                                <c:forEach var="order" items="${currentUser.orders}">

                                    <table cellspacing="0" class="shop_table cart">
                                        <thead>
                                        <tr>

                                            <th class="product-thumbnail"><label>Date: ${order.dateTime}</label></th>
                                            <th class="product-name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-quantity">Quantity</th>
                                            <th class="product-subtotal">Total</th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach var="product" items="${order.products}">
                                            <tr class="cart_item">

                                                <td class="product-thumbnail">
                                                    <a href="/product?id=${product.productId}"><img width="145"
                                                                                                     height="145"
                                                                                                     alt="${product.name}"
                                                                                                     class="shop_thumbnail"
                                                                                                     src="${thumbprefix}${product.imagePath}"></a>
                                                </td>

                                                <td class="product-name">
                                                    <a href="/product?id=${product.productId}">${product.name}</a>
                                                </td>

                                                <td class="product-price">
                                                    <span class="amount">$${product.price}</span>
                                                </td>

                                                <td class="product-quantity">
                                                    <label>-//-</label>
                                                </td>

                                                <td class="product-subtotal">
                                                    <span class="amount">-//-</span>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <tr>
                                            <td class="actions" colspan="5">
                                                <div class="coupon">
                                                    <label>Order Total: -//-</label>
                                                </div>
                                                <input type="submit" value="Repeate Order" name="repeate"
                                                       class="checkout-button button alt wc-forward">
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>

                                </c:forEach>

                            </c:otherwise>
                        </c:choose>


                    </div>
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

<jsp:include page="footer.jsp"/>
</html>
