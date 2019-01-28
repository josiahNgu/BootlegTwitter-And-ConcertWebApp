<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<head>
<link rel="stylesheet" href="./style/styles.css">
<meta charset="UTF-8">
<title>View Shopping Cart</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">Order</a></li>
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