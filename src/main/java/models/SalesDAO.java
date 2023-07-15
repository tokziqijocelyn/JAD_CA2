package models;

import classes.*;
import java.sql.*;
import java.util.*;

public class SalesDAO {
	
	public ArrayList<Sales> getTodaySales() {
		Connection conn = Database.connect();
		String query = "SELECT CASE WHEN HOUR(order_created) >= 0 AND HOUR(order_created) < 6 THEN '12am - 6am' WHEN HOUR(order_created) >= 6 AND HOUR(order_created) < 12 THEN '6am - 12pm' WHEN HOUR(order_created) >= 12 AND HOUR(order_created) < 18 THEN '12pm - 6pm' WHEN HOUR(order_created) >= 18 THEN '6pm - 12am' END AS time_period, SUM(total_price) AS total_price FROM orders WHERE DATE(order_created) = CURDATE() GROUP BY time_period ORDER BY MIN(order_created)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Sales> sales = new ArrayList<>();
			while (rs.next()) {
				String time_period = rs.getString("time_period");
				double total_price = rs.getDouble("total_price");
				sales.add(new Sales(total_price, time_period));
			}

			return sales;
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
	
	public ArrayList<Sales> getWeekSales() {
		Connection conn = Database.connect();
		String query = "SELECT DATE(order_created) AS order_date, SUM(total_price) AS total_price FROM orders WHERE WEEK(order_created) = WEEK(CURDATE()) AND YEAR(order_created) = YEAR(CURDATE()) GROUP BY DATE(order_created) ORDER BY DATE(order_created)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Sales> sales = new ArrayList<>();
			while (rs.next()) {
				String order_date = rs.getString("order_date");
				double total_price = rs.getDouble("total_price");
				sales.add(new Sales(order_date, total_price));
			}

			return sales;
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
	
	public ArrayList<Sales> getMonthSales() {
		Connection conn = Database.connect();
		String query = "SELECT DATE(order_created) AS order_date, SUM(total_price) AS total_price FROM orders WHERE MONTH(order_created) = MONTH(CURDATE()) AND YEAR(order_created) = YEAR(CURDATE()) GROUP BY DATE(order_created) ORDER BY DATE(order_created)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Sales> sales = new ArrayList<>();
			while (rs.next()) {
				String order_date = rs.getString("order_date");
				double total_price = rs.getDouble("total_price");
				sales.add(new Sales(order_date, total_price));
			}

			return sales;
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
	
	public ArrayList<Book> getBookMonthSales(int book_id) {
		Connection conn = Database.connect();
		String query = "SELECT DATE(o.order_created) AS order_date, SUM(ob.quantity) AS total_books_sold FROM orders o INNER JOIN order_book ob ON o.orders_id = ob.orders_id WHERE ob.book_id = ? AND MONTH(o.order_created) = MONTH(CURDATE()) AND YEAR(o.order_created) = YEAR(CURDATE()) GROUP BY DATE(o.order_created) ORDER BY DATE(o.order_created)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, book_id);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				String time_period = rs.getString("order_date");
				int total_books_sold = rs.getInt("total_books_sold");
				books.add(new Book(time_period, total_books_sold ));
			}
			return books;
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
	
	public ArrayList<Book> getBookWeekSales(int book_id) {
		Connection conn = Database.connect();
		String query = "SELECT DATE(o.order_created) AS order_date, SUM(ob.quantity) AS total_books_sold FROM orders o INNER JOIN order_book ob ON o.orders_id = ob.orders_id WHERE ob.book_id = ? AND WEEK(o.order_created) = WEEK(CURDATE()) AND YEAR(o.order_created) = YEAR(CURDATE()) GROUP BY DATE(o.order_created) ORDER BY DATE(o.order_created)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, book_id);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				String time_period = rs.getString("order_date");
				int total_books_sold = rs.getInt("total_books_sold");
				books.add(new Book(time_period, total_books_sold ));
			}

			return books;
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
	
	public ArrayList<Book> getTodayBookSales(int book_id) {
		Connection conn = Database.connect();
		String query = "SELECT CASE WHEN HOUR(o.order_created) >= 0 AND HOUR(o.order_created) < 6 THEN '12am - 6am' WHEN HOUR(o.order_created) >= 6 AND HOUR(o.order_created) < 12 THEN '6am - 12pm' WHEN HOUR(o.order_created) >= 12 AND HOUR(o.order_created) < 18 THEN '12pm - 6pm' WHEN HOUR(o.order_created) >= 18 THEN '6pm - 12am' END AS time_period, SUM(ob.quantity) AS total_books_sold FROM orders o INNER JOIN order_book ob ON o.orders_id = ob.orders_id WHERE ob.book_id = ? AND DATE(o.order_created) = CURDATE() GROUP BY time_period ORDER BY MIN(o.order_created)";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, book_id);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				String time_period = rs.getString("time_period");
				int total_books_sold = rs.getInt("total_books_sold");
				books.add(new Book(time_period, total_books_sold ));
			}

			return books;
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
	
}
