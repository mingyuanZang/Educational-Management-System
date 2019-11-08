package application.model;

import java.io.Serializable;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = 8078172339306040672L;

	protected String firstName;
	protected String lastName;
	private String email;
	private String address;

	public Person() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

}