<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<style>
body {
	background: #f5f5f5;
	margin-top: 20px;
}
</style>
<body>
	<%@page import='classes.*'%>
	<%@page import='java.util.ArrayList'%>
	<%@page import='java.sql.*'%>
	<%@include file="../adminNav.jsp"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@page import="classes.Book"%>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<%

	%>
	<%
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("success")) {
			message = "Book Updated Successfully";
		} else if (code.equals("error")) {
			message = "An error has occured";
		}
	}
	String filter = request.getParameter("filter");
	if (filter == null) {
		filter = "today";
	}

	ArrayList<String> x_axis = (ArrayList<String>) request.getAttribute("x_axis");
	ArrayList<Integer> y_axis = (ArrayList<Integer>) request.getAttribute("y_axis");
	ArrayList<Publisher> publishers = (ArrayList<Publisher>) request.getAttribute("publishers");
	ArrayList<Category> categories = (ArrayList<Category>) request.getAttribute("categories");
	ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
	Book book = (Book) request.getAttribute("book");
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
	<div class="container mt-4">
		<div class="row">

			<div class="col-md-5">
				<div class="project-info-box mt-0">
					<h2>
						<u><b>BOOK DETAILS</b></u>
					</h2>
					<form action="/JAD_CA2/updateBook" method="POST"
						enctype="multipart/form-data">
						<input type="hidden" name="book_id" value="<%=book.getBook_id()%>" />
						<p>
							<b><u>Title:</u></b> <input type="text" class="form-control"
								id="title" value="<%=book.getTitle()%>" required name="title"
								autocomplete="off" disabled />
						</p>
						<p>
							<b><u>ISBN:</u></b> <input type="text" class="form-control"
								id="ISBN" value="<%=book.getISBN()%>" required name="ISBN"
								autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Price:</u></b> <input type="number" class="form-control"
								id="price" value="<%=book.getPrice()%>" step=".01" required
								name="price" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Quantity:</u></b> <input type="number" class="form-control"
								id="quantity" value="<%=book.getQuantity()%>" required
								name="quantity" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Description:</u></b>
							<textarea class="form-control" id="description" required
								name="description" autocomplete="off" disabled><%=book.getDescription()%></textarea>
						</p>
						<p>
							<b><u>Category:</u></b> <select class="form-select mb-2"
								id="category" disabled name="category">
								<%
								for (Category category : categories) {
								%>
								<option
									<%=book.getCategory_id() != null && book.getCategory_id() == category.getCategory_id() ? "selected" : ""%>
									value=<%=category.getCategory_id()%>><%=category.getCategory_name()%></option>
								<%
								}
								%>
							</select>
						</p>
						<p>
							<b><u>Author:</u></b> <select class="form-select mb-2"
								id="author" disabled name="author">
								<%
								for (Author author : authors) {
								%>
								<option
									<%=book.getAuthor_id() != null && book.getAuthor_id() == author.getAuthor_id() ? "selected" : ""%>
									value=<%=author.getAuthor_id()%>><%=author.getAuthor_name()%></option>
								<%
								}
								%>
							</select>
						</p>
						<p>
							<b><u>Publisher:</u></b> <select class="form-select mb-2"
								id="publisher" disabled name="publisher">
								<%
								for (Publisher publisher : publishers) {
								%>
								<option
									<%=book.getPublisher_id() != null && book.getPublisher_id() == publisher.getPublisher_id() ? "selected" : ""%>
									value=<%=publisher.getPublisher_id()%>><%=publisher.getPublisherName()%></option>
								<%
								}
								%>
							</select>
						</p>
						<input type="file" name="image" id="image" accept="image/*"
							class="custom-file-input" disabled>
						<button id="editButton" type="submit" class="btn btn-primary">Edit</button>
					</form>
				</div>
			</div>

			<div class="col-md-7">
				<img src=<%=request.getContextPath() + book.getImage()%>
					alt="Book Image" class="rounded img-fluid" id="preview">
				<div class="m-3">
					<h6>Only supports jpg, png and jpeg [limit:1mb]</h6>

				</div>

				<div class="project-info-bo mt-3">
					<h4>Rating:</h4>
					<h4 class="card-subtitle mb-2 text-muted">
						<%
						if (book.getRating() != 0) {
							for (int i = 0; i < book.getRating(); i++) {
						%>
						<i class="bi bi-star-fill text-warning"></i>
						<%
						}
						%>
						<%
						for (int i = book.getRating(); i < 10; i++) {
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
					</h4>
					<h4 class="card-subtitle mb-2 text-muted">
						Creation Date:
						<fmt:parseDate var="parsedDate"
							value="<%=book.getCreation_date()%>"
							pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${parsedDate}" pattern="dd MMMM yyyy" />
					</h4>
					<form action="/JAD_CA2/deleteBook" method="POST">
						<input type="hidden" name="book_id" value="<%=book.getBook_id()%>" />
						<button type="submit" type="button" class="btn btn-danger">Delete
							Book</button>
					</form>
				</div>
			</div>

		</div>
	</div>

	<select class="form-select mb-2" id="filter">
		<option <%=filter.equals("today") ? "selected" : ""%> value="today">Today</option>
		<option <%=filter.equals("week") ? "selected" : ""%> value="week">Week</option>
		<option <%=filter.equals("month") ? "selected" : ""%> value="month">Month</option>
	</select>

	<div class="chart-container"
		style="position: relative; height: 50vh; width: 80%; margin: 0 auto;">
		<canvas id="myChart"></canvas>
	</div>
</body>
<script>
	// JavaScript to handle the button click
	var isEditable = false;

	document.getElementById("editButton").addEventListener("click", function() {
		// Update the state

		if (!isEditable) {
			event.preventDefault();
			isEditable = !isEditable;
			document.getElementById("title").disabled = !isEditable;
			document.getElementById("price").disabled = !isEditable;
			document.getElementById("description").disabled = !isEditable;
			document.getElementById("category").disabled = !isEditable;
			document.getElementById("author").disabled = !isEditable;
			document.getElementById("ISBN").disabled = !isEditable;
			document.getElementById("quantity").disabled = !isEditable;
			document.getElementById("image").disabled = !isEditable;
			document.getElementById("publisher").disabled = !isEditable;

			this.textContent = isEditable ? "Save" : "Edit";
		}

	});
</script>
<script>
	document.getElementById("image").addEventListener("change", function(e) {
		var file = e.target.files[0];
		if (file) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById("preview").src = e.target.result;
			};
			reader.readAsDataURL(file);
		}
	});
</script>
<script>	
console.log([<%for (int value : y_axis) {
	out.print(value + ", ");
}%>])
		const ctx = document.getElementById('myChart');
		new Chart(
			ctx,
			{
				type: 'line',
				data: {
					labels: [<%="\"" + String.join("\", \"", x_axis) + "\""%>],
					datasets: [{
						label: 'Total Books Sold',
						data: [<%for (int value : y_axis) {
	out.print(value + ", ");
}%>],
						borderWidth: 1
					}]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
	</script>

<script>
		document.addEventListener('DOMContentLoaded', (event) => {
			const filterSelect = document.getElementById('filter');

			filterSelect.addEventListener('change', (event) => {
				if (event.target.value == "today") {
					window.location.href = 'getBook?book_id=' + <%=book.getBook_id()%>;
				} else {
					window.location.href = 'getBook?book_id=' + <%=book.getBook_id()%> + "&filter=" + event.target.value;
				}
			});
		});
	</script>