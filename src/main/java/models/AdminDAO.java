package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import classes.Admin;
import classes.Database;

public class AdminDAO {
	
	public Admin initAdminById(int admin_id) {
		Connection conn = Database.connect();
		String userQuery = "SELECT admin_id, email, username, image_url, password FROM admin WHERE admin_id=?";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(userQuery);
			myStmt.setInt(1, admin_id);
			ResultSet rs = myStmt.executeQuery();
			if (!rs.next()) {
				return new Admin(false);
			} else {
				return new Admin(rs.getInt("admin_id"), rs.getString("email"), rs.getString("username"), true, rs.getString("image_url"), rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public Admin initAdminBySession(HttpSession session) {
		Integer admin_id = (Integer) session.getAttribute("admin_id");
		if (admin_id == null) {
			return new Admin(false);
		}
		Connection conn = Database.connect();
		String userQuery = "SELECT admin_id, email, username, image_url, password FROM admin WHERE admin_id=?";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(userQuery);
			myStmt.setInt(1, admin_id);
			ResultSet rs = myStmt.executeQuery();
			if (!rs.next()) {
				return new Admin(false);
			} else {
				return new Admin(rs.getInt("admin_id"), rs.getString("email"), rs.getString("username"), true, rs.getString("image_url"), rs.getString("password"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public int loginAdmin(String email, String password) {
		Connection conn = Database.connect();
		String query = "SELECT admin_id FROM admin WHERE email = ? and password = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, email);
			myStmt.setString(2, password);

			ResultSet rs = myStmt.executeQuery();
			if (!rs.next()) {
				return -1;
			} else {
				Integer admin_id = rs.getInt("admin_id");
				return admin_id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	
	public boolean updateAdminByID(String imagePath, String username, String email, String pwd, Integer admin_id) {
	    boolean success = false;
	    Connection conn = Database.connect();
	    System.out.println("Update admin in admindao ran");
	    String query = "UPDATE admin SET username = ?, email = ?, image_url = ?, password = ? WHERE admin_id = ?";
	    try {
	        PreparedStatement myStmt = conn.prepareStatement(query);
	        myStmt.setString(1, username);
	        myStmt.setString(2, email);
	        myStmt.setString(3, imagePath);
	        myStmt.setString(4, pwd);
	        myStmt.setInt(5, admin_id);
	        
	        myStmt.executeUpdate();
	        success = true;
	    } catch (NullPointerException e) {
	        System.out.println("NULL ERROR IN ADMINDAO!!!");
	    } catch (Exception e) {
	        System.out.println("VAGUE ERROR!!!");
	        System.out.println(e);
	    }
	    finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
	    return success;
	}
	
	public int deleteAdminByID(int admin_id) {
		int success = 0;
		Connection conn = Database.connect();
		String query = "DELETE FROM admin WHERE admin_id= ?";
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, admin_id);
			success = myStmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return success;
	}

}
