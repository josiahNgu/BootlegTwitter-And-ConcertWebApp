<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> 
<head>
<link rel="stylesheet" href="./style/styles.css">
<meta charset="UTF-8">
<title>View Shopping Cart</title>
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
	<div class="container">
		<div>
			<div style="padding: 5vh 0vh;">
				<h4>Shopping Cart</h4>
			</div>
			<div class="row">
				<div class="col-sm-4 shoppingCartPicture">
					<img alt="concertImage" height="150" width="150"
						src="./pics/rennieHarris.jpg">
				</div>
				<div class="col-sm-3">
					<h6>Rennie Harris Puremovement</h6>
				</div>
				<div class="col-sm-2">
					<h6>9:30 am</h6>
					<h6>08/11/19</h6>
				</div>
				<div class="col-sm-2">
					<h6>1 tickets</h6>
					<h6>&#x24;22.50</h6>
				</div>
				<div class="col-sm-1">
					<button type="button" class="close" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
			</div>
			<div class="row justify-content-end" style="padding: 5vh 0vh;">
				<h6>Subtotal: &#x24;22.50</h6>
			</div>
			<div class="row justify-content-end" style="padding-top: 1vh">
				<a href="CustomerTransaction.jsp"> <input type="button"
					class="btn btn-dark" value="Checkout" />
				</a>
			</div>
		</div>
	</div>
</body>
</html>