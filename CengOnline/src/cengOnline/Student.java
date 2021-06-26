package cengOnline;

public class Student extends User {

	

	public Student(int ID, String name, String surname, String email, String password, MailBox mail) {
		super(ID, name, surname, email, password, mail);
		// TODO Auto-generated constructor stub
	}

	public Student(int ID, String name, String surname, String email, String password) {
		super(ID, name, surname, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student ID:" + getID() + " Name:" + getName() + " Surname:" + getSurname();
	}

	

}
