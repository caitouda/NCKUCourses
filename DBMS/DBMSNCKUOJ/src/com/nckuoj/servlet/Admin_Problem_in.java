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

public class Admin_Problem_in extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Admin_Problem_in() {
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
		String sql5 = "select * from problem where AdminName in(select Adminname from admin where";
		String exist = "false";
	    if(AdminID != null && !AdminID.equals("why"))
	    {
	        sql5 = sql5 + " AdminID = " + AdminID;
	        exist = "true";
	    }
	    if(Adminname != null && !Adminname.equals("why"))
	    {
	        sql5 = sql5 + " Adminname = '" + Adminname + "'";
	        exist = "true";
	    }
	    if(Password != null && !Password.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Password = '" + Password + "'";
	    	else
	    		sql5 = sql5 + " Password = '" + Password + "'";
	        exist = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Email = '" + Email + "'";
	    	else
	    		sql5 = sql5 + " Email = '" + Email + "'";
	        exist = "true";
	    }
	    if(Give != null && !Give.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Give = " + Give;
	    	else
	    		sql5 = sql5 + " Give = " + Give;
	        exist = "true";
	    }
	    if(Test != null && !Test.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Test = " + Test;
	    	else
	    		sql5 = sql5 + " Test = " + Test;
	        exist = "true";
	    }
	    if(PID != null && !PID.equals("why"))
	    {
	    	if(exist == "true")
	            sql5 = sql5 + " and PID = " + PID;
	        else
	        	sql5 = sql5 + " PID = " + PID;
	        exist = "true";
	    }
	    if(Ptitle != null && !Ptitle.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Ptitle = '" + Ptitle + "'";
	    	else
	    		sql5 = sql5 + " Ptitle = '" + Ptitle + "'";
	        exist = "true";
	    }
	    if(CTitle != null && !CTitle.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and CTitle = '" + CTitle + "'";
	    	else
	    		sql5 = sql5 + " Ctitle = '" + CTitle + "'";
	        exist = "true";
	    }
	    if(AdminName != null && !AdminName.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and AdminName = '" + AdminName + "'";
	    	else
	    		sql5 = sql5 + " AdminName = '" + AdminName + "'";
	        exist = "true";
	    }
	    if(AC != null && !AC.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and AC = " + AC;
	    	else
	    		sql5 = sql5 + " AC = " + AC;
	        exist = "true";
	    }
	    if(Try != null && !Try.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Try = " + Try;
	    	else
	    		sql5 = sql5 + " Try = " + Try;
	        exist = "true";
	    }
	    sql5 = sql5 + ");";
	    //System.out.println(sql5);
	    request.setAttribute("sql5", sql5);
	    request.setAttribute("exist", exist);
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
