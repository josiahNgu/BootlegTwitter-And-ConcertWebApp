<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- import Bootstrap  -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
	integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
	crossorigin="anonymous"></script>
<body>
	<div class="container col-sm-3">
		<div class="d-flex justify-content-center align-items-center shadow p-3 mb-5 bg-white rounded ">
			<form action=Register>
				<div>
					User Name <br> <input class="form-control"
						placeholder="Enter a UserName" type=text name=userName required><br>
					Password <br> 
					<input class="form-control"
						placeholder="Enter Password" type=password name=password1 required><br>
					Confirm Password <br> 
					<input class="form-control"
						placeholder="Confirm Password" type=password name=password2 required><br>
				</div>
				<div>
					<button type="submit" value="Register"
						class="btn btn-block btn-info">Sign Up</button>

				</div>
				<div>
					Already have an account?<a href="Login.jsp"> Login </a> <br>
				</div>
			</form>
		</div>

	</div>
</body>
</html>