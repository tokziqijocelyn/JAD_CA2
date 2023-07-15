package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CustomerDAO;

/**
 * Servlet implementation class deleteCustomer
 */
@WebServlet("/deleteCustomer")
public class deleteCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int cust_id = Integer.parseInt(request.getParameter("cust_id"));
		int cust = Integer.parseInt(request.getParameter("cust"));
		
		int affectedRows = 0;
		
		affectedRows = new CustomerDAO().deleteCustomerByID(cust_id);
		
		String url = "/pages/custLogin.jsp";
		
		if (affectedRows == 1 ) {
			url = url + "?code=success";
		} else {
			url = url + "?code=err";
		}
		
		if (cust != 1) {
			url = "loadCustomers"+url;
		}
		
		request.getRequestDispatcher(url).forward(request, response);	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
