package view;

import model.Student;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import java.sql.*;

import controller.DBManagement;

public class Gui extends JFrame implements ActionListener {
	JTextField nameField;
	JTextField ageField;
	JTextField lastdegreeField;
	
	JLabel nameLabel;
	JLabel ageLabel;
	JLabel sexLabel;
	JLabel lastdegreeLabel;
	
	JRadioButton sexButtonMALE;
	JRadioButton sexButtonFEMALE;
	ButtonGroup sexGroup;
	JPanel sexPanel;
	
	DefaultListModel<String> dlm;
	JList<String> degreeList;
	JScrollPane degreeScroll;
	
	JButton submitButton;
	JButton registerButton;
	JButton displayButton;
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane tableScroll;
	JButton clearTable;
	
	public Gui() {
		this.setLayout(new BorderLayout());
		this.setTitle("STUDENT DATABASE");
		
		nameLabel = new JLabel(" NAME : ");
		ageLabel = new JLabel(" AGE : ");
		sexLabel = new JLabel(" SEX : ");
		lastdegreeLabel = new JLabel(" LAST DEGREE : ");
		
		nameField = new JTextField(20);
		ageField = new JTextField(3);
		
		sexButtonMALE = new JRadioButton("MALE");
		sexButtonFEMALE = new JRadioButton("FEMALE");
		sexGroup = new ButtonGroup();
		sexGroup.add(sexButtonFEMALE);
		sexGroup.add(sexButtonMALE);
		sexPanel = new JPanel();
		sexPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		sexPanel.setBackground(Color.red);
		sexPanel.add(sexButtonFEMALE);
		sexPanel.add(sexButtonMALE);
		
		dlm = new DefaultListModel<String>();
		dlm.addElement("Bachelor");
		dlm.addElement("Master");
		dlm.addElement("PHD");
		degreeList = new JList<String>(dlm);
		degreeScroll = new JScrollPane(degreeList);
		
		JPanel detailsPanel = new JPanel(new GridLayout(4,2));
		detailsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "ENTER STUDENT DETAILS"));
		detailsPanel.setBackground(Color.green);
		
		detailsPanel.add(nameLabel);
		detailsPanel.add(nameField);
		detailsPanel.add(ageLabel);
		detailsPanel.add(ageField);
		detailsPanel.add(sexLabel);
		detailsPanel.add(sexPanel);
		detailsPanel.add(lastdegreeLabel);
		detailsPanel.add(degreeScroll);
		
		this.add(detailsPanel, BorderLayout.WEST);
		
		submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(this);
		
		registerButton = new JButton("REGISTER");
		registerButton.addActionListener(this);
		
		displayButton = new JButton("DISPLAY");
		displayButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SUBMIT/REGISTER/READ THE DETAILS"));
		buttonPanel.add(submitButton);
		buttonPanel.add(registerButton);
		buttonPanel.add(displayButton);
		buttonPanel.setBackground(Color.yellow);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		JPanel queryPanel = new JPanel(new FlowLayout());
		queryPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "SUBMIT/REGISTER/READ THE DETAILS"));
		queryPanel.setBackground(Color.orange);
		
		String [] header = {"ID", "NAME", "AGE", "SEX", "DEGREE"};
		dtm = new DefaultTableModel(header, 0);
		table = new JTable(dtm);
		tableScroll = new JScrollPane(table);
		tableScroll.setSize(400, 400);
		queryPanel.add(tableScroll);
		
		clearTable = new JButton("CLEAR TABLE");
		queryPanel.add(clearTable);
		clearTable.addActionListener(this);
		
		this.add(queryPanel, BorderLayout.CENTER);
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.populateTable();
	}
	
	public void populateTable() {
		try {
			Student [] sarr = DBManagement.readFromDB();
			for(int i =0; i < 3; i++) {
				Object [] rowdata = {i, sarr[i].getName() , sarr[i].getAge(), sarr[i].getSex(), sarr[i].getLastDegree()};
				dtm.addRow(rowdata);
			}
		} catch(SQLException e1) {
			
		}
	}
	
	@Override public void actionPerformed(ActionEvent e) {
		String name = nameField.getText();
		String age = ageField.getText();
		if (e.getSource() == submitButton) {
			if(name.isEmpty() || age.isEmpty() ||
					(!sexButtonMALE.isSelected() && !sexButtonFEMALE.isSelected()) || 
					(degreeList.getSelectedValue() == null)) {
				JOptionPane.showMessageDialog(this, "PLEASE CORRECT THE INFO", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				String sex;
				if(sexButtonMALE.isSelected()) {
					sex = "male";
				} else {
					sex = "female";
				}
				String degree = degreeList.getSelectedValue();
				Student s = new Student(name, Integer.parseInt(age), sex, degree);
				
				try {
					DBManagement.registerIntoDB(s);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				this.dispose();
			}
		} else if (e.getSource() == displayButton) {
			try {
				Student [] sarr = DBManagement.readFromDB();
				for(int i =0; i < 3; i++) {
					boolean found = false;
					for(int j = 0; j < dtm.getRowCount(); j++) {
						String name1 = (String)dtm.getValueAt(j, 1);
						if(name1.compareTo(sarr[i].getName()) == 0) {
							found = true;
							break;
						} 
					}
					if(!found) {
						Object [] rowdata = {i, sarr[i].getName() , sarr[i].getAge(), sarr[i].getSex(), sarr[i].getLastDegree()};
						dtm.addRow(rowdata);
					}
				}
			} catch (SQLException e1) {
				
			}
			
			
		} else if(e.getSource() == registerButton) {
			if(name.isEmpty() || age.isEmpty() ||
					(!sexButtonMALE.isSelected() && !sexButtonFEMALE.isSelected()) || 
					(degreeList.getSelectedValue() == null)) {
				JOptionPane.showMessageDialog(this, "PLEASE CORRECT THE INFO", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				String sex;
				if(sexButtonMALE.isSelected()) {
					sex = "male";
				} else {
					sex = "female";
				}
				String degree = degreeList.getSelectedValue();
				Student s = new Student(name, Integer.parseInt(age), sex, degree);
				
				try {
					DBManagement.registerIntoDB(s);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if(e.getSource() == clearTable) {
			System.out.println("CLEAR BUTTON PRESSED");
			dtm.setRowCount(0);
		}
	}
	
	/*
	public static void main(String [] args) {
		Gui g = new Gui();
	}
	*/
}
