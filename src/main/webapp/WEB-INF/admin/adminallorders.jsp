<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View orders</title>

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
                    <li class="active"><a href="/admin/order-history">Orders Management</a></li>
                    <li><a href="/admin/all-products">Product Management</a></li>
                    <li><a href="/admin/income-statistic">Income Statistic</a></li>
                    <li><a href="/admin/top-customers">Top 10 Customers</a></li>
                    <li><a href="/admin/top-products">Top 10 Products</a></li>
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

                        <c:forEach var="i" begin="0" end="${orders.size()-1}" step="1">

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
                                            <a href="/product?id=${product.key.productId}"><img width="145"
                                                                                                height="145"
                                                                                                alt="${product.key.name}"
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
                                    <td class="user-details" colspan="1">

                                        <div class="coupon">
                                            <div>Customer: ${users.get(i).firstName}
                                                    ${users.get(i).secondName}
                                                Phone: ${users.get(i).phoneNumber}
                                                <c:if test="${useradresses.get(i).adressId != 0}">
                                                Address: <strong>${useradresses.get(i).country}, ${useradresses.get(i).city},
                                                    ${useradresses.get(i).street}, ${useradresses.get(i).home},
                                                    ${useradresses.get(i).room}</strong></c:if>
                                            </div>
                                        </div>
                                    </td>
                                    <form action="" method="post">
                                        <td class="user-details" colspan="2">
                                            <label for="order_status">Order status:</label>
                                            <select id="order_status" name="order_status">
                                                <c:forEach var="shippingstatus" items="${orderstatuses}">
                                                    <option value="${shippingstatus.status}"
                                                            <c:if test="${shippingstatus.status == orders.get(i).orderStatus.status}">selected="selected"</c:if> >
                                                            ${shippingstatus.status}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </td><td class="user-details" colspan="2">
                                            <input type="hidden" name="order_id" value="${orders.get(i).orderId}">
                                            <input type="submit" value="Change " name="repeate"
                                                   class="checkout-button button alt wc-forward">

                                        </td>
                                    </form>
                                </tr>
                                </tbody>
                            </table>
                        </c:forEach>
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
<c:if test="${notification != null}">
    <script type="text/javascript">
        $(document).ready(function () {
            $.notify("${notification}", "success",{ position:"center" });
        });
    </script></c:if>
</html>
