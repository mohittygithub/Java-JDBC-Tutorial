import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class JDBCDemo8 extends Frame implements ActionListener
{
	Connection con = null;
	Statement stmt = null;
	ResultSet res = null;
	
	TextField txtRollNo, txtName, txtMobileNo;
	Label lblRollNo, lblName, lblGender, lblMobileNo;
	Button btnFirst, btnNext, btnPre, btnLast;
	Button btnAdd, btnEdit, btnDelete, btnSave, btnCancel;
	Checkbox cbMale, cbFemale;
	CheckboxGroup cbg;
	Panel pNav;
	boolean isEditClicked = false;
	int editRowNumber;
	public JDBCDemo8()
	{
		setTitle("JDBC Example 1");
		setBounds(200,200,300,300);
		
		lblRollNo = new Label("Roll No");
		lblName = new Label("Name");
		lblGender = new Label("Gender");
		lblMobileNo = new Label("Mobile No");
		
		txtRollNo = new TextField();
		txtName = new TextField();
		txtMobileNo = new TextField();
		
		Panel genderPanel = new Panel(new GridLayout(1,2));
		cbg = new CheckboxGroup();
		cbMale = new Checkbox("Male",cbg,false);
		cbFemale = new Checkbox("Female",cbg,false);
		genderPanel.add(cbMale); 	genderPanel.add(cbFemale);
		
		Panel p = new Panel(new GridLayout(4,4,5,5));
		p.add(lblRollNo);		p.add(txtRollNo);
		p.add(lblName);			p.add(txtName);
		p.add(lblGender);		p.add(genderPanel);
		p.add(lblMobileNo);		p.add(txtMobileNo);

		btnFirst = new Button("First");
		btnFirst.addActionListener(this);
		
		btnPre = new Button("Previous");
		btnPre.addActionListener(this);
		
		btnNext = new Button("Next");
		btnNext.addActionListener(this);
		
		btnLast = new Button("Last");
		btnLast.addActionListener(this);
		
		pNav = new Panel(new GridLayout(1,4,5,0));
		pNav.add(btnFirst);
		pNav.add(btnPre);
		pNav.add(btnNext);
		pNav.add(btnLast);
		
		btnAdd = new Button("Add");
		btnAdd.addActionListener(this);
		
		btnEdit = new Button("Edit");
		btnEdit.addActionListener(this);
		
		btnDelete = new Button("Delete");
		btnDelete.addActionListener(this);
		
		btnSave = new Button("Save");
		btnSave.addActionListener(this);
		
		btnCancel = new Button("Cancel");
		btnCancel.addActionListener(this);
		
		Panel crudPanel = new Panel(new GridLayout(1,5,2,0));
		crudPanel.add(btnAdd);
		crudPanel.add(btnEdit);
		crudPanel.add(btnSave);
		crudPanel.add(btnCancel);
		crudPanel.add(btnDelete);
		
		
		add(p);
		add(pNav,"South");
		add(crudPanel,"North");
		
		setEnabledDisabled(true);
		setDatabase();
		
		setVisible(true);
	}
	
	
	
	public void setDatabase()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		
			con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			stmt = con.createStatement();
			
			res = stmt.executeQuery("Select * from students");
			res.first();
			
			fillValues();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void setEnabledDisabled(boolean flag) // true
	{
		txtRollNo.setEditable(!flag);
		txtName.setEditable(!flag);
		txtMobileNo.setEditable(!flag);
		cbMale.setEnabled(!flag);
		cbFemale.setEnabled(!flag);
		
		btnAdd.setEnabled(flag);
		btnEdit.setEnabled(flag);
		btnDelete.setEnabled(flag);
		btnSave.setEnabled(!flag);
		btnCancel.setEnabled(!flag);
		
		pNav.setEnabled(flag);
	}
	
	public void fillValues() throws Exception
	{
		txtRollNo.setText(String.valueOf(res.getInt("roll_no")));
		txtName.setText(res.getString("name"));
		txtMobileNo.setText(String.valueOf(res.getString("mobile_no")));
		String gender = res.getString("gender");
		if(gender.equals("male"))
		{
			cbMale.setState(true);
		}else
		{
			cbFemale.setState(true);
		}				
		
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		try{
			if(ae.getSource() == btnFirst)
			{
				res.first();
				fillValues();
				
			}else if(ae.getSource() == btnPre)
			{
				res.previous();
				if(res.isBeforeFirst())	res.first();
				fillValues();
			}
			else if(ae.getSource() == btnNext)
			{
				res.next();
				if(res.isAfterLast())	res.last();
				fillValues();
				
			}else if(ae.getSource() == btnLast)
			{
				res.last();
				fillValues();
			}else if(ae.getSource() == btnAdd)
			{
				res.last();
				txtRollNo.setText(String.valueOf(res.getInt("roll_no")+1));
				setEnabledDisabled(false);
				txtRollNo.setEditable(false);
				
				txtName.setText("");
				txtMobileNo.setText("");
				/* cbMale.setState(false);
				cbFemale.setState(false); */
				Checkbox current = cbg.getCurrent();
				cbg.setCurrent(null);
				current.setState(false);
			}else if(ae.getSource() == btnSave)
			{
				int rollNo = Integer.parseInt(txtRollNo.getText());
				String name = txtName.getText();
				String gender = "";
				if(cbMale.getState() == true)
				{
					gender = "male";
				}else
				{
					gender = "female";
				}
				
				long mobileNo = Long.parseLong(txtMobileNo.getText());
				
				String query = "";
				if(!isEditClicked){
					query  = "Insert into students(roll_no, name, gender, mobile_no) values(";
					query +=  rollNo + ", "; 
					query +=  "'" + name + "', "; 
					query +=  "'" + gender + "', "; 
					query +=  mobileNo; 
					query += ")";
				}else
				{
					query = "Update students SET ";
					query += "name='"+name+"', ";
					query += "gender='"+gender+"', ";
					query += "mobile_no="+mobileNo;
					query += " where roll_no="+rollNo;
				}
				
				
				//System.out.println("query => " + query);
				
				int count = stmt.executeUpdate(query);
				
				javax.swing.JOptionPane.showMessageDialog(this,count + " Record " + (isEditClicked == true ? "Updated !" : "Inserted !"));
				
				setEnabledDisabled(true);
				res = stmt.executeQuery("Select * from students");
				
				if(!isEditClicked){
					res.last();
				}else
				{
					res.absolute(editRowNumber);
					isEditClicked = false;
				}
				
				fillValues();
			}else if(ae.getSource() == btnCancel)
			{
				setEnabledDisabled(true);
				res.first();
				fillValues();
			}else if(ae.getSource() == btnEdit)
			{
				isEditClicked = true;
				editRowNumber = res.getRow();
				setEnabledDisabled(false);
				txtRollNo.setEditable(false);
			}else if(ae.getSource() == btnDelete)
			{
				int rollNo = Integer.parseInt(txtRollNo.getText());
				//TRUNCATE TABLE students = delete data
				//DROP TABLE students = delete data + structure
				String query = "Delete from students where roll_no="+rollNo;
				int count = stmt.executeUpdate(query);
				javax.swing.JOptionPane.showMessageDialog(this,count + " Record Deleted !");
				res = stmt.executeQuery("Select * from students");
				res.first();
				fillValues();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new JDBCDemo8();
		
	}
}