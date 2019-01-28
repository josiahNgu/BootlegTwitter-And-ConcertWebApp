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
<title>Concert Search Result</title>
</head>
<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">Order</a></li>
				<li class="nav-item"><a class="nav-link" href="Login.jsp">Logout</a></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<div>
			<table class="table table-bordered table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Name</th>
						<th scope="col">Venue</th>
						<th scope="col">Start Time</th>
						<th scope="col">Seat Remaining</th>
						<th scope="col">Price</th>
						<th scope="col">Opening Acts</th>
						<th scope="col">Thumbnail</th>
						<th scope="col">More Info</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Rennie Harris Puremovement</td>
						<td>Lied Center</td>
						<td>9:30 am</td>
						<td>30</td>
						<td>$12</td>
						<td>No</td>
						<td><img src="pics/rennieHarris.jpg"
							alt="Rennie Harris Puremovement" height="150" width="150"></td>
						<td><a href="ConcertDetailsSelection.jsp"> <input
								type="button" class="btn btn-info" value="Details" />
						</a></td>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>