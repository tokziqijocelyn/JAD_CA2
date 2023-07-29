package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PromoDAO;

import java.util.ArrayList;

/**
 * Servlet implementation class UpdateBookPromo
 */
@WebServlet("/UpdateBookPromo")
public class UpdateBookPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateBookPromo() {
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

		String code = "error";
		int book_id = (int) Integer.parseInt(request.getParameter("book_id"));
		String[] sSeasons = request.getParameterValues("promo");
		ArrayList<Integer> chosenSeasons = new ArrayList<>();
		for (String s : sSeasons) {
			chosenSeasons.add(Integer.parseInt(s));
		}
		code = new PromoDAO().updateBookPromo(book_id, chosenSeasons);

		request.getRequestDispatcher("getBook?book_id=" + book_id + "&code=" + code).forward(request, response);
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
