package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Customer;
import classes.Order;
import models.CustomerDAO;

/**
 * Servlet implementation class loadCustomer
 */
@WebServlet("/loadCustomer")
public class loadCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loadCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));

		Customer customer = new CustomerDAO().initCustomerById(customer_id);
		ArrayList<Order> orders = new CustomerDAO().getCustomerOrders(customer.getCust_id());

		ArrayList<ArrayList<Order>> groupedOrders = new ArrayList<>();

		// Group orders by ID
		for (Order order : orders) {
			boolean isNewGroup = true;

			// Check if there is an existing group with the same ID
			for (ArrayList<Order> group : groupedOrders) {
				if (group.get(0).getOrders_id() == order.getOrders_id()) {
					group.add(order);
					isNewGroup = false;
					break;
				}
			}

			// If no existing group found, create a new group
			if (isNewGroup) {
				ArrayList<Order> newGroup = new ArrayList<>();
				newGroup.add(order);
				groupedOrders.add(newGroup);
			}
		}

		request.setAttribute("customer", customer);
		request.setAttribute("orders", groupedOrders);
		request.getRequestDispatcher("/pages/editCustomer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
