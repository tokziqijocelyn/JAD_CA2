<%@page import='classes.Customer'%>
<%@page import='models.CustomerDAO'%>
<%@include file="./styles.jsp"%>

<%
	Customer customer = new CustomerDAO().initCustomerBySession(session);
%>

<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand text-white" href="http://localhost:8080/JAD_CA2/pages/home.jsp"><i class="bi bi-bookmark-star-fill m-1"></i>BokStore</a>
    <button class="navbar-toggler text-white" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link text-white active" href="http://localhost:8080/JAD_CA2/pages/home.jsp">Home
            <span class="visually-hidden">(current)</span>
          </a>
        </li>
        <li class="nav-item"> 
          <%
          	if (customer.isAuthenticated()) {
          		%>
          			<a class="nav-link text-white" href="http://localhost:8080/JAD_CA2/pages/custProfile.jsp">Profile</a>
          		<%
          	} else {
          		%>
          			<a class="nav-link text-white" href="http://localhost:8080/JAD_CA2/pages/custLogin.jsp">Login</a>
          		
          		<%
          	}
          %>
          
        </li>
        <li class ="nav-item">
        	<%
          	if (!customer.isAuthenticated()) {
          		%>
        	<a class="nav-link text-white" href="http://localhost:8080/JAD_CA2/pages/custSignUp.jsp">Sign up</a>
        	<%} %>
        </li>
        <li class="nav-item">          
          <%
          	if (customer.isAuthenticated()) {
          		int cust_id = customer.getCust_id();
          		session.setAttribute("cust_id", cust_id);

          		%>

        		<a class="nav-link text-white" href="/JAD_CA2/loadCart">Cart</a>

 
          		<%
          	}
          %>
         </li>

                <li class="nav-item">
          <%
          	if (customer.isAuthenticated()) {
          		%>
          			<a class="nav-link text-white" href="/JAD_CA2/custLogout">Logout</a>
          		<%
          	}
          %>
        </li>
      </ul>
      <form method="POST" action="/JAD_CA2/loadBooks" class="d-flex">
        <input required class="form-control me-sm-2" type="search"
					placeholder="Search Book" name="search">
        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
      </form>
    </div>
  </div>
</nav>