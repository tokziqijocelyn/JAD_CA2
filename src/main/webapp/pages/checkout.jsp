<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStore</title>
</head>
<%@ page import="classes.Database"%>
<%@ page import="java.sql.*"%>
<%@ page import="classes.Cart"%>
<%@ page import="classes.Checkout"%>
<%@ include file="../custNav.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.text.DecimalFormat"%>

<body>

	<%
	DecimalFormat decimalFormat = new DecimalFormat("#.00");

	int customer_id = customer.getCust_id();

	if (!customer.isAuthenticated()) {
		response.sendRedirect("/JAD_CA2/pages/custLogin.jsp");
		return;
	}

	try {

		float total_price = 0;
		ArrayList<Cart> cartItems = (ArrayList<Cart>) request.getAttribute("cartItems");
	%>

	<section class="h-100 h-custom">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="">
					<div class="card card-registration card-registration-2">
						<div class="card-body p-0">
							<div class="row g-0">
								<div class="">
									<div class="p-5">
										<div
											class="d-flex justify-content-between align-items-center mb-5">
											<h1 class="fw-bold mb-0 text-black">Checkout</h1>
										</div>
										<hr class="my-4">

										<%
										if (cartItems.size() == 0 || cartItems.isEmpty() || cartItems == null) {
											response.sendRedirect("http://localhost:8080/JAD_CA1/pages/cart.jsp");
										%>

										<h1>Nothing to checkout!</h1>
										<a class="btn btn-primary"
											href="http://localhost:8080/JAD_CA2/pages/home.jsp">Shop</a>

										<%
										} else {
										for (Cart cartItem : cartItems) {
											total_price = cartItem.getAmountSaved();
										%>
										<div class="row mb-4">
											<div class="col-md-2 col-lg-2 col-xl-2">
												<img src="<%=request.getContextPath() + cartItem.getImg()%>"
													class="img-fluid rounded-3" alt="Book image">
											</div>
											<div class="col-md-4">
												<h6 class="text-muted"><%=cartItem.getCategory()%></h6>
												<h6 class="text-black mb-0"><%=cartItem.getTitle()%></h6>
											</div>
											<div
												class="col-md-3 d-flex justify-content-end align-items-center">
												<div class="input-group">
													<h6 class="text-black mb-0"><%=cartItem.getQuantity()%>
														qty
													</h6>
												</div>
											</div>
											<div
												class="col-md-3 d-flex justify-content-end align-items-center">

												<%
												if (cartItem.getDiscountAmt() == 0) {
												%>
												<h6 class="mb-0">
													$ <span><%=decimalFormat.format(cartItem.getTotalAmt())%></span>
												</h6>
												<%
												} else {
												%>
												<h6 class="mb-0">
													$ <span><%=decimalFormat.format(cartItem.getDiscountAmt())%></span>
												</h6>
												<%
												}
												%>
											</div>
										</div>
										<hr class="my-4">
										<%
										}
										%>


										<a class="btn btn-primary"
											href="http://localhost:8080/JAD_CA2/pages/cart.jsp">Back
											to Cart</a>
									</div>


								</div>
								<div class="col-lg-7 bg-grey">
									<div class="p-5">
										<h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
										<hr class="my-4">

										<div class="d-flex justify-content-between mb-5">
											<h5 class="text-uppercase">Address</h5>
											<div>
												<h5>
													Street:
													<%=customer.getStreet()%>
												</h5>
												<h5>
													Unit No.:
													<%=customer.getUnit_no()%>
												</h5>
												<h5>
													Block:
													<%=customer.getBlock()%>
												</h5>
												<h5>
													Postal Code:
													<%=customer.getPostal_code()%>
												</h5>
											</div>

										</div>
										<hr class="my-4">

										<div class="d-flex justify-content-between mb-5">
											<h5 class="text-uppercase">Total price</h5>
											<h5>
												$ <span id="totalPrice"><%=Math.round(total_price * 100.0) / 100.0%></span>
											</h5>
										</div>

										<hr class="my-4">
										<div class="d-flex justify-content-between mb-5">
											<h5 class="text-uppercase">Amount Payable - after GST</h5>
											<h5>
												$ <span id="totalPrice"><%=Math.round((total_price * 1.08) * 100.0) / 100.0%></span>
											</h5>
										</div>
										<%
										session.setAttribute("checkout", cartItems);
										session.setAttribute("total", Math.round((total_price * 1.08) * 100.0) / 100.0);
										}
										%>
										<form action="/JAD_CA2/authorize_payment" method="POST">
											<input type="hidden" name="subtotal"
												value="<%=Math.round(total_price * 100.0) / 100.0%>" /> <input
												type="hidden" name="total"
												value="<%=Math.round((total_price * 1.08) * 100.0) / 100.0%>" />
											<input type="hidden" name="tax"
												value="<%=Math.round((total_price * 0.08) * 100.0) / 100.0%>" />
											<button type="submit" class="btn btn-primary">Proceed
												to payment</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<%
	} catch (NullPointerException e) {
	response.sendRedirect(request.getContextPath() + "/loadCheckout");
	} catch (Exception e) {
	response.sendRedirect(request.getContextPath() + "/loadBooks");
	}
	%>
</body>
</html>
