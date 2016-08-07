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

public class Admin_Problem_all extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Admin_Problem_all() {
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
		String AdminID = request.getParameter("AdminID"); 
		request.setAttribute("AdminID", AdminID);
		String Adminname = request.getParameter("Adminname"); 
		request.setAttribute("Adminname", Adminname);
		String Password = request.getParameter("Password"); 
		request.setAttribute("Password", Password);
		String Email = request.getParameter("Email"); 
		request.setAttribute("Email", Email);
		String Give = request.getParameter("Give"); 
		request.setAttribute("Give", Give);
		String Test = request.getParameter("Test"); 
		request.setAttribute("Test", Test);
		String PID = request.getParameter("PID"); 
		request.setAttribute("PID", PID);
		String Ptitle = request.getParameter("Ptitle"); 
		request.setAttribute("Ptitle", Ptitle);
		String CTitle = request.getParameter("CTitle"); 
		request.setAttribute("CTitle", CTitle);
		String AdminName = request.getParameter("AdminName"); 
		request.setAttribute("AdminName", AdminName);
		String AC = request.getParameter("AC"); 
		request.setAttribute("AC", AC);
		String Try = request.getParameter("Try"); 
		request.setAttribute("Try", Try);
		String sql6 = "select * from admin, problem where admin.Adminname = problem.AdminName and";
		String exist2 = "false";
	    if(AdminID != null && !AdminID.equals("why"))
	    {
	        sql6 = sql6 + " AdminID = " + AdminID;
	        exist2 = "true";
	    }
	    if(Adminname != null && !Adminname.equals("why"))
	    {
	        sql6 = sql6 + " Adminname = '" + Adminname + "'";
	        exist2 = "true";
	    }
	    if(Password != null && !Password.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Password = '" + Password + "'";
	    	else
	    		sql6 = sql6 + " Password = '" + Password + "'";
	        exist2 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Email = '" + Email + "'";
	    	else
	    		sql6 = sql6 + " Email = '" + Email + "'";
	        exist2 = "true";
	    }
	    if(Give != null && !Give.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Give = " + Give;
	    	else
	    		sql6 = sql6 + " Give = " + Give;
	        exist2 = "true";
	    }
	    if(Test != null && !Test.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Test = " + Test;
	    	else
	    		sql6 = sql6 + " Test = " + Test;
	        exist2 = "true";
	    }
	    if(PID != null && !PID.equals("why"))
	    {
	    	if(exist2 == "true")
	            sql6 = sql6 + " and PID = " + PID;
	        else
	        	sql6 = sql6 + " PID = " + PID;
	        exist2 = "true";
	    }
	    if(Ptitle != null && !Ptitle.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Ptitle = '" + Ptitle + "'";
	    	else
	    		sql6 = sql6 + " Ptitle = '" + Ptitle + "'";
	        exist2 = "true";
	    }
	    if(CTitle != null && !CTitle.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and CTitle = '" + CTitle + "'";
	    	else
	    		sql6 = sql6 + " Ctitle = '" + CTitle + "'";
	        exist2 = "true";
	    }
	    if(AdminName != null && !AdminName.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and AdminName = '" + AdminName + "'";
	    	else
	    		sql6 = sql6 + " AdminName = '" + AdminName + "'";
	        exist2 = "true";
	    }
	    if(AC != null && !AC.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and AC = " + AC;
	    	else
	    		sql6 = sql6 + " AC = " + AC;
	        exist2 = "true";
	    }
	    if(Try != null && !Try.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Try = " + Try;
	    	else
	    		sql6 = sql6 + " Try = " + Try;
	        exist2 = "true";
	    }
	    sql6 = sql6 + ";";
	    //System.out.println(sql6);
	    request.setAttribute("sql6", sql6);
	    request.setAttribute("exist2", exist2);
		request.getRequestDispatcher("admin_problem.jsp").forward(request, response);
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
