package main;
//moviegoer entry
public class Moviegoer_data {
	String name;
	String mobile_number;
	String email;
	String password;
	public Moviegoer_data(String name, String mobile_number,String email,String password) {
		this.name = name;
		this.mobile_number = mobile_number;
		this.email = email;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public String getMobile() {
		return mobile_number;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
}
