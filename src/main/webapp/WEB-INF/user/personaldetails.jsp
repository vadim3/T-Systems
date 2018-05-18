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
                    <form action="#" class="checkout" method="post" name="checkout">

                        <div id="customer_details" class="col2-set">

                            <h3>Personal Details</h3>

                            <p id="first_name_field" class="form-row form-row-first validate-required">
                                <label class="" for="first_name">First Name </label>
                                <input type="text" value="${currentUser.firstName}" placeholder=""
                                       id="first_name"
                                       name="first_name" class="input-text ">
                            </p>
                            <div class="clear"></div>
                            <p id="second_name_field" class="form-row form-row-first validate-required">
                                <label class="" for="second_name">Last Name </label>
                                <input type="text" value="${currentUser.secondName}" placeholder=""
                                       id="second_name"
                                       name="second_name" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="email_field" class="form-row form-row-first validate-required">
                                <label class="" for="email">E-mail </label>
                                <input type="text" value="${currentUser.email}" placeholder=""
                                       id="email"
                                       name="email" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="birthday_field" class="form-row form-row-first validate-required">
                                <label class="" for="birthday">Birthday </label>
                                <input type="text" value="${currentUser.birthdayData}" placeholder=""
                                       id="birthday"
                                       name="birthday" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="phoneNumber_field" class="form-row form-row-first validate-required">
                                <label class="" for="phone_number">Phone Number </label>
                                <input type="text" value="${currentUser.phoneNumber}" placeholder=""
                                       id="phone_number"
                                       name="phone_number" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <div class="form-row place-order" >
                                <input type="submit" data-value="Update" value="Update" id="place_order" name="change" class="button alt">
                                <input type="button" style="margin-left: 15px" class="button alt" data-value="Change Password" value="Change Password"
                                       id="change_password" name="change" onclick="location.href = '/user/update-password';"
                                       >
                            </div>
                        </div>
                    </form>

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


