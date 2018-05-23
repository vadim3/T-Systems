<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PowerTrade Store</title>

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
                    <li><a href="/catalog">Shop page</a></li>
                    <li><a href="/cart">Cart</a></li>
                    <li><a href="/user/checkout">Checkout</a></li>
                    <li class="active"><a href="/contacts">Contact</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End mainmenu area -->

<div class="single_top">
    <div class="container">
        <div class="map">
            <iframe width="100%" src="https://www.google.com/maps/embed/v1/place?q=place_id:ChIJ6bSZrCcxlkYR79E9XOG27fI&key=AIzaSyCEyspGYzO1srn7ZAGmxMBfRQUaH4864_E" allowfullscreen>"></iframe>
        </div>
        <p>&nbsp;</p>
        <div class="col-md-9 contact_left">
            <h1>Get in Touch</h1>
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
                industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and
                scrambled it to make a type specimen book. It has survived not only five centuries</p>
            <form>
                <div class="column_2">
                    <input type="text" class="text" placeholder="" value="Name" onfocus="this.value = '';"
                           onblur="if (this.value == '') {this.value = 'Name';}">
                    <input type="text" class="text" placeholder="" value="Email" onfocus="this.value = '';"
                           onblur="if (this.value == '') {this.value = 'Email';}" style="margin-left:2.7%">
                    <input type="text" class="text" placeholder="" value="Subject" onfocus="this.value = '';"
                           onblur="if (this.value == '') {this.value = 'Subject';}" style="margin-left:2.7%">
                </div>
                <div class="column_3">
                    <textarea value="Message" placeholder="" onfocus="this.value = '';"
                              onblur="if (this.value == '') {this.value = 'Message';}">Message</textarea>
                </div>
                <div class="form-submit1">
                    <input type="submit" value="Send Message">
                </div>
                <div class="clearfix"></div>
            </form>
        </div>
        <div class="col-md-3 contact_right">
            <h2>Information</h2>
            <address class="address">
                <p>192358 St Petersburg <br>
                    13 line Vasylevskiy Ostrov</p>
                <dl>
                    <dt></dt>
                    <dd>For region:<span> 8(800)888-88-88</span></dd>
                    <dd>Main office:<span> 8(812)888-88-88</span></dd>
                    <dd>FAX: <span>8(812)888-88-88</span></dd>
                    <dd>E-mail:&nbsp; <a href="mailto@example.com">info@powertrade.com</a></dd>
                </dl>
            </address>
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


