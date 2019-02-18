<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<!-- import Bootstrap  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> 
<body style="background-color:#ffffff;">
	<div class="container col-sm-3">
		<div class="d-flex justify-content-center shadow p-3 mb-5 bg-white rounded ">
			<form name="loginForm" action=Login method="post">
				<div class="form-group ">
					User Name <br> <input class="form-control"
						placeholder="Username" type=text name=userName><br>
					Password <br> <input class="form-control"
						placeholder="Enter Password" type=password name=password><br>
				</div>
				<div class="form-group">
					<button type="submit" value ="Login" class="btn btn-block btn-info">Login</button>
				</div>
				<div>
					 New User?<a href="Register.jsp"> Register Here </a> <br>
				</div>
			</form>
		</div>

	</div>
</body>
</html>
