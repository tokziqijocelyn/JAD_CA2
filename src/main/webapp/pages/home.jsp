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
	<%@page import='classes.*'%>
	<%@page import='java.util.Timer'%>
	<%@page import='java.util.TimerTask'%>
	<%@page import='java.util.ArrayList'%>
	<%@page import='java.text.*'%>
	<%@include file="../custNav.jsp"%>

	<%	
	DecimalFormat decfor = new DecimalFormat("0.00");  
	String categoryId = request.getParameter("category");
	String publisherId = request.getParameter("publisher");
	int category_id;
	int publisher_id;

	// Check if the category parameter is provided
	if (categoryId != null && !categoryId.isEmpty()) {
		// Parse the category parameter value to an integer
		try {
			category_id = Integer.parseInt(categoryId);
		} catch (NumberFormatException e) {
			// Handle the case where the category parameter is not a valid integer
			category_id = 0; // Or set a default value, or throw an exception
		}
	} else {
		// Handle the case where the category parameter is not present or empty
		category_id = 0; // Or set a default value, or throw an exception
	}

	// Check if the publisher parameter is provided
	if (publisherId != null && !publisherId.isEmpty()) {
		// Parse the publisher parameter value to an integer
		try {
			publisher_id = Integer.parseInt(publisherId);
		} catch (NumberFormatException e) {
			// Handle the case where the publisher parameter is not a valid integer
			publisher_id = 0; // Or set a default value, or throw an exception
		}
	} else {
		// Handle the case where the publisher parameter is not present or empty
		publisher_id = 0; // Or set a default value, or throw an exception
	}

	ArrayList<Publisher> publishers = (ArrayList<Publisher>) request.getAttribute("publishers");
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
	ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");

	if (publishers == null || categories == null || books == null) {
		response.sendRedirect(request.getContextPath() + "/loadBooks");
		return;
	}
	%>

	<div class="container-fluid">
		<div class="row d-flex justify-content-evenly">
			<div id="filter" class="col-3 mt-5">
				<div class="btn-group" role="group" aria-label="Sort Buttons">
					<form method="POST" action="/JAD_CA2/loadBooks">
						<input type="hidden" name="sort" value="popularity" />
						<button type="submit" class="btn btn-primary mb-4 me-3">Sort
							by Popularity</button>
					</form>
					<form method="POST" action="/JAD_CA2/loadBooks">
						<input type="hidden" name="sort" value="rating" />
						<button type="submit" class="btn btn-primary mb-2">Sort
							by Rating</button>
					</form>
				</div>
				<h4>Sort By Categories</h4>
				<select class="form-select mb-2" id="categories">
					<option <%=category_id == 0 ? "selected" : ""%> value="0">All</option>
					<%
					for (Category category : categories) {
					%>
					<option
						<%=category_id != 0 && category_id == category.getCategory_id() ? "selected" : ""%>
						value=<%=category.getCategory_id()%>><%=category.getCategory_name()%></option>
					<%
					}
					%>
				</select>
				<h4>Sort By Publisher</h4>
				<select class="form-select mb-2" id="publisher">
					<option <%=publisher_id == 0 ? "selected" : ""%> value="0">All</option>
					<%
					for (Publisher publisher : publishers) {
					%>
					<option
						<%=publisher_id != 0 && publisher_id == publisher.getPublisher_id() ? "selected" : ""%>
						value=<%=publisher.getPublisher_id()%>><%=publisher.getPublisherName()%></option>
					<%
					}
					%>
				</select>
			</div>

			<div id="results" class="col-8 ml-5">
			
				<%@include file="promotions.jsp"%>
				<%
				Boolean hasBooks = false;
				for (Book book : books) {
					hasBooks = true;
					Integer book_id = book.getBook_id();
					String title = book.getTitle();
					Integer rating = book.getRating();
					Float price = book.getPrice();
					String category_name = book.getCategory_name();
					String image = book.getImage();
					String author = book.getAuthor_name();
					String publisher_name = book.getPublisher_name();
					Float discount_price = book.getDiscount_price();
				%>
				<div class="card border-primary mb-3 rounded-3 p-2">
					<div class="row no-gutters">
						<div class="col-md-3">
							<img src="<%=request.getContextPath() + image%>"
								alt="Default Image" class="img-fluid rounded-2 m-4"
								style="object-fit: cover; width: 80%; height: 80%;">
						</div>
						<div class="col-md-5">
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
								<div>
									<%
									if (discount_price == null) {
									%>
									<span class="badge bg-secondary">$<%=decfor.format(price)%></span>
									<%
									} else {
									%>
									<span class="badge bg-secondary discounted-price">$<%= decfor.format(price)%></span>
									<br /> <span class="badge bg-success">Discounted Price:
										$<%=decfor.format(discount_price)%></span>
									<%
									}
									%>
									<span class="badge bg-primary"><%=category_name%></span>
								</div>

								<div class="card-text">
									<p>
										<small class="text-muted">Written By: <span
											class="font-weight-bold"><%=author%></span></small> <br> <small
											class="text-muted">Published By: <span
											class="font-weight-bold"><%=publisher_name%></span></small>
									</p>

								</div>
								<form action="pages/book.jsp">
									<input type="hidden" name="book_id" value="<%=book_id%>" />
									<button type="submit" class="btn btn-primary">
										View Book <i class="bi bi-arrow-right-circle-fill"></i>
									</button>
								</form>

							</div>
						</div>
					</div>
				</div>
				<%
				}
				if (!hasBooks) {
				%>
				<div class="card mb-3">
					<div class="card-body">
						<h5 class="card-title">No Books :(</h5>
					</div>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>
</body>
<script>
    document.addEventListener('DOMContentLoaded', (event) => {
        const categoriesSelect = document.getElementById('categories');
        const publisherSelect = document.getElementById('publisher');

        categoriesSelect.addEventListener('change', (event) => {
			if (event.target.value == 0) {
				window.location.href = 'loadBooks';
			} else {
				window.location.href = 'loadBooks?category='+event.target.value;
   			}
        });
        publisherSelect.addEventListener('change', (event) => {
			if (event.target.value == 0) {
				window.location.href = 'loadBooks';
			} else {
				window.location.href = 'loadBooks?publisher='+event.target.value;
   			}
        });
    });
</script>
</html>
