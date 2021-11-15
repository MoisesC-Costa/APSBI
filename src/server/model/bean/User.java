package server.model.bean;

public class User{

	private long id;
	private String username;
	private String password;
	private String email;
	
	public User() {}
	
	public User(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		User another = (User) o;
		
		if (another.getUsername().equals(username) 
				&& another.getPassword().equals(password)) {
				return true;			

		} else {
			return false;

		}
		
	}
	
}
