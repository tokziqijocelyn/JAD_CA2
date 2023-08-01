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
	<%@page import='java.util.*'%>
	<%@page import='classes.*'%>
	<%@page import='models.PromoDAO'%>

	<%
	
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("success")) {
			message = "Season successfully changed";
		} else if (code.equals("error")) {
			message = "An error occured";
		}
	}
	
	ArrayList<Promo> allPromos = new PromoDAO().getAllPromosWithId(0);
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

	<div class="m-5 container">
		<div class="row">
			<div class="col-md-4">
				<img src="<%=request.getContextPath() + "/images/editPromo.png"%>"
					alt="editPromo" class="img-fluid">

			</div>

			<div class="col-md-6">
				<h1>Edit Seasonal Promotion:</h1>
				<form action="/JAD_CA2/editPromo">
					<fieldset>
						<div class="form-group">
							<h4 class="m-2">Select season you want to change</h4>
							<select class="mt-5 form-select" name="promo">

								<%
								for (Promo promo : allPromos) {
								%>
								<option value="<%=promo.getPromotionId()%>"><%=promo.getPromoName()%></option>

								<%
								}
								%>
							</select>
						</div>
					</fieldset>

					<div class="m-4">
						New Season Name: <br> <input type="text" name="name" required
							value="awjkdh" class="form-control"> <br> New Discount Percentage: [eg 50 = 50%]<br>
 
						</div>
					</fieldset>

					<div class="m-4">
						New Season Name: <br> <input type="text" name="name" required class="form-control"> <br> New Discount Percentage: [eg 50 = 50%]<br>
						<input type="number" class="form-control" name="percentage" max=90 required>%
					</div>

					<input type="submit" class="btn btn-primary" value="Edit">
				</form>
			</div>
		</div>
	</div>


</body>
</html>