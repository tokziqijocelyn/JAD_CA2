package classes;

public class Customer {
	
	private int cust_id;
	private String email;
	private String username;
	private boolean authenticated;
	private String password;
	private String block; 
	private String postal_code;
	private String unit_no;
	private String street;
	private String registered_date;
	private String image_url; 
	private double amount_spent;
	
	public Customer (boolean verified) {
		this.authenticated = verified;
	}
	
	public Customer(int cust_id, String email, String username, boolean authenticated, String password, String block,
			String postal_code, String unit_no, String street, String registered_date, String image_url) {
		super();
		this.cust_id = cust_id;
		this.email = email;
		this.username = username;
		this.authenticated = authenticated;
		this.password = password;
		this.block = block;
		this.postal_code = postal_code;
		this.unit_no = unit_no;
		this.street = street;
		this.registered_date = registered_date;
		this.image_url = image_url;
	}

	public Customer(String username, double amount_spent) {
		super();
		this.username = username;
		this.amount_spent = amount_spent;
	}

	public double getAmount_spent() {
		return amount_spent;
	}

	public void setAmount_spent(double amount_spent) {
		this.amount_spent = amount_spent;
	}

	public int getCust_id() {
		return cust_id;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public String getPassword() {
		return password;
	}

	public String getBlock() {
		return block;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public String getUnit_no() {
		return unit_no;
	}

	public String getStreet() {
		return street;
	}

	public String getRegistered_date() {
		return registered_date;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setRegistered_date(String registered_date) {
		this.registered_date = registered_date;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

}