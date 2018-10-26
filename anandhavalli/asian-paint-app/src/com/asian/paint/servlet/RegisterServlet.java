package com.asian.paint.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.asian.paint.db.DBConnectionManager;
import com.google.gson.Gson;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,String>requestMap=parseRequest(request);
		final String emailId=requestMap.get("emailId");
		final String password=requestMap.get("password");
		final String name=requestMap.get("name");
		final String country=requestMap.get("country");
		String errMsg = " ";
		int users_id=(int)Math.random();
		System.out.println(" Reached the Registration ");
		if(emailId==null||emailId.isEmpty())
		{
			errMsg+=" The Email Id could not be null or Empty ";
		}
		if(password==null||password.isEmpty())
		{
			errMsg+=" The password could not be null or Empty ";
		}if(name==null||name.isEmpty())
		{
			errMsg+=" The name could not be null or Empty ";
		}
		if(country==null||country.isEmpty())
		{
			errMsg+=" The country could not be null or Empty ";
		}
//		if(errMsg.contains(null))
//		{
//			RequestDispatcher rd=getServletContext().getRequestDispatcher("/register.html");
//			PrintWriter out=response.getWriter();
//			out.println("<font color=red>"+errMsg+"</font>");
//			rd.include(request, response);
//			
//		}
//		else
//		{
			Connection con=(Connection) getServletContext().getAttribute("DBConnection");
			java.sql.PreparedStatement ps =null;
			try
			{
				ps=con.prepareStatement("insert into users (name,emailId,password,country) values(?,?,?,?)");
			//	ps.setInt(1,users_id);
				ps.setString(1,name);
				ps.setString(2,emailId);
				ps.setString(3, password);
				ps.setString(4, country);
				ps.execute();
				RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
				response.setContentType("application/json");
				
				response.setStatus(201);
				Map<String,String>responseMap=new HashMap<>();
				responseMap.put("message","User is registered Successfully " );
				response.setCharacterEncoding("UTF-8");
				PrintWriter printwriter=response.getWriter();
				printwriter.print(new Gson().toJson(responseMap));
			//	out.println("<font color=green>"+" User Registration is successfully, Please Login Below"+"</font>");
				//rd.include(request, response);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try {
					ps.close();
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
		
	}
	private Map<String, String> parseRequest(HttpServletRequest request) throws IOException {
		Map<String,String>map=new HashMap<>();
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { System.out.println(e.getMessage());}

		  try {
			  ObjectMapper om=new ObjectMapper();
			  map =om.readValue(jb.toString(), Map.class);
		  } catch (Exception e) {
		    // crash and burn
		    throw new IOException("Error parsing JSON request string");
		  }
		return map;
	}

	

}
