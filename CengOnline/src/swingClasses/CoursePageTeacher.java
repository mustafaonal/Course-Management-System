package swingClasses;
import cengOnline.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Date;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.sql.*;
import java.awt.Font;

public class CoursePageTeacher {
	private JFrame frame;
	private JTable table_assignment;
	private JTable table_announcement;
	private JScrollPane pane;
	private JScrollPane pane2;
	private DefaultTableModel model1;
	private DefaultTableModel model2;
	public int index_course = 0;
	private Connection con;
	private int count1 = 1;
	private int count2 = 1;
	final int length = 25;
	private JPanel panel;
	Course course = new Course(0, null, null);

	public CoursePageTeacher(String shortname, int index) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root", "admin");
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO: handle exception
		}

		Teacher teacher = Login.teachers.get(index);

		for (Course c : Login.courses) {
			if (c.getName_short() == shortname) {
				course = c;
				index_course = Login.courses.indexOf(c);
				break;
			}
		}
		frame = new JFrame();
		panel = new JPanel();
		panel.setLayout(null);

		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 825, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String col1[] = { "Assignment id", "Assignment Title", "Assignment Inst", " Due Date" };
		String col2[] = { "Announcement id", "Announcement", "Date" };

		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(col1);
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(col2);

		JButton btnNewButton = new JButton("Go to Stream >");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean type = false;
				StreamPage sp = new StreamPage(type, shortname, 0, index);
				sp.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(657, 553, 132, 30);

		JLabel course_name = new JLabel(course.getName_short() + "  -  " + course.getCourseName());
		course_name.setFont(new Font("Arial", Font.PLAIN, 16));
		course_name.setBackground(Color.CYAN);
		course_name.setForeground(new Color(0, 0, 0));
		course_name.setBounds(10, 45, 579, 30);

		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(UIManager.getColor("Button.darkShadow"));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { teacher.getName() + " " + teacher.getSurname(),
				"Edit Course", "Delete Course", "Mailbox", "Exit" }));
		comboBox.setBounds(645, 11, 139, 22);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals("Exit")) {
					System.exit(0);
				} else if (comboBox.getSelectedItem().equals("Edit Course")) {
					String new_course_short_name = JOptionPane.showInputDialog(frame, "New Course Short Name",
							course.getName_short());
					if (new_course_short_name != null) {
						String new_course_name = JOptionPane.showInputDialog(frame, "New Course Short Name",
								course.getCourseName());
						if (new_course_name != null) {
							course.setCourseName(new_course_name);
							course.setName_short(new_course_short_name);
							Object[] options = { "Okey" };
							String sql = "UPDATE courses SET courseNameShort = '" + new_course_short_name
									+ "', courseName = '" + new_course_name + "' WHERE idCourse = "
									+ course.getCourseID() + ";";
							PreparedStatement stmt4;
							try {
								stmt4 = con.prepareStatement(sql);
								stmt4.executeUpdate();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							int n = JOptionPane.showOptionDialog(frame, "Course name changed.", "Edit Course",
									JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
							course_name.setText(new_course_short_name + "  -  " + new_course_name);
						}
					}
				} else if (comboBox.getSelectedItem().equals("Delete Course")) {
					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(frame, "Do you want to delete this course", "Delete course",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (n == 0) {
						Login.courses.remove(index_course);
						MenuPageTeacher mpt = new MenuPageTeacher(index);
						mpt.getFrame().setVisible(true);
						frame.setVisible(false);
						String sql = "DELETE FROM courses WHERE idCourse = " + course.getCourseID() + ";";
						PreparedStatement stmt;
						try {
							stmt = con.prepareStatement(sql);
							stmt.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				} else if (comboBox.getSelectedItem().equals("Mailbox")) {
					frame.dispose();
					boolean type = false;
					DisplayMailbox d = new DisplayMailbox(type, index);
					d.getFrame().setVisible(true);
				}

			}
		});

		JButton Back = new JButton("Back");
		Back.setBackground(UIManager.getColor("Button.darkShadow"));
		Back.setBounds(10, 11, 89, 22);

		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPageTeacher mpt = new MenuPageTeacher(index);
				mpt.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});

		JButton View_Assignment = new JButton("View Assignment");
		View_Assignment.setBackground(new Color(255, 204, 204));
		View_Assignment.setBounds(657, 95, 127, 23);

		View_Assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_assignment.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select assignment from table!!!", "No Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {
					int ass_id = Integer.parseInt((String) model1.getValueAt(selectedRowIndex, 0));
					ViewAssignmentTeacher vat = new ViewAssignmentTeacher(shortname, index, ass_id);
					vat.getFrame().setVisible(true);
					frame.setVisible(false);

				}
			}
		});

		JButton Delete_Assignment = new JButton("Delete Assignment");
		Delete_Assignment.setBackground(new Color(255, 204, 204));
		Delete_Assignment.setBounds(657, 163, 127, 23);

		Delete_Assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_assignment.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select assignment from table!!!", "No Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {

					int ass_id = Integer.parseInt((String) model1.getValueAt(selectedRowIndex, 0));
					int index_ass = 0;
					for (Assignment ass : course.getAssignments()) {
						if (ass.getAssnID() == ass_id) {
							index_ass = course.getAssignments().indexOf(ass);
							break;
						}
					}
					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(frame, "Do you want to delete this assignment?",
							"Delete assignment", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[1]);
					if (n == 0) {
						course.getAssignments().remove(index_ass);
						String sql = "DELETE FROM assignments WHERE idAssignments = " + ass_id + ";";
						PreparedStatement stmt1;
						try {
							stmt1 = con.prepareStatement(sql);
							stmt1.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
						Object[] options1 = { "Okey" };
						int n1 = JOptionPane.showOptionDialog(frame, "Assignment deleted.", "Delete Assignment",
								JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1);
						model1.removeRow(selectedRowIndex);
						count1--;
						table_assignment.setBounds(10, 95, 605, (count1 * 25));
					}
				}

			}
		});

		JButton Edit_Assignment = new JButton("Edit Assignment");
		Edit_Assignment.setBackground(new Color(255, 204, 204));
		Edit_Assignment.setBounds(657, 129, 127, 23);

		Edit_Assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_assignment.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select assignment from table!!!", "No Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {
					int ass_id = Integer.parseInt((String) model1.getValueAt(selectedRowIndex, 0));
					int index_ass = 0;
					for (Assignment ass : course.getAssignments()) {
						if (ass.getAssnID() == ass_id) {
							index_ass = course.getAssignments().indexOf(ass);
							break;
						}
					}

					String new_ass_title = JOptionPane.showInputDialog(frame, "New Assignment Title",
							course.getAssignments().get(index_ass).getTitle());
					if (new_ass_title != null) {
						String new_ass_inst = JOptionPane.showInputDialog(frame, "New Assignment Instructions",
								course.getAssignments().get(index_ass).getInstructions());
						if (new_ass_inst != null) {
							String new_ass_due_date = JOptionPane.showInputDialog(frame, "New Assignment Due Date",
									course.getAssignments().get(index_ass).getDue_date());
							if (new_ass_due_date != null) {
								course.getAssignments().get(index_ass).setTitle(new_ass_title);
								course.getAssignments().get(index_ass).setInstructions(new_ass_inst);
								// course.getAssignments().get(index_ass).setDue_date(new_ass_due_date);

								Object[] options = { "Okey" };
								String sql = "UPDATE assignments SET assignmentTitle = '" + new_ass_title
										+ "', assignmentsInstr = '" + new_ass_inst + "', assignmentsDue = '"
										+ new_ass_due_date + "' WHERE idAssignments = " + ass_id + ";";
								PreparedStatement stmt5;
								try {
									stmt5 = con.prepareStatement(sql);
									stmt5.executeUpdate();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								int n = JOptionPane.showOptionDialog(frame, "Assignment changed.", "Edit Assignment",
										JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

								table_assignment.setValueAt(new_ass_title, selectedRowIndex, 1);
								table_assignment.setValueAt(new_ass_inst, selectedRowIndex, 2);
								table_assignment.setValueAt(new_ass_due_date, selectedRowIndex, 3);
							}
						}
					}

				}
			}
		});

		JButton Add_Assignment = new JButton("Add Assignment");
		Add_Assignment.setBackground(new Color(255, 204, 204));
		Add_Assignment.setBounds(657, 197, 127, 23);

		Add_Assignment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String new_ass_title = JOptionPane.showInputDialog(frame, "New Assignment Title");
				if (new_ass_title != null) {
					String new_ass_inst = JOptionPane.showInputDialog(frame, "New Assignment Instructions");
					if (new_ass_inst != null) {
						String new_ass_due_date = JOptionPane.showInputDialog(frame, "New Assignment Due Date");
						if (new_ass_due_date != null) {

							String sql8 = "INSERT INTO `cengonline`.`assignments` (`idCourse`, `assignmentTitle`, `assignmentsInstr`, `assignmentsDue`) VALUES (?, ?, ?, ?);";

							PreparedStatement stmt10;
							try {
								stmt10 = con.prepareStatement(sql8);
								stmt10.setInt(1, course.getCourseID());
								stmt10.setString(2, new_ass_title);
								stmt10.setString(3, new_ass_inst);
								stmt10.setString(4, new_ass_due_date);
								stmt10.executeUpdate();

								String getID = "select idAssignments from cengonline.assignments ORDER BY idAssignments DESC LIMIT 1;";
								PreparedStatement statementID = con.prepareStatement(getID);
								ResultSet a = statementID.executeQuery();
								int idAssignment = 0;
								while (a.next())
									idAssignment = a.getInt(1);

								Assignment new_assignment = new Assignment(idAssignment, new_ass_title, new_ass_inst,
										new_ass_due_date);
								course.getAssignments().add(new_assignment);

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							Object[] options = { "Okey" };
							int n = JOptionPane.showOptionDialog(frame, "Assignment added.", "Add Assignment",
									JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

							CoursePageTeacher cpt = new CoursePageTeacher(shortname, index);
							cpt.getFrame().setVisible(true);
							frame.setVisible(false);
						}
					}
				}
			}
		});

		JButton Edit_Announcement = new JButton("Edit Announcement");
		Edit_Announcement.setBackground(new Color(255, 204, 204));
		Edit_Announcement.setBounds(657, 385, 127, 23);

		Edit_Announcement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_announcement.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select announcement from table!!!", "Not Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {

					Timestamp timestamp = new Timestamp(System.currentTimeMillis());

					int ann_id = Integer.parseInt((String) model2.getValueAt(selectedRowIndex, 0));
					int index_ann = 0;
					for (Announcement ann : course.getAnnouncements()) {
						if (ann.getAnnounceID() == ann_id) {
							index_ann = course.getAnnouncements().indexOf(ann);
							break;
						}
					}

					String new_ann = JOptionPane.showInputDialog(frame, "New Announcement",
							course.getAnnouncements().get(index_ann).getAnnouncement());
					course.getAnnouncements().get(index_ann).setAnnouncement(new_ann);
					course.getAnnouncements().get(index_ann).setDate(timestamp);

					Object[] options = { "Okey" };
					String sql = "UPDATE announcements SET announcementsText = '" + new_ann + "', announcementDate = '"
							+ course.getAnnouncements().get(index_ann).getDate() + "' WHERE idAnnouncements = " + ann_id
							+ ";";
					PreparedStatement stmt50;
					try {
						stmt50 = con.prepareStatement(sql);
						stmt50.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int n = JOptionPane.showOptionDialog(frame, "Announcement changed.", "Edit Announcement",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

					table_announcement.setValueAt(new_ann, selectedRowIndex, 1);
					table_announcement.setValueAt(timestamp, selectedRowIndex, 2);

				}
			}
		});

		JButton Add_Announcement = new JButton("Add Announcement");
		Add_Announcement.setBackground(new Color(255, 204, 204));
		Add_Announcement.setBounds(657, 451, 127, 23);

		Add_Announcement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String new_ann = JOptionPane.showInputDialog(frame, "New Announcement Title");
				if (new_ann != null) {

					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					String sql8 = "INSERT INTO `cengonline`.`announcements` (`idCourse`, `announcementsText`, `announcementDate`) VALUES (?, ?, ?);";
					String getID = "select idAnnouncements from cengonline.announcements ORDER BY idAnnouncements DESC LIMIT 1;";

					PreparedStatement stmt10;
					try {
						stmt10 = con.prepareStatement(sql8);
						stmt10.setInt(1, course.getCourseID());
						stmt10.setString(2, new_ann);
						stmt10.setTimestamp(3, timestamp);
						stmt10.executeUpdate();

						PreparedStatement statementID = con.prepareStatement(getID);
						ResultSet a = statementID.executeQuery();
						int idAnnouncement = 0;
						while (a.next())
							idAnnouncement = a.getInt(1);

						Announcement new_announcement = new Announcement(idAnnouncement, new_ann, timestamp);

						Login.announcements.add(new_announcement);
						course.getAnnouncements().add(new_announcement);

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Assignment added.", "Add Assignment",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

					CoursePageTeacher cpt = new CoursePageTeacher(shortname, index);
					cpt.getFrame().setVisible(true);
					frame.setVisible(false);

				}
			}
		});

		JButton Delete_Announcement = new JButton("Delete Announcement");
		Delete_Announcement.setBackground(new Color(255, 204, 204));
		Delete_Announcement.setBounds(657, 418, 127, 23);

		Delete_Announcement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_announcement.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select announcement from table!!!", "Not Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {

					int ann_id = Integer.parseInt((String) model2.getValueAt(selectedRowIndex, 0));
					int index_ann = 0;
					for (Announcement ann : course.getAnnouncements()) {
						if (ann.getAnnounceID() == ann_id) {
							index_ann = course.getAnnouncements().indexOf(ann);
							break;
						}
					}
					Object[] options = { "Yes", "No" };
					int n = JOptionPane.showOptionDialog(frame, "Do you want to delete this announcement?",
							"Delete announcement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, options[1]);
					if (n == 0) {
						course.getAnnouncements().remove(index_ann);
						String sql = "DELETE FROM announcements WHERE idAnnouncements = " + ann_id + ";";
						PreparedStatement stmt2;
						try {
							stmt2 = con.prepareStatement(sql);
							stmt2.executeUpdate();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
						Object[] options1 = { "Okey" };
						int n1 = JOptionPane.showOptionDialog(frame, "Announcement deleted.", "Delete Announcement",
								JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1);

						model2.removeRow(selectedRowIndex);
						count2--;
						table_announcement.setBounds(10, 347, 605, (count2 * length));
					}

				}

			}
		});

		for (Assignment assignment : course.getAssignments()) {
			String[] row_ass = { Integer.toString(assignment.getAssnID()), assignment.getTitle(),
					assignment.getInstructions(), assignment.getDue_date().toString() };
			model1.addRow(row_ass);
			count1++;
		}

		for (Announcement announcement : course.getAnnouncements()) {
			String[] row_ann = { Integer.toString(announcement.getAnnounceID()), announcement.getAnnouncement(),
					announcement.getDate().toString() };
			model2.addRow(row_ann);
			count2++;
		}

		table_assignment = new JTable();

		table_assignment.setForeground(new Color(0, 0, 0));
		table_assignment.setBackground(new Color(204, 204, 255));
		table_assignment.setRowHeight(25);

		table_assignment.setModel(model1);
		pane = new JScrollPane(table_assignment);

		pane.setBounds(10, 95, 605, 150);
		table_assignment.setBounds(10, 95, 605, (count1 * 25));

		panel.add(pane);

		table_announcement = new JTable();

		table_announcement.setForeground(new Color(0, 0, 0));
		table_announcement.setBackground(new Color(204, 204, 255));
		table_announcement.setRowHeight(25);
		table_announcement.setModel(model2);

		pane2 = new JScrollPane(table_announcement);
		pane2.setBounds(10, 341, 605, 150);
		table_announcement.setBounds(10, 341, 605, (count2 * length));
		panel.add(pane2);

		panel.add(course_name);

		panel.add(Delete_Announcement);
		panel.add(comboBox);
		panel.add(Back);
		panel.add(View_Assignment);
		panel.add(btnNewButton);
		panel.add(Add_Assignment);
		panel.add(Delete_Assignment);
		panel.add(Add_Announcement);
		panel.add(Edit_Announcement);
		panel.add(Edit_Assignment);

		Image img = new ImageIcon(this.getClass().getResource("/BgPurple.png")).getImage();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_1 = new JButton("View Announcement");
		btnNewButton_1.setBackground(new Color(255, 204, 204));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_announcement.getSelectedRow();
				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "Please select announcement from table!!!", "Not Found",
							JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
				} else {

					String announce = (String)model2.getValueAt(selectedRowIndex, 1);
					JOptionPane.showMessageDialog(frame,announce);
					
					
					
				}
			}
		});
		btnNewButton_1.setBounds(657, 343, 127, 23);
		panel.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 825, 649);
		lblNewLabel.setIcon(new ImageIcon(img));
		panel.add(lblNewLabel);

		frame.getContentPane().add(panel);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
