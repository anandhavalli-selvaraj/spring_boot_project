package com.asian.paint.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.jws.WebService;

@WebService
public class DBConnectionManager{

	private Connection connection;
	public DBConnectionManager()
	{
		
	}
	public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(dbURL, user, pwd);
	}
	
	public Connection getConnection(){
		return this.connection;
	}
	public Connection getDBConnection()
	{
		if(connection==null)
		{
		String dbUser ;
		String dbPassword;
		String dbUrl;
			dbUser="root";
			dbPassword="root";
			dbUrl="jdbc:mysql://localhost:3306/asianpaint";
		
		try {
			System.out.println("Initialize the DBConnection");
			DBConnectionManager manager = new DBConnectionManager(dbUrl, dbUser, dbPassword);
			Connection connection=(Connection) manager.getConnection();
			return connection;
		}
		catch(Exception e)
		{
			System.out.println("The exception occured via DBConnection {}"+e);
		}
		}
		return connection;
	}
}
