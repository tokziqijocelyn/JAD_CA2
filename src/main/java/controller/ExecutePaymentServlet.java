package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.paypal.api.payments.*;

import classes.Cart;
import classes.PaymentServices;
import models.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.base.rest.PayPalRESTException;

/**
 * Servlet implementation class ExecutePaymentServlet
 */
@WebServlet("/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecutePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		try {
			PaymentServices paymentServices = new PaymentServices();
			Payment payment = paymentServices.executePayment(paymentId, payerId);
			
			HttpSession session = request.getSession();
			ArrayList<Cart> checkout = (ArrayList<Cart>) session.getAttribute("checkout");
			
			int cust_id = (Integer) session.getAttribute("cust_id");
			double total_price  = (Double) session.getAttribute("total");
			
			String code = new CartDAO().checkout(cust_id, checkout, total_price);
			
			String url = "pages/cart.jsp?code="+code;
			
			response.sendRedirect(url);
			
		} catch (PayPalRESTException ex) {
			request.setAttribute("errorMessage", ex.getMessage());
			ex.printStackTrace();
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}		
	}


}
