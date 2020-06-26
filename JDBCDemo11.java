import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class JDBCDemo10 extends Frame implements ActionListener
{
	Connection con = null;
	Statement  stmt = null;
	ResultSet rst = null;
	
	TextField txtRollNo, txtName, txtMobileNo;
	Label lblRollNo, lblName, lblMobileNo, lblGender;
	Checkbox cbMale, cbFemale;
	CheckboxGroup cbg;
	Button btnSave, btnCancel, btnShowRecords;
	Panel pForm, pBtn;
	JDBCDemo9 frmRecords;
	public JDBCDemo10()
	{
		try{
			setTitle("CRUD with JTable Demo");
			setBounds(400,400,400,400);
			//setLayout();
			
			pForm = new Panel(new GridLayout(4,5,5,5));
			lblRollNo = new Label("Roll No",Label.CENTER);
			lblName = new Label("Name",Label.CENTER);
			lblGender = new Label("Gender",Label.CENTER);
			lblMobileNo = new Label("Mobile No",Label.CENTER);
			
			txtRollNo = new TextField();
			txtName = new TextField();
			txtMobileNo = new TextField();
			
			Panel pGender = new Panel(new GridLayout(1,2,0,3));
			cbg = new CheckboxGroup();
			cbMale = new Checkbox("Male",cbg,false);
			cbFemale = new Checkbox("Female",cbg,false);
			pGender.add(cbMale);
			pGender.add(cbFemale);
			
			pBtn = new Panel(new GridLayout(3,1,5,0));
			btnSave = new Button("Save");
			btnSave.addActionListener(this);
			
			btnCancel = new Button("Cancel");
			btnCancel.addActionListener(this);
			
			btnShowRecords = new Button("Show Records");
			btnShowRecords.addActionListener(this);
			
			pForm.add(lblRollNo);			pForm.add(txtRollNo);
			pForm.add(lblName);				pForm.add(txtName);
			pForm.add(lblGender);			pForm.add(pGender);
			pForm.add(lblMobileNo);			pForm.add(txtMobileNo);
			pBtn.add(btnSave);				pBtn.add(btnCancel);
			pBtn.add(btnShowRecords);				
			
			add(pForm);
			add(pBtn,"South");
			
			setDatabase();
			
			setVisible(true);
		}catch(Exception e)
		{
			
		}
		//new JDBCDemo9();
	}
	
	public void setDatabase() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
		
		stmt = con.createStatement();
		
		rst = stmt.executeQuery("Select * from students");
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == btnShowRecords)
		{
			frmRecords = new JDBCDemo9(this);
			System.out.println(frmRecords);
		}else if(ae.getSource() == btnSave)
		{
			/* System.out.println(frmRecords);
			System.out.println(frmRecords.editRowNumber); */
		}
	}
	
	public static void main(String args[])
	{
		new JDBCDemo10();
	}
}