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

</head>

<jsp:include page="header.jsp"/>

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
            <div class="col-md-4">
                <div class="single-sidebar">
                    <h2 class="sidebar-title">Search Products</h2>
                    <form action="">
                        <input type="text" placeholder="Search products...">
                        <input type="submit" value="Search">
                    </form>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Products</h2>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                    <div class="thubmnail-recent">
                        <img src="../assets/img/product-thumb-1.jpg" class="recent-thumb" alt="">
                        <h2><a href="single-product.html">Sony Smart TV - 2015</a></h2>
                        <div class="product-sidebar-price">
                            <ins>$700.00</ins>
                            <del>$100.00</del>
                        </div>
                    </div>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Recent Posts</h2>
                    <ul>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                        <li><a href="single-product.html">Sony Smart TV - 2015</a></li>
                    </ul>
                </div>

            </div>
            <div class="col-md-1"></div>
            <div class="col-md-7">

                <div class="woocommerce">
                    <form action="#" class="checkout" method="post" name="checkout">

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
                                            <c:if test="${prvendor.name == product.productCategory.name}">selected="selected"</c:if> >
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
                                            <c:if test="${prvendor.name == product.productVendor.name}">selected="selected"</c:if> >
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
                                <input type="text" value="${product.imagePath}" placeholder="required"
                                       id="image_path"
                                       name="image_path" class="input-text ">
                            </p>

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

<jsp:include page="footer.jsp"/>
</html>
