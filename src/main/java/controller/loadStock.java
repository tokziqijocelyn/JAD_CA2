package controller;

import java.io.IOException;
import java.util.ArrayList;
import classes.Book;
import models.BookDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loadStock
 */
@WebServlet("/loadStock")
public class loadStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadStock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		BookDAO bookDAO = new BookDAO();
		String search = request.getParameter("search");
		ArrayList<Book> custom_stock = new ArrayList<>(); 
		if (search != null) {
			int stock = Integer.parseInt(search);
			custom_stock = bookDAO.getBookStockBySearch(stock);
		} else {
			custom_stock = bookDAO.getAllBooksStock();
			System.out.print("This is the customer stock"+custom_stock);
		}

		ArrayList<Book> no_stock = bookDAO.getNoStockBooks();
		ArrayList<Book> low_stock = bookDAO.getLowStockBooks();
		ArrayList<Book> high_stock = bookDAO.getHighStockBooks();
		
		request.setAttribute("no_stock", no_stock);
		request.setAttribute("low_stock", low_stock);
		request.setAttribute("high_stock", high_stock);
		request.setAttribute("custom_stock", custom_stock);
		request.getRequestDispatcher("/pages/stocks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
