<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
<%@page import='classes.Database'%>
<%@page import='classes.Book'%>
<%@page import='models.BookDAO'%>
<%@page import='classes.Review'%>
<%@page import='models.ReviewDAO'%>
<%@page import='java.sql.*'%>
<%@page import='java.util.ArrayList'%>
<%@include file="../custNav.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

</head>
<body>
	<div>
		<%
		String code = request.getParameter("code");
		String message = "";
		Boolean has = false;
		if (code != null) {
			if (code.equals("success")) {
				message = "Review Added Successfully";
			} else if (code.equals("addedcart")){
				message = "Added to Cart!";
			} else if (code.equals("failedcart")){
				message = "Failed to addd to cart";
			}
		}
		String bookId = request.getParameter("book_id");
		int book_id = 0;

		try {
			book_id = Integer.parseInt(bookId);
		} catch (Exception e) {
			response.sendRedirect("/JAD_CA2/pages/home.jsp");
		}

		Book book = new BookDAO().getBookById(book_id);

		Connection conn = Database.connect();
		%>
		<%
		if (!message.equals("")) {
		%>
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<%=message%>
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
		<%
		}
		%>
		<%
		Integer rating = book.getRating();
		Float price = book.getPrice();
		Integer quantity = book.getQuantity();
		%>

		<div class="container m-5">
			<div class="row">
				<div class="col-md-5">
					<div class="d-flex justify-content-center align-items-center"
						style="height: 400px;">
						<img src="<%=request.getContextPath()+ book.getImage()%>" alt="book image" class="img-fluid"
							style="object-fit: cover; max-width: 100%; max-height: 100%;">
					</div>
				</div>

				<div class="col-md-7 mt-5 ml-1">
					<h3>
						Title:
						<%=book.getTitle()%></h3>
					<h5>
						Author:
						<%=book.getAuthor_name()%></h5>
					<h5>
						Publisher:
						<%=book.getPublisher_name()%></h5>
					<span class="badge bg-primary mt-2 mb-2">CATEGORY: <%=book.getCategory_name()%></span>
					<span class="badge bg-success">$<%=price%></span>
					<h5 class="card-subtitle mt-2 mb-2 text-muted">
						<%
						if (rating != 0) {
							for (int i = 0; i < rating; i++) {
						%>
						<i class="bi bi-star-fill text-warning"></i>
						<%
						}
						%>
						<%
						for (int i = rating; i < 10; i++) {
						%>
						<i class="bi bi-star text-muted"></i>
						<%
						}
						} else {
						%>
						No Rating :(
						<%
						}
						%>
					</h5>

					<h5 mt-5>Description:</h5>
					<p><%=book.getDescription()%>
					</p>

					<%
					if (!customer.isAuthenticated()) {
					%>
					<h1>Login To Add To Cart</h1>
					<%
					} else {
					%>

					<form action="/JAD_CA2/addCart?book_id=<%=bookId%>" method="POST"
						class="mt-5 pt-2">
						<%
						if (quantity > 0) {
						%>
						<select class="form-select" id="exampleSelect1" name="quantity"
							onchange="updateSubtotal(this.value, <%=price%>)" class="m-2">
							<%
							for (int i = 1; i <= quantity; i++) {
								if (i == 1) { // Check if it's the first option
							%>
							<option value="<%=i%>" selected><%=i%></option>
							<%
							} else {
							%>
							<option value="<%=i%>"><%=i%></option>
							<%
							}
							}
							%>
						</select>


						<button type="submit" class="btn btn-primary">Add to cart</button>
						<p class="text-secondary">
							Stocks left:
							<%=quantity%></p>
						<%
						} else {
						%>
						<button type="submit" class="btn btn-primary" disabled>Out
							of stock</button>
						<%
						}
						%>
						<input type="hidden" name="cust_id"
							value="<%=customer.getCust_id()%>">
					</form>


					<%
					}
					%>

				</div>
			</div>
			<h1>Reviews</h1>
			<%
			// Retrieve reviews for the book
			ArrayList<Review> reviewList = new ReviewDAO().findReviewByID(book_id);
					
			if (reviewList.size() == 0){
				%>
			<div>No Reviews :( </div>
			<%
			} else{
			for (int i = 0; i < reviewList.size(); i++) {
				Review review = reviewList.get(i);
				
			%>
			<div class="card mt-3">
				<div class="card-body">
					<h6 class="card-title"><%=review.getUsername()%></h6>
					<div class="rating">
						<%
						for (int j = 0; j < review.getRating(); j++) {
						%>
						<i class="bi bi-star-fill text-warning"></i>
						<%
						}
						for (int j = review.getRating(); j < 10; j++) {
						%>
						<i class="bi bi-star text-muted"></i>
						<%
						}
						%>
					</div>
					<p class="card-text"><%=review.getReview()%></p>
				</div>
			</div>
			<%
			}}
			%>
			<%
			if (customer.isAuthenticated()) {
			%>
			<form action="/JAD_CA2/addReview" method="POST">
				<div class="mb-3">
					<label for="reviewText" class="form-label">Review</label>
					<textarea class="form-control" id="review" name="review" rows="4"
						required></textarea>
				</div>
				<div class="mb-3">
					<label for="rating" class="form-label">Rating</label> <select
						class="form-select" id="rating" name="rating" required>
						<option value="1">1 Star</option>
						<option value="2">2 Stars</option>
						<option value="3">3 Stars</option>
						<option value="4">4 Stars</option>
						<option value="5">5 Stars</option>
						<option value="6">6 Stars</option>
						<option value="7">7 Stars</option>
						<option value="8">8 Stars</option>
						<option value="9">9 Stars</option>
						<option value="10">10 Stars</option>
					</select>
				</div>
				<input type="hidden" name="book_id" value="<%=bookId%>"> <input
					type="hidden" name="cust_id" value="<%=customer.getCust_id()%>">
				<button type="submit" class="btn btn-primary">Submit Review</button>
			</form>

			<%
			}
			%>
		</div>

	</div>
</body>
</html>