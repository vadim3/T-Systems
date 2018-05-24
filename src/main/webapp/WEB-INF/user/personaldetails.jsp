<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
                    <h2>Shopping Cart</h2>
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
            <div class="col-md-1">

            </div>
            <div class="col-md-7">

                <div class="woocommerce">
                    <form:form method="POST" modelAttribute="currentUser">

                        <div id="customer_details" class="col2-set">

                            <h3>Personal Details</h3>

                            <spring:bind path="userId">
                                <form:hidden path="userId"/>
                            </spring:bind>

                            <spring:bind path="accessLevel">
                                <form:hidden path="accessLevel"/>
                            </spring:bind>

                            <p id="first_name_field" class="form-row form-row-first validate-required">
                                <label class="" for="first_name">First Name </label>
                                <spring:bind path="firstName">
                                    <form:input type="text" id="first_name" path="firstName" placeholder=""
                                                class="input-text "/>
                                    <form:errors path="firstName" cssStyle="color: red"/>
                                </spring:bind>
                            </p>
                            <div class="clear"></div>
                            <p id="second_name_field" class="form-row form-row-first validate-required">
                                <label class="" for="second_name">Last Name </label>
                                <spring:bind path="secondName">
                                    <form:input type="text" id="second_name" path="secondName" placeholder=""
                                                class="input-text "/>
                                    <form:errors path="secondName" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="email_field" class="form-row form-row-first validate-required">
                                <label class="" for="email">E-mail </label>
                                <spring:bind path="email">
                                    <form:input type="text" id="email" path="email" placeholder="" class="input-text "/>
                                    <form:errors path="email" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="birthday_field" class="form-row form-row-first validate-required">
                                <label class="" for="birthday">Birthday </label>
                                <spring:bind path="birthdayData">
                                    <form:input type="text" id="birthday" path="birthdayData" placeholder=""
                                                class="input-text "/>
                                    <form:errors path="birthdayData" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="phoneNumber_field" class="form-row form-row-first validate-required">
                                <label class="" for="phone_number">Phone Number </label>
                                <spring:bind path="phoneNumber">
                                    <form:input type="text" id="phone_number" path="phoneNumber" placeholder=""
                                                class="input-text "/>
                                    <form:errors path="phoneNumber" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <div class="form-row place-order">
                                <input type="submit" data-value="Update" value="Update" id="place_order" name="change"
                                       class="button alt">
                                <input type="button" style="margin-left: 15px" class="button alt"
                                       data-value="Change Password" value="Change Password"
                                       id="change_password" name="change"
                                       onclick="location.href = '/user/update-password';"
                                >
                            </div>
                        </div>
                    </form:form>

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
<c:if test="${not empty error}">
    <script type="text/javascript">
        $(document).ready(function () {
            $.notify("${error}", "error",{ position:"center" });
        });
    </script></c:if>
</body>
</html>


