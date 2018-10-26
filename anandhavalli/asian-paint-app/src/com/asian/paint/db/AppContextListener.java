package com.asian.paint.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

		try {
			Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String dbUser = (String) context.getAttribute("dbUser");
		String dbPassword = (String) context.getAttribute("dbPassword");
		String dbUrl = (String) context.getAttribute("dbUrl");
		if(dbUrl==null||dbUser==null||dbPassword==null)
		{
			dbUser="root";
			dbPassword="root";
			dbUrl="jdbc:mysql://localhost:3306/asianpaint";
		}
		try {
			System.out.println("Initialize the DBConnection");
			DBConnectionManager manager = new DBConnectionManager(dbUrl, dbUser, dbPassword);
			context.setAttribute("DBConnection", manager.getConnection());
			System.out.println("Connection was successfully established ");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
