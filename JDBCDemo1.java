import java.sql.*;
class JDBCDemo1
{
	public static void main(String args[])
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//(connectionString,username,password);
			con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			stmt = con.createStatement();
			
			String query = "Select * from students";
			
			res = stmt.executeQuery(query);
			
			System.out.format("%15s%15s%15s%15s","Roll No","Name","Gender","Mobile No");
			System.out.println();
			while(res.next())
			{
				int rollNo = res.getInt("roll_no");
				String name = res.getString("name");
				String gender = res.getString("gender");
				int mobile_no = res.getInt(4);
				
				System.out.format("%15d%15s%15s%15d", rollNo, name, gender, mobile_no);
				System.out.println();
			}
			
			res.close();
			stmt.close();
			con.close();
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}