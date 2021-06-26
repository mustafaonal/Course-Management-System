package swingClasses;
import cengOnline.*;
import java.awt.BorderLayout;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.TimeZone;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cengOnline.User;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class SendEmailPage {

	private JFrame frame;
	private JTextField toTextField;
	private JTextField fromTextField;
	private JTextField titleTextField;
	private JTextArea messagetextArea;
	private User sender;
	private User receiver;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private JLabel lblNewLabel;

	public SendEmailPage(boolean type, int index) {

		if (type)
			sender = Login.students.get(index);
		else
			sender = Login.teachers.get(index);

		frame = new JFrame();
		frame.setBounds(100, 100, 607, 418);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblTo = new JLabel("To:");
		lblTo.setFont(new Font("Verdana Pro", Font.PLAIN, 13));
		lblTo.setBounds(97, 102, 27, 30);
		frame.getContentPane().add(lblTo);

		toTextField = new JTextField();
		toTextField.setBounds(178, 109, 271, 23);
		frame.getContentPane().add(toTextField);
		toTextField.setColumns(10);

		JLabel lblFrom = new JLabel("From:");
		lblFrom.setFont(new Font("Verdana Pro", Font.PLAIN, 13));
		lblFrom.setBounds(97, 74, 39, 16);
		frame.getContentPane().add(lblFrom);

		fromTextField = new JTextField(sender.getEmail());
		fromTextField.setBounds(178, 74, 271, 25);
		frame.getContentPane().add(fromTextField);
		fromTextField.setColumns(10);

		JLabel lblSubject = new JLabel("Subject:");
		lblSubject.setFont(new Font("Verdana Pro", Font.PLAIN, 13));
		lblSubject.setBounds(97, 142, 56, 22);
		frame.getContentPane().add(lblSubject);

		titleTextField = new JTextField();
		titleTextField.setBounds(178, 145, 271, 22);
		frame.getContentPane().add(titleTextField);
		titleTextField.setColumns(10);

		messagetextArea = new JTextArea();
		messagetextArea.setBounds(97, 193, 352, 104);
		frame.getContentPane().add(messagetextArea);

		JButton btnSendEmail = new JButton("Send Email");
		btnSendEmail.setHorizontalAlignment(SwingConstants.LEFT);
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				
				try {

					if (fromTextField.getText().equals(sender.getEmail())) {

						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CengOnline", "root",
								"admin");
						String sql = "INSERT INTO `cengonline`.`messages` (`senderMail`, `receiverMail`, `title`, `messageText`, `date`) VALUES (?, ?, ?, ?, ?);";

						PreparedStatement st = con.prepareStatement(sql);

						st.setString(1, fromTextField.getText());
						st.setString(2, toTextField.getText());
						st.setString(3, titleTextField.getText());
						st.setString(4, messagetextArea.getText());
						st.setTimestamp(5, timestamp);
						st.executeUpdate();

						Statement sta = con.createStatement();

						ResultSet messageID = sta
								.executeQuery("select * from cengonline.messages ORDER BY messageID DESC LIMIT 1;");

						while (messageID.next()) {
							Message m = new Message(messageID.getInt(6), fromTextField.getText(), toTextField.getText(),
									titleTextField.getText(), timestamp, messagetextArea.getText());
							sender.getMail().addToSent(m);

							for (Student stu : Login.students) {
								if (stu.getEmail().equals(toTextField.getText())) {
									receiver = stu;
									break;
								}
							}
							for (Teacher tc : Login.teachers) {
								if (tc.getEmail().equals(toTextField.getText())) {
									receiver = tc;
									break;
								}
							}

							receiver.getMail().addToInbox(m);
						}
						
						
						JOptionPane.showMessageDialog(frame, "Email sent");
						SendEmailPage newEmail = new SendEmailPage(type, index);						
						newEmail.getFrame().setVisible(true);
						frame.dispose();
						
					}
					else {
						JOptionPane.showMessageDialog(frame, "Wrong email");
						SendEmailPage newEmail = new SendEmailPage(type, index);						
						newEmail.getFrame().setVisible(true);
						frame.dispose();
					}
				}

				catch (SQLException | ClassNotFoundException e1) {
					System.err.println(e1);
				}

			}
		});
		btnSendEmail.setBounds(476, 228, 97, 36);
		frame.getContentPane().add(btnSendEmail);

		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayMailbox mailbox = new DisplayMailbox(type, index);
				mailbox.getFrame().setVisible(true);
				frame.dispose();

			}
		});
		btnNewButton.setBounds(10, 24, 97, 21);
		frame.getContentPane().add(btnNewButton);
		
		Image img = new ImageIcon(this.getClass().getResource("/newyork.png")).getImage();

		JLabel imgLbl = new JLabel("");
		imgLbl.setBounds(0, 0, 636, 406);
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
