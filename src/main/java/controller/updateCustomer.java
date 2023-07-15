package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import classes.*;
import models.CustomerDAO;

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
 * Servlet implementation class updateCustomer
 */
@WebServlet("/updateCustomer")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class updateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int cust_id = Integer.parseInt(request.getParameter("cust_id"));
		int cust = Integer.parseInt(request.getParameter("cust"));
		
		boolean updated = false;
		
		String imagePath = request.getParameter("image_url");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		
		String street = request.getParameter("street");
		String block = request.getParameter("block");
		String unitNo = request.getParameter("unit_no");
		int postal = Integer.parseInt(request.getParameter("postal_code"));
		
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
		
		updated = new CustomerDAO().updateCustomerByID(imagePath, username, email,  pwd, block, street, unitNo, postal ,cust_id);

		
		String url = "?customer_id=" + cust_id ;
		
		if (cust == 1) {
			url = "pages/custProfile.jsp" + url;
		} else {
			url = "loadCustomer";
		}

		
		if (updated) {
			url = url + "&code=success";
		    
		} else {
			url = url + "&code=error";
		}
		
		System.out.println("THIS IS THE URLL" + url);
		
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
