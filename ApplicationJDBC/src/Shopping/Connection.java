package Shopping;

//import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
	public Connection getConnection() throws SQLException,ClassNotFoundException
	{
	//1) Register the Driver.dounload link
			
			Class.forName("com.mysql.jdbc.Driver");//registr a driver //Class.forName()
			java.sql.Connection con=null;//Connection object
			//2)create a Connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","");
			if(con!=null)//check connection
			{
				return (Connection) con;
			}
			else
			{
				return null;
			}

}
}
