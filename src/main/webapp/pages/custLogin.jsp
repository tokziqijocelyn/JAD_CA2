<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
<style>
.divider:after, .divider:before {
	content: "";
	flex: 1;
	height: 1px;
	background: #eee;
}
</style>
</head>
<%@include file="../styles.jsp"%>
 <%@include file="../custNav.jsp"%>

<body>
	<%
	String error = request.getParameter("err");
	String code =  request.getParameter("code");
	String errMsg = "";
	String message = "";
	if (code != null){
		if (code.equals("success")) {
			message = "Success!";
		} else if (code.equals("created")){
			message = "Successfully created!";
		}

	}
	if (error != null) {
		if (error.equals("invalid")) {
			errMsg = "Invalid Credentials";
		} 
		
	}
	%>
	<section class="vh-100">
	
			
		<div class="container py-5 h-100">
			<div
				class="row d-flex align-items-center justify-content-center h-100">
				<div class="col-md-8 col-lg-7 col-xl-6">
					<img
						src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
						class="img-fluid" alt="Phone image">
				</div>
				<div class="col-md-7 col-lg-5 col-xl-5 offset-xl-1">
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
					<h2>Customer Login</h2>

					<form method="POST" action='/JAD_CA2/loginCustomer'>
						<!-- Email input -->
						<div class="form-outline mb-4">
							<label class="form-label" for="form1Example13">Email
								address</label> <input type="email" name="email"
								class="form-control form-control-lg" autocomplete="off" required />
						</div>

						<!-- Password input -->
						<div class="form-outline mb-4">
							<label class="form-label" for="form1Example23">Password</label> <input
								type="password" name="password"
								class="form-control form-control-lg" required />
						</div>

						<div class="divider d-flex align-items-center my-4"></div>
						<%=errMsg%>
						<br />
						<br />
						<!-- Submit button -->
						<button class="btn btn-primary btn-lg btn-block">Sign in</button>
						<a href="http://localhost:8080/JAD_CA2/pages/home.jsp" class="text-decoration-underline fw-bold"><p>Continue as guest</p></a>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>