//MySQL Injection Example
import java.sql.*;
class JDBCDemo5
{
	public static void main(String args[])
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			stmt = con.createStatement();
			
			/* String email = "user1@gmail.com";
			String password = "user1"; */
			
			String email = "a' OR '1' = '1";
			String password = "a' OR '1' = '1";
			
			String query = "Select * from user where email = '"+email+"' and password = '"+password+"'";
			
			System.out.println(query);
			
			res = stmt.executeQuery(query);
			
			res.last();
			int totalRows = res.getRow();
			
			if(totalRows > 0)
			{
				System.out.println("Login successfully !");
			}else
			{
				System.out.println("Email or password is incorrect !");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}