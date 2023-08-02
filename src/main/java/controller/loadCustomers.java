package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Customer;
import models.CustomerDAO;
import models.OrderDAO;

/**
 * Servlet implementation class loadCustomer
 */
@WebServlet("/loadCustomers")
public class loadCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadCustomers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Customer> customers;
		
		String startDateString = request.getParameter("start");
		String endDateString = request.getParameter("end");
		

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if (startDateString == null || endDateString == null) {
			customers = new CustomerDAO().getAllCustomers();
		} else {
			// Convert to LocalDate
			LocalDate startDate = LocalDate.parse(startDateString, inputFormatter);
			LocalDate endDate = LocalDate.parse(endDateString, inputFormatter);

			// Format to desired output
			String sqlStartDate = startDate.format(outputFormatter) + " 00:00:00";
			String sqlEndDate = endDate.format(outputFormatter) + " 23:59:59";

			customers = new CustomerDAO().getAllCustomersByDate(sqlStartDate, sqlEndDate);
		}
		

		
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/pages/customers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
