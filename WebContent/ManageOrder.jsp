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
<meta charset="UTF-8">
<title>Manage Orders</title>
</head>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="ViewOrders.jsp">View Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container" style="padding: 5vh 0vh;">
		<h4 style="padding: 3vh 0px">Order Number: 107145</h4>
		<div class="row">
			<div class="col-sm-8">
				<div class="d-flex">
					<div class="col-sm-4">
						<h6>Rennie Harris Puremovement</h6><br />
						<p>Ticket quantity: 4<br />
						Total price: $48.00<br />
						Venue name: Lied Center<br />
						Showtime: 9:30am 25/1/2019<br />
						Venue name: Lied Center <br />
						<div class="btn-toolbar row">
						<a class="btn btn-dark" href="ConcertDetailsSelection.jsp">View</a>
						&nbsp;
						<a class="btn btn-light" href="CancelOrder.jsp">Cancel</a>
						</div><br />						
					</div>
				</div>
			</div>
			<div class ="col-sm-8">
				<h5>Order Total: $48.00</h5><br />
				<h5>Ordered Date: 20/1/2019 3:57:44 PM</h5><br />
				
			</div>
		</div>
	</div>
	
</body>
</html>