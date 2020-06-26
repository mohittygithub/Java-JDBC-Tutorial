//MySQL Injection Prevention
import java.sql.*;
class JDBCDemo6
{
	public static void main(String args[])
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			String query = "Select * from user where email = ? and password = ?";
			
			pstmt = con.prepareStatement(query);
			
			/* String email = "user1@gmail.com";
			String password = "user1"; */
			
			String email = "a' OR '1' = '1";
			String password = "a' OR '1' = '1";
			
			pstmt.setString(1,email);
			pstmt.setString(2,password);
			
			System.out.println(query);
			
			res = pstmt.executeQuery();
			
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