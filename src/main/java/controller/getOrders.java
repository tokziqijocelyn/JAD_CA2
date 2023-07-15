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

import classes.Order;
import models.OrderDAO;

/**
 * Servlet implementation class getOrders
 */
@WebServlet("/getOrders")
public class getOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String startDateString = request.getParameter("start");
		String endDateString = request.getParameter("end");
		

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		ArrayList<Order> orders;
		
		if (startDateString == null || endDateString == null) {
			orders = new OrderDAO().getAllOrders();
		} else {
			// Convert to LocalDate
			LocalDate startDate = LocalDate.parse(startDateString, inputFormatter);
			LocalDate endDate = LocalDate.parse(endDateString, inputFormatter);

			// Format to desired output
			String sqlStartDate = startDate.format(outputFormatter) + " 00:00:00";
			String sqlEndDate = endDate.format(outputFormatter) + " 23:59:59";

			orders = new OrderDAO().getAllOrdersByDate(sqlStartDate, sqlEndDate);
		}
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
		request.setAttribute("orders", groupedOrders);
		request.getRequestDispatcher("/pages/bookOrders.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
