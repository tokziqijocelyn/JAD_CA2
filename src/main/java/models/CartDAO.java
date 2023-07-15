package models;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;

import java.text.DecimalFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Cart;
import classes.Checkout;
import classes.Database;



public class CartDAO {
	
	public ArrayList<Cart> getCartItems(int cust_id){
		ArrayList<Cart> cartItems = new ArrayList<Cart>();
		
		Connection conn = Database.connect();
		String bookQuery = "SELECT books.title, ROUND(books.price, 2) AS price, ROUND(books.price * SUM(cart.quantity), 2) AS amount, book_category.category_name AS category, SUM(cart.quantity) AS quantity, books.image, books.quantity AS totalqty, books.book_id, ROUND((SELECT SUM(books.price * cart.quantity) FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN cart ON cart.book_id = books.book_id WHERE cart.cust_id = ?), 2) AS total_price FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN cart ON cart.book_id = books.book_id WHERE cart.cust_id = ? GROUP BY books.book_id;";
		
		try {
			
			PreparedStatement myStmt = conn.prepareStatement(bookQuery);
			myStmt.setInt(1, cust_id);
			myStmt.setInt(2, cust_id);
			ResultSet rs = myStmt.executeQuery();
			
			while(rs.next()) {
				Boolean has = false;
				DecimalFormat decimalFormat = new DecimalFormat("#.00");
				
				String title = rs.getString("title");
				Float price = rs.getFloat("price");
				Float amount = rs.getFloat("amount");
				
				String category = rs.getString("category");
				int quantity  = rs.getInt("quantity");
				String img = rs.getString("image");
				int inventoryLeft = rs.getInt("totalqty");
				int book_id = rs.getInt("book_id");;
				float totalPrice = rs.getFloat("total_price");;
				totalPrice = Float.parseFloat(decimalFormat.format(totalPrice));
				cartItems.add(new Cart(title, price, amount, category, quantity, img, inventoryLeft, book_id, totalPrice));
			}
		
					
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		
		return cartItems;
	}
	
	public boolean addToCart(int cust_id, int book_id, int qty) {
		boolean success = false;
		
		Connection conn = Database.connect();
        String cartQuery = "INSERT INTO cart (book_id, cust_id, quantity) VALUES (?,?,?);";

		  try {
		        PreparedStatement cartStmt = conn.prepareStatement(cartQuery);
		        cartStmt.setInt(1, book_id);
		        cartStmt.setInt(2, cust_id);
		        cartStmt.setInt(3, qty);
		        cartStmt.executeUpdate();

		        success = true;
		    }	catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
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
		
		return success;
		
	}
	
	public boolean updateCart(int cust_id, int book_id, int qty, String arith) {
		
		boolean success = false;
		
		String query = "UPDATE cart SET quantity = quantity WHERE book_id = ? AND cust_id = ?;";
		String limitQuery = "SELECT books.quantity FROM books WHERE book_id = ?";
		int limit = 0;
	
		Connection conn = Database.connect();
		
		try {	
			PreparedStatement myStmt = conn.prepareStatement(limitQuery);
			myStmt.setInt(1, book_id);
			ResultSet rs = myStmt.executeQuery();
			
			if (rs.next()) {
				limit = rs.getInt("quantity");
			}
			
		}catch(Exception e) {
			System.out.print(e);
		}
		
		if (arith.equals("+") && qty<limit) {
			query = "UPDATE cart SET quantity = (quantity + 1) WHERE book_id = ? AND cust_id = ?;";
		} else if (arith.equals("-")&&qty>1) {
			query = "UPDATE cart SET quantity = (quantity - 1) WHERE book_id = ? AND cust_id = ?;";
		}
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, book_id);
			myStmt.setInt(2, cust_id);
	        myStmt.executeUpdate();

	        conn.close();
	        
	        success = true;
	        
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
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
		
		
		return success;        
	}
	
	
	public boolean deleteCart(int cust_id, int book_id) {
		boolean success = false; 
        Connection conn = Database.connect();
        
        String query = "DELETE from cart WHERE cust_id = ? AND book_id = ?";
        
        try {
        	PreparedStatement myStmt = conn.prepareStatement(query);
        	myStmt.setInt(1, cust_id);
        	myStmt.setInt(2, book_id);
        	myStmt.executeUpdate();
        	
        	conn.close();
        	
        	success = true;
        	
        }catch (SQLException e) {
    		e.printStackTrace();
		} catch (Exception e) {
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
		
		return success;
	}
	
	public String checkout(int cust_id, ArrayList<Cart> checkout) {
		String success = "error";
		
		String orderQuery = "INSERT INTO orders (customer_id, address_id, total_price) SELECT ?, address_id, ? AS total_price FROM customer_address WHERE customer_id = ?;";
		String orderBookQuery = "INSERT INTO order_book (orders_id, book_id, quantity) VALUES (?,?,?);";
		String updateQtyQuery = "UPDATE books SET quantity = (quantity - ?) WHERE book_id = ? ";
		String deleteCart = "DELETE FROM cart WHERE cust_id = ?";
		
		Connection conn = Database.connect();
		
		try {
			PreparedStatement orderStmt = conn.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);
			orderStmt.setInt(1, cust_id);
			orderStmt.setInt(3, cust_id);
			orderStmt.setFloat(2, checkout.get(0).getTotal_price());
	
			int id = orderStmt.executeUpdate();
			System.out.println("HIS IS THE ID" + id);
			
			int insertedId = 0;
			System.out.println("This is like the generated keys + + " + orderStmt.getGeneratedKeys());
			ResultSet generatedKeys = orderStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
			    insertedId = generatedKeys.getInt(1);
			    System.out.println("Inserted ID: " + insertedId);
			} else {
			    System.out.println("Failed to retrieve inserted ID. ++ "+generatedKeys.getInt(1));
			    throw new Error("Failed to retrieve inserted ID.");
			}
			
			
			for (Cart order: checkout) {
				PreparedStatement orderBookStmt = conn.prepareStatement(orderBookQuery);
				PreparedStatement qtyStmt = conn.prepareStatement(updateQtyQuery);
				orderBookStmt.setInt(1,insertedId);
				orderBookStmt.setInt(2, order.getBook_id());
				orderBookStmt.setInt(3, order.getQuantity());
				
				qtyStmt.setInt(2, order.getBook_id());
				qtyStmt.setInt(1, order.getQuantity());
				
				orderBookStmt.executeUpdate();
				qtyStmt.executeUpdate();
			}
			
			PreparedStatement deleteStmt = conn.prepareStatement(deleteCart);
			deleteStmt.setInt(1, cust_id);
			deleteStmt.executeUpdate();
			
			success = "successCheckedout";
			
		} catch (SQLException e) {
	    		e.printStackTrace();
			} catch (Exception e) {
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
		
		return success;
	}
}
