<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:choose><c:when test="${isnewproduct}">Add Product</c:when><c:otherwise>Update Product</c:otherwise></c:choose>
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
    <link rel="stylesheet" href="../assets/css/alertify.min.css">
    <link rel="stylesheet" href="../assets/css/themes/bootstrap.css">

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

                    <h2><c:choose><c:when test="${isnewproduct}">Adding Product</c:when><c:otherwise>Update Product</c:otherwise></c:choose></h2>

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
                    <form action="#" class="checkout" method="post" name="checkout" enctype="multipart/form-data">

                        <div id="customer_details" class="col2-set">

                            <h3>Product Details</h3>

                            <input type="hidden" name="product_id" value="${product.productId}">

                            <p id="name_field" class="form-row form-row-first validate-required">
                                <label class="" for="name">Tradename <abbr title="required"
                                                                           class="required">*</abbr></label>
                                <input type="text" value="${product.name}" placeholder="required"
                                       id="name"
                                       name="name" class="input-text">
                            </p>

                            <div class="clear"></div>

                            <p id="price_field" class="form-row form-row-first validate-required">
                                <label class="" for="price">Price <abbr title="required"
                                                                        class="required">*</abbr></label>
                                <input type="text" value="${product.price}" placeholder="required"
                                       id="price"
                                       name="price" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="stock_quintity_field" class="form-row form-row-first validate-required">
                                <label class="" for="stock_quintity">Stock Quintity <abbr title="required"
                                                                                          class="required">*</abbr></label>
                                <input id="stock_quintity" type="number" name="stock_quintity" size="4"
                                       class="input-text qty text" title="Qty"
                                       value="${product.stockQuantity}" min="0" step="1">
                            </p>

                            <div class="clear"></div>

                            <label for="product_category">Product Category <abbr title="required"
                                                                                 class="required">*</abbr></label>
                            <select id="product_category" name="product_category">
                                <c:forEach var="prvendor" items="${allcategories}">
                                    <option value="${prvendor.name}"
                                            <c:if test="${prvendor.name == product.productCategoryDTO.name}">selected="selected"</c:if> >
                                            ${prvendor.name}
                                    </option>
                                </c:forEach>
                            </select>

                            <div class="clear"></div>

                            <label for="product_vendor">Product Vendor <abbr title="required"
                                                                             class="required">*</abbr></label>
                            <select id="product_vendor" name="product_vendor">
                                <c:forEach var="prvendor" items="${allvendors}">
                                    <option value="${prvendor.name}"
                                            <c:if test="${prvendor.name == product.productVendorDTO.name}">selected="selected"</c:if> >
                                            ${prvendor.name}
                                    </option>
                                </c:forEach>
                            </select>

                            <div class="clear"></div>

                            <p id="description_field" class="form-row form-row-first validate-required">
                                <label class="" for="description">Description <abbr title="required"
                                                                                    class="required">*</abbr></label>
                                <textarea id="description" name="description" class="input-text "
                                          placeholder="required">${product.description}</textarea>
                            </p>

                            <div class="clear"></div>

                            <p id="image_path_field" class="form-row form-row-first validate-required">
                                <label class="" for="image_path">Image Path <abbr title="required"
                                                                                  class="required">*</abbr></label>
                                <input type="file" placeholder="required"
                                       id="image_path"
                                       name="image_path" class="input-text ">

                            </p>
                            <%--value="${product.imagePath}"--%>

                            <div class="clear"></div>

                            <p id="weight_field" class="form-row form-row-first validate-required">
                                <label class="" for="image_path">Weight </label>
                                <input type="text" value="${product.weight}" placeholder="Optional"
                                       id="weight"
                                       name="weight" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="volume_field" class="form-row form-row-first validate-required">
                                <label class="" for="volume">Volume </label>
                                <input type="text" value="${product.volume}" placeholder="Optional"
                                       id="volume"
                                       name="volume" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="color_field" class="form-row form-row-first validate-required">
                                <label class="" for="color">Color </label>
                                <input type="text" value="${product.color}" placeholder="Optional"
                                       id="color"
                                       name="color" class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="power_field" class="form-row form-row-first validate-required">
                                <label class="" for="color">Power </label>
                                <input type="text" value="${product.power}" placeholder="Optional"
                                       id="power"
                                       name="power" class="input-text ">
                            </p>

                            <div class="form-row place-order">
                                <input type="submit" data-value="Update"
                                       value="<c:choose><c:when test="${isnewproduct}">Add</c:when><c:otherwise>Update</c:otherwise></c:choose>"
                                       id="place_order" name="change"
                                       class="button alt">
                                <c:choose><c:when test="${!isnewproduct}">
                                <input type="button" data-value="Delete" onclick="deleteProduct(${product.productId})"
                                       value="Delete"
                                       id="place_order" name="Delete"
                                       class="button alt">
                                </c:when></c:choose>
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

<script type="text/javascript">
    //override defaults
    alertify.defaults.transition = "slide";
    alertify.defaults.theme.ok = "btn btn-primary";
    alertify.defaults.theme.cancel = "btn btn-danger";
    alertify.defaults.theme.input = "form-control";
</script>
<script>
    function deleteProduct(product_id) {
        popBox();
        function popBox() {
            alertify.confirm('Are you Sure', function(e){
                if (e){
                    var xhr = new XMLHttpRequest();
                    xhr.open("DELETE", "change-product?product_id=" + product_id, false);
                    xhr.send();
                    alertify.success('Successfully removed');
                    setTimeout(function(){}, 1500);
                    window.location.replace("all-products");
                    alertify.info('successfully')
                } else {}
            });
            // if (x == true) {
                // var xhr = new XMLHttpRequest();
                // xhr.open("DELETE", "change-product?product_id=" + product_id, false);
                // xhr.send();
                // window.location.replace("all-products");
                // xhr.open("GET", "all-products", false);
                // xhr.send();
            // }
        }
    }</script>



</html>
