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
                    <li><a href="/user/previous-orders">Order History</a></li>
                    <li class="active"><a href="/user/personal-details">Personal Details</a></li>
                    <li><a href="/user/shipping-address">Shipping Adress</a></li>
                    <li><a href="/user/checkout">Checkout</a></li>
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
            <%@ include file="leftside.jsp" %>
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
                                <input type="button" style="margin-left: 15px" class="button alt"
                                       onclick="generatePassword()" data-value="Generate Password"
                                       value="Generate Password"
                                       id="generate_password" name="generate"
                                >
                            </div>
                            <div style="margin-top: 15px">
                                <c:if test="${not empty message}">
                                    <h3>${message}</h3>
                                </c:if>
                            </div>
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

<%@ include file="footer.jsp" %>
<c:if test="${not empty notification}">
    <script type="text/javascript">
        $(document).ready(function () {
            $.notify("${notification}", "success",{ position:"center" });
        });
    </script></c:if>
</body>
</html>



