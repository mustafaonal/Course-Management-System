package cengOnline;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import swingClasses.MenuPageStudent;
import swingClasses.MenuPageTeacher;

import javax.swing.ImageIcon;

public class Login {

	static int index;
	private JFrame frame;
	private JTextField email_textfield;
	private JPasswordField passwordField;
	public static List<Teacher> teachers = new ArrayList<Teacher>();
	public static List<Student> students = new ArrayList<Student>();
	public static List<Course> courses = new ArrayList<Course>();
	public static List<Announcement> announcements = new ArrayList<Announcement>();
	public static List<Assignment> assignments = new ArrayList<Assignment>();
	public static List<Attachment> attachments = new ArrayList<Attachment>();
	public static List<Message> messages = new ArrayList<Message>();
	public static List<Post> posts = new ArrayList<Post>();
	public static List<Comment> comments = new ArrayList<Comment>();

	private void emptyAllLists()
	{
		teachers.clear();
		students.clear();
		courses.clear();
		announcements.clear();
		assignments.clear();
		attachments.clear();
		messages.clear();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root", "admin");

			Statement stmt1 = con.createStatement();
			Statement stmt2 = con.createStatement();
			Statement stmt3 = con.createStatement();
			Statement stmt4 = con.createStatement();
			Statement stmt5 = con.createStatement();
			Statement stmt6 = con.createStatement();
			Statement stmt7 = con.createStatement();
			Statement stmt8 = con.createStatement();
			Statement stmt9 = con.createStatement();
			Statement stmt10 = con.createStatement();
			Statement stmt11 = con.createStatement();

			
			ResultSet rsTeacher = stmt1.executeQuery("select * from cengonline.teachers;");
			while (rsTeacher.next()) {
				Teacher t = new Teacher(rsTeacher.getInt(1), rsTeacher.getString(2), rsTeacher.getString(3),
						rsTeacher.getString(4), rsTeacher.getString(5));
				teachers.add(t);
			}

			ResultSet rsStudent = stmt2.executeQuery("select * from cengonline.students;");
			while (rsStudent.next()) {
				Student s = new Student(rsStudent.getInt(1), rsStudent.getString(2), rsStudent.getString(3),
						rsStudent.getString(4), rsStudent.getString(5));
				students.add(s);
			}
			ResultSet rsCourse = stmt3.executeQuery("select * from cengonline.courses;");
			while (rsCourse.next()) {
				Course c = new Course(rsCourse.getInt(1), rsCourse.getString(2), rsCourse.getString(3));
				courses.add(c);
			}

			ResultSet rsEnrollment = stmt4.executeQuery("select * from cengonline.enrollment ORDER BY idCourse ASC;");
			while (rsEnrollment.next()) {

				int courseID = rsEnrollment.getInt(1);
				int studentID = rsEnrollment.getInt(2);

				for (Course course : courses) {
					if (course.getCourseID() == courseID) {
						for (Student student : students) {
							if (student.getID() == studentID) {
								course.addtoStudents(student);
							}
						}

					}
				}

			}
			ResultSet rsCourseTeacher = stmt5
					.executeQuery("select * from cengonline.course_teacher ORDER BY idCourse ASC;");
			while (rsCourseTeacher.next()) {

				int courseID = rsCourseTeacher.getInt(1);
				int teacherID = rsCourseTeacher.getInt(2);

				for (Course course : courses) {
					if (course.getCourseID() == courseID) {
						for (Teacher teacher : teachers) {
							if (teacher.getID() == teacherID) {
								course.addtoTeachers(teacher);
							}
						}

					}
				}
			}

			ResultSet rsAnnouncement = stmt6.executeQuery("select * from cengonline.announcements");
			while (rsAnnouncement.next()) {
				Announcement an = new Announcement(rsAnnouncement.getInt(1), rsAnnouncement.getString(3),
						rsAnnouncement.getDate(4));
				int courseID = rsAnnouncement.getInt(2);

				for (Course course : courses) {
					if (course.getCourseID() == courseID) {
						course.addtoAnnouncements(an);
					}
				}
			}
			ResultSet rsAssignment = stmt7.executeQuery("select * from cengonline.assignments");

			while (rsAssignment.next()) {
				Assignment as = new Assignment(rsAssignment.getInt(1), rsAssignment.getString(3),
						rsAssignment.getString(4), rsAssignment.getString(5));
				int courseID = rsAssignment.getInt(2);

				assignments.add(as);

				for (Course course : courses) {
					if (course.getCourseID() == courseID) {
						course.addtoAssignments(as);
					}
				}
			}
			ResultSet rsAttachment = stmt9.executeQuery("select * from cengonline.attachments");
			while (rsAttachment.next()) {
				Attachment a = new Attachment(rsAttachment.getInt(1), rsAttachment.getInt(2),
						rsAttachment.getString(3), rsAttachment.getInt(4));
				
				attachments.add(a);
			}

			for (Attachment a : attachments) {
				for (Assignment as : assignments) {
					if (as.getAssnID() == a.getAssignmentID()) {
						as.addToAttachments(a);
					}
				}

			}
			
			
			ResultSet rsPosts = stmt10.executeQuery("select * from cengonline.posts");
			while(rsPosts.next()) {
				Post p = new Post(rsPosts.getInt(1),rsPosts.getInt(2), rsPosts.getString(4),rsPosts.getString(3), rsPosts.getTimestamp(5));
				posts.add(p);
				
			}
             
			for (Post p : posts) {
				for (Course c : courses) {
					if (c.getCourseID() == p.getCourseId()) {
						c.addToPosts(p);
					}
				}
			}
			
			ResultSet rsComments = stmt11.executeQuery("select * from cengonline.comments");
			while(rsComments.next()) {
			    Comment c = new Comment(rsComments.getInt(1),rsComments.getInt(2),rsComments.getString(3),rsComments.getString(4),rsComments.getDate(5));
			    comments.add(c);
				
			}
			
			for (Comment c : comments) {
				for (Post p : posts) {
					if (c.getPostId() == p.getPostId()) {
						p.addToComments(c);
					}
				}
			}
			
			
			ResultSet rsMessage = stmt8.executeQuery("select * from cengonline.messages");
			while (rsMessage.next()) {
				
				Message m = new Message(rsMessage.getInt(6), rsMessage.getString(1), rsMessage.getString(2),
						rsMessage.getString(3), rsMessage.getTimestamp(5), rsMessage.getString(4));
				messages.add(m);

			}
			con.close();

			for (Message m : messages) {

				for (Student s : students) {
					if (m.getSender().equalsIgnoreCase(s.getEmail())) {
						s.getMail().addToSent(m);
					}
					if (m.getReceiver().equalsIgnoreCase(s.getEmail())) {
						s.getMail().addToInbox(m);
					}
				}
				for (Teacher s : teachers) {
					if (m.getSender().equalsIgnoreCase(s.getEmail())) {
						s.getMail().addToSent(m);
					}
					if (m.getReceiver().equalsIgnoreCase(s.getEmail())) {
						s.getMail().addToInbox(m);
					}
				}

			}


			

		} catch (Exception e) {
			System.err.println(e);
		}

		frame = new JFrame();
		frame.setTitle("CENGONLINE LOGIN");
		frame.setBounds(100, 100, 496, 406);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CENGONLINE ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Verdana Pro", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(0, 25, 482, 97);
		frame.getContentPane().add(lblNewLabel_1);

		email_textfield = new JTextField();
		email_textfield.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		email_textfield.setBounds(186, 147, 204, 32);
		frame.getContentPane().add(email_textfield);
		email_textfield.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		passwordField.setBounds(186, 198, 204, 32);
		frame.getContentPane().add(passwordField);

		JLabel lblId = new JLabel("Email:");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		lblId.setBounds(65, 147, 116, 32);
		frame.getContentPane().add(lblId);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		lblPassword.setBounds(52, 198, 116, 32);
		frame.getContentPane().add(lblPassword);


		JButton btnEnter = new JButton("ENTER");

		btnEnter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int user = 2;  // 0=Student 1=Teacher 2=Wrong
				char pass[] = passwordField.getPassword();
				for (Teacher t : teachers) {
					if (email_textfield.getText().equalsIgnoreCase(t.getEmail())) {
						if (Arrays.equals(pass, t.getPassword().toCharArray())) {
							index = teachers.indexOf(t);
							user = 1;
							break;
						} else {
							user = 2;
							break;
						}
					}

				}
				if (user == 2) {
					for (Student s : students) {
						if (email_textfield.getText().equalsIgnoreCase(s.getEmail())) {
							if (Arrays.equals(pass, s.getPassword().toCharArray())) {
								index = students.indexOf(s);
								user = 0;
								break;
							} else {
								user = 2;
								break;
							}
						}

					}
				}

				switch(user)
				{
				case 0: 				
					MenuPageStudent mp = new MenuPageStudent(index);
					mp.getFrame().setVisible(true);
					frame.setVisible(false);
					break;
				case 1:
					MenuPageTeacher menuPageTeacher = new MenuPageTeacher(index);
					menuPageTeacher.getFrame().setVisible(true);
					frame.setVisible(false);
					break;
				default: 
					JOptionPane.showMessageDialog(frame, "Wrong email or password");
					emptyAllLists();
					
					Login newlogin = new Login();
					newlogin.getFrame().setVisible(true);
					frame.dispose();
					break;				
				
				}
			
			}
		});
		btnEnter.setBounds(186, 268, 104, 32);
		frame.getContentPane().add(btnEnter);

		Image img = new ImageIcon(this.getClass().getResource("/BgPurple.png")).getImage();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-51, -21, 581, 493);
		lblNewLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
