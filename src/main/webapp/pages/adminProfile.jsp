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
	<%@include file="../adminNav.jsp"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	<%
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("success")) {
			message = "Account Updated Successfully";
		} else if (code.equals("error")){
			message = "Account was not updated";
		}
	}
	
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
						<u><b>Profile</b></u>
						ADMIN
					</h2>
					<form action="/JAD_CA2/updateAdmin" method="POST"
						enctype="multipart/form-data">
						<input type="hidden" name="admin_id" value="<%=admin.getAdmin_id()%>" />
						<p>
							<b><u>Username:</u></b> <input type="text" class="form-control"
								id="username" value="<%=admin.getUsername()%>" required name="username"
								autocomplete="off" disabled />
						</p>
						
						<p>
							<b><u>Email:</u></b> <input type="email" class="form-control"
								value="<%=admin.getEmail()%>"
								autocomplete="off" disabled id="email" required name="email"/>
						</p>
												
						<p>
							<b><u>Password:</u></b> <input type="text" class="form-control"
								value="<%=admin.getPwd()%>"
								autocomplete="off" disabled required id="pwd" name="pwd"/>
						</p>
						<input type="hidden" name="admin_id" value="<%=admin.getAdmin_id() %>">
						<input type="file" name="image" id="image" accept="image/*"
						class="custom-file-input" disabled>

						<button id="editButton" type="submit" class="btn btn-primary">Edit</button>
						<input type="hidden" name="imagePath" value="<%=admin.getImage()%>">
					</form>
				</div>
			</div>

			<div class="col-md-7">
				<img id="preview" src="<%=request.getContextPath() +  admin.getImage()%>" alt="Image Preview"  style="max-width: 500px; max-height: 500px; margin-left:100px">
				<div class="m-3">
					<h6>Only supports jpg, png and jpeg [limit:1mb]</h6>
					
					
				</div>
					</form>

				<div class="project-info-bo mt-3">
					<form action="/JAD_CA2/deleteAdmin" method="POST">
						<input type="hidden" name="admin_id" value="<%= admin.getAdmin_id() %>"/>
						<button type="submit" type="button" class="btn btn-danger">Delete account</button>
					</form>
				</div>
			</div>

		</div>
	</div>
</body>
<%
// conn.close();
%>
<script>
	// JavaScript to handle the button click
	var isEditable = false;

	document.getElementById("editButton").addEventListener("click", function() {
		// Update the state

		if (!isEditable) {
			event.preventDefault();
			isEditable = !isEditable;
			document.getElementById("username").disabled = !isEditable;
			document.getElementById("email").disabled = !isEditable;
			document.getElementById("image").disabled = !isEditable;
			document.getElementById("pwd").disabled = !isEditable;

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

</body>
</html>