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
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$800.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
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
                            <h2>The cart is empty</h2>
                            </c:when>

                            <c:otherwise>

                            <form id="form-action" method="post" action="">
                                <table cellspacing="0" class="shop_table cart">
                                    <thead>
                                    <tr>
                                        <th class="product-remove">&nbsp;</th>
                                        <th class="product-thumbnail">&nbsp;</th>
                                        <th class="product-name">Product</th>
                                        <th class="product-price">Price</th>
                                        <th class="product-quantity">Quantity</th>
                                        <th class="product-subtotal">Total</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach var="customentry" items="${cartproducts}">
                                    <tr id="tr${customentry.key.productId}" class="cart_item">
                                        <td class="product-remove">
                                            <a title="Remove this item" class="minus" onclick="function x() {
                                              document.getElementById('tr${customentry.key.productId}').style.display = 'none';
                                              document.getElementById('p${customentry.key.productId}').value = 0;
                                            } x()">x</a>
                                        </td>

                                        <td class="product-thumbnail">
                                            <a href="/product?id=${customentry.key.productId}"><img width="145" height="145"
                                                                                                    alt="poster_1_up" class="shop_thumbnail"
                                                                                                    src="${thumbprefix}${customentry.key.imagePath}"></a>
                                        </td>

                                        <td class="product-name">
                                            <input type="hidden" name="products" value="${customentry.key.productId}">
                                            <a href="/product?id=${customentry.key.productId}">${customentry.key.name}</a>
                                        </td>

                                        <td class="product-price">
                                            <span class="amount">$${customentry.key.price}</span>
                                        </td>

                                        <td class="product-quantity">
                                            <div class="quantity buttons_added">

                                                <input id="p${customentry.key.productId}" type="number" name="product_quantities" size="4" class="input-text qty text" title="Qty"
                                                       value="${customentry.value}" min="0" step="1">

                                            </div>
                                        </td>

                                        <td class="product-subtotal">
                                            <span class="amount">${customentry.key.price * customentry.value}</span>
                                        </td>
                                    </tr>
                                    </c:forEach>

                                    <tr>
                                        <td class="actions" colspan="6">
                                            <div class="coupon">
                                                <label for="coupon_code">Coupon:</label>
                                                <input type="text" placeholder="Coupon code" value="" id="coupon_code"
                                                       class="input-text" name="coupon_code">
                                                <input type="submit" value="Apply Coupon" name="apply_coupon"
                                                       class="button">
                                            </div>
                                            <input type="hidden" name="is-update" id="is-update" value="true">
                                            <input type="submit" value="Update Cart" class="button">
                                            <input type="submit" value="Checkout" onClick="document.getElementById('is-update').value='false'"
                                                   class="checkout-button button alt wc-forward">
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>

                            <div class="cart-collaterals">

                                <div class="cross-sells">
                                    <h2>You may be interested in...</h2>
                                    <ul class="products">
                                        <li class="product">
                                            <a href="single-product.html">
                                                <img width="325" height="325" alt="T_4_front"
                                                     class="attachment-shop_catalog wp-post-image"
                                                     src="../assets/img/product-2.jpg">
                                                <h3>Ship Your Idea</h3>
                                                <span class="price"><span class="amount">$20.00</span></span>
                                            </a>

                                            <a class="add_to_cart_button" data-quantity="1" data-product_sku=""
                                               data-product_id="22" rel="nofollow" href="single-product.html">Select
                                                options</a>
                                        </li>

                                        <li class="product">
                                            <a href="single-product.html">
                                                <img width="325" height="325" alt="T_4_front"
                                                     class="attachment-shop_catalog wp-post-image"
                                                     src="../assets/img/product-4.jpg">
                                                <h3>Ship Your Idea</h3>
                                                <span class="price"><span class="amount">$20.00</span></span>
                                            </a>

                                            <a class="add_to_cart_button" data-quantity="1" data-product_sku=""
                                               data-product_id="22" rel="nofollow" href="single-product.html">Select
                                                options</a>
                                        </li>
                                    </ul>
                                </div>

                                <div class="cart_totals ">
                                    <h2>Cart Totals</h2>

                                    <table cellspacing="0">
                                        <tbody>
                                        <tr class="cart-subtotal">
                                            <th>Cart Subtotal</th>
                                            <td><span class="amount">$${sum}</span></td>
                                        </tr>

                                        <tr class="shipping">
                                            <th>Shipping and Handling</th>
                                            <td>Free Shipping</td>
                                        </tr>

                                        <tr class="order-total">
                                            <th>Order Total</th>
                                            <td><strong><span class="amount">$${sum}</span></strong></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
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
