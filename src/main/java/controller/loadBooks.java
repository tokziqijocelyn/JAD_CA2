package controller;

import java.io.IOException;
import classes.Publisher;
import classes.Book;
import classes.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.*;

/**
 * Servlet implementation class loadBooks
 */
@WebServlet("/loadBooks")
public class loadBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public loadBooks() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<Publisher> publishers = new PublisherDAO().getPublisher();
		ArrayList<Category> categories = new CategoryDAO().getCategories();

		String sort = request.getParameter("sort");
		String search = request.getParameter("search");
		String categoryId = request.getParameter("category");
		String publisherId = request.getParameter("publisher");
		int category_id;
		int publisher_id;

		// Check if the category parameter is provided
		if (categoryId != null && !categoryId.isEmpty()) {
			// Parse the category parameter value to an integer
			try {
				category_id = Integer.parseInt(categoryId);
			} catch (NumberFormatException e) {
				// Handle the case where the category parameter is not a valid integer
				category_id = 0; // Or set a default value, or throw an exception
			}
		} else {
			// Handle the case where the category parameter is not present or empty
			category_id = 0; // Or set a default value, or throw an exception
		}

		// Check if the publisher parameter is provided
		if (publisherId != null && !publisherId.isEmpty()) {
			// Parse the publisher parameter value to an integer
			try {
				publisher_id = Integer.parseInt(publisherId);
			} catch (NumberFormatException e) {
				// Handle the case where the publisher parameter is not a valid integer
				publisher_id = 0; // Or set a default value, or throw an exception
			}
		} else {
			// Handle the case where the publisher parameter is not present or empty
			publisher_id = 0; // Or set a default value, or throw an exception
		}

		ArrayList<Book> books;
		BookDAO bookDao = new BookDAO();

		if (category_id != 0) {
			books = bookDao.getBooksByCategoryId(category_id);
		} else if (publisher_id != 0) {
			books = bookDao.getBooksByPusliherId(publisher_id);
		} else if (sort != null) {
			if (sort.equals("rating")) {
				books = bookDao.getBooksByRating();
			} else {
				books = bookDao.getBooksByPopularity();
			}
		} else if (search != null) {
			books = bookDao.getBooksBySearch(search);
		} else {
			books = bookDao.getAllBooks();
		}

		request.setAttribute("books", books);
		request.setAttribute("publishers", publishers);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/pages/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
