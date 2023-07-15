<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<body>
	<%@page import='classes.*'%>
	<%@page import='java.util.*'%>
	<%@include file="../adminNav.jsp"%>
	<%
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("addSuccess")) {
			message = "Publisher Added Successfully";
		} else if (code.equals("editSuccess")) {
			message = "Publisher Updated Successfully";
		}
	}
	ArrayList<Publisher> publishers = (ArrayList<Publisher>) request.getAttribute("publishers");
	
	if (publishers == null) {
		response.sendRedirect(request.getContextPath() + "/loadPublisher");
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
	<div class="container mt-4">
		<div class="row">
			<div class="col-md-5">
				<h2>
					<u><b>PUBLISHER DETAILS</b></u>
				</h2>
				<nav class="mt-4">
					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<button class="nav-link active" id="nav-home-tab"
							data-bs-toggle="tab" data-bs-target="#nav-home" type="button"
							role="tab" aria-controls="nav-home" aria-selected="true">Add
							Publisher</button>
						<button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
							data-bs-target="#nav-profile" type="button" role="tab"
							aria-controls="nav-profile" aria-selected="false">Edit
							Publisher</button>
					</div>
				</nav>
				<div class="tab-content mt-4" id="nav-tabContent">
					<div class="tab-pane fade show active" id="nav-home"
						role="tabpanel" aria-labelledby="nav-home-tab" tabindex="0">
						<h4>Add Publisher</h4>
						<form action="/JAD_CA2/addPublisher" method="POST">
							<b><u>Publisher Name: </u></b> <input type="text"
								class="form-control my-4" required name="publisher"
								autocomplete="off" />
							<button type="submit" class="btn btn-primary">Add
								Publisher</button>
						</form>
					</div>
					<div class="tab-pane fade" id="nav-profile" role="tabpanel"
						aria-labelledby="nav-profile-tab" tabindex="0">
						<h4>Edit Publisher:</h4>
						<form action="/JAD_CA2/editPublisher" method="POST">
							<select class="form-select mb-2" name="publisher_id">
								<%
								for (Publisher publisher : publishers) {
								%>
								<option value=<%=publisher.getPublisher_id()%>><%=publisher.getPublisherName()%></option>
								<%
								}
								%>
							</select> <b><u>New Publisher Name: </u></b> <input type="text"
								class="form-control my-4" required name="publisher"
								autocomplete="off" />
							<button type="submit" class="btn btn-primary">Edit
								Publisher</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>