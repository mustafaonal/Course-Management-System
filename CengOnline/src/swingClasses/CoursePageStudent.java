package swingClasses;
import cengOnline.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class CoursePageStudent {
	private JFrame frame;
	private JScrollPane pane;
	private JScrollPane pane2;
	private JTable assignmentTable;
	private JTable announcementTable;
	private JTableHeader header;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTableHeader header2;
	private JPanel panel;
	private JButton button;
	private Course currentCourse;

	int x = 1;
	int y = 1;

	final int length = 30;
	private JButton btnNewButton;

	public CoursePageStudent(int id, int index) {

		Student student = Login.students.get(index);
		currentCourse = new Course(0, null, null);

		for (Course c : Login.courses) {
			if (c.getCourseID() == id) {
				currentCourse = c;
				break;
			}
		}

		frame = new JFrame();
		frame.setBounds(100, 100, 812, 653);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBounds(0, 0, 897, 622);
		panel.setBackground(new Color(102, 102, 153));

		assignmentTable = new JTable();
		assignmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model = new DefaultTableModel();
		String col[] = { "idAssignment", "Assignment Title", "Instructions", "Due Date", "Attachment" };
		model.setColumnIdentifiers(col);
		assignmentTable.setModel(model);
		assignmentTable.setBackground(new Color(255, 204, 153));
		assignmentTable.setForeground(new Color(0, 0, 0));
		assignmentTable.setRowHeight(30);

		header = assignmentTable.getTableHeader();
		header.setBackground(Color.yellow);
		panel.setLayout(null);
		pane = new JScrollPane(assignmentTable);

		Attachment currentAttach = null;
		boolean attach = false;

		for (Assignment a : currentCourse.getAssignments()) {
			for (Attachment at : a.getAttachments()) {
				if (at.getStudentID() == student.getID()) {

					currentAttach = at;
					attach = true;
					break;
				}
			}
			if (attach == true) {
				String row[] = { Integer.toString(a.getAssnID()), a.getTitle(), a.getInstructions(),
						a.getDue_date().toString(), currentAttach.getAttachment() };
				model.addRow(row);
				x++;
			}

			else {
				String row[] = { Integer.toString(a.getAssnID()), a.getTitle(), a.getInstructions(),
						a.getDue_date().toString(), " " };
				model.addRow(row);
				x++;
			}

			attach = false;
		}
		frame.getContentPane().add(panel);

		announcementTable = new JTable();
		announcementTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model2 = new DefaultTableModel();
		String col2[] = { "idAnnouncement", "Announcement", "Date" };
		model2.setColumnIdentifiers(col2);
		announcementTable.setModel(model2);
		announcementTable.setBackground(new Color(255, 204, 153));
		announcementTable.setForeground(new Color(0, 0, 0));
		announcementTable.setRowHeight(30);
		header2 = announcementTable.getTableHeader();
		header2.setBackground(Color.yellow);
		panel.setLayout(null);

		pane2 = new JScrollPane(announcementTable);

		for (Announcement a : currentCourse.getAnnouncements()) {
			String row[] = { Integer.toString(a.getAnnounceID()), a.getAnnouncement(), a.getDate().toString() };
			model2.addRow(row);
			y++;
		}
	

		panel.add(pane);
		panel.add(pane2);
		frame.getContentPane().add(panel);

		button = new JButton("< BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisteredLectures r = new RegisteredLectures(index);
				r.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setBounds(54, 22, 91, 25);

		pane.setBounds(54, 314, 596, 152);
		assignmentTable.setBounds(54, 235, 409, (x * length));

		pane2.setBounds(54, 86, 596, 152);
		announcementTable.setBounds(54, 45, 409, (y * length));

		panel.add(button);

		JButton btnAttachAssignment = new JButton("Attach Assignment");
		btnAttachAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = assignmentTable.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select the attachment from table!!!",
							"Not Found", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {

					String st = (String) model.getValueAt(selectedRowIndex, 0);
					int idAssgn = Integer.parseInt(st);
					AddAttachmentPage a = new AddAttachmentPage(idAssgn, index, id);
					a.getFrame().setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		btnAttachAssignment.setBounds(123, 513, 125, 25);
		panel.add(btnAttachAssignment);

		JButton btnDeleteAssignment = new JButton("Delete Assignment");
		btnDeleteAssignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int selectedRowIndex = assignmentTable.getSelectedRow();

				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select the attachment from table!!!",
							"Not Found", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {
					String st = (String) model.getValueAt(selectedRowIndex, 0);
					int idAssgn = Integer.parseInt(st);
					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(frame, "Do you want to delete this attachment?",
							"Delete announcement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, options[1]);
					if (n == 0) {
						for (Assignment a : currentCourse.getAssignments()) {
							for (Attachment at : a.getAttachments()) {
								if (at.getAssignmentID() == idAssgn && student.getID() == at.getStudentID()) {

									try {
										Class.forName("com.mysql.jdbc.Driver");
										Connection con = DriverManager.getConnection(
												"jdbc:mysql://localhost:3306/CengOnline", "root", "admin");
										String sql = "DELETE FROM cengonline.attachments WHERE idStudent = "
												+ at.getStudentID() + " AND idAssignment =" + at.getAssignmentID()
												+ ";";
										PreparedStatement stmt = con.prepareStatement(sql);
										stmt.executeUpdate();
									} catch (SQLException | ClassNotFoundException e1) {
										System.err.println(e1);
									}
									a.getAttachments().remove(at);
									Login.attachments.remove(at);
									JOptionPane.showMessageDialog(frame, "Assignment has been deleted");
									frame.dispose();
									break;
								}
							}

						}

						CoursePageStudent c = new CoursePageStudent(id, index);
						c.getFrame().setVisible(true);
						frame.setVisible(false);

					}
				}
			}
		});
		btnDeleteAssignment.setBounds(429, 513, 125, 25);
		panel.add(btnDeleteAssignment);

		JButton btnGoToStream = new JButton("Go To Stream >");
		btnGoToStream.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean type = true;
				StreamPage s = new StreamPage(type, " ", id, index);
				s.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnGoToStream.setBounds(525, 22, 125, 25);
		panel.add(btnGoToStream);

		btnNewButton = new JButton("VIEW");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int selectedRowIndex = announcementTable.getSelectedRow();
				int selectedRowIndex2 = assignmentTable.getSelectedRow();

				if (selectedRowIndex == -1 && selectedRowIndex2 > -1) { // assignment table select

					String assignment = (String) model.getValueAt(selectedRowIndex2, 2);

					JOptionPane.showMessageDialog(frame, assignment);

					assignmentTable.getSelectionModel().clearSelection();

				} else if (selectedRowIndex > -1 && selectedRowIndex2 == -1) { // announcement table select

					String announce = (String) model2.getValueAt(selectedRowIndex, 1);

					JOptionPane.showMessageDialog(frame, announce);

					announcementTable.getSelectionModel().clearSelection();

				} else { // neither

					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select from the table!!!", "Not Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				}
			}

		});
		btnNewButton.setBounds(681, 271, 85, 21);
		panel.add(btnNewButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/newyork.png")).getImage();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-193, -23, 1044, 737);
		lblNewLabel.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
