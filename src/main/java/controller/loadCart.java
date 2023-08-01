package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Cart;
import models.CartDAO;

/**
 * Servlet implementation class loadCart
 */
@WebServlet("/loadCart")
public class loadCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Cart> cartItems = null;
		
		HttpSession session = request.getSession();
		int cust_id = (int) session.getAttribute("cust_id");
		
		try {
			
			cartItems = new CartDAO().getCartItems(cust_id);
			
			
		} catch (NullPointerException e) {
			request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
		}
		request.setAttribute("cartItems", cartItems);
		request.getRequestDispatcher("/pages/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
