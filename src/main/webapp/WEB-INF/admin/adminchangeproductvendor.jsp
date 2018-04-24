<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:choose><c:when test="${isnewvendor}">Add Vendor</c:when><c:otherwise>Update Vendor</c:otherwise></c:choose>
    </title>

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
                    <h2><c:choose><c:when test="${isnewvendor}">Adding Vendor</c:when><c:otherwise>Update Vendor</c:otherwise></c:choose></h2>
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

                            <h3>Vendor Details</h3>

                            <input type="hidden" name="vendor_id" value="${vendor.productVendorId}">

                            <p id="name_field" class="form-row form-row-first validate-required">
                                <label class="" for="name">Vendor name <abbr title="required"
                                                                               class="required">*</abbr></label>
                                <input type="text" value="${vendor.name}" placeholder="required"
                                       id="name"
                                       name="name" class="input-text">
                            </p>

                            <div class="clear"></div>

                            <div class="form-row place-order">
                                <input type="submit" data-value="Update"
                                       value="<c:choose><c:when test="${isnewvendor}">Add</c:when><c:otherwise>Update</c:otherwise></c:choose>"
                                       id="place_order" name="change"
                                       class="button alt">
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
</html>
