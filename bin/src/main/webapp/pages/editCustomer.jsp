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
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ page import="classes.*"%>
	<%@ page import="java.sql.*,java.util.*,java.text.SimpleDateFormat"%>
	<%@ page import="java.util.Date"%>
	<%
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("success")) {
			message = "Customer Updated Successfully";
		} else if (code.equals("error")){
			message = "An Error has occurred";
		}
	}

	Customer customer = (Customer) request.getAttribute("customer");
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
						<u><b>CUSTOMER DETAILS</b></u>
					</h2>
					<form action="/JAD_CA2/updateCustomer" method="POST"
						enctype="multipart/form-data">
						<input type="hidden" name="cust_id"
							value="<%=customer.getCust_id()%>" />
						<p>
							<b><u>Username: </u></b> <input type="text" class="form-control"
								id="username" value="<%=customer.getUsername()%>" required
								name="username" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Email:</u></b> <input type="email" class="form-control"
								id="email" value="<%=customer.getEmail()%>" required
								name="email" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Password:</u></b> <input type="text" class="form-control"
								id="password" value="<%=customer.getPassword()%>" required
								name="password" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Block:</u></b> <input type="text" class="form-control"
								id="block" value="<%=customer.getBlock()%>" required
								name="block" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Postal Code:</u></b> <input type="number"
								class="form-control" id="postal_code"
								value="<%=customer.getPostal_code()%>" required
								name="postal_code" autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Unit Number:</u></b> <input type="text"
								class="form-control" id="unit_no"
								value="<%=customer.getUnit_no()%>" required name="unit_no"
								autocomplete="off" disabled />
						</p>
						<p>
							<b><u>Street:</u></b> <input type="text" class="form-control"
								id="street" value="<%=customer.getStreet()%>" required
								name="street" autocomplete="off" disabled />
						</p>
						<input type="hidden" name="cust" value="0"> <input
							type="file" name="image" id="image" accept="image/*"
							class="custom-file-input" disabled> <input type="hidden"
							name="image_url" value="<%=customer.getImage_url()%>">
						<button id="editButton" type="submit" class="btn btn-primary">Edit</button>
					</form>
				</div>
			</div>

			<div class="col-md-7">
				<div class="col-6">
					<img src="<%=request.getContextPath() + customer.getImage_url()%>" alt="Customer Image"
						class="rounded img-fluid" id="preview">
				</div>

				<div class="project-info-bo mt-3">
					<h4 class="card-subtitle mb-2 text-muted">
						Registered Date:
						<fmt:parseDate var="parsedDate"
							value="<%=customer.getRegistered_date()%>"
							pattern="yyyy-MM-dd HH:mm:ss" />
						<fmt:formatDate value="${parsedDate}" pattern="dd MMMM yyyy" />
					</h4>
					<form action="/JAD_CA2/deleteCustomer" method="POST">
						<input type="hidden" name="cust" value="0"> <input
							type="hidden" name="cust_id" value="<%=customer.getCust_id()%>" />
						<button type="submit" type="button" class="btn btn-danger">Delete
							Customer</button>
					</form>
				</div>
			</div>
		</div>

		<div class="mt-5">
			<h2>
				<u><b>CUSTOMER ORDERS</b></u>
			</h2>
			<section class="container p-3">
				<div class="card mb-3 shadow">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr class="table-active text-uppercase">
									<th scope="col">Order Date</th>
									<th scope="col">Order Details</th>
									<th scope="col">Total Price</th>
								</tr>
							</thead>
							<tbody>
								<%
								ArrayList<ArrayList<Order>> orders = (ArrayList<ArrayList<Order>>) request.getAttribute("orders");

								Boolean has = false;
								for (ArrayList<Order> groupedOrders : orders) {
									has = true;
								%>
								<tr>
									<%
									String orderDateStr = groupedOrders.get(0).getOrder_date();
									SimpleDateFormat sdfSql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date orderDate = sdfSql.parse(orderDateStr);

									SimpleDateFormat sdfOutput = new SimpleDateFormat("dd MMMM yyyy hh:mm a", Locale.ENGLISH);
									String formattedOrderDate = sdfOutput.format(orderDate);
									%>
									<td><%=formattedOrderDate%></td>
									<td>
										<%
										for (Order order : groupedOrders) {
										%>
										<div class="d-flex align-items-center mb-2">
											<p class="mb-1"><%=order.getTitle() + " - ISBN: " + order.getISBN() + " - Quantity: " + order.getQuantity() %></p>
										</div> <%
 }
 %>
									</td>
									<td>$<%=String.format("%.2f", groupedOrders.get(0).getTotal_price())%></td>
								</tr>
								<%
								}
								if (!has) {
								%>
								<tr>
									<td colspan="3" class="text-center">
										<h1>No Orders :(</h1>
									</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</section>
		</div>
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
			document.getElementById("username").disabled = !isEditable;
			document.getElementById("password").disabled = !isEditable;
			document.getElementById("block").disabled = !isEditable;
			document.getElementById("postal_code").disabled = !isEditable;
			document.getElementById("unit_no").disabled = !isEditable;
			document.getElementById("street").disabled = !isEditable;
			document.getElementById("email").disabled = !isEditable;
			document.getElementById("image").disabled = !isEditable;

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
</html>