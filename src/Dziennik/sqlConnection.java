package Dziennik;
import java.sql.*;
import javax.swing.*;


public class sqlConnection {
	Connection myConn = null;
	
	public static Connection dbConnector()
	{
		
		try
		{
		
			Class.forName("org.sqlite.JDBC");
			
			//create ocnnection
			Connection myConn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Admin\\Desktop\\Terminy\\Dziennik Elektroniczny V1\\DZIENNIKDB\\DZIENNIKDB.sqlite");
			JOptionPane.showMessageDialog(null, "Utworzono po��czenie z baz�");
			//return connection
			return myConn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
