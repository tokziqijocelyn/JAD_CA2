package models;

import java.util.ArrayList;

import classes.Publisher;
import classes.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PublisherDAO {
	
	public ArrayList<Publisher> getPublisher () {
		Connection conn = Database.connect();
		String query_publisher = "SELECT publisher_id, name FROM publisher";
		try {
			PreparedStatement myStmt_publisher = conn.prepareStatement(query_publisher);
			ResultSet rs_publisher = myStmt_publisher.executeQuery();
			ArrayList<Publisher> publishers = new ArrayList<>();
			while (rs_publisher.next()) {
				publishers.add(new Publisher(rs_publisher.getInt("publisher_id"), rs_publisher.getString("name")));
			}
			return publishers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public void addPublisher(String publisher) {
		Connection conn = Database.connect();
		String query = "INSERT INTO publisher (name) VALUES (?)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, publisher);
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
	
	public void editPublisher(String publisher, int publisher_id) {
		Connection conn = Database.connect();
		String query = "UPDATE publisher set name = ? WHERE publisher_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, publisher);
			myStmt.setInt(2, publisher_id);
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
