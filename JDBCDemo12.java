import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

class JDBCDemo9 extends Frame implements ActionListener
{
	Connection con = null;
	Statement  stmt = null;
	ResultSet rst = null;
	
	String cols[];
	String rows[][];
	JTable tab;
	JScrollPane jsp;
	ResultSetMetaData rmd = null; 
	Button editBtn;
	int editRowNumber = 1;
	JDBCDemo10 formClass = null;
	
	public JDBCDemo9(JDBCDemo10 formClass)
	{
		try
		{
			setTitle("JTable Demo");
			setBounds(400,400,700,300);
			
			setDatabase();
			
			this.formClass = formClass;
			
			rmd = rst.getMetaData();
			
			cols = new String[rmd.getColumnCount()];
			
			for(int i=0;i<rmd.getColumnCount();i++)
			{
				cols[i] = rmd.getColumnName(i+1);
			}
			
			//System.out.println(cols);
			
			rst.last();
			int totalRows = rst.getRow();
			rows = new String[totalRows][cols.length];
			
			int i=0;
			rst.beforeFirst();
			while(rst.next())
			{
				rows[i][0] = rst.getString("roll_no");
				rows[i][1] = rst.getString("name");
				rows[i][2] = rst.getString("gender");
				rows[i][3] = rst.getString("mobile_no");
				i++;
			}
			
			tab = new JTable(rows,cols);
			jsp = new JScrollPane(tab);
			
			editBtn = new Button("Edit");
			editBtn.addActionListener(this);
			
			add(jsp);
			add(editBtn,"South");
			
			
			setVisible(true);
		}catch(Exception e)
		{
			
		}		
	}

	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == editBtn){
			editRowNumber = tab.getSelectedRow() + 1;
			
			try
			{
				rst.absolute(editRowNumber);
				formClass.txtRollNo.setText(rst.getString("roll_no"));
				formClass.txtRollNo.setEditable(false);
				formClass.txtName.setText(rst.getString("name"));
				formClass.txtMobileNo.setText(rst.getString("mobile_no"));
				if(rst.getString("gender").equals("male"))
					formClass.cbMale.setState(true);
				else
					formClass.cbFemale.setState(true);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			dispose();
			//setVisible(false);
		}
		
		//setVisible(false);
		/* try
		{
			rst.absolute(rowNumber);
		
			System.out.println("Roll No : " + rst.getInt("roll_no"));
		}catch(Exception e)
		{
			
		} */
		
	}
	
	public void setDatabase() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
		
		stmt = con.createStatement();
		
		rst = stmt.executeQuery("Select * from students");
	}
	
	
	/* public static void main(String args[])
	{
		new JDBCDemo9();
	} */
}