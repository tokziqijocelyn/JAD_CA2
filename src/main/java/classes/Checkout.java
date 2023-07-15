package classes;

public class Checkout {

	private int book_id;
	private float total_price;
	
	public Checkout(int book_id, float total_price) {
		super();
		this.book_id = book_id;
		this.total_price = total_price;
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
