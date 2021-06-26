package swingClasses;
import cengOnline.*;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

public class MenuPageTeacher {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel model;
	private JTableHeader header;
	private JButton course_select;
	public int x = 1;


	public MenuPageTeacher(int index) {
		Teacher teacher = Login.teachers.get(index);
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);
			
		
		String col[] = {"Course Short Name", "Course Name"};
		
		table = new JTable();
		table.setBorder(new LineBorder(new Color(112, 128, 144)));
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		table.setBackground(new Color(176, 196, 222));
		table.setForeground(new Color(0, 0, 0));
		table.setRowHeight(30);
		
		header = table.getTableHeader();
		header.setBackground(Color.yellow);
		
		
		frame.getContentPane().add(table);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(255, 255, 204));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Courses", "Add Course", "Mail Box", "Exit"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(267, 6, 156, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel deuceng = new JLabel("DEUCENG ONLINE - " + teacher.getName() + " " + teacher.getSurname());
		deuceng.setFont(new Font("Arial", Font.PLAIN, 10));
		
		deuceng.setBackground(new Color(255, 255, 204));
		deuceng.setForeground(new Color(0, 0, 0));
		deuceng.setBounds(20, 4, 202, 27);
		frame.getContentPane().add(deuceng);
		
		course_select = new JButton("Select Course");
		
		frame.getContentPane().add(course_select);
		
		model.addRow(col);
		
		
		
		for (Course course : Login.courses) {
			for (Teacher tc : course.getTeachers()) {
				if(tc.getID() == teacher.getID()) {
					String[] col2 = {course.getName_short(), course.getCourseName()};
					model.addRow(col2);
					x++;
				}
			}
		}
		frame.setBounds(0, 0, 462,500);
		course_select.setBounds(150, (x*40) + 15, 120, 23);
		table.setBounds(20, 45, 403, (x*30));
		
		course_select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int selectedRowIndex=table.getSelectedRow();
                CoursePageTeacher cpt = new CoursePageTeacher((String) model.getValueAt(selectedRowIndex, 0), index);
                cpt.getFrame().setVisible(true);
                frame.setVisible(false);

            }
        });
		
		
		comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {	
            	if (comboBox.getSelectedItem().equals("Exit")) {
            		System.exit(0);
            	}else if (comboBox.getSelectedItem().equals("Mail Box")) {
            		frame.dispose();
    				boolean type = false;
    				DisplayMailbox d=new DisplayMailbox(type,index);
    				d.getFrame().setVisible(true);
            	}else if (comboBox.getSelectedItem().equals("Add Course")) {
            		AddCoursePage addCoursePage = new AddCoursePage(index, teacher.getID());
            		addCoursePage.getFrame().setVisible(true);
            		frame.dispose();
            	}else if (comboBox.getSelectedItem().equals("Courses")) {
            		//
            	} 
            	

            }
        });
		
		Image img = new ImageIcon(this.getClass().getResource("/newyork.png")).getImage();
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(-193, -23, 703, 585);
		lblNewLabel.setIcon(new ImageIcon(img));
		frame.getContentPane().add(lblNewLabel);
		
		
	}


	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
}
