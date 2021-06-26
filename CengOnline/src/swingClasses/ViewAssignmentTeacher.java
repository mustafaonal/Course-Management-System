package swingClasses;
import cengOnline.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class ViewAssignmentTeacher {
	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTableHeader header;
	private JTextField assignment_info;
	private Connection con;
	private HashMap<Integer, Integer> grades = new HashMap<Integer, Integer>();
	private Assignment currentAssignment;

	public ViewAssignmentTeacher(String shortname, int index, int assignmentID) {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		table = new JTable();
		model = new DefaultTableModel();
		header = table.getTableHeader();
		header.setBackground(Color.yellow);

		frame.getContentPane().add(table);

		String col[] = { "Student ID", "Grade", "Attachment" };

		model = new DefaultTableModel();
		model.setColumnIdentifiers(col);
		table.setModel(model);

		table.setBackground(new Color(255, 204, 153));
		table.setForeground(new Color(0, 0, 0));
		table.setRowHeight(30);

		header = table.getTableHeader();
		header.setBackground(Color.yellow);

		model.addRow(col);

		JLabel assignment_info = new JLabel();
		assignment_info.setFont(new Font("Tahoma", Font.PLAIN, 15));
		assignment_info.setBounds(20, 22, 403, 32);
		frame.getContentPane().add(assignment_info);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 77, 403, 65);
		frame.getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		for (Course course : Login.courses) {
			for (Assignment assignment : course.getAssignments()) {
				if (assignment.getAssnID() == assignmentID) {
					currentAssignment = assignment;
					assignment_info.setText(assignment.getTitle() + "  -  " + assignment.getDue_date());
					textArea.setText(assignment.getInstructions());
					for (Attachment attachment : assignment.getAttachments()) {
						int stdId = attachment.getStudentID();
						String text = attachment.getAttachment();

						int grade = attachment.getGrade();
						grades.put(stdId, grade);

						String row[] = { Integer.toString(stdId), grades.get(stdId).toString(), text };
						model.addRow(row);

					}
				}
			}
		}

		frame.setBounds(100, 100, 545, 599);
		table.setBounds(20, 152, 403, 306);

		JButton back_button = new JButton("Back");
		back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CoursePageTeacher crs = new CoursePageTeacher(shortname, index);
				crs.getFrame().setVisible(true);
				frame.dispose();

			}
		});
		back_button.setBounds(38, 490, 89, 23);
		frame.getContentPane().add(back_button);

		JButton select_assignment = new JButton("Select");
		select_assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table.getSelectedRow();

				int stu_id = Integer.parseInt((String) model.getValueAt(selectedRowIndex, 0));

				for (Attachment at : Login.attachments) {
					if (at.getStudentID() == stu_id && at.getAssignmentID() == assignmentID) {

						JOptionPane.showMessageDialog(frame, at.getAttachment());

					}
				}

			}
		});
		select_assignment.setBounds(322, 490, 89, 23);
		frame.getContentPane().add(select_assignment);

		JTextArea gradeTextArea = new JTextArea();
		gradeTextArea.setBounds(433, 285, 85, 23);
		frame.getContentPane().add(gradeTextArea);

		JButton gradeButton = new JButton("Grade");
		gradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select announcement from table!!!", "Not Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {
					int stu_id = Integer.parseInt((String) model.getValueAt(selectedRowIndex, 0));

					if (Integer.parseInt(gradeTextArea.getText()) <= 100
							&& Integer.parseInt(gradeTextArea.getText()) >= 0) {

						grades.replace(stu_id, Integer.parseInt(gradeTextArea.getText()));

						for (Attachment at : Login.attachments) {
							if (at.getStudentID() == stu_id && at.getAssignmentID() == assignmentID) {
								at.setGrade(Integer.parseInt(gradeTextArea.getText()));
							}
						}
						for (Attachment attach : currentAssignment.getAttachments()) {
							if (attach.getStudentID() == stu_id && attach.getAssignmentID() == assignmentID) {
								attach.setGrade(Integer.parseInt(gradeTextArea.getText()));
							}

						}

						try {
							Class.forName("com.mysql.jdbc.Driver");
							con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root",
									"admin");

							String sql = "UPDATE attachments SET grade = " + Integer.parseInt(gradeTextArea.getText())
									+ " WHERE idAssignment = " + assignmentID + " AND idStudent = " + stu_id + ";";

							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.executeUpdate();

						} catch (SQLException | ClassNotFoundException e1) {

						}

						table.setValueAt(gradeTextArea.getText(), selectedRowIndex, 1);

					} else {
						JOptionPane.showMessageDialog(frame, "Invalid grade value");
						

					}
				}

			}
		});
		gradeButton.setBounds(433, 329, 85, 21);
		frame.getContentPane().add(gradeButton);
		Image img = new ImageIcon(this.getClass().getResource("/polite.png")).getImage();

		JLabel imgLbl = new JLabel("");
		imgLbl.setBounds(0, 0, 531, 562);
		imgLbl.setIcon(new ImageIcon(img));
		frame.getContentPane().add(imgLbl);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
