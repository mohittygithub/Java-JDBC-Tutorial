import java.sql.*;
import java.util.Date;
class JDBCDemo3
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
			
			String query = "Insert into students(roll_no, name, gender, mobile_no) values(?,?,?,?)";
			
			pstmt = con.prepareStatement(query);
			
			//bind parameters with query
			pstmt.setInt(1,roll_no);
			pstmt.setString(2,name);
			pstmt.setString(3,gender);
			pstmt.setInt(4,mobile_no);
			
			int count=0;
			
			long startTime = new Date().getTime(); // millisecond
			
			for(int i=0;i<=10000;i++)
			{
				pstmt.executeUpdate();
			}
			
			long endTime = new Date().getTime(); 
			
			System.out.println("PreparedStatement execution  time => " + (endTime - startTime));
			
			System.out.println("Total " + count + " Rows Affected !");
			
			con.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}