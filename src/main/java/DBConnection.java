import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection 
{
   public static void main(String args[]) throws Exception
   {
	      Connection con = null;
	     
	     Class.forName("com.mysql.cj.jdbc.Driver"); 
	     System.out.println("Driver Class Loaded");
	         
	     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsps12", "root", "root");
	     System.out.println("Connection Established");
   }
}
