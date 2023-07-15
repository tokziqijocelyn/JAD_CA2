package classes;

public class Cart {
	
	private String title;
	private float price; 
	private float totalAmt; 
	private String category;
	private int quantity;
	private String img;
	private int inventoryLeft;
	private int book_id;
	private float total_price;
	
	public Cart(String title, float price, float totalAmt, String category, int quantity, String img, int inventoryLeft,
			int book_id, float total_price) {
		super();
		this.title = title;
		this.price = price;
		this.totalAmt = totalAmt;
		this.category = category;
		this.quantity = quantity;
		this.img = img;
		this.inventoryLeft = inventoryLeft;
		this.book_id = book_id;
		this.total_price = total_price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(float totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getInventoryLeft() {
		return inventoryLeft;
	}

	public void setInventoryLeft(int inventoryLeft) {
		this.inventoryLeft = inventoryLeft;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}

	

	
}
