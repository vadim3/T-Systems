<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Changing Products</title>

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

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Changing Products</h2>
                </div>
            </div>
        </div>
    </div>
</div>


<%--All products START--%>
<div class="single-product-area">
    <div class="zigzag-bottom"></div>
    <div class="container">
        <div class="col-md-4">
            <form name="myform" action="" method="get">
                <div class="single-sidebar">
                    <h2 class="sidebar-title">CATEGORIES</h2>
                    <div>
                        <div class="col col-4">
                            <c:forEach var="category" items="${allCategories}">
                                <label class="checkbox"><input type="radio" value="${category.name}"
                                                               name="category"><a style="display: inline"
                                        href="/admin/change-category?category=${category.productCategoryId}">${category.name}</a></label>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">BRAND</h2>
                    <div>
                        <div class="col col-4">
                            <c:forEach var="vendor" items="${allVendors}">
                                <label class="checkbox"><input type="radio" value="${vendor.name}"
                                                               name="vendor"><a style="display: inline"
                                        href="/admin/change-vendor?vendor=${vendor.productVendorId}">${vendor.name}</a></label>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="single-sidebar">
                    <h2 class="sidebar-title">Price Filter</h2>
                    <label for="minprice">from</label>
                    <input id="minprice" name="minprice" type="text" placeholder="">
                    <label for="maxprice">to</label>
                    <input id="maxprice" name="maxprice" type="text" placeholder="">
                    <input type="submit" value="Filter">
                </div>

            </form>

            <div class="single-sidebar">
                <h2 class="sidebar-title">Adding New</h2>
                <ButtonGroup justified>
                    <Button onclick="location.href = '/admin/change-product';" class="btn btn-success btn-lg">Product
                    </Button>
                    <Button onclick="location.href = '/admin/change-category';" class="btn btn-warning btn-lg">
                        Category
                    </Button>
                    <Button onclick="location.href = '/admin/change-vendor';" class="btn btn-info btn-lg">Vendor
                    </Button>
                </ButtonGroup>

            </div>
        </div>


        <div class="col-md-8">
            <h3 class="sidebar-title">${productList.size()} products</h3>
            <div class="row">
                <c:forEach var="product" items="${productList}" varStatus="loopOne">
                    <div class="col-md-3 col-sm-6">
                        <div class="single-shop-product">
                            <div class="product-upper">
                                <img src="${imgprefix}${product.imagePath}" alt="${product.name}">
                            </div>
                            <h2><a href="/product?id=${product.productId}">${product.name}</a></h2>
                            <div class="product-carousel-price">
                                <ins>$${product.price}</ins>
                                <del>$${product.price}</del>
                            </div>

                            <div class="product-option-shop">
                                <form method="get" action="/catalog">
                                    <input type="hidden" name="item" value="${product.productId}"
                                           data-product-id="${product.productId}">
                                    <button type="button"
                                            onclick="location.href='/admin/change-product?item=${product.productId}';"
                                            class="btn btn-block btn-danger">Change product
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <%--All products END--%>

                <%--Pagination Area--%>
                <div class="col-md-12">
                    <div class="product-pagination text-center">
                        <nav>
                            <ul class="pagination">
                                <li>
                                    <a href="/admin/all-products?category=${searchCategory}&vendor=${searchVendor}&minprice=${minprice}&maxprice=${maxprice}&page=${page-1}"
                                       aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach begin="1" end="${pageQuantity}" varStatus="page1">
                                    <li><a
                                            <c:if test="${page1.index == page}">style="background-color: #5a88ca; color: #FFFFFF"</c:if>
                                            href="/admin/all-products?category=${searchCategory}&vendor=${searchVendor}&minprice=${minprice}&maxprice=${maxprice}&page=${page1.index}">${page1.index}</a>
                                    </li>
                                </c:forEach>
                                <li>
                                    <a href="/admin/all-products?category=${searchCategory}&vendor=${searchVendor}&minprice=${minprice}&maxprice=${maxprice}&page=${page+1}"
                                       aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="footer.jsp" %>
</html>

