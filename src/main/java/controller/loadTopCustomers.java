package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Book;
import classes.Customer;
import models.CustomerDAO;

/**
 * Servlet implementation class loadTopCustomers
 */
@WebServlet("/loadTopCustomers")
public class loadTopCustomers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadTopCustomers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filter = request.getParameter("filter");

		ArrayList<Customer> customers;
		CustomerDAO CustomerDao = new CustomerDAO();
		
		if (filter == null) {
			customers = CustomerDao.getTopCustomersToday();
		} else if (filter.equals("week")) {
			customers = CustomerDao.getTopCustomersWeek();
		} else {
			customers = CustomerDao.getTopCustomersMonth();
		}
				
		ArrayList<String> x_axis = new ArrayList<>();
		ArrayList<Double> y_axis = new ArrayList<>();
		
		for (Customer customer: customers) {
			y_axis.add(customer.getAmount_spent());
			x_axis.add(customer.getUsername());
		}
		
		request.setAttribute("x_axis", x_axis);
		request.setAttribute("y_axis", y_axis);
		request.getRequestDispatcher("/pages/topCustomers.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
