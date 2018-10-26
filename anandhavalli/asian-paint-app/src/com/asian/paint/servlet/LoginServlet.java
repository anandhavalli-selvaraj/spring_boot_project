package com.asian.paint.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asian.paint.db.DBConnectionManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		final String userName = request.getParameter("email");
		final String password = request.getParameter("password");
		final Connection con = getDBConnection();
		java.sql.PreparedStatement ps=null;
		try {
			ps=con.prepareStatement("select users_id,name,country,emailId,password from users where emailId = ? and password = ? limit 1 ");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			PrintWriter out=response.getWriter();
			if(rs!=null&&rs.next())
			{	
			String name=rs.getString("name");
			System.out.println("The User"+name+" is logged in the portal");
		out.println("<font size=10 color=red>"+name+"is successfully logged in the portal "+"</font>");
			}
			else
			{
				out.println("<font size=10 color=red>"+"Please Login Below "+"</font>");

				RequestDispatcher rd=getServletContext().getRequestDispatcher("login.html");
				rd.include(request,response);
			}
	} catch (Exception e) {
			e.printStackTrace();
		}

		finally {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	protected Connection getDBConnection() {
		String dbUser;
		String dbPassword;
		String dbUrl;
		dbUser = "root";
		dbPassword = "root";
		dbUrl = "jdbc:mysql://localhost:3306/asianpaint";

		try {
			System.out.println("Initialize the DBConnection");
			DBConnectionManager manager = new DBConnectionManager(dbUrl, dbUser, dbPassword);
			Connection connection = (Connection) manager.getConnection();
			return connection;
		} catch (Exception e) {
			System.out.println("The exception occured via DBConnection {}" + e);
		}
		return null;
	}

}
