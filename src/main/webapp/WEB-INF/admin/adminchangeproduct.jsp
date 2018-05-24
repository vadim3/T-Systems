<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:choose><c:when
                test="${isnewproduct}">Add Product</c:when><c:otherwise>Update Product</c:otherwise></c:choose>
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
                    <li><a href="/admin/order-history">Orders Management</a></li>
                    <li class="active"><a href="/admin/all-products">Product Management</a></li>
                    <li><a href="/admin/income-statistic">Income Statistic</a></li>
                    <li><a href="/admin/top-customers">Top 10 Customers</a></li>
                    <li><a href="/admin/top-products">Top 10 Products</a></li>
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

                    <h2><c:choose><c:when
                            test="${isnewproduct}">Adding Product</c:when><c:otherwise>Update Product</c:otherwise></c:choose></h2>

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
                            <spring:bind path="product.productId">
                                <input type="hidden" name="${status.expression}" value="${status.value}">
                            </spring:bind>

                            <spring:bind path="product.imagePath">
                                <input type="hidden" name="${status.expression}" value="${status.value}">
                            </spring:bind>

                            <p id="name_field" class="form-row form-row-first validate-required">
                                <label class="" for="name">Tradename <abbr title="required"
                                                                           class="required">*</abbr></label>
                                <spring:bind path="product.name">
                                    <input type="text" value="${status.value}" placeholder="required"
                                           id="name" name="${status.expression}" class="input-text">
                                    <form:errors path="product.name" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="price_field" class="form-row form-row-first validate-required">
                                <label class="" for="price">Price <abbr title="required"
                                                                        class="required">*</abbr></label>
                                <spring:bind path="product.price">
                                    <input type="text" value="${status.value}" placeholder="required"
                                           id="price" name="${status.expression}" class="input-text">
                                    <form:errors path="product.price" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="stock_quintity_field" class="form-row form-row-first validate-required">
                                <label class="" for="stock_quintity">Stock Quintity <abbr title="required"
                                                                                          class="required">*</abbr></label>
                                <spring:bind path="product.stockQuantity">
                                    <input id="stock_quintity" type="number" name="${status.expression}" size="4"
                                           class="input-text qty text" title="Qty" value="${status.value}" min="0"
                                           step="1">
                                    <form:errors path="product.stockQuantity" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <label for="product_category">Product Category <abbr title="required"
                                                                                 class="required">*</abbr></label>
                            <spring:bind path="product.productCategoryDTO.productCategoryId">
                                <input type="hidden" name="${status.expression}" value="${status.value}">
                            </spring:bind>

                            <spring:bind path="product.productCategoryDTO.name">
                                <input type="hidden" name="${status.expression}" value="${status.value}">
                            </spring:bind>

                            <select id="product_category" name="product_category">
                                <c:forEach var="prcat" items="${allcategories}">
                                    <option value="${prcat.name}"
                                            <c:if test="${prcat.name == product.productCategoryDTO.name}">selected="selected"</c:if> >
                                            ${prcat.name}
                                    </option>
                                </c:forEach>
                            </select>

                            <div class="clear"></div>

                            <label for="product_vendor">Product Vendor <abbr title="required"
                                                                             class="required">*</abbr></label>
                            <spring:bind path="product.productVendorDTO.productVendorId">
                                <input type="hidden" name="${status.expression}" value="${status.value}">
                            </spring:bind>
                            <spring:bind path="product.productVendorDTO.name">
                                <input type="hidden" name="${status.expression}" value="${status.value}">
                            </spring:bind>

                            <select id="product_vendor" name="product_vendor">
                                <c:forEach var="shippingstatus" items="${allvendors}">
                                    <option value="${shippingstatus.name}"
                                            <c:if test="${shippingstatus.name == product.productVendorDTO.name}">selected="selected"</c:if> >
                                            ${shippingstatus.name}
                                    </option>
                                </c:forEach>
                            </select>

                            <div class="clear"></div>

                            <p id="description_field" class="form-row form-row-first validate-required">
                                <label class="" for="description">Description <abbr title="required"
                                                                                    class="required">*</abbr></label>
                                <spring:bind path="product.description">
                                    <textarea id="description" name="${status.expression}" class="input-text "
                                              placeholder="required">${status.value}</textarea>
                                    <form:errors path="product.description" cssStyle="color: red"/>
                                </spring:bind>

                            </p>

                            <div class="clear"></div>

                            <p id="weight_field" class="form-row form-row-first validate-required">
                                <label class="" for="weight">Weight </label>
                                <spring:bind path="product.weight">
                                    <input id="weight" type="number" name="${status.expression}" size="4"
                                           class="input-text qty text" title="Qty" value="${status.value}" min="0"
                                           step="0.01">
                                    <form:errors path="product.weight" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="volume_field" class="form-row form-row-first validate-required">
                                <label class="" for="volume">Volume </label>

                                <spring:bind path="product.volume">
                                    <input id="volume" type="number" name="${status.expression}" size="4"
                                           class="input-text" title="Qty" value="${status.value}" min="0" step="0.01">
                                    <form:errors path="product.volume" cssStyle="color: red"/>
                                </spring:bind>

                            </p>

                            <div class="clear"></div>

                            <p id="power_field" class="form-row form-row-first validate-required">
                                <label class="" for="power">Power </label>
                                <spring:bind path="product.power">
                                    <input id="power" type="number" name="${status.expression}" size="4"
                                           class="input-text" title="Qty" value="${status.value}" min="0" step="0.01">
                                    <form:errors path="product.power" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="clear"></div>

                            <p id="image_path_field" class="form-row form-row-first validate-required">
                                <label class="" for="image_path">Image File <abbr title="required"
                                                                                  class="required">*</abbr></label>
                                <input type="file" placeholder="required" id="image_path" name="image_file"
                                       class="input-text ">
                            </p>

                            <div class="clear"></div>

                            <p id="color_field" class="form-row form-row-first validate-required">
                                <label class="" for="color">Color </label>
                                <spring:bind path="product.color">
                                    <input type="text" value="${status.value}" placeholder="required"
                                           id="color" name="${status.expression}" class="input-text">
                                    <form:errors path="product.color" cssStyle="color: red"/>
                                </spring:bind>
                            </p>

                            <div class="form-row place-order">
                                <input type="submit" data-value="Update" value="Update" id="place_order" name="change"
                                       class="button alt">

                                    <input type="button" data-value="Delete"
                                           onclick="deleteProduct(${product.productId})"
                                           value="Delete" id="delete_product" name="Delete" class="button alt">

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
<c:if test="${notification != null}">
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
            alertify.confirm('Are you Sure', function (e) {
                if (e) {
                    var xhr = new XMLHttpRequest();
                    xhr.open("DELETE", "change-product?product_id=" + product_id, false);
                    xhr.send();
                    alertify.success('Successfully removed');
                    setTimeout(function () {
                    }, 1500);
                    window.location.replace("all-products");
                    alertify.info('successfully')
                } else {
                }
            }).autoOk(10);
        }
    }</script>


</html>
