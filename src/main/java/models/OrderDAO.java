package models;

import classes.*;
import java.sql.*;
import java.util.*;
import java.time.*;

public class OrderDAO {

	public ArrayList<Order> getAllOrders() {
		Connection conn = Database.connect();
		String query = "SELECT orders.orders_id, orders.total_price, customer.username, customer_address.block, customer_address.postal_code, customer_address.unit_no, customer_address.street, customer.email, DATE(orders.order_created) as order_date, books.title, books.ISBN, order_book.quantity FROM orders JOIN order_book ON orders.orders_id = order_book.orders_id JOIN books ON order_book.book_id = books.book_id JOIN customer ON orders.customer_id = customer.customer_id JOIN customer_address ON customer.customer_id = customer_address.customer_id ORDER BY order_date";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				int orders_id = rs.getInt("orders_id");
				double total_price = rs.getDouble("total_price");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String block = rs.getString("block");
				String postal_code = rs.getString("postal_code");
				String unit_no = rs.getString("unit_no");
				String street = rs.getString("street");
				String order_date = rs.getString("order_date");
				String title = rs.getString("title");
				String ISBN = rs.getString("ISBN");
				int quantity = rs.getInt("quantity");

				orders.add(new Order(orders_id, total_price, username, email, block, postal_code, unit_no, street,
						order_date, title, ISBN, quantity));
			}

			return orders;
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
	
	public ArrayList<Order> getAllOrdersByDate(String sqlStartDate, String sqlEndDate) {
		Connection conn = Database.connect();
		String query = "SELECT orders.orders_id, orders.total_price, customer.username, customer_address.block, customer_address.postal_code, customer_address.unit_no, customer_address.street, customer.email, DATE(orders.order_created) as order_date, books.title, books.ISBN, order_book.quantity FROM orders JOIN order_book ON orders.orders_id = order_book.orders_id JOIN books ON order_book.book_id = books.book_id JOIN customer ON orders.customer_id = customer.customer_id JOIN customer_address ON customer.customer_id = customer_address.customer_id WHERE orders.order_created BETWEEN ? AND ? ORDER BY order_date";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, sqlStartDate);
			myStmt.setString(2, sqlEndDate);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				int orders_id = rs.getInt("orders_id");
				double total_price = rs.getDouble("total_price");
				String username = rs.getString("username");
				String email = rs.getString("email");
				String block = rs.getString("block");
				String postal_code = rs.getString("postal_code");
				String unit_no = rs.getString("unit_no");
				String street = rs.getString("street");
				String order_date = rs.getString("order_date");
				String title = rs.getString("title");
				String ISBN = rs.getString("ISBN");
				int quantity = rs.getInt("quantity");

				orders.add(new Order(orders_id, total_price, username, email, block, postal_code, unit_no, street,
						order_date, title, ISBN, quantity));
			}

			return orders;
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
