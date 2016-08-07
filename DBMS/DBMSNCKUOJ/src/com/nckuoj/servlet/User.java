package com.nckuoj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.nckuoj.bean.UserInfo;

public class User extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public User() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String UserID = request.getParameter("UserID"); 
		request.setAttribute("UserID", UserID);
		String Username = request.getParameter("Username"); 
		request.setAttribute("Username", Username);
		String Password = request.getParameter("Password"); 
		request.setAttribute("Password", Password);
		String Email = request.getParameter("Email"); 
		request.setAttribute("Email", Email);
		String Rank = request.getParameter("Rank"); 
		request.setAttribute("Rank", Rank);
		String School = request.getParameter("School"); 
		request.setAttribute("School", School);
		String Accepted = request.getParameter("Accepted"); 
		request.setAttribute("Accepted", Accepted);
		String sql8 = "select * from user where";
		String exist = "false";
	    if(UserID != null && !UserID.equals("why"))
	    {
	        sql8 = sql8 + " UserID = " + UserID;
	        exist = "true";
	    }
	    if(Username != null && !Username.equals("why"))
	    {
	    	if(exist == "true")
	            sql8 = sql8 + " and Username = '" + Username + "'";
	    	else 
	    		sql8 = sql8 + " Username = '" + Username + "'";
	        exist = "true";
	    }
	    if(Password != null && !Password.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Password = '" + Password + "'";
	    	else
	    		sql8 = sql8 + " Password = '" + Password + "'";
	        exist = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Email = '" + Email + "'";
	    	else
	    		sql8 = sql8 + " Email = '" + Email + "'";
	        exist = "true";
	    }
	    if(Rank != null && !Rank.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Rank = " + Rank;
	    	else
	    		sql8 = sql8 + " Rank = " + Rank;
	        exist = "true";
	    }
	    if(School != null && !School.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and School = '" + School + "'";
	    	else
	    		sql8 = sql8 + " School = '" + School + "'";
	        exist = "true";
	    }
	    if(Accepted != null && !Accepted.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Accepted = " + Accepted;
	    	else
	    		sql8 = sql8 + " Accepted = " + Accepted;
	        exist = "true";
	    }
	    sql8 = sql8 + ";";
	    System.out.println(sql8);
	    request.setAttribute("sql8", sql8);
	    request.setAttribute("exist", exist);
		request.getRequestDispatcher("user.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
