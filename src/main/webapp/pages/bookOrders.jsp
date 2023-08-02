 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BokStore</title>
</head>
<%@page import='classes.*'%>
<%@page import='java.util.*'%>
<%@include file="../adminNav.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<body>
	<%
	ArrayList<ArrayList<Order>> orders = (ArrayList<ArrayList<Order>>) request.getAttribute("orders");
	Boolean has = false;

	if (orders == null) {
		response.sendRedirect(request.getContextPath() + "/getOrders");
		return;
	}
	%>
	<div class="container mt-4">
		<div class="row">
			<div class="col-md-5">
				<div class="project-info-box mt-0">
					<h2>
						<u><b>PAST ORDERS</b></u>
					</h2>
					<input class="me-5 " id="date" type="text" name="dates" value=' '
						style="height: 50px; width: 250px" />
				</div>
			</div>
			<div class="table-responsive mt-3">
				<table class="table">
					<thead>
						<tr class="table-active text-uppercase">
							<th scope="col">Order Date</th>
							<th scope="col">Order Details</th>
							<th scope="col">Total Revenue</th>
						</tr>
					</thead>
					<tbody>
						<%
						for (ArrayList<Order> orderInSameGroup : orders) {
							has = true;
						%>
						<tr>
							<td><%=orderInSameGroup.get(0).getOrder_date()%></td>
							<td><h5>
									Order ID:
									<%=orderInSameGroup.get(0).getOrders_id()%></h5> <br>
								<p class="mb-0">
									<strong class="text-secondary">Username:</strong> <span
										class="ml-2"><%=orderInSameGroup.get(0).getUsername()%></span>
								</p>
								<p class="mb-0">
									<strong class="text-secondary">Email:</strong> <span
										class="ml-2"><%=orderInSameGroup.get(0).getEmail()%></span>
								</p>
								<p class="mb-0">
									<strong class="text-secondary">Address:</strong> <span
										class="ml-2"><%=orderInSameGroup.get(0).getBlock()%>, <%=orderInSameGroup.get(0).getStreet()%>,
										<%=orderInSameGroup.get(0).getUnit_no()%>, <%=orderInSameGroup.get(0).getPostal_code()%></span>
								</p> <br />
								<h4 class="text-secondary my-2">Order:</h4> <%
 for (Order order : orderInSameGroup) {
 %>
								<div class="border-bottom py-3">
									<span class="ml-2"><%=order.getTitle()%></span> - <strong
										class="text-secondary">ISBN:</strong> <span class="ml-2"><%=order.getISBN()%></span>
									x <span class="ml-2"><%=order.getQuantity()%> Qty</span>
									<p class="mb-0"></p>
								</div> <%
 }
 %></td>
							<td class="align-middle">$<%=orderInSameGroup.get(0).getTotal_price()%></td>
						</tr>
						<%
						}
						%>
						<%
						if (!has) {
						%>
						<tr>
							<td colspan="3" class="text-center">
								<h1>No Orders :(</h1>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
<script>
	$('input[name="dates"]').daterangepicker();
	let params = new URLSearchParams(window.location.search);
	let start = params.get('start');
	let end = params.get('end');

	if (!start && !end) {
		$("#date").val("");
	} else {
		$("#date").val(start + " - " + end);
	}
	
    $("#date").change(() => {
        var dates = $("#date").val().split(' ');
        var today_date = convertDate(dates[0]);
        var last_date = convertDate(dates[2]);
        window.location.href = "getOrders?start=" + today_date + "&end=" + last_date;
    })
    
            function convertDate(date) {
            var dateArr = date.split('/');

            var newDate = dateArr[0]+ "/" + dateArr[1] + "/" + dateArr[2];

            return newDate;
        }
</script>
</html>