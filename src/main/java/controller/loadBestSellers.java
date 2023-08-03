package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Book;
import models.BookDAO;

/**
 * Servlet implementation class loadBestSellers
 */
@WebServlet("/loadBestSellers")
public class loadBestSellers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loadBestSellers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filter = request.getParameter("filter");
		System.out.print("hello??");
		ArrayList<Book> books;
		Book topBook = null;
		BookDAO bookDao = new BookDAO();
		
		if (filter == null) {
			books = bookDao.getBestSellingBooksToday();
		} else if (filter.equals("week")) {
			books = bookDao.getBestSellingBooksWeek();
		} else {
			books = bookDao.getBestSellingBooksMonth();
		}
				
		ArrayList<String> x_axis = new ArrayList<>();
		ArrayList<Integer> y_axis = new ArrayList<>();
		
		for (Book book: books) {
			y_axis.add(book.getTotal_sold());
			x_axis.add(book.getTitle());
		}
		
		try {
			 topBook = bookDao.getBestSellingBook();
		} catch (NullPointerException e) {
			topBook = new Book(0,"",0);
		}
		
		request.setAttribute("x_axis", x_axis);
		request.setAttribute("y_axis", y_axis);
		request.setAttribute("topBook", topBook);
		request.getRequestDispatcher("/pages/bestSellers.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
