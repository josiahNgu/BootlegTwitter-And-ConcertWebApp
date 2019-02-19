<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Customer Review</title>
<link rel="stylesheet" href="styles/styles.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders.jsp">Order</a></li>
				<li class="nav-item"><a class="nav-link"
					href="ViewAndCheckoutShoppingCart.jsp">Cart</a></li>
				<li class="nav-item"><form action=Logout method="post">
						<input type="submit" class="btn btn-secondary" value="logout" />
					</form></li>
			</ul>
		</nav>
		<div class="container col-lg-6">
			<form action=CustomerReview method="post">
				<div class="form-group">
				<label>Comment</label>
				<textarea class="form-control w-50" style="border:2px solid #BD3D3A;" rows="2" name="comment"></textarea>
				<button type="submit" class="btn btn-danger" style="margin-top:10px;">Submit</button>
				
				</div>
			
			
			</form>
		</div>
	</div>
</body>
</html>