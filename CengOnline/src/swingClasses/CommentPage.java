package swingClasses;
import cengOnline.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cengOnline.Post;
import cengOnline.User;

import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;


public class CommentPage{

	private Post currentPost;
	private JFrame frame;
	private JTextField post_text;
	private JTextArea comments;
	private JTextArea comment;
	private JLabel lblEnterYourComments;
	private JButton btnSend;

	private User user;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane_1;
	
	public CommentPage(boolean type, int id, int index, int postId, Course currentCourse) {
		
		if(type)
		{
			
			user = Login.students.get(index);
		}
		else {
			user = Login.teachers.get(index);
		}
		
		currentPost = new Post(1,1," ", " ");
		
		for(Post p:currentCourse.getPosts()) {
			if(p.getPostId()==postId) {
				currentPost=p;
				break;
			}
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.getContentPane().setLayout(null);
		
		JLabel senderLabel = new JLabel(currentPost.getSender());
		senderLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		senderLabel.setBounds(72, 85, 243, 25);
		frame.getContentPane().add(senderLabel);
		
		post_text = new JTextField(currentPost.getPostText());
		post_text.setEditable(false);
		post_text.setBounds(72, 128, 513, 45);
		frame.getContentPane().add(post_text);
		post_text.setColumns(10);
		frame.setBounds(100, 100, 697, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(72, 197, 513, 121);
		frame.getContentPane().add(scrollPane_1);

	
		comments = new JTextArea(5, 20);
		scrollPane_1.setViewportView(comments);
		comments.setEditable(false);

		comments.setLineWrap(true);
		comments.setWrapStyleWord(true);
		
		comment = new JTextArea();
		comment.setBounds(72, 412, 513, 106);
		frame.getContentPane().add(comment);
		
		lblEnterYourComments = new JLabel("Enter your comments about this post:");
		lblEnterYourComments.setFont(new Font("Arial", Font.PLAIN, 13));
		lblEnterYourComments.setBounds(72, 369, 252, 16);
		frame.getContentPane().add(lblEnterYourComments);
		
		btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root",
							"admin");

					String sql = "INSERT INTO `cengonline`.`comments` (`idPost`,`sender`,`commentText`,`commentDate`) VALUES (?, ?, ?, ?);";
					PreparedStatement stmt = con.prepareStatement(sql);

					
					
					SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd");  
					Date date = formatter1.parse("2020-06-09");
					stmt.setInt(1, currentPost.getPostId());
					stmt.setString(2, user.getEmail());
					stmt.setString(3,comment.getText());
					stmt.setString(4,"2020-06-09");
					stmt.executeUpdate();
					Comment comm = new Comment(currentPost.getPostId(),user.getEmail(),comment.getText(),date);
					currentPost.addToComments(comm);
					CommentPage c=new CommentPage(type, id,index,postId, currentCourse);
					c.getFrame().setVisible(true);
				    frame.setVisible(false);
					
				} catch (SQLException | ClassNotFoundException e1) {
					System.err.println(e1);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(488, 538, 97, 25);
		frame.getContentPane().add(btnSend);
		
		btnNewButton_1 = new JButton("< BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				StreamPage stream = new StreamPage(type, currentCourse.getName_short(), id, index);
				stream.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(23, 33, 85, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		for(Comment c:currentPost.getComments()) {
			comments.append(c.getSender() + ": " + c.getCommentText() + "\n");
		}
		
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
