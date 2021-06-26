package swingClasses;
import cengOnline.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Image;

public class DisplayMailbox{

	private JFrame frame;
	private JScrollPane pane;
	private JScrollPane pane2;
	private JTable inboxTable;
	private JTable sentTable;
	private JTableHeader header;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTableHeader header2;

	private JPanel panel;
	private JButton btnSendMail;

	private Student st;
	private Teacher tc;
	int x = 1;
	int y = 1;
	final int length = 27;
	private JLabel lblNewLabel;
	public DisplayMailbox(boolean type,int index) {
		
		if(type)
		{
			 st = Login.students.get(index);
		}
		else {
			 tc = Login.teachers.get(index);
		}
		
		String col[] = { "From", "To", "Date","Title","Message"};
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 700);
		panel = new JPanel();
		panel.setBackground(new Color(102, 102, 153));
		inboxTable = new JTable();
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(col);
		inboxTable.setModel(model);
		inboxTable.setBackground(new Color(255, 204, 153));
		inboxTable.setForeground(new Color(0, 0, 0));
		inboxTable.setRowHeight(30);
		
		header = inboxTable.getTableHeader();
		sentTable = new JTable();
		header.setBackground(Color.yellow);
		panel.setLayout(null);
		
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(col);
		sentTable.setModel(model2);
		sentTable.setBackground(new Color(255, 204, 153));
		sentTable.setForeground(new Color(0, 0, 0));
		sentTable.setRowHeight(30);
		
		header2 = sentTable.getTableHeader();
		header2.setBackground(Color.yellow);
		

		pane2 = new JScrollPane(inboxTable);
		pane = new JScrollPane(sentTable);
		if(type)
		{
			for(Message mail: st.getMail().getInbox()) {
				String []row={mail.getSender(), mail.getReceiver(), mail.getDate().toString(), mail.getTitle(), mail.getMessage()};
				model.addRow(row);
				x++;
			}
			
			for(Message mail: st.getMail().getSent()) {
				String []row2 = {mail.getSender(), mail.getReceiver(), mail.getDate().toString(), mail.getTitle(), mail.getMessage()};
				model2.addRow(row2);
				y++;
			}
		}
		else {
			for(Message mail: tc.getMail().getInbox()) {
				String []row={mail.getSender(), mail.getReceiver(), mail.getDate().toString(), mail.getTitle(), mail.getMessage()};
				model.addRow(row);
				x++;
			}
			
			for(Message mail: tc.getMail().getSent()) {
				String []row2 = {mail.getSender(), mail.getReceiver(), mail.getDate().toString(), mail.getTitle(), mail.getMessage()};
				model2.addRow(row2);
				y++;
			}
		}

		pane.setBounds(53, 340, 527, 150);
		inboxTable.setBounds(53, 340, 527,(y* length));	
		pane2.setBounds(53, 87, 526, 150);
		sentTable.setBounds(54, 79, 526, (x * length));

		
		panel.add(pane);
		panel.add(pane2);
		frame.getContentPane().add(panel);
		
		btnSendMail = new JButton("Send Mail");
		btnSendMail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SendEmailPage s=new SendEmailPage(type,index);
				s.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		btnSendMail.setBounds(423, 537, 97, 25);
		panel.add(btnSendMail);
		
		JButton button = new JButton("< BACK");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(type) {
					MenuPageStudent menuStudent = new MenuPageStudent(index);
					menuStudent.getFrame().setVisible(true);
					frame.setVisible(false);
				}
				else {
					MenuPageTeacher menuTeacher = new MenuPageTeacher(index);
					menuTeacher.getFrame().setVisible(true);
					frame.setVisible(false);
				}
				
				
			}
		});
		button.setBounds(101, 537, 97, 25);
		panel.add(button);
		
		JLabel INBOX = new JLabel("INBOX");
		INBOX.setFont(new Font("Arial", Font.PLAIN, 35));
		INBOX.setBounds(240, 43, 112, 33);
		panel.add(INBOX);
		
		JLabel SENT = new JLabel("SENT");
		SENT.setFont(new Font("Arial", Font.PLAIN, 35));
		SENT.setBounds(252, 297, 112, 33);
		panel.add(SENT);
		
		Image img = new ImageIcon(this.getClass().getResource("/Pine.png")).getImage();

		JLabel imgLbl = new JLabel("");
		imgLbl.setBounds(0, 0, 657, 680);
		imgLbl.setIcon(new ImageIcon(img));
		panel.add(imgLbl);
	
	
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
