<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

</head>
<body>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<body style="background-color: #F1E4E8;">
	<div class="container col-sm-3">
		<div
			class="d-flex justify-content-center shadow p-3 mb-5 bg-white rounded ">
			<form name="changePassword" action=ChangePassword method="post">
				<div class="form-group ">
					User Name <br> <input class="form-control"
						placeholder="Username" type=text name=userName required><br>
					Old Password <br> <input class="form-control"
						placeholder="Enter Old Password" type=password name=password required><br>

					New Password <br> <input class="form-control"
						placeholder="Enter New Password" type=password name=password1
						id=password required onblur="checkPass();return false;"><br>
					Confirm Password <br> <input class="form-control"
						placeholder="Confirm Password" type=password name=password2
						id=confirmPassword required onkeyup="checkPass();return false;"><br>
				</div>
				<div class="form-group">
								<span id="confirmMessage" class="confirmMessage"></span>
					<button type="submit" value="ChangePassword" class="btn btn-block btn-info" id="#changePassword">Confirm</button>
				</div>
				<div>
					Return to <a href="Login.jsp"> Login </a> <br>
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
				document.getElementById("#changePassword").disabled = false;
			} else {
				pass2.style.borderColor = badColor;
				message.style.color = badColor;
				message.innerHTML = "Passwords Do Not Match!"
				document.getElementById("#changePassword").disabled = true;


			}
		}  
	</script>
</body>
</body>
</html>