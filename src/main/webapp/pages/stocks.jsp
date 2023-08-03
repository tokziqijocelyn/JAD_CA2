<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<body>
	<%@include file="../adminNav.jsp"%>
	<%@page import='classes.*'%>
	<%@page import='java.util.ArrayList'%>
	<%
	String code = request.getParameter("code");
	String message = "";

	ArrayList<Book> no_stock = (ArrayList<Book>) request.getAttribute("no_stock");
	ArrayList<Book> low_stock = (ArrayList<Book>) request.getAttribute("low_stock");
	ArrayList<Book> high_stock = (ArrayList<Book>) request.getAttribute("high_stock");
	ArrayList<Book> custom_stock = (ArrayList<Book>) request.getAttribute("custom_stock");

	if (no_stock == null || low_stock == null || high_stock == null || custom_stock == null) {
		response.sendRedirect(request.getContextPath() + "/loadStock");
		return;
	}

	if (code != null) {
		if (code.equals("success")) {
			message = "Stock updated successfully";
		}
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
	<div class="container mt-4">
		<div class="row">
			<div class="col-md-12">
				<h2>
					<u><b>STOCK DETAILS</b></u>
				</h2>

				<nav class="mt-4">

					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<button class="nav-link active" id="nav-home-tab"
							data-bs-toggle="tab" data-bs-target="#nav-2" type="button"
							role="tab" aria-controls="nav-2" aria-selected="true">Custom
							Stock</button>
						<button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
							data-bs-target="#nav-home" type="button" role="tab"
							aria-controls="nav-home" aria-selected="false">No Stock</button>
						<button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
							data-bs-target="#nav-profile" type="button" role="tab"
							aria-controls="nav-profile" aria-selected="false">Low
							Stock</button>
						<button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
							data-bs-target="#nav-1" type="button" role="tab"
							aria-controls="nav-1" aria-selected="false">High Stock</button>

					</div>
				</nav>

				<div class="tab-content mt-4" id="nav-tabContent">


					<div class="tab-pane fade show active" id="nav-2" role="tabpanel"
						aria-labelledby="nav-2" tabindex="0">

						<h1>Find custom stock:</h1>

						<form action="loadStock" class="flex">
							<input class="input-group-text" type="number" name="search">
							<input class="btn btn-primary" type="submit" value="search">
						</form>

						<%
						Boolean exists = false;
						for (Book book : custom_stock) {
							exists = true;
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
							Integer stock = book.getQuantity();
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
											<br /> <span class="badge bg-success">Discounted
												Price: $<%=discount_price%></span>
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
										<h3>
											<small class="text-muted">Total Stock: <span
												class="font-weight-bold"><%=stock%></span></small>
										</h3>
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal"
											data-bs-target="#<%=ISBN + book_id.toString()%>">
											Update Book Quantity</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="<%=ISBN + book_id.toString()%>"
							tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel"><%=title%></h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form action="/JAD_CA2/updateStock" method="POST" class="p-5">
										<input type="hidden" name="book_id" value="<%=book_id%>" /> <b><u>New
												Quantity:</u></b> <input type="number" class="form-control"
											id="quantity" required name="quantity" autocomplete="off" />
										<button type="submit" type="button"
											class="btn btn-primary mt-3">Update Stock</button>
									</form>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
						<%
						}
						if (!exists) {
						%>
						<h1>No Books :(</h1>
						<%
						}
						%>


					</div>


					<div class="tab-pane fade " id="nav-home"
						role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">

						<h4>No Stock</h4>
						<%
						Boolean has = false;
						for (Book book : no_stock) {
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
							Integer stock = book.getQuantity();
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
											<br /> <span class="badge bg-success">Discounted
												Price: $<%=discount_price%></span>
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
										<h3>
											<small class="text-muted">Total Stock: <span
												class="font-weight-bold"><%=stock%></span></small>
										</h3>
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal"
											data-bs-target="#<%=ISBN + book_id.toString()%>">
											Update Book Quantity</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="<%=ISBN + book_id.toString()%>"
							tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel"><%=title%></h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form action="/JAD_CA2/updateStock" method="POST" class="p-5">
										<input type="hidden" name="book_id" value="<%=book_id%>" /> <b><u>New
												Quantity:</u></b> <input type="number" class="form-control"
											id="quantity" required name="quantity" autocomplete="off" />
										<button type="submit" type="button"
											class="btn btn-primary mt-3">Update Stock</button>
									</form>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
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


					<div class="tab-pane fade" id="nav-profile" role="tabpanel"
						aria-labelledby="nav-profile-tab" tabindex="0">
						<h4>Low Stock</h4>
						<%
						Boolean has_low = false;
						for (Book book : low_stock) {
							has_low = true;
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
							Integer stock = book.getQuantity();
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
											<br /> <span class="badge bg-success">Discounted
												Price: $<%=discount_price%></span>
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
										<h3>
											<small class="text-muted">Total Stock: <span
												class="font-weight-bold"><%=stock%></span></small>
										</h3>
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal"
											data-bs-target="#<%=ISBN + book_id.toString()%>">
											Update Book Quantity</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="<%=ISBN + book_id.toString()%>"
							tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel"><%=title%></h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form action="/JAD_CA2/updateStock" method="POST" class="p-5">
										<input type="hidden" name="book_id" value="<%=book_id%>" /> <b><u>New
												Quantity:</u></b> <input type="number" class="form-control"
											id="quantity" required name="quantity" autocomplete="off" />
										<button type="submit" type="button"
											class="btn btn-primary mt-3">Update Stock</button>
									</form>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
						<%
						}
						if (!has_low) {
						%>
						<h1>No Books :(</h1>
						<%
						}
						%>
					</div>


					<div class="tab-pane fade" id="nav-1" role="tabpanel"
						aria-labelledby="nav-1" tabindex="0">
						<h4>High Stock</h4>
						<%
						Boolean has_high = false;
						for (Book book : high_stock) {
							has_high = true;
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
							Integer stock = book.getQuantity();
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
											<br /> <span class="badge bg-success">Discounted
												Price: $<%=discount_price%></span>
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
										<h3>
											<small class="text-muted">Total Stock: <span
												class="font-weight-bold"><%=stock%></span></small>
										</h3>
										<button type="button" class="btn btn-primary"
											data-bs-toggle="modal"
											data-bs-target="#<%=ISBN + book_id.toString()%>">
											Update Book Quantity</button>
									</div>
								</div>
							</div>
						</div>
						<div class="modal fade" id="<%=ISBN + book_id.toString()%>"
							tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="exampleModalLabel"><%=title%></h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form action="/JAD_CA2/updateStock" method="POST" class="p-5">
										<input type="hidden" name="book_id" value="<%=book_id%>" /> <b><u>New
												Quantity:</u></b> <input type="number" class="form-control"
											id="quantity" required name="quantity" autocomplete="off" />
										<button type="submit" type="button"
											class="btn btn-primary mt-3">Update Stock</button>
									</form>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
									</div>
								</div>
							</div>
						</div>
						<%
						}
						if (!has_high) {
						%>
						<h1>No Books :(</h1>
						<%
						}
						%>
					</div>



				</div>
			</div>
		</div>
	</div>
</body>
</html>