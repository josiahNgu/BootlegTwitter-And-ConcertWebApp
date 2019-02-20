<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<head>
<meta charset="UTF-8">
<title>Cancel Order</title>
</head>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders">View Orders</a>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>

	<div class="container" style="padding: 5vh 0vh;">
		<h4 style="padding: 3vh 0px">Are you sure you want to cancel this
			order?</h4>
		<h4 style="padding: 3vh 0px">Order Number: ${cancelOrderInfo.orderNumber}</h4>
		<div class="row">
			<div class="col-sm-8">
				<div class="d-flex">
					<div class="col-sm-12">
						<h6>${cancelOrderInfo.movieName}</h6>
						<br />
						<p>
							Ticket quantity: ${cancelOrderInfo.quantity}<br /> Total price: $${cancelOrderInfo.itemTotalPrice}<br /> Venue name:
							${cancelOrderInfo.venueName}<br /> Showtime: ${cancelOrderInfo.showTime}<br />
						<div class="btn-toolbar row">
							<form action="CancelOrder">
								<button type="submit" class="btn btn-dark" value=${cancelOrderInfo.orderItemId} name="orderItemId">Cancel</button>
							</form>	
							&nbsp; 
							<a class="btn btn-dark"
								href="CustomerHomepage.jsp">Discard Cancellation</a>
						</div>
						<br />
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
