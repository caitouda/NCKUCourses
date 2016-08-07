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

public class Admin_Problem_notexists extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Admin_Problem_notexists() {
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
		String sql17 = "select * from problem where not exists(select * from admin where problem.AdminName = admin.Adminname and";
		String exist5 = "false";
	    if(AdminID != null && !AdminID.equals("why"))
	    {
	        sql17 = sql17 + " AdminID = " + AdminID;
	        exist5 = "true";
	    }
	    if(Adminname != null && !Adminname.equals("why"))
	    {
	        sql17 = sql17 + " Adminname = '" + Adminname + "'";
	        exist5 = "true";
	    }
	    if(Password != null && !Password.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Password = '" + Password + "'";
	    	else
	    		sql17 = sql17 + " Password = '" + Password + "'";
	        exist5 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Email = '" + Email + "'";
	    	else
	    		sql17 = sql17 + " Email = '" + Email + "'";
	        exist5 = "true";
	    }
	    if(Give != null && !Give.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Give = " + Give;
	    	else
	    		sql17 = sql17 + " Give = " + Give;
	        exist5 = "true";
	    }
	    if(Test != null && !Test.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Test = " + Test;
	    	else
	    		sql17 = sql17 + " Test = " + Test;
	        exist5 = "true";
	    }
	    if(PID != null && !PID.equals("why"))
	    {
	    	if(exist5 == "true")
	            sql17 = sql17 + " and PID = " + PID;
	        else
	        	sql17 = sql17 + " PID = " + PID;
	        exist5 = "true";
	    }
	    if(Ptitle != null && !Ptitle.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Ptitle = '" + Ptitle + "'";
	    	else
	    		sql17 = sql17 + " Ptitle = '" + Ptitle + "'";
	        exist5 = "true";
	    }
	    if(CTitle != null && !CTitle.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and CTitle = '" + CTitle + "'";
	    	else
	    		sql17 = sql17 + " Ctitle = '" + CTitle + "'";
	        exist5 = "true";
	    }
	    if(AdminName != null && !AdminName.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and AdminName = '" + AdminName + "'";
	    	else
	    		sql17 = sql17 + " AdminName = '" + AdminName + "'";
	        exist5 = "true";
	    }
	    if(AC != null && !AC.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and AC = " + AC;
	    	else
	    		sql17 = sql17 + " AC = " + AC;
	        exist5 = "true";
	    }
	    if(Try != null && !Try.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Try = " + Try;
	    	else
	    		sql17 = sql17 + " Try = " + Try;
	        exist5 = "true";
	    }
	    sql17 = sql17 + ");";
	    //System.out.println(sql17);
	    request.setAttribute("sql17", sql17);
	    request.setAttribute("exist5", exist5);
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
