package models;

import classes.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BookDAO {

	public ArrayList<Book> getBooksByCategoryId(int cat_id) {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name as publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) as decimal(1,0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) as rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) as discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE book_category.category_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, cat_id);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public ArrayList<Book> getBooksByPusliherId(int pub_id) {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name as publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) as decimal(1,0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) as rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) as discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE publisher.publisher_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, pub_id);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public ArrayList<Book> getBooksByRating() {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name as publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) as decimal(1,0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) as rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) as discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id ORDER BY rating DESC";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public ArrayList<Book> getBooksByPopularity() {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name AS publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) as decimal(1,0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY reviews.book_id), 0) as rating, books.price, ROUND(books.price - MAX(books.price * seasonal_promotions.percentage_off), 2) as discounted_price, book_category.category_name, books.image, authors.name FROM order_book JOIN books ON order_book.book_id = books.book_id JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id GROUP BY books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name, books.book_id, books.title, books.description, books.price, book_category.category_name, books.image, authors.name ORDER BY COUNT(books.book_id) DESC";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public ArrayList<Book> getBooksBySearch(String search) {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name as publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) as decimal(1,0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) as rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) as discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE books.title LIKE ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, "%" + search + "%");
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public ArrayList<Book> getAllBooks() {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name AS publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) AS DECIMAL(1, 0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) AS rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) AS discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public Book getBookById(int bookId) {
		Connection conn = Database.connect();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, (books.quantity - COALESCE((SELECT SUM(cart.quantity) FROM cart WHERE cart.book_id = ?), 0)) AS quantity, books.ISBN, publisher.name as publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) as decimal(1,0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) as rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) as discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE books.book_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, bookId);
			myStmt.setInt(2, bookId);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					return (new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					return (new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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

	public String getBookImg(int book_id) {
		String imagePath = "/images/default.jpg";

		Connection conn = Database.connect();
		String queryImg = "SELECT image FROM jad_ca1.books WHERE book_id = ?;";

		try {

			PreparedStatement Stmt = conn.prepareStatement(queryImg);
			Stmt.setInt(1, book_id);
			ResultSet rs = Stmt.executeQuery();
			while (rs.next()) {
				imagePath = rs.getString("image");
			}
			System.out.println(imagePath);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.print("KAUWGHD");

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return imagePath;
	}

	public boolean updateBookByID(String imagePath, String title, String ISBN, Float price, Integer quantity,
			String description, Integer category, Integer author, Integer publisher_id, Integer book_id) {
		boolean success = false;
		Connection conn = Database.connect();
		String query = "UPDATE books SET title = ?, author_id = ?, price = ?, quantity = ?, ISBN = ?, book_category_id = ?, description = ?, image = ?, publisher_id = ? WHERE book_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, title);
			myStmt.setInt(2, author);
			myStmt.setFloat(3, price);
			myStmt.setInt(4, quantity);
			myStmt.setString(5, ISBN);
			myStmt.setInt(6, category);
			myStmt.setString(7, description);
			myStmt.setString(8, imagePath);
			myStmt.setInt(9, publisher_id);
			myStmt.setInt(10, book_id);
			myStmt.executeUpdate();
			conn.close();
			success = true;
		} catch (Exception e) {
			System.out.println("theres a fucmign error");
			System.out.println(e);
		}

		return success;

	}

	public ArrayList<Book> getBestSellingBooksToday() {
		Connection conn = Database.connect();
		String query = "SELECT b.book_id, b.title, SUM(ob.quantity) AS total_quantity FROM order_book ob JOIN orders o ON ob.orders_id = o.orders_id JOIN books b ON ob.book_id = b.book_id WHERE DATE(o.order_created) = CURRENT_DATE() GROUP BY b.book_id, b.title ORDER BY total_quantity DESC LIMIT 10";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				Integer total_quantity = rs.getInt("total_quantity");

				books.add(new Book(book_id, title, total_quantity));
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

	public ArrayList<Book> getBestSellingBooksWeek() {
		Connection conn = Database.connect();
		String query = "SELECT b.book_id, b.title, SUM(ob.quantity) AS total_quantity FROM order_book ob JOIN orders o ON ob.orders_id = o.orders_id JOIN books b ON ob.book_id = b.book_id WHERE WEEK(o.order_created) = WEEK(CURRENT_DATE()) GROUP BY b.book_id, b.title ORDER BY total_quantity DESC LIMIT 10";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				Integer total_quantity = rs.getInt("total_quantity");

				books.add(new Book(book_id, title, total_quantity));
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

	public ArrayList<Book> getBestSellingBooksMonth() {
		Connection conn = Database.connect();
		String query = "SELECT b.book_id, b.title, SUM(ob.quantity) AS total_quantity FROM order_book ob JOIN orders o ON ob.orders_id = o.orders_id JOIN books b ON ob.book_id = b.book_id WHERE DATE_FORMAT(o.order_created, '%Y-%m') = DATE_FORMAT(CURRENT_DATE(), '%Y-%m') GROUP BY b.book_id, b.title ORDER BY total_quantity DESC LIMIT 10";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			ArrayList<Book> books = new ArrayList<>();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				Integer total_quantity = rs.getInt("total_quantity");

				books.add(new Book(book_id, title, total_quantity));
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

	public String addBook(String imagePath, String title, String ISBN, Float price, Integer quantity,
			String description, Integer category, Integer author, Integer publisher_id) {

		String code = "error";

		Connection conn = Database.connect();
		String query = "INSERT INTO books (title, author_id, price, quantity, ISBN, book_category_id, description, image, publisher_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";

		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setString(1, title);
			myStmt.setInt(2, author);
			myStmt.setFloat(3, price);
			myStmt.setInt(4, quantity);
			myStmt.setString(5, ISBN);
			myStmt.setInt(6, category);
			myStmt.setString(7, description);
			myStmt.setString(8, imagePath);
			myStmt.setInt(9, publisher_id);
			myStmt.executeUpdate();

			code = "success";

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

		return code;

	}

	public String deleteBookById(int book_id) {
		String code = "error";
		Connection conn = Database.connect();
		String query = "DELETE FROM books WHERE book_id= ?";

		try {

			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, book_id);
			myStmt.executeUpdate();
			code = "deleteBook";

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return code;

	}

	public ArrayList<Book> getDiscountedBooks(int day) {
		String query = "SELECT * FROM (SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name AS publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) AS DECIMAL(1, 0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) AS rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) AS discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id) AS subquery JOIN book_promotions AS bp ON bp.book_id = subquery.book_id WHERE bp.promotion_id = ?";
		ArrayList<Book> books = new ArrayList<>();

		Connection conn = Database.connect();

		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, day);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = null;
				String ISBN = null;
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = null;
				String image = rs.getString("image");
				Integer author_id = null;
				String creation_date = null;
				Integer quantity = null;
				Integer publisher_id = null;
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
						creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
						discounted_price));

			}
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return books;
	}

	public ArrayList<Book> getNoStockBooks() {
		Connection conn = Database.connect();
		ArrayList<Book> books = new ArrayList<>();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name AS publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) AS DECIMAL(1, 0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) AS rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) AS discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE books.quantity = 0";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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
		return books;
	}

	public ArrayList<Book> getLowStockBooks() {
		Connection conn = Database.connect();
		ArrayList<Book> books = new ArrayList<>();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name AS publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) AS DECIMAL(1, 0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) AS rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) AS discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE books.quantity < 100  AND books.quantity > 0";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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
		return books;
	}

	public ArrayList<Book> getHighStockBooks() {
		Connection conn = Database.connect();
		ArrayList<Book> books = new ArrayList<>();
		String query = "SELECT books.creation_date, books.book_category_id, books.author_id, books.publisher_id, books.quantity, books.ISBN, publisher.name AS publisher_name, books.book_id, books.title, books.description, IFNULL((SELECT CAST(AVG(IFNULL(reviews.rating, 0.0)) AS DECIMAL(1, 0)) FROM reviews WHERE reviews.book_id = books.book_id GROUP BY books.book_id), 0) AS rating, books.price, ROUND(books.price - books.price * seasonal_promotions.percentage_off, 2) AS discounted_price, book_category.category_name, books.image, authors.name FROM books JOIN book_category ON books.book_category_id = book_category.category_id JOIN publisher ON books.publisher_id = publisher.publisher_id JOIN authors ON books.author_id = authors.author_id LEFT JOIN book_promotions ON books.book_id = book_promotions.book_id AND DAYOFWEEK(CURRENT_DATE) - 1 = book_promotions.promotion_id LEFT JOIN seasonal_promotions ON book_promotions.promotion_id = seasonal_promotions.promotion_id WHERE books.quantity > 100  AND books.quantity > 0";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			while (rs.next()) {
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = rs.getInt("rating");
				Float price = rs.getFloat("price");
				Integer category_id = rs.getInt("book_category_id");
				String image = rs.getString("image");
				Integer author_id = rs.getInt("author_id");
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = rs.getInt("publisher_id");
				String author_name = rs.getString("name");
				String publisher_name = rs.getString("publisher_name");
				String category_name = rs.getString("category_name");
				Float discounted_price = rs.getFloat("discounted_price");

				if (discounted_price == 0.0) {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name));
				} else {
					books.add(new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
							creation_date, quantity, publisher_id, author_name, publisher_name, category_name,
							discounted_price));
				}
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
		return books;
	}

	public void updateBookQuantity(Integer book_id, Integer quantity) {
		Connection conn = Database.connect();
		String query = "UPDATE books SET quantity = ? WHERE book_id = ?";
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			myStmt.setInt(1, quantity);
			myStmt.setInt(2, book_id);

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

	public Book getBestSellingBook() {
		
		Book book = null;
		String query = "SELECT b.*, SUM(ob.quantity) AS total_quantity FROM order_book ob JOIN orders o ON ob.orders_id = o.orders_id JOIN books b ON ob.book_id = b.book_id WHERE DATE_FORMAT(o.order_created, '%Y-%m') = DATE_FORMAT(CURRENT_DATE(), '%Y-%m') GROUP BY b.book_id, b.title ORDER BY total_quantity DESC LIMIT 1";
		Connection conn = Database.connect();
		System.out.print("enter");
		
		try {
			PreparedStatement myStmt = conn.prepareStatement(query);
			ResultSet rs = myStmt.executeQuery();
			System.out.print("hello");
		
			if (rs.next()) {
				System.out.print(rs.getInt("book_id"));
				Integer book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				System.out.println("This is what gets print out: " + title);
				String description = rs.getString("description");
				String ISBN = rs.getString("ISBN");
				Integer rating = 0;
				Float price = rs.getFloat("price");
				Integer category_id = 0;
				String image = rs.getString("image");
				Integer author_id = 0;
				String creation_date = rs.getString("creation_date");
				Integer quantity = rs.getInt("quantity");
				Integer publisher_id = 0;
				String author_name = "Author";
				String publisher_name = "Publisher";
				String category_name = "Category";

				book = new Book(book_id, title, description, ISBN, rating, price, category_id, image, author_id,
						creation_date, quantity, publisher_id, author_name, publisher_name, category_name);
			} else {
				System.out.print("12");
				book = new Book(0, "Empty", 0);
			}
			
		} catch (NullPointerException e) {
	
			book = new Book(0, "", 0);
			
		}catch (Exception e) {
		
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
		return book;
		
	}

}
