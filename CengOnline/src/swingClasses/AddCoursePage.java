
package swingClasses;
import cengOnline.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AddCoursePage {

	private JFrame frame;
	private JTextField courseNameField;
	private JTextField shortNameField;

	public AddCoursePage(int index, int idTeacher) {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 276);
		frame.getContentPane().setLayout(null);

		courseNameField = new JTextField();
		courseNameField.setBounds(177, 124, 156, 27);
		frame.getContentPane().add(courseNameField);
		courseNameField.setColumns(10);

		shortNameField = new JTextField();
		shortNameField.setBounds(177, 71, 156, 27);
		frame.getContentPane().add(shortNameField);
		shortNameField.setColumns(10);

		JLabel course_short_name = new JLabel("Course Short Name:");
		course_short_name.setBounds(34, 71, 115, 27);
		frame.getContentPane().add(course_short_name);

		JLabel course_name = new JLabel("Course Name:");
		course_name.setBounds(35, 124, 96, 27);
		frame.getContentPane().add(course_name);

		JButton add_Course = new JButton("Create New Course");
		add_Course.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root", "admin");

					String sql = "INSERT INTO `cengonline`.`courses` (`courseNameShort`, `courseName`) VALUES (?, ?);";
					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, shortNameField.getText());
					stmt.setString(2, courseNameField.getText());
					stmt.executeUpdate();

					String getID = "select idCourse from cengonline.courses ORDER BY idCourse DESC LIMIT 1;";
					PreparedStatement statementID = con.prepareStatement(getID);
					
					ResultSet a = statementID.executeQuery();
					int idCourse = 0;
					while (a.next())
						idCourse = a.getInt(1);
					
					Course course = new Course(idCourse, shortNameField.getText(),courseNameField.getText());
					Login.courses.add(course);
					Teacher teacher = null;
					for(Teacher t : Login.teachers) {
						if(t.getID() == idTeacher) {
							teacher = t;
						}
						
					}
					course.getTeachers().add(teacher);
										
					String addToCourseTeacher = "INSERT INTO cengonline.course_teacher ( idCourse, idTeacher) VALUES (?,?);";
					PreparedStatement courseTeacher = con.prepareStatement(addToCourseTeacher);
					courseTeacher.setInt(1, idCourse);
					courseTeacher.setInt(2,  idTeacher);
					courseTeacher.executeUpdate();
					JOptionPane.showMessageDialog(frame, "Course added successfully");
					
					MenuPageTeacher mpt = new MenuPageTeacher(index);
					mpt.getFrame().setVisible(true);
					frame.dispose();
					
					
					

				} catch (SQLException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(frame, "Course already exists");
					MenuPageTeacher mpt = new MenuPageTeacher(index);
					mpt.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
		add_Course.setBounds(177, 180, 156, 27);
		frame.getContentPane().add(add_Course);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel deuceng = new JLabel("DEUCENG");
		deuceng.setBounds(23, 16, 66, 23);
		frame.getContentPane().add(deuceng);

		JButton back = new JButton("Back");
		back.setBounds(316, 7, 89, 23);
		frame.getContentPane().add(back);
		
		Image img = new ImageIcon(this.getClass().getResource("/Pine.png")).getImage();

		JLabel imgLbl = new JLabel("");
		imgLbl.setBounds(-218, -333, 696, 631);
		imgLbl.setIcon(new ImageIcon(img));
		frame.getContentPane().add(imgLbl);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPageTeacher mpt = new MenuPageTeacher(index);
				mpt.getFrame().setVisible(true);
				frame.dispose();
			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
