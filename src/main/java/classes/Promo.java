package classes;

public class Promo {
	
	private String promoName = "";
	private double discount = 0;
	
	public Promo(String promoName, double discount) {
		super();
		this.promoName = promoName;
		this.discount = discount;
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

	

}
