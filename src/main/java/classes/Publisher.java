package classes;

public class Publisher {

	private int publisher_id;
	private String publisherName;
	
	public Publisher(int publisher_id, String publisherName) {
		this.publisher_id = publisher_id;
		this.publisherName = publisherName;
	}

	public int getPublisher_id() {
		return publisher_id;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	
	
}
