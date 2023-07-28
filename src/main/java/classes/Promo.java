package classes;

public class Promo {
	
	private String promoName = "";
	private double discount = 0;
	private int noOfBooks = 0;
	
	public Promo(String promoName, double discount, int noOfBooks) {
		super();
		this.promoName = promoName;
		this.discount = discount;
		this.noOfBooks = noOfBooks;
	}
	
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	public void setNoOfBooks(int noOfBooks) {
		this.noOfBooks = noOfBooks;
	}
	
	

}
