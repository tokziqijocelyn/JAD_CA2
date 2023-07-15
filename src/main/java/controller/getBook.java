package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.*;
import models.AuthorDAO;
import models.BookDAO;
import models.CategoryDAO;
import models.PublisherDAO;
import models.SalesDAO;

/**
 * Servlet implementation class getBook
 */
@WebServlet("/getBook")
public class getBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Publisher> publishers = new PublisherDAO().getPublisher();
		ArrayList<Category> categories = new CategoryDAO().getCategories();
		ArrayList<Author> authors = new AuthorDAO().getAuthors();
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		Book book = new BookDAO().getBookById(book_id);
		
		String filter = request.getParameter("filter");

		ArrayList<Book> sold_books;
		SalesDAO salesDao = new SalesDAO();

		if (filter == null) {
			sold_books = salesDao.getTodayBookSales(book_id);
		} else if (filter.equals("week")) {
			sold_books = salesDao.getBookWeekSales(book_id);
		} else {
			sold_books = salesDao.getBookMonthSales(book_id);
		}

		ArrayList<String> x_axis = new ArrayList<>();
		ArrayList<Integer> y_axis = new ArrayList<>();

		for (Book sold_book : sold_books) {
			y_axis.add(sold_book.getTotal_sold());
			x_axis.add(sold_book.getDate_time());
		}

		request.setAttribute("x_axis", x_axis);
		request.setAttribute("y_axis", y_axis);
		
		request.setAttribute("book", book);
		request.setAttribute("publishers", publishers);
		request.setAttribute("categories", categories);
		request.setAttribute("authors", authors);
		request.getRequestDispatcher("/pages/editBook.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
