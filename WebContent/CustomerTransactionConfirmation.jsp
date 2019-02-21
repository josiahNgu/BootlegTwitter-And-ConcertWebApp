<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transcation Comfirmation</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- import Bootstrap  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

</head>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders">Order</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewAndCheckoutShoppingCart.jsp">Cart</a></li>
				<li class="nav-item"><form action=Logout method="post"><input type="submit" class="btn btn-secondary" value="logout" /></form></li>
			</ul>
		</nav>
	</div>
	<div class="alert ${transactionAlertColor} container" role="alert">
		<h4 class="alert-heading">${transactionAlertHeader}</h4>

		<p class="mb-0">
			 ${orderNumber}<br/>
 			<c:forEach var="shoppingList" items="${confirmOrder}">
  				 ${shoppingList.movieName} at ${shoppingList.venue} for ${shoppingList.numOfreqSeat} seat at $${shoppingList.ppSeat} per ticket <br/>
   			</c:forEach> 
			${transactionAlertContent}
		</p>
	</div>
</body>
</html>