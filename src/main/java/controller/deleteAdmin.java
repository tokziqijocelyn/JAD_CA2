package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.AdminDAO;
import models.CustomerDAO;

/**
 * Servlet implementation class deleteAdmin
 */
@WebServlet("/deleteAdmin")
public class deleteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	int admin_id = Integer.parseInt(request.getParameter("admin_id"));
		
		int affectedRows = 0;
		
		affectedRows = new AdminDAO().deleteAdminByID(admin_id);
				
		String url = "/pages/adminLogin.jsp";
		
		if (affectedRows == 1 ) {
			url = url + "?code=success";
		} else {
			url = url + "?code=err";
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
