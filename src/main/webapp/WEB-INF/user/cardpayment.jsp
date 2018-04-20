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
    <link rel="stylesheet" href="../assets/css/card.payment.style.css">
    <link rel="stylesheet" href="../assets/css/owl.carousel.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/responsive.css">

</head>

<jsp:include page="header.jsp"/>

<div class="promo-area" style="padding: 10px;">
    <div class="container">
        <div class="row"> </div>
    </div>
</div>

<div class="col-xs-12 col-md-4" >


    <!-- CREDIT CARD FORM STARTS HERE -->
    <div class="panel panel-default credit-card-box" style="padding: 10px;">
        <div class="panel-heading display-table" >
            <div class="row display-tr" >
                <h3 class="panel-title display-td" >Payment Details</h3>
                <div class="display-td" >
                    <img class="img-responsive pull-right" src="../assets/img/paymentcards.png">
                </div>
            </div>
        </div>
        <div class="panel-body">
            <form role="form" id="payment-form">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="cardNumber">CARD NUMBER</label>
                            <div class="input-group">
                                <input
                                        type="tel"
                                        class="form-control"
                                        name="cardNumber"
                                        placeholder="Valid Card Number"
                                        autocomplete="cc-number"
                                        required autofocus
                                />
                                <span class="input-group-addon"><i class="fa fa-credit-card"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-7 col-md-7">
                        <div class="form-group">
                            <label for="cardExpiry"><span class="hidden-xs">EXPIRATION</span><span class="visible-xs-inline">EXP</span> DATE</label>
                            <input
                                    type="tel"
                                    class="form-control"
                                    name="cardExpiry"
                                    placeholder="MM / YY"
                                    autocomplete="cc-exp"
                                    required
                            />
                        </div>
                    </div>
                    <div class="col-xs-5 col-md-5 pull-right">
                        <div class="form-group">
                            <label for="cardCVC">CV CODE</label>
                            <input
                                    type="tel"
                                    class="form-control"
                                    name="cardCVC"
                                    placeholder="CVC"
                                    autocomplete="cc-csc"
                                    required
                            />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="couponCode">COUPON CODE</label>
                            <input type="text" class="form-control" name="couponCode" />
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <button class="btn btn-success btn-lg btn-block" type="submit">Start Subscription</button>
                    </div>
                </div>
                <div class="row" style="display:none;">
                    <div class="col-xs-12">
                        <p class="payment-errors"></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- CREDIT CARD FORM ENDS HERE -->


</div>




<div class="promo-area">
    <div class="container">
        <div class="row"> </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>
</html>

