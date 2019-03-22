<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ConfirmOrder</title>
<!-- import Bootstrap  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
function confirmFunction(){
	var firstName = $("#firstName").val();
	var lastName = $("#lastName").val();
	var cardType = $("#cardType").val();
	var cardNumber = $("#cardNumber").val();
	var expiryMonth = $("#expiryMonth").val();
	var expiryYear = $("#expiryYear").val();
	var cvc = $("#cvc").val();
	var billingAddress = $("#billingAddress").val();
	var shippingAddress = $("#shippingAddress").val();
	var userId =  $("#userId").val();
	var subtotal =$("#subtotal").val();

	$.post("../Bank/BankServlet", {firstName:firstName,lastName:lastName,cardType:cardType,cardNumber:cardNumber,
		expiryMonth:expiryMonth,expiryYear:expiryYear,cvc:cvc,billingAddress:billingAddress,shippingAddress:shippingAddress,
		userId:userId,subtotal:subtotal}, function(data,status) {
 		if(data == 1){
 			 $('#alert').addClass('alert-success');
 			 $('#alert').removeClass('alert-danger');
 			document.getElementById("alert").innerHTML= "Your order was placed succesfully!";
 			$("#printButton").removeClass('d-none');
 			$("#body").addClass('d-none');
 			placeOrder(billingAddress,cardNumber);
		} 
 		if(data == 0){
 			 $('#alert').addClass('alert-danger');
 			document.getElementById("alert").innerHTML= "Transaction was not successful! Please try again later.";
 		} 
	});
	
}

function placeOrder(billingAddress,cardNumber){
	$.post("PlaceOrder", {billingAddress:billingAddress,cardNumber:cardNumber});
}

function printFunction()
{
    var mywindow = window.open('', 'PRINT');

    mywindow.document.write('<html><head><title>' + document.title  + '</title>');
    mywindow.document.write('</head><body >');
    mywindow.document.write('<h1>' + document.title  + '</h1>');
    mywindow.document.write(document.getElementById("shoppingList").innerHTML);
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10*/

    mywindow.print();
    mywindow.close();

    return true;
}

</script>
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
	
	<!-- Display whether the transaction is successful -->
	<div class="container">
		<div id="alert" class="" role="alert">
			<!-- Print Button -->
		</div>
		<button id="printButton" class="d-none btn btn-info" onClick="printFunction()">Print</button>
	</div>
	
	<input type="hidden" id="shoppingListSession" value="${shoppingList}">
	
	
	<div class="container" id="body" style="padding: 5vh 0vh;">
		<h4 style="padding: 3vh 0px">Summary</h4>
		<div class="row">
			<div id="shoppingList" class="col-sm-8">
					<c:forEach var="shoppingList" items="${shoppingList}">
						<div class="row"  style="padding: 3vh;">
							<div class="col-sm-4">
								<h6>${shoppingList.movieName}</h6>
							</div>
							<div class="col-sm-4">
								<h6>${shoppingList.venue}</h6>
							</div>
							<div class="col-sm-4">
								<h6>${shoppingList.numOfreqSeat } tickets</h6>
								<h6>&#x24;${shoppingList.ppSeat }</h6>
							</div>
						</div>
					</c:forEach>
			<div class="col-sm-10 d-flex justify-content-end" style="padding:5vh;">
				<h6>Subtotal: &#x24;${subtotal}</h6>
			</div>
			</div>


			<div class="col-sm-4 col-lg-4 shadow-lg p-3 mb-5 rounded"
				style="background-color: #3d3d3d; color: white;">
				<h4>Card Details</h4>
					<div class="form-group">
						<label for="firstName">First Name</label> 
						<input type="text" id ="firstName" class="form-control" placeholder="first name">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label> <input type="text"
							id="lastName" class="form-control" placeholder="last name">
					</div>
						<div class="form-group">
							<label for="cardType">Card Type</label> <select
								class="form-control" id="cardType">
								<option>Visa</option>
								<option>MasterCard</option>
								<option>Discover</option>
							</select>
						</div>
						<div class="form-group" id="cardNumberField">
							<label for="cardNumber">Card Number</label> 
							<input type="number" class="form-control" id="cardNumber" required>
						</div>
						<div class="form-group">
							<label for="expirationDate">Expiration date</label>
							<div class="row justify-content-center">
								<div class="col-sm-6">
									<select class="form-control" id="expiryMonth"
										id="expiry-month" required>
										<option value="01">Jan (01)</option>
										<option value="02">Feb (02)</option>
										<option value="03">Mar (03)</option>
										<option value="04">Apr (04)</option>
										<option value="05">May (05)</option>
										<option value="06">June (06)</option>
										<option value="07">July (07)</option>
										<option value="08">Aug (08)</option>
										<option value="09">Sep (09)</option>
										<option value="10">Oct (10)</option>
										<option value="11">Nov (11)</option>
										<option value="12">Dec (12)</option>
									</select>
								</div>
								<div class="col-sm-6">
									<select class="form-control" id="expiryYear" required>
										<option value="2019">2019</option>
										<option value="2020">2020</option>
										<option value="2021">2021</option>
										<option value="2022">2022</option>
										<option value="2023">2023</option>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="cvc">CVC Number</label> <input type="password"
								class="form-control" id="cvc" placeholder="XXX" required>
						</div>
						<div class="form-group">
							<label for="billingAddress">Billing Address</label> <input
								type="text" class="form-control" id ="billingAddress" placeholder="Billing Address" required>
						</div>
						<div class="form-group">
							<label for="shippingAddress">Shipping Address</label> <input
								type="text" class="form-control" id="shippingAddress" placeholder="shipping address" required>
						</div>
						<input type="hidden" id="userId" value="${userBean.userId}" />
						<input type="hidden" id="subtotal" value="${subtotal}" >
						<input type="hidden" id="shoppingSession" value="${shoppingList}" >												
						<button onClick="confirmFunction()" type="submit" class="btn btn-primary w-100" >Confirm</button>
			</div>
		</div>
		<div class="row col-sm-8">
			<div class="col-sm-8">
				<i class="fa fa-arrow-left"></i><a href="ViewAndCheckoutShoppingCart.jsp"> Continue Shopping</a>
			</div>

		</div>
	</div>
</body>
</html>