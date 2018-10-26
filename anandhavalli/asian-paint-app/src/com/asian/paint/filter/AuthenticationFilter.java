package com.asian.paint.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Init method of filter configuration loaded ");
		
	}
	@Override
	public void destroy() {
		System.out.println("Destroyed the filter configuration");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		String uri=request.getRequestURI();
//		System.out.println(" The requested URI "+uri);
//		HttpSession session=request.getSession(false);
//		boolean flag=uri.endsWith("RegisterServlet")  ||uri.endsWith("LoginServlet");
//		
//		
//		 if(session == null&& !uri.endsWith("html")&&!flag){
//	//	if(session == null && !(uri.endsWith("html") || uri.endsWith("Login") || uri.endsWith("Register"))){
//			System.out.println("Unauthorized access request");
//			response.sendRedirect("login.html");
//		}else{
//			// pass the request along the filter chain
			filterChain.doFilter(request, response);
			
	}

	
}
