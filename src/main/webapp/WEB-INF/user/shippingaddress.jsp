<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Adress</title>

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
            <div class="col-md-1"></div>
            <div class="col-md-7">

                <div class="woocommerce">
                    <form:form method="POST" modelAttribute="currentUserAdress">

                        <div id="customer_details" class="col2-set">

                            <h3>Update Shipping Address</h3>

                            <spring:bind path="adressId">
                                <form:hidden path="adressId"/>
                            </spring:bind>

                            <p id="country_field" class="form-row form-row-first address-field validate-state"
                               data-o_class="form-row form-row-first address-field validate-state">
                                <label class="" for="country">County</label>
                                <spring:bind path="country">
                                    <form:input type="text" id="country" path="country" placeholder="State / County"
                                                class="input-text "></form:input>
                                    <form:errors path="country"></form:errors>
                                </spring:bind>
                            </p>

                            <p id="city_field"
                               class="form-row form-row-wide address-field validate-required"
                               data-o_class="form-row form-row-wide address-field validate-required">
                                <label class="" for="city">Town / City <abbr title="required"
                                                                             class="required">*</abbr>
                                </label>
                                <spring:bind path="city">
                                    <form:input type="text" id="city" path="city" placeholder="Town / City"
                                                class="input-text "></form:input>
                                    <form:errors path="city"></form:errors>
                                </spring:bind>
                            </p>

                            <p id="street_field"
                               class="form-row form-row-wide address-field validate-required">
                                <label class="" for="street">Adress <abbr title="required"
                                                                          class="required">*</abbr>
                                </label>
                                <spring:bind path="street">
                                    <form:input type="text" id="street" path="street" placeholder="Street address"
                                                class="input-text "></form:input>
                                    <form:errors path="street"></form:errors>
                                </spring:bind>

                            </p>

                            <p id="home_field"
                               class="form-row form-row-wide address-field " }>
                                <spring:bind path="home">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" id="home" path="home" placeholder="Home / Building"
                                            class="input-text "/>
                                <form:errors path="home"></form:errors>
                            </div>
                            </spring:bind>
                            </p>

                            <p id="room_field" class="form-row form-row-wide address-field">
                                <spring:bind path="home">
                                    <form:input type="text" id="room" path="room"
                                                placeholder="Apartment, suite, unit etc."
                                                class="input-text "></form:input>
                                    <form:errors path="room"></form:errors>
                                </spring:bind>
                            </p>

                            <p id="zipcode_field"
                               class="form-row form-row-last address-field validate-required validate-postcode"
                               data-o_class="form-row form-row-last address-field validate-required validate-postcode">
                                <label class="" for="zipcode">Postcode <abbr title="required"
                                                                             class="required">*</abbr>
                                </label>
                                <spring:bind path="zipCode">
                                    <form:input type="text" id="zipcode" path="zipCode" placeholder="Postcode / Zip"
                                                class="input-text "></form:input>
                                    <form:errors path="zipCode"></form:errors>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>
                            <div class="form-row place-order">
                                <input type="submit" data-value="Update" value="Update" id="place_order" name="change"
                                       class="button alt">
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
</body>
</html>

