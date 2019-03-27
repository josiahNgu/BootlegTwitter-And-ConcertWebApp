<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Concert Detail</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
 -->
 <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
 
<script>
function updateCart(){
	var numOfTickets = $("#numOfTickets").val();
	$.post("UpdateShoppingCart", {numOfTickets:numOfTickets}, function(data,status) {
			  if(data == 1) {	
				  document.getElementById("status").innerHTML= "Item added to Cart";
				  $('#status').addClass('alert-success');
	  			}	
	});
}
$(document).ready(function(){
    $("#addCommentButton").click(function(){
        $("#commentForm").toggle();
    });
});

function newComment(){
	var comment = $("#comment").val();
	var rating = $("#rating").val();
	$.post("CustomerReview", {comment:comment , rating:rating}, function(data,status) {
		if(data==1){
			 document.getElementById("status").innerHTML= "Your comment was submitted succesfully! Thank you for your response.";
			 $('#status').addClass('alert-success');
			 // dont work with eclipse browser
			 location.reload();
			
		}
		if(data == 0){
			 document.getElementById("status").innerHTML= "Your comment was not submited. Comment length exceeded the max length of 255 characters. Please try again later.";
			 $('#status').addClass('alert-danger');
		}
	});
}

</script>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
</head>
<style type="text/css">
.commentSection{
background-color : whitesmoke;
}
.stars-container {
 position: relative;
 display: inline-block;
 color: transparent;
 font-size: 1.5rem;
}
.stars-container:before{
 position: absolute;
 top: 0;
 left: 0;
content: "★★★★★";
color:lightgray;
}
.stars-container:after {
 position: absolute;
 top: 0;
 left: 0;
 content: "★★★★★";
 color: gold;
 overflow: hidden;
}
.stars-0:after { width: 0%; }
.stars-10:after { width: 20%; }
.stars-20:after { width: 40%; }
.stars-30:after { width: 50%; }
.stars-40:after { width: 80%; }
.stars-50:after { width: 100%; }
</style>

<body>
	<div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="CustomerHomepage.jsp">Home</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link">${userBean.userName}</a></li>
				<li class="nav-item"><a class="nav-link" href="ViewOrders">Order</a></li>
				<li class="nav-item"><a class="nav-link"
					href="ViewAndCheckoutShoppingCart.jsp">Cart</a></li>
				<li class="nav-item"><form action=Logout method="post">
						<input type="submit" class="btn btn-secondary" value="logout" />
					</form></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<!-- alert whether if add to cart is successful -->
		<div class="alert" id="status"><h2></h2></div>
		<div style="padding-bottom: 3vh;"></div>
		<div class="row border border-danger rounded-top rounded-bottom"
			style="padding: 5vh;">
			<div class="d-flex col-xs-12 row ">
				<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
					<img class="img-fluid" src="${detailResult.thumbnail} "
					alt="${detailResult.movieName}">
				</div>
				<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 ">
					<h3>${detailResult.movieName}</h3>
					<p>The performance will begin at ${detailResult.startTime} and
						ends at ${detailResult.endTime} at the ${detailResult.venue}.
						There is only ${detailResult.seatLeft} seats left. Purchase a
						ticket at &dollar;${detailResult.ppSeat} per ticket now!</p>
					<div class="align-self-center">
							<select class="w-25" id="numOfTickets">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select> <input type="hidden" id="ticketPrice"
								value="${detailResult.ppSeat}">
							<input type="button" value="Add to Cart" onClick = "updateCart()" class="btn btn-danger w-50">
					</div>
				</div>
			</div>
		</div>
		<div class="d-flex justify-content-between" style="padding-top: 5vh">
			<a href="ConcertSearchResult.jsp"> <input type="button"
				class="btn btn-dark" value="Back" />
			</a>
			<a href="ViewAndCheckoutShoppingCart.jsp"> <input type="button"
				class="btn btn-dark" value="Checkout" />
			</a>
		</div>
	</div>
	<div id="commentSection"class="commentSection">
		<hr>
		<div class="col-lg-12  ">
		<div class="d-flex flex-column align-items-center justify-content-center">
			<span class="stars-container stars-${overallRating}0" style="padding-top:2vh;">★★★★★</span>
			<input type="button" class="btn btn-dark" value="Add comment" id="addCommentButton"/>
			
			<!-- Add New Comment Form -->
			<div id="commentForm" style="display:none">
			<div class="form-group">
					<label>Comment</label> <br>
					<textarea style="border: 2px solid;" rows="2"
						id="comment"></textarea>
				</div>
				<div class="form-group">
					<label>Rating</label> <select class="w-50 form-control "
						style="border: 2px solid ;" id="rating">
						<option value="5">5</option>
						<option value="4">4</option>
						<option value="3">3</option>
						<option value="2">2</option>
						<option value="1">1</option>
					</select>
				</div>
			<button type="submit" onClick="newComment()" class="btn btn-success"
					style="margin-top: 10px;">Submit</button>
			</div>
			
			
			</div>
			<div id="allComments">
			<c:forEach var="comments" items="${comments}">
				<div>
					<div class="col-lg-6">
						<div class="col">
							<span class="stars-container stars-${comments.rating}0" style="padding-top:2vh;">★★★★★</span>
							<h4>${comments.userName}</h4>
							<p>${comments.date}</p>
							<p>${comments.comment}</p>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>