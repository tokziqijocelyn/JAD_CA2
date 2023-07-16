package classes;

import java.sql.*;
//test
public class Database {
	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String connURL = "jdbc:mysql://localhost:3306/jad_ca1?user=JAD&password=JAD&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			return conn;
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
	}

	public static ResultSet executeSQL(PreparedStatement ps) {
		try {
			ResultSet rs = ps.executeQuery();
			return rs;
		} catch (Exception e) {
			return null;
		}
	}
}