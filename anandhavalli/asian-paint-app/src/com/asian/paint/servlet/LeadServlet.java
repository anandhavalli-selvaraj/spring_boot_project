package com.asian.paint.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asian.paint.db.DBConnectionManager;
import com.asian.paint.db.User;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class LeadServlet
 */
@WebServlet("/LeadServlet")
public class LeadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Reached the LeadServlet");
		response.setContentType("application/json");
		
		response.setStatus(200);
		Connection con=(Connection) getServletContext().getAttribute("DBConnection");
		if(con==null)
		{
			con=(Connection) new DBConnectionManager().getDBConnection();
		}
		java.sql.PreparedStatement ps =null;
		try
		{
			ps=con.prepareStatement("select * from users");
			ResultSet rs=ps.executeQuery();
			List<User>userList=new ArrayList<>();
			while(rs!=null&&rs.next())
			{
				User user=new User();
				user.setCountry(rs.getString("country"));
				user.setEmailId(rs.getString("emailId"));
				user.setUserName(rs.getString("name"));
				userList.add(user);
				
			
			}
			response.setCharacterEncoding("UTF-8");
		PrintWriter printwriter=response.getWriter();
		printwriter.print(new Gson().toJson(userList));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
