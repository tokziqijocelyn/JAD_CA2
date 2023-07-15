package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import classes.*;
import models.AdminDAO;

import java.sql.*;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
/**
 * Servlet implementation class updateAdmin
 */
@WebServlet("/updateAdmin")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class updateAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int admin_id = Integer.parseInt(request.getParameter("admin_id"));

		boolean updated = false;

		String imagePath = request.getParameter("imagePath");
		String adminUsername = request.getParameter("username");
		String adminEmail = request.getParameter("email");
		String adminPwd = request.getParameter("pwd");

		ServletContext servletContext = getServletContext();
		String appPath = getServletContext().getRealPath(""); // Get server path
		String projectDirectory = appPath.substring(0, appPath.indexOf("wtpwebapps"));
		String output = "";
		String word = "\\.metadata";

		int index = appPath.indexOf(word);
		if (index != -1) {
			output = appPath.substring(0, index);
		}

		String absolutePath = output + File.separator + "JAD_CA2" + File.separator + "src"+ File.separator + "main"+ File.separator +"webapp" + File.separator + "images";
		System.out.println( absolutePath);
		File fileSaveDir = new File(absolutePath);

		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("image");

		if (part != null && part.getSize() > 0) {
			String fileName = getFileName(part);
			String extension = fileName.substring(fileName.lastIndexOf("."));
			String uuid = UUID.randomUUID().toString();
			String newFileName = uuid + extension;
			String filePath = absolutePath + File.separator + newFileName;

			try (OutputStream out = new FileOutputStream(filePath); InputStream fileContent = part.getInputStream()) {

				byte[] buffer = new byte[1024];
				int length;
				while ((length = fileContent.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
				response.getWriter().write(filePath);



			} catch (IOException e) {
				System.out.println(e);
			}
			imagePath = "/images/" + newFileName;
			System.out.print(imagePath);

		}

		updated = new AdminDAO().updateAdminByID(imagePath,adminUsername, adminEmail,  adminPwd, admin_id);

		String url = "pages/adminProfile.jsp" ;


		if (updated) {
//		    response.sendRedirect("getBook?book_id="+book_id+"&code=success");
			url = url + "?code=success";

		} else {
//		    request.getRequestDispatcher("getBook?book_id="+book_id+"&code=error").forward(request, response);
			url = url + "?code=error";
		}

		response.sendRedirect(url);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
            	System.out.print(element.substring(element.indexOf("=") + 1).trim().replace("\"", ""));
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }

        return null;
    }

}