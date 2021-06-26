package cengOnline;

public class Teacher extends User{



	public Teacher(int ID, String name, String surname, String email, String password, MailBox mail) {
		super(ID, name, surname, email, password, mail);
		// TODO Auto-generated constructor stub
	}

	public Teacher(int ID, String name, String surname, String email, String password) {
		super(ID, name, surname, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Teacher ID:" + getID() + " Name:" + getName() + ", Surname:" + getSurname();
	}
	
}
