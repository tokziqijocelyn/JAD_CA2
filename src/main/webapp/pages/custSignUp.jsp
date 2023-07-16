<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<body>
	<%@page import='classes.Database'%>
	<%@page import='java.sql.*'%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ page import="classes.Customer"%>
	<%@include file="../custNav.jsp"%>

	<%
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("duplicate")) {
			message = "Customer already exist!";
		} else if (code.equals("error")) {
			message = "Something went wrong";
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
			<div class="col-md-5">
				<div class="project-info-box mt-0">
					<h2>
						<u><b>CUSTOMER DETAILS</b></u>
					</h2>
					<form action="/JAD_CA2/addCustomer" method="POST"
						enctype="multipart/form-data">
						<p>
							<b><u>Username: </u></b> <input type="text" class="form-control"
								id="username" value="" required name="username"
								autocomplete="off" />
						</p>
						<p>
							<b><u>Email:</u></b> <input type="email" class="form-control"
								id="email" value="" required name="email" autocomplete="off" />
						</p>
						<p>
							<b><u>Password:</u></b> <input type="text" class="form-control"
								id="password" value="" required name="password"
								autocomplete="off" />
						</p>
						<p>
							<b><u>Block:</u></b> <input type="text" class="form-control"
								id="block" value="" required name="block" autocomplete="off" />
						</p>
						<p>
							<b><u>Postal Code:</u></b> <input type="number"
								class="form-control" id="postal_code" value="" required
								name="postal_code" autocomplete="off" />
						</p>
						<p>
							<b><u>Unit Number:</u></b> <input type="text"
								class="form-control" id="unit_no" value="" required
								name="unit_no" autocomplete="off" />
						</p>
						<p>
							<b><u>Street:</u></b> <input type="text" class="form-control"
								id="street" value="" required name="street" autocomplete="off" />
						</p>
						<input type="file" name="image" id="image" accept="image/*"
							class="custom-file-input">
						<button type="submit" class="btn btn-success">Create
							account</button>

					</form>
				</div>
			</div>

			<div class="col-md-7">
				<div class="col-6">
					<img src="<%=request.getContextPath()%>/images/customer.jpg"
						alt="Customer Image" class="rounded img-fluid" id="preview">
				</div>

			</div>
		</div>

	</div>
</body>

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