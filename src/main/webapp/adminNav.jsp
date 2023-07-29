<%@page import='classes.Admin'%>
<%@page import='models.AdminDAO'%>
<%@include file="./styles.jsp"%>

<%
Admin admin = new AdminDAO().initAdminBySession(session);
if (!admin.isAuthenticated()) {
	response.sendRedirect("/JAD_CA2/pages/adminLogin.jsp");
	return;
}
%>

<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand text-white"
			href="http://localhost:8080/JAD_CA2/pages/dashboard.jsp">BokStore
			Admin</a>
		<button class="navbar-toggler text-white" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarColor01"
			aria-controls="navbarColor01" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link text-white active"
					href="http://localhost:8080/JAD_CA2/pages/dashboard.jsp">Dashboard
						<span class="visually-hidden">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="http://localhost:8080/JAD_CA2/pages/bookOrders.jsp">View
						Orders</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="http://localhost:8080/JAD_CA2/pages/customers.jsp">View
						Customers</a></li>
				<li class="nav-item"><a class="nav-link text-white"
					href="http://localhost:8080/JAD_CA2/pages/stocks.jsp">View
						Stocks</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Edit
						Books</a>
					<div class="dropdown-menu" data-bs-popper="static">
						<a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/author.jsp">Authors</a>
						<a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/addBook.jsp">Add
							Book</a> <a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/category.jsp">Category</a>
						<a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/publisher.jsp">Publisher</a>
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Analytics</a>
					<div class="dropdown-menu" data-bs-popper="static">
						<a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/bestSellers.jsp">Best
							Selling Books</a> <a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/salesReport.jsp">Sales
							Report</a>
							<a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/topCustomers.jsp">View Top Customers</a>
					</div></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">Account</a>
					<div class="dropdown-menu" data-bs-popper="static">
						<a class="nav-link text-white dropdown-item"
							href="http://localhost:8080/JAD_CA2/pages/adminProfile.jsp">Profile</a>
						<a class="nav-link text-white dropdown-item"
							href="/JAD_CA2/adminLogout">Logout</a>
					</div></li>


			</ul>
			<form action="/JAD_CA2/loadDashboard" class="d-flex">
				<input required class="form-control me-sm-2" type="search"
					placeholder="Search Book" name="search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>