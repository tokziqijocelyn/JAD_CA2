package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ReviewDAO;

/**
 * Servlet implementation class addReview
 */
@WebServlet("/addReview")
public class addReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		boolean success = false;
		
		Integer rating = Integer.parseInt(request.getParameter("rating"));
		String review = request.getParameter("review");
		Integer cust_id = Integer.parseInt(request.getParameter("cust_id"));
		Integer book_id = Integer.parseInt(request.getParameter("book_id"));
		
		success = new ReviewDAO().addReview(review, cust_id, book_id, rating);
		String url = "pages/book.jsp?book_id=" + book_id;
		
		if (success) {
			url = url + "&code=success";
		} else {
			url = url + "&code=success";
		}
		
		//response.sendRedirect(url);
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
