<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<body>
		<%@page import='java.util.*'%>
		<%@page import='classes.*'%>
	<%@include file="../adminNav.jsp"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%

		ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
	
		if (customers == null) {
			response.sendRedirect(request.getContextPath() + "/loadCustomers");
			return;
		}
	
	%>
	<div class="container mt-5">
		<h2>
			<u><b>View Customers</b></u>
		</h2>
		<div class="row">
			<div class="col-lg-12">
				<div class="row">
					<%
					for (Customer customer : customers) {
						Integer customer_id = customer.getCust_id();
						String username = customer.getUsername();
						String block = customer.getBlock();
						String postal_code = customer.getPostal_code();
						String unit_no = customer.getUnit_no();
						String street = customer.getStreet();
						String registered_date = customer.getRegistered_date();
						String image_url = customer.getImage_url();
					%>
					<div class="col-sm-6 col-lg-3 mb-4">
						<div class="candidate-list candidate-grid">
							<div class="candidate-list-image">
								<img class="img-fluid"
									src=<%=request.getContextPath() + image_url %> alt="">
							</div>
							<div class="candidate-list-details">
								<div class="candidate-list-info">
									<div class="candidate-list-title">
										<h5><%=username %></h5>
									</div>
									<div class="candidate-list-option">
										<ul class="list-unstyled">
											<li><i class="bi bi-calendar pr-1 me-1"></i></i><fmt:parseDate
											var="parsedDate" value="<%=registered_date%>"
											pattern="yyyy-MM-dd HH:mm:ss" /> <fmt:formatDate
											value="${parsedDate}" pattern="dd MMMM yyyy" /></li>
											<li><i class="bi bi-geo-alt-fill me-1"></i></i><%=block + " - " + street + " - " + postal_code + " - #" +  unit_no%></li>
										</ul>
									</div>
								</div>
								<div
									class="candidate-list-favourite-time d-flex justify-content-center align-items-center">
									<div class="d-flex justify-content-center align-items-center">
										<span class="me-2">View Profile</span>
										<form action="/JAD_CA2/loadCustomer">
											<input type="hidden" name="customer_id" value="<%=customer_id%>" />
											<button
												class="candidate-list-favourite order-2 d-flex align-items-center justify-content-center">
												<i class="bi bi-arrow-right"></i>
											</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>