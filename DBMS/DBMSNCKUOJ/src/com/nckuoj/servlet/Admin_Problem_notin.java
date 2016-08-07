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

public class Admin_Problem_notin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Admin_Problem_notin() {
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
		String sql15 = "select * from problem where AdminName not in(select Adminname from admin where";
		String exist3 = "false";
	    if(AdminID != null && !AdminID.equals("why"))
	    {
	        sql15 = sql15 + " AdminID = " + AdminID;
	        exist3 = "true";
	    }
	    if(Adminname != null && !Adminname.equals("why"))
	    {
	        sql15 = sql15 + " Adminname = '" + Adminname + "'";
	        exist3 = "true";
	    }
	    if(Password != null && !Password.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Password = '" + Password + "'";
	    	else
	    		sql15 = sql15 + " Password = '" + Password + "'";
	        exist3 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Email = '" + Email + "'";
	    	else
	    		sql15 = sql15 + " Email = '" + Email + "'";
	        exist3 = "true";
	    }
	    if(Give != null && !Give.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Give = " + Give;
	    	else
	    		sql15 = sql15 + " Give = " + Give;
	        exist3 = "true";
	    }
	    if(Test != null && !Test.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Test = " + Test;
	    	else
	    		sql15 = sql15 + " Test = " + Test;
	        exist3 = "true";
	    }
	    if(PID != null && !PID.equals("why"))
	    {
	    	if(exist3 == "true")
	            sql15 = sql15 + " and PID = " + PID;
	        else
	        	sql15 = sql15 + " PID = " + PID;
	        exist3 = "true";
	    }
	    if(Ptitle != null && !Ptitle.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Ptitle = '" + Ptitle + "'";
	    	else
	    		sql15 = sql15 + " Ptitle = '" + Ptitle + "'";
	        exist3 = "true";
	    }
	    if(CTitle != null && !CTitle.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and CTitle = '" + CTitle + "'";
	    	else
	    		sql15 = sql15 + " Ctitle = '" + CTitle + "'";
	        exist3 = "true";
	    }
	    if(AdminName != null && !AdminName.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and AdminName = '" + AdminName + "'";
	    	else
	    		sql15 = sql15 + " AdminName = '" + AdminName + "'";
	        exist3 = "true";
	    }
	    if(AC != null && !AC.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and AC = " + AC;
	    	else
	    		sql15 = sql15 + " AC = " + AC;
	        exist3 = "true";
	    }
	    if(Try != null && !Try.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Try = " + Try;
	    	else
	    		sql15 = sql15 + " Try = " + Try;
	        exist3 = "true";
	    }
	    sql15 = sql15 + ");";
	    //System.out.println(sql15);
	    request.setAttribute("sql15", sql15);
	    request.setAttribute("exist3", exist3);
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
