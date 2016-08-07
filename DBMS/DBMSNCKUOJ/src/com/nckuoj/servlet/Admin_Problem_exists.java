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

public class Admin_Problem_exists extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Admin_Problem_exists() {
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
		String sql16 = "select * from problem where exists(select * from admin where problem.AdminName = admin.Adminname and";
		String exist4 = "false";
	    if(AdminID != null && !AdminID.equals("why"))
	    {
	        sql16 = sql16 + " AdminID = " + AdminID;
	        exist4 = "true";
	    }
	    if(Adminname != null && !Adminname.equals("why"))
	    {
	        sql16 = sql16 + " Adminname = '" + Adminname + "'";
	        exist4 = "true";
	    }
	    if(Password != null && !Password.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Password = '" + Password + "'";
	    	else
	    		sql16 = sql16 + " Password = '" + Password + "'";
	        exist4 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Email = '" + Email + "'";
	    	else
	    		sql16 = sql16 + " Email = '" + Email + "'";
	        exist4 = "true";
	    }
	    if(Give != null && !Give.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Give = " + Give;
	    	else
	    		sql16 = sql16 + " Give = " + Give;
	        exist4 = "true";
	    }
	    if(Test != null && !Test.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Test = " + Test;
	    	else
	    		sql16 = sql16 + " Test = " + Test;
	        exist4 = "true";
	    }
	    if(PID != null && !PID.equals("why"))
	    {
	    	if(exist4 == "true")
	            sql16 = sql16 + " and PID = " + PID;
	        else
	        	sql16 = sql16 + " PID = " + PID;
	        exist4 = "true";
	    }
	    if(Ptitle != null && !Ptitle.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Ptitle = '" + Ptitle + "'";
	    	else
	    		sql16 = sql16 + " Ptitle = '" + Ptitle + "'";
	        exist4 = "true";
	    }
	    if(CTitle != null && !CTitle.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and CTitle = '" + CTitle + "'";
	    	else
	    		sql16 = sql16 + " Ctitle = '" + CTitle + "'";
	        exist4 = "true";
	    }
	    if(AdminName != null && !AdminName.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and AdminName = '" + AdminName + "'";
	    	else
	    		sql16 = sql16 + " AdminName = '" + AdminName + "'";
	        exist4 = "true";
	    }
	    if(AC != null && !AC.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and AC = " + AC;
	    	else
	    		sql16 = sql16 + " AC = " + AC;
	        exist4 = "true";
	    }
	    if(Try != null && !Try.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Try = " + Try;
	    	else
	    		sql16 = sql16 + " Try = " + Try;
	        exist4 = "true";
	    }
	    sql16 = sql16 + ");";
	    //System.out.println(sql16);
	    request.setAttribute("sql16", sql16);
	    request.setAttribute("exist4", exist4);
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
