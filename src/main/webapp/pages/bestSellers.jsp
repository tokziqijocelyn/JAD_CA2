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
	ArrayList<Integer> y_axis = (ArrayList<Integer>) request.getAttribute("y_axis");

	if (x_axis == null || y_axis == null) {
		response.sendRedirect(request.getContextPath() + "/loadBestSellers");
		return;
	}
	%>

	<select class="form-select mb-2" id="filter">
		<option <%=filter.equals("today") ? "selected" : ""%> value="today">Today</option>
		<option <%=filter.equals("week") ? "selected" : ""%> value="week">Week</option>
		<option <%=filter.equals("month") ? "selected" : ""%> value="month">Month</option>
	</select>

	<div class="chart-container"
		style="position: relative; height: 40vh; width: 80vw">
		<canvas id="myChart"></canvas>
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
						label: 'Best Selling Book',
						data: [<%for (int value : y_axis) {
	out.print(value + ", ");
}%>],
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
					window.location.href = 'loadBestSellers';
				} else {
					window.location.href = 'loadBestSellers?filter=' + event.target.value;
				}
			});
		});
	</script>

</body>
</html>