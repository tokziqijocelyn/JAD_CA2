package classes;

public class Sales {
	private String order_date;
	private double total_price;
	private String time_period;
	
	public Sales(String order_date, double total_price) {
		super();
		this.order_date = order_date;
		this.total_price = total_price;
	}

	public Sales(double total_price, String time_period) {
		super();
		this.total_price = total_price;
		this.time_period = time_period;
	}

	public String getOrder_date() {
		return order_date;
	}

	public double getTotal_price() {
		return total_price;
	}

	public String getTime_period() {
		return time_period;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public void setTime_period(String time_period) {
		this.time_period = time_period;
	}
	
	
}
