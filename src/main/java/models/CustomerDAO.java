package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import classes.Book;
import classes.Customer;
import classes.Database;
import classes.Order;

import java.util.*;

public class CustomerDAO {

	public Customer initCustomerById(int cust_id) {
		Connection conn = Database.connect();
		String userQuery = "SELECT customer.customer_id, customer.username, customer.email, customer.password, customer_address.block, customer_address.postal_code, customer_address.unit_no, customer_address.street, customer.registered_date, customer.image_url FROM customer JOIN customer_address ON customer.customer_id = customer_address.customer_id WHERE customer.customer_id=?";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(userQuery);
			myStmt.setInt(1, cust_id);
			ResultSet rs = myStmt.executeQuery();
			if (!rs.next()) {
				return new Customer(false);
			} else {
				return new Customer(rs.getInt("customer_id"), rs.getString("email"), rs.getString("username"), true,
						rs.getString("password"), rs.getString("block"), rs.getString("postal_code"),
						rs.getString("unit_no"), rs.getString("street"), rs.getString("registered_date"),
						rs.getString("image_url"));
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

	public Customer initCustomerBySession(HttpSession session) {
		Integer cust_id = (Integer) session.getAttribute("cust_id");
		System.out.print("CUST ID RIGHT NOW!!!!" + cust_id);
		if (cust_id == null) {
			return new Customer(false);
		} 
		Connection conn = Database.connect();
		String userQuery = "SELECT customer.customer_id, customer.username, customer.email, customer.password, customer_address.block, customer_address.postal_code, customer_address.unit_no, customer_address.street, customer.registered_date, customer.image_url FROM customer JOIN customer_address ON customer.customer_id = customer_address.customer_id WHERE customer.customer_id=?";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(userQuery);
			myStmt.setInt(1, cust_id);
			ResultSet rs = myStmt.executeQuery();
			if (!rs.next()) {
				System.out.print("There was no rs.next()");
				return new Customer(false);
			} else {
				System.out.print("It reached this part");
				return new Customer(rs.getInt("customer_id"), rs.getString("email"), rs.getString("username"), true,
						rs.getString("password"), rs.getString("block"), rs.getString("postal_code"),
						rs.getString("unit_no"), rs.getString("street"), rs.getString("registered_date"),
						rs.getString("image_url"));
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

	public int loginCustomer(String email, String password) {
		Connection conn = Database.connect();
		String query = "SELECT customer_id FROM customer WHERE email = ? and password = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, email);
			myStmt.setString(2, password);

			ResultSet rs = myStmt.executeQuery();
			if (!rs.next()) {
				return -1;
			} else {
				Integer cust_id = rs.getInt("customer_id");
				System.out.println("THIS IS THE CUST ID"+cust_id);
				return cust_id;
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

	public ArrayList<Customer> getAllCustomers() {
		Connection conn = Database.connect();
		String query = "SELECT customer.customer_id, customer.username, customer.email, customer.password, customer_address.block, customer_address.postal_code, customer_address.unit_no, customer_address.street, customer.registered_date, customer.image_url FROM customer JOIN customer_address ON customer.customer_id = customer_address.customer_id";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("customer_id"), rs.getString("email"), rs.getString("username"),
						true, rs.getString("password"), rs.getString("block"), rs.getString("postal_code"),
						rs.getString("unit_no"), rs.getString("street"), rs.getString("registered_date"),
						rs.getString("image_url")));
			}
			return customers;
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
	
	public ArrayList<Customer> getAllCustomersByDate(String sqlStartDate, String sqlEndDate) {
		Connection conn = Database.connect();
		String query = "SELECT customer.customer_id, customer.username, customer.email, customer.password, customer_address.block, customer_address.postal_code, customer_address.unit_no, customer_address.street, customer.registered_date, customer.image_url FROM customer JOIN customer_address ON customer.customer_id = customer_address.customer_id WHERE customer.registered_date BETWEEN ? AND ? ORDER BY customer.registered_date;";
		PreparedStatement myStmt;
		try {
			
			myStmt = conn.prepareStatement(query);
			myStmt.setString(1, sqlStartDate);
			myStmt.setString(2, sqlEndDate);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("customer_id"), rs.getString("email"), rs.getString("username"),
						true, rs.getString("password"), rs.getString("block"), rs.getString("postal_code"),
						rs.getString("unit_no"), rs.getString("street"), rs.getString("registered_date"),
						rs.getString("image_url")));
			}
			return customers;
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
	
	

	public ArrayList<Order> getCustomerOrders(int customer_id) {
		Connection conn = Database.connect();
		String query = "SELECT orders.orders_id, orders.order_created, books.title, books.ISBN, orders.total_price, order_book.quantity FROM orders JOIN order_book ON orders.orders_id = order_book.orders_id JOIN books ON order_book.book_id = books.book_id WHERE orders.customer_id = ? ORDER BY orders.orders_id";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, customer_id);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Order> orders = new ArrayList<>();
			while (rs.next()) {
				int orders_id = rs.getInt("orders_id");
				double total_price = rs.getDouble("total_price");
				String order_date = rs.getString("order_created");
				String title = rs.getString("title");
				String ISBN = rs.getString("ISBN");
				int quantity = rs.getInt("quantity");

				orders.add(new Order(orders_id, total_price, order_date, title, ISBN, quantity));
			}
			return orders;
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

	public ArrayList<Customer> getTopCustomersToday() {
		Connection conn = Database.connect();
		String query = "SELECT c.username, SUM(o.total_price) AS amount_spent FROM orders o JOIN customer c ON c.customer_id = o.customer_id WHERE DATE(o.order_created) = CURDATE() GROUP BY o.customer_id ORDER BY amount_spent DESC LIMIT 10;";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				String username = rs.getString("username");
				double amount_spent = rs.getDouble("amount_spent");
				customers.add(new Customer(username, amount_spent));
			}
			return customers;
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
	
	public ArrayList<Customer> getTopCustomersWeek() {
		Connection conn = Database.connect();
		String query = "SELECT c.username, SUM(o.total_price) AS amount_spent FROM orders o JOIN customer c ON c.customer_id = o.customer_id WHERE YEARWEEK(o.order_created) = YEARWEEK(CURDATE()) GROUP BY o.customer_id ORDER BY amount_spent DESC LIMIT 10";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				String username = rs.getString("username");
				double amount_spent = rs.getDouble("amount_spent");
				customers.add(new Customer(username, amount_spent));
			}
			return customers;
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
	
	public ArrayList<Customer> getTopCustomersMonth() {
		Connection conn = Database.connect();
		String query = "SELECT c.username, SUM(o.total_price) AS amount_spent FROM orders o JOIN customer c ON c.customer_id = o.customer_id WHERE EXTRACT(YEAR_MONTH FROM o.order_created) = EXTRACT(YEAR_MONTH FROM CURDATE()) GROUP BY o.customer_id ORDER BY amount_spent DESC LIMIT 10";
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				String username = rs.getString("username");
				double amount_spent = rs.getDouble("amount_spent");
				customers.add(new Customer(username, amount_spent));
			}
			return customers;
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
	
	public boolean updateCustomerByID(String imagePath, String username, String email, String pwd, String block, String street, String unit, int postal, Integer cust_id) {
		boolean success = false;
	    Connection conn = Database.connect();
	    System.out.println("Update customer in customerdao ran");
		String query = "UPDATE customer SET username = ?, email = ?, password = ?, image_url = ? WHERE customer_id = ?";
		String query_address = "UPDATE customer_address SET block = ?, street = ?, unit_no = ?, postal_code = ? WHERE customer_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			PreparedStatement myStmt_address = conn.prepareStatement(query_address);
			myStmt.setString(1, username);
			myStmt.setString(2, email);
			myStmt.setString(3, pwd);
			myStmt.setString(4, imagePath);
			myStmt.setInt(5, cust_id);
			myStmt_address.setString(1, block);
			myStmt_address.setString(2, street);
			myStmt_address.setString(3, unit);
			myStmt_address.setInt(4, postal);
			myStmt_address.setInt(5, cust_id);
			myStmt.executeUpdate();
			myStmt_address.executeUpdate();
			success = true;
	    } catch (NullPointerException e) {
	        System.out.println("NULL ERROR IN CUSTDAO 2");
	    } catch (Exception e) {
	        System.out.println("VAGUE ERROR! 2!!");
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
	
	public int deleteCustomerByID(int cust_id) {
		int success = 0;
		Connection conn = Database.connect();
		String query = "DELETE FROM customer WHERE customer_id= ?";
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, cust_id);
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
	
	public String addCustomer(String imagePath, String username, String email, String pwd, String block, String street, String unit, int postal, Integer cust_id) {
		String success = "error";
		Connection conn = Database.connect();
		String query = "INSERT INTO customer (username, email, password, image_url) VALUES (?,?,?,?);";
		String addQuery = "INSERT INTO customer_address (customer_id, block, postal_code, unit_no, street) VALUES (?,?,?,?,?);";
		
		try {
            PreparedStatement orderStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setString(1, username);
            orderStmt.setString(2, email);
            orderStmt.setString(3, pwd);
            orderStmt.setString(4, imagePath);
            orderStmt.executeUpdate();

            int insertedId = 0;

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                insertedId = generatedKeys.getInt(1);
                System.out.println("Inserted ID: " + insertedId);
            } else {
                System.out.println("Failed to retrieve inserted ID.");
                throw new Error("Failed to retrieve inserted ID.");
            }

            PreparedStatement addStmt = conn.prepareStatement(addQuery);
            addStmt.setInt(1, insertedId);
            addStmt.setString(2, block);
            addStmt.setInt(3, postal);
            addStmt.setString(4, unit);
            addStmt.setString(5, street);
            addStmt.executeUpdate();
            
            success = "success";
		}catch (SQLIntegrityConstraintViolationException e) {
            success = "duplicate";
        } catch (Exception e) {
            System.out.print(e);
            success = "error";
        }
		
		return success;
	}
	
}
