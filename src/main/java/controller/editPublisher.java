package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Publisher;
import models.CategoryDAO;
import models.PublisherDAO;

/**
 * Servlet implementation class editPublisher
 */
@WebServlet("/editPublisher")
public class editPublisher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPublisher() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String publisher = request.getParameter("publisher");
		int publisher_id = Integer.parseInt(request.getParameter("publisher_id"));

		new PublisherDAO().editPublisher(publisher, publisher_id);

		ArrayList<Publisher> publishers = new PublisherDAO().getPublisher();

		request.setAttribute("publishers", publishers);
		request.getRequestDispatcher("/pages/publisher.jsp?code=editSuccess").forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
