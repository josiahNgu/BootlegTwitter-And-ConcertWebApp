<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transcation Comfirmation</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- import Bootstrap  -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<!--import fontawesome -->
<script defer
	src="https://use.fontawesome.com/releases/v5.6.3/js/all.js"
	integrity="sha384-EIHISlAOj4zgYieurP0SdoiBYfGJKkgWedPHH4jCzpCXLmzVsw1ouK59MuUtP4a1"
	crossorigin="anonymous"></script>
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
<div class="alert alert-success container" role="alert">
  <h4 class="alert-heading">Success!</h4>

  <p class="mb-0">Your order for Rennie Harris Puremovement was at Lied Center placed succesfully.
  	 You order 1 ticket at $22.50 per ticket.</p>
</div>
</body>
</html>