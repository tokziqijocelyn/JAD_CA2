package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import classes.Database;
import classes.Review;


public class ReviewDAO {
	
	public ArrayList<Review> findReviewByID(int book_id) {
		ArrayList<Review> review = new ArrayList<>();
		
		try {
			Connection conn = Database.connect();
			String query = "SELECT c.username, r.review, r.rating FROM reviews r JOIN customer c ON r.customer_id = c.customer_id WHERE book_id = ?";
			PreparedStatement myStmt;
			myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, book_id);
			ResultSet rs = myStmt.executeQuery();
			

            while (rs.next()) {
                Review newReview = new Review();
                newReview.setUsername(rs.getString("username"));
                newReview.setReview(rs.getString("review"));
                newReview.setRating(rs.getInt("rating"));
                review.add(newReview);
            }
            
			conn.close();
			
			
		} catch (Exception e) {
			System.err.println("Error :" + e);
		}
		
		
		return review;
	}
	
	public boolean addReview(String review, int cust_id, int book_id, int rating) {
		boolean success = false;
		Connection conn = Database.connect();
		String query = "INSERT INTO reviews (review, rating, customer_id, book_id) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, review);
			myStmt.setInt(2,  rating);
			myStmt.setInt(3, cust_id);
			myStmt.setInt(4, book_id);
			myStmt.executeUpdate();
			
			success = true;
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
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