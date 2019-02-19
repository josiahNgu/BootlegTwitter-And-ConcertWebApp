<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<head>
<meta charset="UTF-8">
<title>Cancel Confirmation</title>
</head>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders.jsp">View Orders</a>
				<li class="nav-item"><a class="nav-link" href="ViewAndCheckoutShoppingCart.jsp">Cart</a></li>
				<li class="nav-item"><form action=Logout method="post"><input type="submit" class="btn btn-secondary" value="logout" /></form></li>			</ul>
		</nav>
	</div>

	<div class="container" style="padding: 5vh 0vh;">
		<h4 style="padding: 3vh 0px">Your order has been cancelled</h4>
		<h4 style="padding: 3vh 0px">Order Number: 107145</h4>
		<div class="row">
			<div class="col-sm-8">
				<div class="d-flex">
					<div class="col-sm-12">
						<h6>Rennie Harris Puremovement</h6>
						<br />
						<p>
							Ticket quantity: 4<br /> Total price: $48.00<br /> Venue name:
							Lied Center<br /> Showtime: 9:30am 25/1/2019<br />
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<h5>Refunded Amount: $48.00</h5>
				<br />
				<h6>This amount is refunded to your original purchase method.</h6>

			</div>
		</div>
	</div>

</body>
</html>
