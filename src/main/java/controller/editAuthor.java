package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Author;
import models.AuthorDAO;

/**
 * Servlet implementation class editAuthor
 */
@WebServlet("/editAuthor")
public class editAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String author = request.getParameter("author");
		int author_id = Integer.parseInt(request.getParameter("author_id"));
		
		new AuthorDAO().editAuthor(author, author_id);
		
		ArrayList<Author> authors = new AuthorDAO().getAuthors();
		
		request.setAttribute("authors", authors);
		request.getRequestDispatcher("/pages/author.jsp?code=editSuccess").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
