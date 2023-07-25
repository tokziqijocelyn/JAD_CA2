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
<script
	src="https://www.paypal.com/sdk/js?client-id=AWinJf8GPVRc95mbJYzyGOkHw2dOScgpucRq2e05nxk6mc4JUKCAn3y0WUs9AdFhs1-sR4sG-ukDPvHF&currency=SGD"></script>

<body>

	<%
	int customer_id = customer.getCust_id();

	if (!customer.isAuthenticated()) {
		response.sendRedirect("/JAD_CA2/pages/custLogin.jsp");
		return;
	}

	try {

		float total_price = 0;
		ArrayList<Cart> cartItems = (ArrayList<Cart>) session.getAttribute("cartItems");
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
											total_price = cartItem.getTotal_price();
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
												<h6 class="mb-0">
													$ <span><%=cartItem.getTotalAmt()%></span>
												</h6>
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
												$ <span id="totalPrice"><%=total_price%></span>
											</h5>
										</div>
										<%
										session.setAttribute("checkout", cartItems);
										}
										%>

										<div id="paypal-button-container"></div>


										<form action="/JAD_CA2/addOrder" method="post">
											<input type="hidden" name="cust_id" value="<%=customer_id%>">
											<input type="hidden" name="checkout" value="checkout">
											<input type="submit" value="Pay" class="btn btn-primary">

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
	<script>
     paypal.Buttons({
       // Sets up the transaction when a payment button is clicked
       createOrder: (data, actions) => {
         return actions.order.create({
           purchase_units: [{
             amount: {
               value: <%=Math.round((total_price * 1.07) * 100.0) / 100.0%> 
             }
           }]
         });
       },
       // Finalize the transaction after payer approval
       onApprove: (data, actions) => {
         return actions.order.capture().then(function(orderData) {
           // Successful capture! For dev/demo purposes:
           const transaction = orderData.purchase_units[0].payments.captures[0];
           window.location.replace("PurchaseSuccessServlet");
         });
       }
     }).render('#paypal-button-container');
   </script>
	<%
	} catch (NullPointerException e) {
	response.sendRedirect(request.getContextPath() + "/loadCart?cust_id=" + customer_id);
	} catch (Exception e) {
	response.sendRedirect(request.getContextPath() + "/loadBooks");
	}
	%>
</body>
</html>
