package swingClasses;

import java.awt.BorderLayout;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cengOnline.*;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AddAttachmentPage {

	private JFrame frame;
	private JTextField subjectText;
	private JLabel lblAssgnment;
	private Assignment assgn;
	private JLabel lblNewLabel;

	public AddAttachmentPage(int id, int index, int courseId) {

		frame = new JFrame();
		frame.setBounds(100, 100, 452, 390);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		

		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setBounds(57, 28, 56, 16);
		frame.getContentPane().add(lblSubject);

		Student student = Login.students.get(index);
		Course currentCourse = new Course(0, null, null);
		for (Course c : Login.courses) {
			if (c.getCourseID() == courseId) {
				currentCourse = c;
				break;
			}
		}

		assgn = new Assignment();
		for (Assignment a : currentCourse.getAssignments()) {
			if (a.getAssnID() == id) {
				assgn = a;
			}
		}

		JButton btnAssign = new JButton("Assign");
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String text = subjectText.getText();
				Attachment attach = new Attachment(student.getID(), id, text);

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root",
							"admin");

					String sql = "INSERT INTO `cengonline`.`attachments` (`idStudent`, `idAssignment`,`attachAssignment`) VALUES (?, ?, ?);";
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setInt(1, student.getID());
					stmt.setInt(2, id);
					stmt.setString(3, text);
					stmt.executeUpdate();

					assgn.addToAttachments(attach);
					Login.attachments.add(attach);

					JOptionPane.showMessageDialog(frame, "Assignment has been added");
					frame.dispose();

					CoursePageStudent c = new CoursePageStudent(courseId, index);
					c.getFrame().setVisible(true);

				} catch (SQLException | ClassNotFoundException e1) {

					JOptionPane.showMessageDialog(frame, "Can not add more than 1 attachment");
					frame.dispose();
					CoursePageStudent c = new CoursePageStudent(courseId, index);
					c.getFrame().setVisible(true);

				}

			}
		});

		lblAssgnment = new JLabel(assgn.getTitle());
		lblAssgnment.setBounds(145, 8, 110, 36);
		frame.getContentPane().add(lblAssgnment);

		btnAssign.setBounds(168, 302, 97, 25);
		frame.getContentPane().add(btnAssign);

		subjectText = new JTextField();
		subjectText.setBounds(57, 59, 306, 229);
		frame.getContentPane().add(subjectText);
		subjectText.setColumns(10);
		
		Image img = new ImageIcon(this.getClass().getResource("/aqua.png")).getImage();
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(-193, -23, 656, 406);
		lblNewLabel_1.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel_1);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
