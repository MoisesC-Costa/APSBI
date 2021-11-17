package server.model.bean;

public class User{

	private int id;
	private String nome;
	private String password;
	private String email;
	
	public User() {}
	
	public User(User user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.password = user.getPassword();
		this.email = user.getEmail();
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String Nome) {
		this.nome = Nome;
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
		
		if (another.getEmail().equals(email) 
				&& another.getPassword().equals(password)) {
				return true;			

		} else {
			return false;

		}
		
	}
	
}
