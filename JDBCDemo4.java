import java.sql.*;
import java.util.Date;
class JDBCDemo4
{
	public static void main(String args[])
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			int roll_no = 106;
			String name = "Test Students";
			String gender = "male";
			int mobile_no = 12343456;
			
			
			//System.out.println("Total " + count + " Rows Affected !");
			
			
			stmt = con.createStatement();
			
			String query = "Insert into students(roll_no, name, gender, mobile_no) values("+roll_no+", '"+name+"', '"+gender +"', "+mobile_no+")";
			
			
			long startTime = new Date().getTime(); // millisecond
			
			for(int i=0;i<=10000;i++)
			{
				stmt.executeUpdate(query);
			}
			
			long endTime = new Date().getTime(); 
			
			System.out.println("CreateStatement execution  time => " + (endTime - startTime));
			
			//System.out.println("Total " + count + " Rows Affected !");
			
			
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}