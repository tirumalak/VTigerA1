package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	public Connection con;
	public Connection connectToDB() throws SQLException 
	{
		Driver dobj=new Driver();
			DriverManager.registerDriver(dobj);
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advproject","root","root");
			return con;
			
			
		}
	public Connection connectToDB(String url,String un,String pswd) throws SQLException 
	{
		Driver dobj=new Driver();
			DriverManager.registerDriver(dobj);
			con=DriverManager.getConnection(url,un,pswd);
			return con;
			
			
		}
	public ResultSet fetchDataFromDatabase(String query) throws SQLException
	{
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		return result;
	}
	public int updateDataToDatabase(String query) throws SQLException
	{
		Statement stmt = con.createStatement();
		int result = stmt.executeUpdate(query);
		return result;
	}
	
	public void closeDBConnection() throws SQLException
	{
		con.close();
	}
		
	

}
