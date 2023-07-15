package models;

import java.sql.*;
import java.util.*;
import classes.Author;
import classes.Database;

public class AuthorDAO {

	public ArrayList<Author> getAuthors() {
		Connection conn = Database.connect();
		String query_author = "SELECT author_id, name FROM authors";
		try {
			PreparedStatement myStmt_author = conn.prepareStatement(query_author);
			ResultSet rs_author = myStmt_author.executeQuery();
			ArrayList<Author> authors = new ArrayList<>();
			while (rs_author.next()) {
				authors.add(new Author(rs_author.getInt("author_id"), rs_author.getString("name")));
			}
			return authors;
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

	public void addAuthor(String author) {
		Connection conn = Database.connect();
		String query_author = "INSERT INTO authors (name) VALUES (?)";
		try {
			PreparedStatement myStmt_author = conn.prepareStatement(query_author);
			myStmt_author.setString(1, author);
			myStmt_author.executeUpdate();
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
	
	public void editAuthor(String author, int author_id) {
		Connection conn = Database.connect();
		String query_author = "UPDATE authors set name = ? WHERE author_id = ?";
		try {
			PreparedStatement myStmt_author = conn.prepareStatement(query_author);
			myStmt_author.setString(1, author);
			myStmt_author.setInt(2, author_id);
			myStmt_author.executeUpdate();
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
