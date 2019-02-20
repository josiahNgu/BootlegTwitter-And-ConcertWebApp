<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> 
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<head>
<meta charset="UTF-8">
<title>View Orders</title>
</head>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div>
			<table class="table table-bordered table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Order Number</th>
						<th scope="col">Order Total</th>
						<th scope="col">Ordered date</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orderResult}">
						<tr>
							<td>${order.orderNumber}</td>
							<td>${order.orderTotal}</td>
							<td>${order.orderDate}</td>
							<td>
							<form action="ManageOrder">
								<button type="submit" class="btn btn-dark" value=${order.orderNumber} name="orderNumber">View</button>
							</form>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
