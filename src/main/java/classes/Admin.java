package classes;

public class Admin {
	private int admin_id;
	private String email;
	private String username;
	private boolean authenticated;
	private String image;
	private String pwd;
	private String creation_date;
	
	public Admin (boolean verified) {
		this.authenticated = verified;
		
	}
	

	public Admin(int admin_id, String email, String username, boolean authenticated, String image, String pwd) {
		super();
		this.admin_id = admin_id;
		this.email = email;
		this.username = username;
		this.authenticated = authenticated;
		this.image = image;
		this.pwd = pwd;
	}

	public int getAdmin_id() {
		return admin_id;
	}
	
	public String getImage() {
		return image;
	}
	
	public String getPwd() {
		return pwd;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}
	
	public String getCreationDate() {
		return creation_date;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setImg(String img) {
		this.image = img;
	}
	
	public void setCreationDate(String creationDate) {
		this.creation_date = creationDate;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}