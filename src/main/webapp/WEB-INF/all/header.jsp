<%@ page import="java.util.HashMap" %>
<%@ page import="store.entities.Product" %>
<%@ page import="java.util.Map" %>
<%@ page import="store.entities.User" %>
<body>

<div class="header-area">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="user-menu">
                    <ul>
                        <li><a href="/cart"><i class="fa fa-shopping-cart"></i> My Cart</a></li>
                        <li><a href="/login"><i class="fa fa-user"></i>Sign In</a></li>
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
                    <li class="active"><a href="index.html">Home</a></li>
                    <li><a href="/catalog">Shop page</a></li>
                    <li><a href="/product?id=1">Single product</a></li>
                    <li><a href="/cart">Cart</a></li>
                    <li><a href="/user/checkout">Checkout</a></li>
                    <li><a href="/contacts">Contact</a></li>
                </ul>
            </div>
        </div>
    </div>
</div> <!-- End mainmenu area -->