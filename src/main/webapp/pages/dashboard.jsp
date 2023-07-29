<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<style>
/* Styles for the "cancelled" price (strikethrough) */
.discounted-price {
	text-decoration: line-through;
	color: red; /* You can adjust the color as needed */
	font-size: 18px; /* Adjust the font size as per your preference */
}
</style>
<body>
	<%@include file="../adminNav.jsp"%>
	<%@page import='classes.*'%>
	<%@page import='java.util.ArrayList'%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%
	String code = request.getParameter("code");
	String search = request.getParameter("search");
	String message = "";
	Boolean has = false;
	if (code != null) {
		if (code.equals("success")) {
			message = "Book Added Successfully";
		} else if (code.equals("deleteCust")) {
			message = "Customer Deleted Successfully";
		} else if (code.equals("deleteBook")) {
			message = "Book Deleted Successfully";
		}
	}

	ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");

	if (books == null) {
		response.sendRedirect(request.getContextPath() + "/loadDashboard");
		return;
	}
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
	<div class="container-fluid mt-5">
		<h2>
			<u><b>Dashboard</b></u>
		</h2>
		<div class="row">
			<div id="results" class="col-12">
				<%
				for (Book book : books) {
					has = true;
					Integer book_id = book.getBook_id();
					String title = book.getTitle();
					String description = book.getDescription();
					Integer rating = book.getRating();
					Float price = book.getPrice();
					String category_name = book.getCategory_name();
					String image = book.getImage();
					String author = book.getAuthor_name();
					String publisher_name = book.getPublisher_name();
					String ISBN = book.getISBN();
					Float discount_price = book.getDiscount_price();
				%>
				<div class="card mb-3">
					<div class="row no-gutters">
						<div class="col-md-2">
							<img src="<%=request.getContextPath() + image%>"
								alt="Default Image" class="img-fluid rounded"
								style="object-fit: cover; width: 100%; height: 100%;">
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<h5 class="card-title font-weight-bold"><%=title%></h5>
								<h6 class="card-subtitle mb-2 text-muted">
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
								</h6>
								<h6 class="card-subtitle mb-2 text-muted">
									$<%=price%></h6>
								<p class="card-text"><%=description%></p>
								<div>
									<%
									if (discount_price == null) {
									%>
									<span class="badge bg-secondary">$<%=price%></span>
									<%
									} else {
									%>
									<span class="badge bg-secondary discounted-price">$<%=price%></span>
									<br /> <span class="badge bg-success">Discounted Price:
										$<%=discount_price%></span>
									<%
									}
									%>
									<span class="badge bg-primary"><%=book.getCategory_name()%></span>
								</div>
								<p class="card-text font-weight-bold text-uppercase">
									Publisher:
									<%=publisher_name%></p>
								<p class="card-text font-weight-bold text-uppercase">
									ISBN:
									<%=ISBN%></p>
								<p class="card-text">
									<small class="text-muted">Author: <span
										class="font-weight-bold"><%=author%></span></small>
								</p>
								<form action="/JAD_CA2/getBook">
									<input type="hidden" name="book_id" value="<%=book_id%>" />
									<button type="submit" class="btn btn-primary">Edit
										Book</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<%
				}
				if (!has) {
				%>
				<h1>No Books :(</h1>
				<%
				}
				%>
			</div>
		</div>
	</div>
</body>
</html>