package cengOnline;

import java.util.List;

public abstract  class User {
	
	private int ID;
	private String name;
	private String surname;
	private String email;
	private String password;
	private MailBox mail;
	
	
	public User(int ID, String name, String surname, String email, String password) {
		super();
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.mail = new MailBox();
	}


	public User(int ID, String name, String surname, String email, String password, MailBox mail) {
		super();
		this.ID = ID;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.mail = new MailBox(ID);
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public MailBox getMail() {
		return this.mail;
	}


	public void setMail(MailBox mail) {
		this.mail = mail;
	}


	
	
	

}
