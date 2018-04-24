<%@ page import="java.util.HashMap" %>
<%@ page import="store.entities.Product" %>
<%@ page import="java.util.Map" %>
<body>

<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>
                        <li><a href="/admin/order-history"><i class="fa fa-briefcase"></i> Orders Management</a></li>
                        <li><a href="/admin/all-products"><i class="fa fa-archive"></i>Product Management</a></li>
                        <li><a href="/admin/user-management"><i class="fa fa-user"></i>Users Management</a></li>
                        <li><a href="/admin/income-statistic"><i class="fa fa-calendar"></i>Statistics</a></li>
                        <li><a href="/user/user-management"><i class="fa fa-upload"></i>File Import</a></li>
                        <li><a href="/logout"><i class="fa fa-sign-out"></i>Logout</a></li>
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
                        HashMap<Product, Integer> cartProducts = ((HashMap) session.getAttribute("cartProducts"));
                        int items = 0;
                        double sum = 0;
                        for (Map.Entry<Product, Integer> entry : cartProducts.entrySet())
                        {
                            items += entry.getValue();
                            sum += entry.getKey().getPrice() * entry.getValue();
                        }
                    %>
                    <a href="/cart">Cart - <span class="cart-amunt">$<%=sum%></span> <i class="fa fa-shopping-cart"></i> <span class="product-count"><%=items%></span></a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->

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
                    <li><a href="/admin/order-history">Orders Management</a></li>
                    <li><a href="/admin/all-products">Product Management</a></li>
                    <li><a href="/admin/income-statistic">Income Statistic</a></li>
                    <li><a href="/admin/top-customers">Top 10 Customers</a></li>
                    <li><a href="/admin/top-products">Top 10 Products</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->