package models;

import classes.*;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class PromoDAO {
	
	public Promo getCurrentPromotion() {
		Promo promo = null;
		String query = "SELECT sp.promotion_name, sp.percentage_off, COUNT(bp.book_id) as numberOfBooks FROM seasonal_promotions as sp JOIN book_promotions as bp ON bp.promotion_id = sp.promotion_id WHERE sp.promotion_id = ?;";
		Connection conn = Database.connect();

		try {
			LocalDate today = LocalDate.now();
			int promotion_id = today.getDayOfWeek().getValue();

			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, promotion_id);
			ResultSet rs = myStmt.executeQuery();
			
			if (rs.next()) {
				String promotion_name = rs.getString("promotion_name");
				double discount = rs.getDouble("percentage_off");
				int noOfBooks = rs.getInt("numberOfBooks");
				promo = new Promo(promotion_name, discount, noOfBooks);
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

}
