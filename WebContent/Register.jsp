<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Registration</title>
</head>
<!-- import Bootstrap  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script> 

<body style="background-color: #F1E4E8">
	<div class=" container col-sm-3">
	<div class="d-flex justify-content-center align-items-center shadow p-3 mb-5 bg-white rounded ">
		<form action=Register method=post>
			<div>
				User Name <br> <input class="form-control" placeholder="Enter UserName" type=text name=userName required><br>
				Password <br> <input class="form-control" placeholder="Enter Password" type=password name=password1 id=password
				 required onkeyup="checkPass();return false;"><br> Confirm Password <br>

				<input class="form-control" placeholder="Confirm Password" type=password name=password2 id=confirmPassword 
				 required onkeyup="checkPass();return false;"><br>

				<span id="confirmMessage" class="confirmMessage"></span>

			</div>
			<div>
				<button type="submit" value="Register" class="btn btn-block btn-info" id="#submitForm" >Sign
					Up</button>

			</div>
			<div>
				Already have an account?<a href="Login.jsp"> Login </a> <br>
			</div>
		</form>
	</div>

	</div>
	<script type="text/javascript">
		function checkPass() {
			var pass1 = document.getElementById('password');
			var pass2 = document.getElementById('confirmPassword');
			var message = document.getElementById('confirmMessage');
			var badColor = "#cc0000";
			var whiteColor = "#ffffff";
			if (pass1.value == pass2.value) {
				message.innerHTML = "";
				pass2.style.backgroundColor = whiteColor ;
				document.getElementById("#submitForm").disabled = false;
			} else {
				pass2.style.borderColor = badColor;
				message.style.color = badColor;
				message.innerHTML = "Passwords Do Not Match!"
				document.getElementById("#submitForm").disabled = true;


			}
		}  
	</script>
</body>

</html>