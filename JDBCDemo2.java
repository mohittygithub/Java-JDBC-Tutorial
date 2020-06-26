import java.sql.*;
import java.util.Scanner;
class JDBCDemo2
{
	
	public static void main(String args[])
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter Roll No : ");
			int roll_no = sc.nextInt();
			
			sc.nextLine();
			
			System.out.println("Enter Name : ");
			String name = sc.nextLine();
			
			System.out.println("Enter gender : ");
			String gender = sc.nextLine();
			
			System.out.println("Enter Mobile : ");
			int mobile_no = sc.nextInt();
			
			stmt = con.createStatement();
			
			//String query = "Insert into students(roll_no,name,gender,mobile_no) values(104,'Jatin','male',1324567823)";
			
			String customQuery = "Insert into students(roll_no,name,gender,mobile_no) values("+roll_no+", '"+name+"', '"+ gender +"', "+mobile_no+")";
			
			//System.out.println("customQuery => " + customQuery);
			
			int count = stmt.executeUpdate(customQuery);
		
			System.out.println( "Total " + count + " Rows Affected !");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}


