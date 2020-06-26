import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class JDBCDemo7 extends Frame implements ActionListener
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
	
	public JDBCDemo7()
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
		
		Panel pNav = new Panel(new GridLayout(1,4,5,0));
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
		
		setDisabled();
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
	
	public void setDisabled()
	{
		txtRollNo.setEditable(false);
		txtName.setEditable(false);
		txtMobileNo.setEditable(false);
		cbMale.setState(false);
		cbFemale.setState(false);
		//cbMale.setEditable(false);
		//cbFemale.setEditable(false);
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
		System.out.println("Action Performed");
		try{
			if(ae.getSource() == btnFirst)
			{
				System.out.println("first block");
				res.first();
				fillValues();
				
			}else if(ae.getSource() == btnPre)
			{
				System.out.println("previous block");
				res.previous();
				if(res.isBeforeFirst())	res.first();
				fillValues();
			}
			else if(ae.getSource() == btnNext)
			{
				System.out.println("previous next");
				res.next();
				if(res.isAfterLast())	res.last();
				fillValues();
				
			}else if(ae.getSource() == btnLast)
			{
				System.out.println("previous last");
				res.last();
				fillValues();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		new JDBCDemo7();
	}
}