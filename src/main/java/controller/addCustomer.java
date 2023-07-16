package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.CustomerDAO;
/**
 * Servlet implementation class addCustomer
 */
@WebServlet("/addCustomer")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class addCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String code = "error";
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		String block = request.getParameter("block");
		String unit_no = request.getParameter("unit_no");
		String street = request.getParameter("street");
		int postal_code = Integer.parseInt(request.getParameter("postal_code"));
		String imagePath = "/images/customer.jpg";
		
		 ServletContext servletContext = getServletContext();
		 String appPath = getServletContext().getRealPath(""); // Get server path
		 String projectDirectory = appPath.substring(0, appPath.indexOf("wtpwebapps"));
		 String output = "";
		 String word = "\\.metadata";
        int index = appPath.indexOf(word);
        if (index != -1) {
            output = appPath.substring(0, index);
            System.out.println("This is the output");
            System.out.println(output);
        }
		String absolutePath = output + File.separator + "JAD_CA2" + File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "images";
		System.out.println(absolutePath);
        File fileSaveDir = new File(absolutePath);
		
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Part part = request.getPart("image");
        
        if (part != null && part.getSize() > 0 ) {
       	 String fileName = getFileName(part);
            System.out.println("This is the default file");
            System.out.println(fileName);
            String extension = fileName.substring(fileName.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + extension;
            String filePath = absolutePath + File.separator + newFileName;
    		
            try (OutputStream out = new FileOutputStream(filePath);
               InputStream fileContent = part.getInputStream()) {
                   
               byte[] buffer = new byte[1024];
               int length;
               while ((length = fileContent.read(buffer)) > 0) {
                   out.write(buffer, 0, length);
               }
               
               response.getWriter().write(filePath);
                   
           } catch (IOException e) {
           	String errorMessage = "Error occurred during file upload: " + e.getMessage();
               response.getWriter().write("error: " + errorMessage);
           }
            
            System.out.println(newFileName);
            imagePath = "/images/" + newFileName;
            
       } else {
          imagePath = "/images/customer.jpg";
       }
        
        code = new CustomerDAO().addCustomer(imagePath, username, email, pwd, block, street, unit_no, postal_code, null);
        
        String url;
        
        if (code.equals("duplicate")) {
        	url = "pages/custSignUp.jsp?code="+code;
        } else {
        	url = "pages/custLogin.jsp?code="+code;
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
	
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        
        return null;
    }

}
