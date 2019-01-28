<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transcation</title>
<link rel="stylesheet" href="`style/styles.css">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- import Bootstrap  -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!--import fontawesome -->
<script defer src="https://use.fontawesome.com/releases/v5.6.3/js/all.js" integrity="sha384-EIHISlAOj4zgYieurP0SdoiBYfGJKkgWedPHH4jCzpCXLmzVsw1ouK59MuUtP4a1" crossorigin="anonymous"></script>


</head>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="ViewOrders.jsp">Order</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container" style="padding: 5vh 0vh;">
		<div class="row">
			<div class="col-sm-8 col-lg-8">Summary</div>
			<div class="col-sm-4 col-lg-4 shadow-lg p-3 mb-5 rounded"
				style="background-color: #3d3d3d; color: white;">
				<h4>Card Details</h4>
				<form>
					<div class="form-group">
						<label for="firstName">First Name</label> <input type="text"
							class="form-control" placeholder="first name">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label> <input type="text"
							class="form-control" placeholder="last name">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label> <input type="text"
							class="form-control" placeholder="last name">
					</div>
					<div class="form-group">
						<label for="cardType">Card Type</label> <select
							class="form-control" id="cardType">
							<option>Visa</option>
							<option>MasterCard</option>
							<option>Discover</option>
						</select>
					</div>
					<div class="form-group" id="cardNumberField">
						<label for="cardNumber">Card Number</label> <input type="number"
							class="form-control" id="cardNumber">
					</div>
					<div class="form-group">
						<label for="expirationDate">Expiration date</label>
						<div class="row justify-content-center">
							<div class="col-sm-6">
								<select class="form-control" name="expiry-month"
									id="expiry-month">
									<option>Month</option>
									<option value="01">Jan (01)</option>
									<option value="02">Feb (02)</option>
									<option value="03">Mar (03)</option>
									<option value="04">Apr (04)</option>
									<option value="05">May (05)</option>
									<option value="06">June (06)</option>
									<option value="07">July (07)</option>
									<option value="08">Aug (08)</option>
									<option value="09">Sep (09)</option>
									<option value="10">Oct (10)</option>
									<option value="11">Nov (11)</option>
									<option value="12">Dec (12)</option>
								</select>
							</div>
							<div class="col-sm-6">
								<select class="form-control" name="expiry-year">
									<option>Year</option>
									<option value="19">2019</option>
									<option value="20">2020</option>
									<option value="21">2021</option>
									<option value="22">2022</option>
									<option value="23">2023</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="cvc">CVC Number</label> <input type="password"
							class="form-control" id="cvc" placeholder="XXX">
					</div>
					<div class="form-group">
						<label for="billingAddress">Billing Address</label> <input
							type="text" class="form-control" placeholder="billing address">
					</div>
					<div class="form-group">
						<label for="shippingAddress">Shipping Address</label> <input
							type="text" class="form-control" placeholder="shipping address">
					</div>
					<div class="form-group">
						<button type="submit" value="" class="btn btn-primary w-100">Comfirm</button>
					</div>
				</form>
			</div>
		</div>
		<i class="fa fa-arrow-left"></i><a
			href="ViewAndCheckoutShoppingCart.jsp"> Continue Shopping</a>
		</i>
	</div>
</body>
</html>