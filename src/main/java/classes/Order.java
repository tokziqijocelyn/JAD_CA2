package classes;

public class Order {
	
    private int orders_id;
    private double total_price;
    private String username;
    private String email;
    private String block;
    private String postal_code;
    private String unit_no;
    private String street;
    private String order_date;
    private String title;
    private String ISBN;
    private int quantity;
    
	public Order(int orders_id, double total_price, String username, String email, String block, String postal_code,
			String unit_no, String street, String order_date, String title, String iSBN, int quantity) {
		super();
		this.orders_id = orders_id;
		this.total_price = total_price;
		this.username = username;
		this.email = email;
		this.block = block;
		this.postal_code = postal_code;
		this.unit_no = unit_no;
		this.street = street;
		this.order_date = order_date;
		this.title = title;
		ISBN = iSBN;
		this.quantity = quantity;
	}

	public Order(int orders_id, double total_price, String order_date, String title, String iSBN, int quantity) {
		super();
		this.orders_id = orders_id;
		this.total_price = total_price;
		this.order_date = order_date;
		this.title = title;
		ISBN = iSBN;
		this.quantity = quantity;
	}

	public int getOrders_id() {
		return orders_id;
	}

	public double getTotal_price() {
		return total_price;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
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

	public String getOrder_date() {
		return order_date;
	}

	public String getTitle() {
		return title;
	}

	public String getISBN() {
		return ISBN;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    
    
}
