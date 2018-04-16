<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PowerTrade Store</title>

    <!-- Google Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="../assets/css/font-awesome.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="../assets/css/login.css">
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
                    <h2>Forgot password?</h2>
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
    <form method="post" action="/rememberPassword">
        <div class="col-xs-12">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-fw fa-user"></i></div>
                    <input type="email" name="email" class="form-control" placeholder="E-mail">
                </div>
                <br>
                <button type="submit" class="btn btn-block btn-purple">Remind password</button>
            </div>
        </div>
    </form>
    <form method="GET" action="/">
        <button type="submit" class="btn btn-block">Back to Home</button>
    </form>
</div>
<c:if test="${remindCheck!=null}">
    <c:if test="${remindCheck==false}">
        <font color="red">
            <h3 align="center">
                User doesn't exist. Try again!
            </h3>
        </font>
    </c:if>
    <c:if test="${remindCheck==true}">
        <font color="green">
            <h3 align="center">
                Your password sent on your e-mail
            </h3>
        </font>
    </c:if>
</c:if>
<jsp:include page="footer.jsp"/>
</html>


