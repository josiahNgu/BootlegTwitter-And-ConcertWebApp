<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
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
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
		<div class=" d-flex justify-content-center" style="padding: 5vh;">
			<form class="form-inline" action="VenueAndConcertSearchQuery"
				method="post">
				<input class="form-control" type="search" placeholder="Search"
					aria-label="Search" name="search" required />
				<div class="dropdown mr-1">
					<select class="custom-select">
						<c:forEach var="venue" items="${venue}">
							<option value=${venue}>${venue}</option>
						</c:forEach>
						<!-- <option>Lied Center</option> -->
					</select> 
				</div>
				<input class="form-control" type="date" placeholder="date" required>
				<input type="submit" class="btn btn-dark" value="Search" />
			</form>
		</div>


		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="container">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img class="d-block w-100" src="pics/drake.jpg" alt="First slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100" src="pics/jcole.jpg" alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block w-100" src="pics/higherBrother.jpg"
							alt="Third slide">
					</div>
				</div>
				<a class="carousel-control-prev" href="#carouselExampleIndicators"
					role="button" data-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"></span> <span
					class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
					role="button" data-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"></span> <span
					class="sr-only">Next</span>
				</a>
			</div>

		</div>
	</div>
</body>
</html>
