<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>View Shopping Cart</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders">Order</a></li>
				<li class="nav-item"><a class="nav-link"
					href="ViewAndCheckoutShoppingCart.jsp">Cart</a></li>
				<li class="nav-item"><form action=Logout method="post">
						<input type="submit" class="btn btn-secondary" value="logout" />
					</form></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div>
			<div style="padding: 5vh 0vh;">
				<h4>Shopping Cart</h4>
			</div>
			<div class="row">
				<c:forEach var="show" items="${shoppingList}" varStatus="loop">
					<div class="col-sm-4 shoppingCartPicture" style="padding: 5px;">
						<img alt="${show.movieName}" height="150" width="150"
							src="${show.thumbnail}">
					</div>
					<div class="col-sm-3">
						<h6>${show.movieName}</h6>
					</div>
					<div class="col-sm-2">
						<h6>${show.startTime}</h6>
					</div>
					<div class="col-sm-2">
						<h6>${show.numOfreqSeat}tickets</h6>
						<h6>at &#x24;${show.ppSeat}</h6>
					</div>
					<div class="col-sm-1">
						<form action="DeleteItemShoppingCart" method="post">
							<input type="hidden" name="index" value="${loop.index}">
							<button type="submit" class="close" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</form>
					</div>
				</c:forEach>

			</div>
			<div class="row justify-content-end" style="padding: 5vh 0vh;">
				<h6>Subtotal:&#36;${subtotal}</h6>
			</div>
			<div class="alert ${seatNumberAlertColor}">
			 <c:forEach var="error"
				items="${seatNumberError}" varStatus="loop">
				${error} <br>
			</c:forEach>
			</div>

			<div class="row justify-content-end" style="padding-top: 1vh">
				<form action=ShoppingCart method="post">
					<input type="submit" class="btn btn-dark" value="Checkout" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>