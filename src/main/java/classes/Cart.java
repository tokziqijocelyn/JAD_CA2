package classes;

public class Cart {
	
	private String title;
	private float price; 
	private float totalAmt; 
	private float discountAmt;
	private String category;
	private int quantity;
	private String img;
	private int inventoryLeft;
	private int book_id;
	private float total_price;
	private float amountSaved;
	
	
	
	public Cart(String title, float price, float totalAmt, float discountAmt, String category, int quantity, String img,
			int inventoryLeft, int book_id, float total_price, float amountSaved) {
		super();
		this.title = title;
		this.price = price;
		this.totalAmt = totalAmt;
		this.discountAmt = discountAmt;
		this.category = category;
		this.quantity = quantity;
		this.img = img;
		this.inventoryLeft = inventoryLeft;
		this.book_id = book_id;
		this.total_price = total_price;
		this.amountSaved = amountSaved;
	}
	public float getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(float discountAmt) {
		this.discountAmt = discountAmt;
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
	public float getAmountSaved() {
		return amountSaved;
	}
	public void setAmountSaved(float amountSaved) {
		this.amountSaved = amountSaved;
	}
	
	
	

	
}
