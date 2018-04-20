<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign In</title>

    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="../assets/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="../assets/css/login.css">
    <link rel="stylesheet" href="../assets/css/owl.carousel.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/responsive.css">

</head>

<jsp:include page="header.jsp"/>

<div class="product-big-title-area">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="product-bit-title text-center">
                    <h2>Sign In</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End big title name -->

<div class="promo-area">
    <div class="container">
        <div class="row"> </div>
    </div>
</div>

<div class="col-sm-6 col-md-4 col-lg-3" style="margin:40px auto; float:none;">
    <c:url var="loginUrl" value="/login"/>
    <form method="POST" action="${loginUrl}">
        <div class="col-xs-12">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-fw fa-user"></i></div>
                    <input type="email" name="username" class="form-control" placeholder="E-mail">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-fw fa-lock"></i></div>
                    <input type="password" name="password" class="form-control" placeholder="Password">
                </div>
            </div>
        </div>
        <div class="col-xs-6">
            <div class="checkbox"><label><input type="checkbox" name="remember-me-param"> Remember me</label>
            </div>
        </div>
        <div class="col-xs-6" style="padding: 10px;">
            <button type="submit" class="btn btn-block btn-primary">Sign in</button>
        </div>
        <%--<input type="hidden" name="<c:out value="${_csrf.parameterName}"/>"--%>
               <%--value="<c:out value="${_csrf.token}"/>"/>--%>
    </form>

    <div class="clear" style="padding: 10px;"></div>

    <form method="GET" action="/register">
        <button type="submit" class="btn btn-block btn-info">Sign Up</button>
    </form>

    <div class="clear" style="padding: 10px;"></div>

    <form method="GET" action="/rememberPassword">
        <button type="submit" class="btn btn-block btn-danger">Forgot password</button>
    </form>
</div>

<div class="promo-area">
    <div class="container">
        <div class="row"> </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</html>


