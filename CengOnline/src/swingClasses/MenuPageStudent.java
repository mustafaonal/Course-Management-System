package swingClasses;
import cengOnline.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.UIManager;

public class MenuPageStudent{

private JFrame frame;
	
	public MenuPageStudent(int index) {
		
		frame = new JFrame();
		frame.setBounds(50, 50, 499, 480);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnOpenMailbox = new JButton("Open Mailbox");
		btnOpenMailbox.setFont(new Font("Arial", Font.PLAIN, 11));
		btnOpenMailbox.setBackground(SystemColor.inactiveCaption);
		btnOpenMailbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				boolean type = true;
				DisplayMailbox d=new DisplayMailbox(type,index);
				d.getFrame().setVisible(true);
			}
		});
		
		
		String name = Login.students.get(index).getName() + " " + Login.students.get(index).getSurname();
		JLabel lblNewLabel = new JLabel(name);
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		lblNewLabel.setBackground(new Color(0, 0, 51));
		lblNewLabel.setBounds(22, 36, 220, 16);
		frame.getContentPane().add(lblNewLabel);
		btnOpenMailbox.setBounds(252, 226, 140, 26);
		frame.getContentPane().add(btnOpenMailbox);
		
		JLabel lblWelcomeToDeu = new JLabel("Welcome to DEUCENG Online");
		lblWelcomeToDeu.setFont(new Font("Verdana Pro", Font.BOLD, 24));
		lblWelcomeToDeu.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToDeu.setForeground(new Color(0, 0, 51));
		lblWelcomeToDeu.setBackground(new Color(0, 51, 102));
		lblWelcomeToDeu.setBounds(0, 126, 485, 69);
		frame.getContentPane().add(lblWelcomeToDeu);
		
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(0, 0, 51));
		lblEmail.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		lblEmail.setBounds(22, 62, 56, 16);
		frame.getContentPane().add(lblEmail);
		
		
		JLabel lblNewLabel_3 = new JLabel(Login.students.get(index).getEmail());
		lblNewLabel_3.setForeground(new Color(0, 0, 51));
		lblNewLabel_3.setFont(new Font("Verdana Pro", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(76, 62, 225, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnRegisteredLectures = new JButton("Registered Lectures");
		btnRegisteredLectures.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRegisteredLectures.setBackground(SystemColor.inactiveCaption);
		btnRegisteredLectures.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisteredLectures r=new RegisteredLectures(index);
				r.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnRegisteredLectures.setBounds(76, 226, 140, 26);
		frame.getContentPane().add(btnRegisteredLectures);
		
		JLabel imgLabel = new JLabel("");

		Image img = new ImageIcon(this.getClass().getResource("/DeepBlueBg.png")).getImage();
		
		imgLabel.setBounds(-178, -182, 696, 683);
		imgLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(imgLabel);
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
