<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Category</title>

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
    <link rel="stylesheet" href="../assets/css/alertify.min.css">
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
                    <h2>Updating Category</h2>
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
                    <form:form method="POST" modelAttribute="category">

                        <div id="customer_details" class="col2-set">

                            <h3>Category Details</h3>

                            <spring:bind path="productCategoryId">
                                <form:hidden path="productCategoryId"/>
                            </spring:bind>

                            <p id="name_field" class="form-row form-row-first validate-required">
                                <label class="" for="name">Category name <abbr title="required"
                                                                               class="required">*</abbr></label>
                                <spring:bind path="name">
                                    <form:input type="text" id="name" path="name" placeholder="required"
                                                class="input-text "/>
                                    <form:errors path="name" cssStyle="color: red"/>
                                </spring:bind>
                            </p>
                            <div class="clear"></div>
                            <div class="form-row place-order">
                                <input type="submit" data-value="Update" value="Update" id="place_order" name="change"
                                       class="button alt">
                            </div>

                            <input type="button" data-value="Delete" onclick="deleteProduct(${category.productCategoryId})"
                                   value="Delete" id="delete" name="Delete" class="button alt">
                            <div style="margin-top: 15px">
                                <c:if test="${not empty error}">
                                    <h3 style="color: red">${error}</h3>
                                </c:if>
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
<script type="text/javascript">
    alertify.defaults.transition = "slide";
    alertify.defaults.theme.ok = "btn btn-primary";
    alertify.defaults.theme.cancel = "btn btn-danger";
    alertify.defaults.theme.input = "form-control";
</script>
<script>
    function deleteProduct(category_id) {
        popBox();
        function popBox() {
            alertify.confirm('Are you Sure to Delete Category?', function(e){
                if (e){
                    var xhr = new XMLHttpRequest();
                    xhr.open("DELETE", "change-category?category_id=" + category_id, false);
                    xhr.send();
                    if (xhr.status == 500) {
                        alertify.error('This category contain products, delete or change there before');
                        setTimeout(function(){}, 1500);
                        return;
                    }
                    alertify.success('Successfully removed');
                    setTimeout(function(){}, 1500);
                    window.location.replace("all-products");
                    alertify.info('successfully');
                } else {}}).autoOk(10);
        }
    }</script>
</html>
