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
                    <li><a href="/">Home</a></li>
                    <li class="active"><a href="/user/previous-orders">Order History</a></li>
                    <li><a href="/user/personal-details">Personal Details</a></li>
                    <li><a href="/user/shipping-address">Shipping Adress</a></li>
                    <li><a href="/user/checkout">Checkout</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->


<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="row">
            <%@ include file="leftside.jsp" %>

            <div class="col-md-8">
                <div class="product-content-right">
                    <div class="woocommerce">

                        <c:choose>
                            <c:when test="${isempty}">
                                <h2>You Don't Have Any Orders</h2>
                            </c:when>

                            <c:otherwise>
                                <c:forEach var="i" begin="0" end="${orders.size()-1}" step="1">
                                    <%--<c:forEach var="order" items="${currentUser.orders}">--%>

                                    <table cellspacing="0" class="shop_table cart">
                                        <thead>
                                        <tr>

                                            <th class="product-thumbnail"><label>Date: ${orders.get(i).dateTime}</label>
                                            </th>
                                            <th class="product-name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-quantity">Quantity</th>
                                            <th class="product-subtotal">Total</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="product" items="${allorders.get(i)}">
                                            <tr class="cart_item">

                                                <td class="product-thumbnail">
                                                    <a href="/product?id=${product.key.productId}">
                                                        <img width="145" height="145" alt="${product.key.name}"
                                                             class="shop_thumbnail"
                                                             src="${thumbprefix}${product.key.imagePath}"></a>
                                                </td>

                                                <td class="product-name">
                                                    <a href="/product?id=${product.key.productId}">${product.key.name}</a>
                                                </td>

                                                <td class="product-price">
                                                    <span class="amount">$${product.key.price}</span>
                                                </td>

                                                <td class="product-quantity">
                                                    <label>${product.value}</label>
                                                </td>

                                                <td class="product-subtotal">
                                                    <span class="amount">$${product.key.price * product.value}</span>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <tr>
                                            <td class="actions" colspan="5">
                                                <form action="" method="post">
                                                    <div class="coupon">
                                                        <label>Order Total: </label>
                                                    </div>
                                                    <input type="hidden" name="order_id" value="${orders.get(i).orderId}">
                                                    <input type="submit" value="Repeate Order" name="repeate"
                                                           class="checkout-button button alt wc-forward">
                                                </form>
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

<%@ include file="footer.jsp" %>
</body>
</html>
