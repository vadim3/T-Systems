<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="store.dto.ProductDTO" %>
<body>

<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>
                        <c:choose>
                            <c:when test="${sessionScope.currentUser.accessLevel == '2'}">
                                <li><a href="/admin/order-history"><i class="fa fa-briefcase"></i> Orders Management</a></li>
                                <li><a href="/admin/all-products"><i class="fa fa-archive"></i>Product Management</a></li>
                                <li><a href="/admin/user-management"><i class="fa fa-user"></i>Users Management</a></li>
                                <li><a href="/admin/income-statistic"><i class="fa fa-calendar"></i>Statistics</a></li>
                                <li><a href="/user/user-management"><i class="fa fa-upload"></i>File Import</a></li>
                                <li><a href="/logout"><i class="fa fa-sign-out"></i>Logout</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="/cart"><i class="fa fa-shopping-cart"></i> My Cart</a></li>
                                <li><a href="/user/previous-orders"><i class="fa fa-book"></i>Order History</a></li>
                                <li><a href="/user/personal-details"><i class="fa fa-user"></i>Personal Details</a></li>
                                <li><a href="/user/shipping-address"><i class="fa fa-truck"></i>Shipping Address</a></li>
                                <li><a href="/logout"><i class="fa fa-sign-out"></i>Logout</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>


        </div>
    </div>
</div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <h1><a href="/"><img src="../assets/img/logo.png"></a></h1>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item">
                    <%
                        HashMap<ProductDTO, Integer> cartProducts = ((HashMap) session.getAttribute("cartProducts"));
                        int items = 0;
                        double sum = 0;
                        for (Map.Entry<ProductDTO, Integer> entry : cartProducts.entrySet())
                        {
                            items += entry.getValue();
                            sum += entry.getKey().getPrice() * entry.getValue();
                        }
                    %>
                    <a href="/cart">Cart - <span class="cart-amunt">$<%=sum%></span> <i class="fa fa-shopping-cart"></i>
                        <c:if test="${!sessionScope.cartProducts.isEmpty()}"><span class="product-count"><%=items%></span></c:if>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->