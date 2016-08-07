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

public class School extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public School() {
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
		String Schoolname = request.getParameter("Schoolname"); 
		//Schoolname = Schoolname.replaceAll("\r|\n", "");
		request.setAttribute("Schoolname", Schoolname);
		String Phonenumber = request.getParameter("Phonenumber"); 
		//Phonenumber = Phonenumber.replaceAll("\r|\n", "");
		request.setAttribute("Phonenumber", Phonenumber);
		String Email = request.getParameter("Email"); 
		//Email = Email.replaceAll("\r|\n", "");
		request.setAttribute("Email", Email);
		String sql4 = "select * from school where";
		String exist = "false";
	    //System.out.println(Schoolname);
	    if(Schoolname != null && !Schoolname.equals("why"))
	    {
	        sql4 = sql4 + " Schoolname = '" + Schoolname + "'";
	        exist = "true";
	    }
	    //System.out.println(Phonenumber);
	    if(Phonenumber != null && !Phonenumber.equals("why"))
	    {
	    	//System.out.println("why");
	    	if(exist == "true")
	    		sql4 = sql4 + " and Phonenumber = '" + Phonenumber + "'";
	    	else
	    		sql4 = sql4 + " Phonenumber = '" + Phonenumber + "'";
	        exist = "true";
	    }
	    //System.out.println(Email);
	    /*if(Email.equalsIgnoreCase("why"))
	    	System.out.println("Really?");*/
	    if(Email != null && !Email.equals("why"))
	    {
	    	//System.out.println("why2");
	    	if(exist == "true")
	    		sql4 = sql4 + " and Email = '" + Email + "'";
	    	else
	    		sql4 = sql4 + " Email = '" + Email + "'";
	        exist = "true";
	    }
	    sql4 = sql4 + ";";
	    //System.out.println(exist);
	    //System.out.println(sql4);
	    request.setAttribute("sql4", sql4);
	    request.setAttribute("exist", exist);
		request.getRequestDispatcher("school.jsp").forward(request, response);
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
