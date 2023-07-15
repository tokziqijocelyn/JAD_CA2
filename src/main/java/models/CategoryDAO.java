package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Database;
import classes.Category;

public class CategoryDAO {

	public ArrayList<Category> getCategories () {
		Connection conn = Database.connect();
		String query_category = "SELECT category_id, category_name FROM book_category";
		try {
			PreparedStatement myStmt_category = conn.prepareStatement(query_category);
			ResultSet rs_category = myStmt_category.executeQuery();
			ArrayList<Category> categories = new ArrayList<>();
			while (rs_category.next()) {
				categories.add(new Category(rs_category.getInt("category_id"), rs_category.getString("category_name")));
			}
			return categories;
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
	
	public void addCategory(String category) {
		Connection conn = Database.connect();
		String query = "INSERT INTO book_category (category_name) VALUES (?)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, category);
			myStmt.executeUpdate();
			return;
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
	}
	
	public void editCategory(String category, int category_id) {
		Connection conn = Database.connect();
		String query = "UPDATE book_category set category_name = ? WHERE category_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, category);
			myStmt.setInt(2, category_id);
			myStmt.executeUpdate();
			return;
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
	}
	
}
