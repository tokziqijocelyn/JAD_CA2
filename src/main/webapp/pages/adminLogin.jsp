<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<body>
	<%@include file="../styles.jsp"%>
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
	<!-- Section: Design Block -->
	<section class="text-center">
		<!-- Background image -->
		<div class="p-5 bg-image"
			style="background-image: url('https://mdbootstrap.com/img/new/textures/full/171.jpg'); height: 300px;"></div>
		<!-- Background image -->


		<div class="card mx-4 mx-md-5 shadow-5-strong"
			style="margin-top: -100px; background: hsla(0, 0%, 100%, 0.8); backdrop-filter: blur(30px);">
			<div class="card-body py-5 px-md-5">
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

				<div class="row d-flex justify-content-center">
					<div class="col-lg-8">
						<h2 class="fw-bold mb-5">BokStore Admin</h2>
						<form method="POST" action='/JAD_CA2/loginAdmin'>
							<div class="form-outline mb-4">
								<input name="email" type="email" id="form3Example3"
									class="form-control" required autocomplete="off"/> <label class="form-label"
									for="form3Example3">Email address</label>
							</div>

							<!-- Password input -->
							<div class="form-outline mb-4">
								<input name="password" type="password" id="form3Example4"
									class="form-control" required autocomplete="off"/> <label class="form-label"
									for="form3Example4">Password</label>
							</div>
							<br />
							<p><%=errMsg%></p>
							<br />
							<!-- Submit button -->
							<button type="submit" class="btn btn-primary btn-block mb-4">
								Login</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Section: Design Block -->
</body>
</html>