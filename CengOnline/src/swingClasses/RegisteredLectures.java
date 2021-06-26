package swingClasses;
import cengOnline.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;

public class RegisteredLectures {

	private JFrame frame;
	private JTable jTable1;
	private JTableHeader header;
	private DefaultTableModel model;
	int x = 1;
	private JPanel panel;

	private JScrollPane pane;
	
	public RegisteredLectures(int index) {

		Student student = Login.students.get(index);
		frame = new JFrame();

		panel = new JPanel();
		panel.setLayout(null);

		frame.setBounds(0, 0, 600, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String col[] = { "Course ID", " ", "Course Name" };

		model = new DefaultTableModel();
		model.setColumnIdentifiers(col);

		jTable1 = new JTable();

		jTable1.setModel(model);
		for (Course course : Login.courses) {
			for (Student s : course.getStudents()) {
				if (s.getID() == student.getID()) {
					String row[] = { Integer.toString(course.getCourseID()), course.getName_short(),
							course.getCourseName() };
					model.addRow(row);
					x++;
				}
			}
		}

		JButton btnSelectedRow = new JButton("SELECT");
		btnSelectedRow.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSelectedRow.setBounds(435, 449, 85, 22);
		btnSelectedRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRowIndex = jTable1.getSelectedRow();
				String st = (String) model.getValueAt(selectedRowIndex, 0);
				int id = Integer.parseInt(st);
				CoursePageStudent coursePageStudent = new CoursePageStudent(id, index);
				coursePageStudent.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});


		jTable1.setBounds(71, 80, 400, (x * 30));
		
		jTable1.setBorder(new LineBorder(new Color(119, 136, 153)));
		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.setFont(new Font("Arial", Font.PLAIN, 13));
		jTable1.setBackground(new Color(173, 216, 230));
		jTable1.setForeground(new Color(0, 0, 0));
		jTable1.setRowHeight(30);
		header = jTable1.getTableHeader();
		header.setBackground(Color.pink);
		
		pane = new JScrollPane(jTable1);
		pane.setBounds(71,80,400,270);
		panel.add(pane);
		
		JButton btnNewButton = new JButton("< BACK");
		btnNewButton.setBounds(58, 450, 85, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPageStudent menuPageStudent = new MenuPageStudent(index);
				menuPageStudent.getFrame().setVisible(true);
				frame.setVisible(false);
			}
		});
		panel.add(btnNewButton);

		Image img = new ImageIcon(this.getClass().getResource("/Pine.png")).getImage();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 600, 550);
		lblNewLabel.setIcon(new ImageIcon(img));
		
		panel.add(btnSelectedRow);

		panel.add(lblNewLabel);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(panel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
