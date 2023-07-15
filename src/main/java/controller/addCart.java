package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.CartDAO;

/**
 * Servlet implementation class addCart
 */
@WebServlet("/addCart")
public class addCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int cust_id = Integer.parseInt(request.getParameter("cust_id"));
        int book_id = Integer.parseInt(request.getParameter("book_id"));
        
        boolean success = false;
        
        success= new CartDAO().addToCart(cust_id, book_id, quantity);
        
        String url = "pages/book.jsp?book_id=" + book_id;
        
        if (success) {
        	url = url + "&code=addedcart";
        } else {
        	url = url + "&code=failedcart";
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
