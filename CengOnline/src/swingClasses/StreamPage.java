package swingClasses;
import cengOnline.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cengOnline.Course;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StreamPage {

	private Course currentCourse;
	private JTable table_post;
	private DefaultTableModel model1;
	private JFrame frame;
	private JButton btnGoToComment;
	private Connection con;
	private User user;
	private JLabel lblNewLabel;

	public StreamPage(boolean type, String shortname, int id, int index) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root", "admin");
		} catch (SQLException | ClassNotFoundException e1) {
			// TODO: handle exception
		}

		currentCourse = new Course(0, null, null);

		for (Course c : Login.courses) {
			if (type) {
				if (c.getCourseID() == id) {
					currentCourse = c;
					break;
				}
			} else {
				if (c.getName_short() == shortname) {
					currentCourse = c;
					break;
				}
			}
		}

		if (type) {

			user = Login.students.get(index);
		} else {
			user = Login.teachers.get(index);
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 825, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String col1[] = { "Post Id", "Sender", "Post", "Date" };

		model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(col1);
		model1.addRow(col1);

		JLabel course_name2 = new JLabel(currentCourse.getCourseName() + " Stream Area");
		course_name2.setFont(new Font("Arial", Font.PLAIN, 12));
		course_name2.setBackground(Color.DARK_GRAY);
		course_name2.setForeground(Color.CYAN);
		course_name2.setBounds(99, 53, 252, 30);
		frame.getContentPane().add(course_name2);

		table_post = new JTable();
		table_post.setSize(535, 398);
		table_post.setLocation(99, 97);

		table_post.setForeground(new Color(0, 0, 0));
		table_post.setBackground(new Color(255, 153, 0));
		table_post.setRowHeight(25);
		frame.getContentPane().add(table_post);

		table_post.setModel(model1);

		btnGoToComment = new JButton("GO TO COMMENT PAGE >");
		btnGoToComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = table_post.getSelectedRow();

				if (selectedRowIndex == -1) {
					Object[] options = { "Okey" };
					int n = JOptionPane.showOptionDialog(frame, "No post selected", "Not Found", JOptionPane.OK_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options);
				}
				if (selectedRowIndex >= 0) {
					String st = (String) model1.getValueAt(selectedRowIndex, 0);
					int idPost = Integer.parseInt(st);
					CommentPage cp = new CommentPage(type, id, index, idPost, currentCourse);
					cp.getFrame().setVisible(true);
					frame.setVisible(false);
				}

			}
		});
		btnGoToComment.setBounds(408, 530, 226, 25);
		frame.getContentPane().add(btnGoToComment);

		if (!type) {

			JButton add_post = new JButton("ADD POST");
			add_post.setBounds(668, 244, 110, 18);
			frame.getContentPane().add(add_post);

			add_post.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String new_post_title = JOptionPane.showInputDialog(frame, "New Post");
					if (new_post_title != null) {
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						try {
							
							String sql = "INSERT INTO cengonline.posts(idCourse, sender_mail, postText, postDate) VALUES (?, ?, ?, ?);";

							PreparedStatement stmt;

							stmt = con.prepareStatement(sql);
							stmt.setInt(1, currentCourse.getCourseID());
							stmt.setString(2, user.getEmail());
							stmt.setString(3, new_post_title);
							stmt.setTimestamp(4, timestamp );
							stmt.executeUpdate();

							String getID = "select idPost from cengonline.posts ORDER BY idPost DESC LIMIT 1;";
							PreparedStatement statementID = con.prepareStatement(getID);
							ResultSet a = statementID.executeQuery();
							int idPost = 0;
							while (a.next())
								idPost = a.getInt(1);

							Post new_post = new Post(idPost, currentCourse.getCourseID(), new_post_title,
									user.getEmail(), timestamp);
							Login.posts.add(new_post);
							currentCourse.getPosts().add(new_post);

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						Object[] options = { "Okey" };
						int n = JOptionPane.showOptionDialog(frame, "Post added.", "Post Assignment",
								JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

						StreamPage sp = new StreamPage(type, shortname, id, index);
						sp.getFrame().setVisible(true);
						frame.dispose();
					}
				}
			});

			JButton edit_post = new JButton("EDIT POST");
			edit_post.setBounds(668, 291, 110, 18);
			frame.getContentPane().add(edit_post);

			edit_post.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRowIndex = table_post.getSelectedRow();
					if (selectedRowIndex == -1) {
						Object[] options = { "Okey" };
						int n = JOptionPane.showOptionDialog(frame, "Please select post from table!!!", "Not Found",
								JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
					} else {
						int post_id = Integer.parseInt((String) model1.getValueAt(selectedRowIndex, 0));
						int index_post = 0;
						for (Post post : Login.posts) {
							if (post.getPostId() == post_id) {
								index_post = Login.posts.indexOf(post);
								break;
							}
						}

						String new_post_title = JOptionPane.showInputDialog(frame, "New Post Title",
								Login.posts.get(index_post).getPostText());
						if (new_post_title != null) {
							Login.posts.get(index_post).setPostText(new_post_title);

							Object[] options = { "Okey" };
							String sql = "UPDATE posts SET postText = '" + new_post_title + "' WHERE idPost = "
									+ post_id + ";";
							PreparedStatement stmt5;
							try {
								stmt5 = con.prepareStatement(sql);
								stmt5.executeUpdate();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int n = JOptionPane.showOptionDialog(frame, "Post changed.", "Edit Post",
									JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);

							table_post.setValueAt(new_post_title, selectedRowIndex, 2);

						}

					}
				}
			});

			JButton deleteButton = new JButton("DELETE POST");
			deleteButton.setBounds(668, 338, 110, 18);
			frame.getContentPane().add(deleteButton);

			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root", "admin");

						int selectedRowIndex = table_post.getSelectedRow();
						if (selectedRowIndex == -1) {
							Object[] options = { "Okey" };
							int n = JOptionPane.showOptionDialog(frame, "No post selected", "Not Found",
									JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
						}
						if (selectedRowIndex >= 0) {
							String st = (String) model1.getValueAt(selectedRowIndex, 0);
							int id = Integer.parseInt(st);

							for (Post p : Login.posts) {
								if (p.getPostId() == id) {
									String sql = "DELETE FROM cengonline.posts WHERE idPost = " + id + ";";
									PreparedStatement stmt = con1.prepareStatement(sql);
									stmt.executeUpdate();

									Login.posts.remove(p);
									model1.removeRow(selectedRowIndex);
									currentCourse.getPosts().remove(p);
									break;

								}
							}
						}

					} catch (SQLException | ClassNotFoundException e1) {
						// TODO: handle exception
					}

				}
			});
		}

		JButton backButton = new JButton("< BACK");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (type) {
					CoursePageStudent cps = new CoursePageStudent(id, index);
					cps.getFrame().setVisible(true);
					frame.dispose();
				} else {

					CoursePageTeacher cpt = new CoursePageTeacher(shortname, index);
					cpt.getFrame().setVisible(true);
					frame.dispose();
				}

			}
		});
		backButton.setBounds(99, 530, 100, 25);
		frame.getContentPane().add(backButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/aqua.png")).getImage();

		JLabel imgLbl = new JLabel("");
		imgLbl.setBounds(0, 0, 836, 629);
		imgLbl.setIcon(new ImageIcon(img));
		frame.getContentPane().add(imgLbl);

		for (Post p : currentCourse.getPosts()) {
			String row[] = { Integer.toString(p.getPostId()), p.getSender(), p.getPostText(), p.getDate().toString()};
			model1.addRow(row);

		}

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
