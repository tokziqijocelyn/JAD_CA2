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
	<%@page import='java.util.ArrayList'%>
	<%@page import='java.sql.*'%>
	<%@include file="../adminNav.jsp"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<%@page import="classes.Book"%>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

	<%
	String filter = request.getParameter("filter");
	if (filter == null) {
		filter = "today";
	}

	ArrayList<String> x_axis = (ArrayList<String>) request.getAttribute("x_axis");
	ArrayList<Double> y_axis = (ArrayList<Double>) request.getAttribute("y_axis");

	if (x_axis == null || y_axis == null) {
		response.sendRedirect(request.getContextPath() + "/loadTopCustomers");
		return;
	}
	%>

	<div class="container my-5">
		<div class="row mx-2">

			<div class="col-md-4">
				<h2 class="mb-4">
					<u><b>Top Customers</b></u>
				</h2>
				<select class="form-select mb-2" id="filter">
					<option <%=filter.equals("today") ? "selected" : ""%> value="today">Today</option>
					<option <%=filter.equals("week") ? "selected" : ""%> value="week">Week</option>
					<option <%=filter.equals("month") ? "selected" : ""%> value="month">Month</option>
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 mx-2">

				<div class="chart-container"
					style="position: relative; height: 40vh; width: 80vw">
					<canvas id="myChart"></canvas>
				</div>
			</div>
		</div>
	</div>


	<script>	
		const ctx = document.getElementById('myChart');
		new Chart(
			ctx,
			{
				type: 'bar',
				data: {
					labels: [<%="\"" + String.join("\", \"", x_axis) + "\""%>],
					datasets: [{
						label: 'Amount Spent By Customer',
						data: [<% for (Double value : y_axis) { out.print(value + ", "); } %>],
						borderWidth: 1
					}]
				},
				options: {
					scales: {
						y: {
							beginAtZero: true
						}
					}
				}
			});
	</script>

	<script>
		document.addEventListener('DOMContentLoaded', (event) => {
			const filterSelect = document.getElementById('filter');

			filterSelect.addEventListener('change', (event) => {
				if (event.target.value == "today") {
					window.location.href = 'loadTopCustomers';
				} else {
					window.location.href = 'loadTopCustomers?filter=' + event.target.value;
				}
			});
		});
	</script>
</body>
</html>