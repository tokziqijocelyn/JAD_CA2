package models;

import classes.*;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class PromoDAO {
	
	public Promo getCurrentPromotion() {
		Promo promo = new Promo();
		String query = "SELECT sp.promotion_name, sp.percentage_off, COUNT(bp.book_id) as numberOfBooks FROM seasonal_promotions as sp JOIN book_promotions as bp ON bp.promotion_id = sp.promotion_id WHERE sp.promotion_id = ?;";
		Connection conn = Database.connect();

		try {
			LocalDate today = LocalDate.now();
			int promotion_id = today.getDayOfWeek().getValue();

			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, promotion_id);
			ResultSet rs = myStmt.executeQuery();
			
			if (rs.next()) {
				
				promo.setPromoName(rs.getString("promotion_name"));
				promo.setDiscount(rs.getDouble("percentage_off"));
				promo.setNoOfBooks(rs.getInt("numberOfBooks"));
			}
			
		} 
		
		catch (Exception e ) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return promo;
	}
	
	public ArrayList<Promo> getAllPromos(){
		
		ArrayList<Promo> allPromos = new ArrayList<Promo>();

		String query = "SELECT sp.promotion_id, sp.promotion_name, sp.percentage_off, COALESCE(COUNT(bp.book_id), 0) AS numberOfBooks FROM seasonal_promotions AS sp LEFT JOIN book_promotions AS bp ON bp.promotion_id = sp.promotion_id GROUP BY sp.promotion_name, sp.percentage_off;";
		Connection conn = Database.connect();

		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			
			while (rs.next()) {
				Promo promo = new Promo();
				promo.setPromoName(rs.getString("promotion_name"));
				promo.setDiscount(rs.getDouble("percentage_off"));
				promo.setNoOfBooks(rs.getInt("numberOfBooks"));
				promo.setPromotionId(rs.getInt("promotion_id"));
				allPromos.add(promo);
			}
		} 
		
		catch (Exception e ) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return allPromos;
	}
	
	public String updateBookPromo(int book_id, int[] promos) {
		String code = "";
		Connection conn = Database.connect();
		String deleteQuery = "DELETE FROM book_promotions WHERE book_id = ?;";
		String insertQuery = "INSERT INTO book_promotions (book_id, promotion_id) VALUES (?, ?);";
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(deleteQuery);
			myStmt.setInt(1, book_id);
			ResultSet rs = myStmt.executeQuery();
			
			for (int promo : promos) {
				
			}
		} 
		
		catch (Exception e ) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return code;
	}

}
