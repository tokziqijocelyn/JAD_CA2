package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Book;
import classes.Sales;
import models.SalesDAO;

/**
 * Servlet implementation class loadSales
 */
@WebServlet("/loadSales")
public class loadSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadSales() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filter = request.getParameter("filter");

		ArrayList<Sales> sales;
		SalesDAO salesDao = new SalesDAO();
		
		if (filter == null) {
			sales = salesDao.getTodaySales();
		} else if (filter.equals("week")) {
			sales = salesDao.getWeekSales();
		} else {
			sales = salesDao.getMonthSales();
		}
				
		ArrayList<String> x_axis = new ArrayList<>();
		ArrayList<Double> y_axis = new ArrayList<>();
		
		for (Sales sale: sales) {
			y_axis.add(sale.getTotal_price());
			
			if (sale.getOrder_date() == null) {
				x_axis.add(sale.getTime_period());
			} else {
				x_axis.add(sale.getOrder_date());
			}
		}
		
		request.setAttribute("x_axis", x_axis);
		request.setAttribute("y_axis", y_axis);
		request.getRequestDispatcher("/pages/salesReport.jsp").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
