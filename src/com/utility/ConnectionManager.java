package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	static String connectionid="jdbc:oracle:thin:@localhost:1521:xe";
	static String userid="hr";
	static String password="hr";
	
	//Class.forName("oracle.jdbc.driver.OracleDriver");
	public static Connection getConnection(){
		Connection conn =null;
try{
	conn= DriverManager.getConnection(connectionid,userid,password);
	System.out.println("Connection successful");
}
catch(Exception e){
	System.out.println("Could not establish connection");
}
return conn;
	}
	
	public static void closeConnection(Connection conn) {
		if(conn!=null)
		{
			try{
				conn.close();
			
			}
			catch (SQLException e) {
			e.printStackTrace();
			}
		}
		
	}

}
