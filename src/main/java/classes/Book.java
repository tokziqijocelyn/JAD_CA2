package classes;

public class Book {
	private Integer book_id;
	private String title;
	private String description;
	private String ISBN;
	private Integer rating;
	private Float price;
	private Integer category_id;
	private String image;
	private Integer author_id;
	private String creation_date;
	private Integer quantity;
	private Integer publisher_id;
	private String author_name;
	private String publisher_name;
	private String category_name;
	private String date_time;
	private int total_sold;
	
	public Book(Integer book_id, String title, String description, String iSBN, Integer rating, Float price,
			Integer category_id, String image, Integer author_id, String creation_date, Integer quantity,
			Integer publisher_id, String author_name, String publisher_name, String category_name) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.description = description;
		ISBN = iSBN;
		this.rating = rating;
		this.price = price;
		this.category_id = category_id;
		this.image = image;
		this.author_id = author_id;
		this.creation_date = creation_date;
		this.quantity = quantity;
		this.publisher_id = publisher_id;
		this.author_name = author_name;
		this.publisher_name = publisher_name;
		this.category_name = category_name;
	}
	

	public Book(String date_time, int total_sold) {
		super();
		this.date_time = date_time;
		this.total_sold = total_sold;
	}


	public Book(Integer book_id, String title, int total_sold) {
		super();
		this.book_id = book_id;
		this.title = title;
		this.total_sold = total_sold;
	}


	public String getDate_time() {
		return date_time;
	}



	public int getTotal_sold() {
		return total_sold;
	}



	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}



	public void setTotal_sold(int total_sold) {
		this.total_sold = total_sold;
	}



	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public Integer getPublisher_id() {
		return publisher_id;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	public void setPublisher_id(Integer publisher_id) {
		this.publisher_id = publisher_id;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getISBN() {
		return ISBN;
	}

	public Integer getRating() {
		return rating;
	}

	public Float getPrice() {
		return price;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public String getImage() {
		return image;
	}

	public Integer getAuthor_id() {
		return author_id;
	}

	public String getCreation_date() {
		return creation_date;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setAuthor_id(Integer author_id) {
		this.author_id = author_id;
	}

	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}