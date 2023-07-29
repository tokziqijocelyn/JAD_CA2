package controller;

import java.io.IOException;
import java.util.ArrayList;

import models.BookDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Book;

/**
 * Servlet implementation class updateStock
 */
@WebServlet("/updateStock")
public class updateStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer book_id = Integer.parseInt(request.getParameter("book_id"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));

		BookDAO bookDAO = new BookDAO();
		bookDAO.updateBookQuantity(book_id, quantity);
		ArrayList<Book> no_stock = bookDAO.getNoStockBooks();
		ArrayList<Book> low_stock = bookDAO.getLowStockBooks();
		ArrayList<Book> high_stock = bookDAO.getHighStockBooks();
		
		request.setAttribute("no_stock", no_stock);
		request.setAttribute("low_stock", low_stock);
		request.setAttribute("high_stock", high_stock);
		
		request.getRequestDispatcher("/pages/stocks.jsp?code=success").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
