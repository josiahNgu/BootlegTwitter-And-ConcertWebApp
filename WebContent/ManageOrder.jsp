<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> 
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>


<head>
<meta charset="UTF-8">
<title>Manage Orders</title>
</head>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders.jsp">View Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container" style="padding: 5vh 0vh;">
		<h4 style="padding: 3vh 0px">Order Number: ${manageOrder[0].orderNumber}</h4>
		
		<div class="container">
		<div>
			<table class="table table-bordered table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Band Name</th>
						<th scope="col">Quantity</th>
						<th scope="col">Total price</th>
						<th scope="col">Venue</th>
						<th scope="col">Showtime</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${manageOrder}">
						<tr>
							<td>${order.movieName}</td>
							<td>${order.quantity}</td>
							<td>${order.itemTotalPrice}</td>
							<td>${order.venueName}</td>
							<td>${order.showTime}</td>
							<td>
							<form action="ManageOrder">
								<button type="submit" class="btn btn-dark" value=${order.orderNumber} name="orderNumber">View</button>
							</form>	
							<br />
							<form action="CancelOrder">
								<button type="submit" class="btn btn-dark" value=${order.orderItemId} name="orderItemId">Cancel</button>
							</form>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
		
		<div class="row">
			<div class ="col-sm-8">
				<h5>Order Total: $${manageOrder[0].orderTotal}</h5><br />
				<h5>Ordered Date: ${manageOrder[0].orderDate}</h5><br />
				
			</div>
		</div>
	</div>
	
</body>
</html>
