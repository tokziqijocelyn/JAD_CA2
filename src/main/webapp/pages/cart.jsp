<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BookStore</title>
</head>
<body>
	<%@ page import="classes.Database"%>
	<%@ page import="classes.Cart"%>
	<%@ page import="java.sql.*"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ include file="../custNav.jsp"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@ page import="java.text.DecimalFormat"%>

	<%
	DecimalFormat decimalFormat = new DecimalFormat("#.00");
	ArrayList<Cart> cartItems = new ArrayList<Cart>();
	float totalPrice = 0;
	boolean has = false;
	String code = request.getParameter("code");
	String message = "";
	if (code != null) {
		if (code.equals("success")) {
			message = "Added to cart!";
		} else if (code.equals("err")) {
			message = "Something went wrong";
		} else if (code.equals("successCheckedout")) {
			message = "Successfully checked out!";
		}
	}

	int cust_id = customer.getCust_id();

	if (!customer.isAuthenticated()) {
		response.sendRedirect("/JAD_CA2/pages/custLogin.jsp");
		return;
	}

	try {
		cartItems = (ArrayList<Cart>) request.getAttribute("cartItems");
	%>

	<section class="h-100 h-custom">
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
											<h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
										</div>
										<hr class="my-4">

										<%
										if (cartItems.size() == 0 || cartItems.isEmpty()) {
										%>
										<h1>Nothing added to cart</h1>
										<%
										}
										for (Cart cartItem : cartItems) {
										totalPrice = cartItem.getTotal_price();
										has = true;
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

													<form action="/JAD_CA2/updateCart" method="POST">
														<input type="submit" id="minus" name="arith" class="btn"
															value="-"> <input type="hidden" name="book_id"
															value="<%=cartItem.getBook_id()%>"> <input
															type="hidden" name="cust_id" value="<%=cust_id%>">
														<input type="number" value="<%=cartItem.getQuantity()%>"
															name="qty" min="1" max="<%=cartItem.getInventoryLeft()%>"
															readonly min="1" max="<%=cartItem.getInventoryLeft()%>" />
														<input type="submit" id="plus" name="arith" class="btn"
															value="+">
													</form>
												</div>
											</div>
											<div
												class="col-md-3 d-flex justify-content-end align-items-center">
												<h6 class="mb-0"></h6>
												<a href="#!" class="text-muted"><i class="fas fa-times"></i></a>
												<form action="/JAD_CA2/deleteCart">
													<input type="hidden" name="book_id"
														value="<%=cartItem.getBook_id()%>"> <input
														type="hidden" name="cust_id" value="<%=cust_id%>">
													<input type="submit" name="delete" value="X"
														class="btn m-2">
												</form>

											</div>
										</div>
										<hr class="my-4">
										<%
										}
										%>


										<a class="btn btn-primary"
											href="http://localhost:8080/JAD_CA2/pages/home.jsp">Back
											to Shop</a>


									</div>

								</div>
								<div class="col-lg-7 bg-grey">
									<%
									if (has && !(cartItems.size() == 0) && !(cartItems == null)) {
										session.setAttribute("cartItems", cartItems);
									%>
									<div class="p-5">
										<h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
										<hr class="my-4">

										<div class="d-flex justify-content-between mb-5">
											<h5 class="text-uppercase">Total price</h5>
											<h5>
												<%
												String formattedAmount = decimalFormat.format(totalPrice);
												%>
												$ <span id="totalPrice"><%=formattedAmount%></span>
											</h5>
										</div>

										<a class="btn btn-primary"
											href="http://localhost:8080/JAD_CA2/pages/checkout.jsp">Proceed
											to checkout</a>


									</div>
									<%
									}
									%>
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
	response.sendRedirect(request.getContextPath() + "/loadCart?cust_id=" + cust_id);
	} catch (Exception e) {
	response.sendRedirect(request.getContextPath() + "/loadBooks");
	}
	%>

</body>
</html>
