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

public class Problem extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Problem() {
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
		String sql7 = "select * from problem where";
		String exist = "false";
	    if(PID != null && !PID.equals("why"))
	    {
	        sql7 = sql7 + " PID = " + PID;
	        exist = "true";
	    }
	    if(Ptitle != null && !Ptitle.equals("why"))
	    {
	    	if(exist == "true")
	    		sql7 = sql7 + " and Ptitle = '" + Ptitle + "'";
	    	else
	    		sql7 = sql7 + " Ptitle = '" + Ptitle + "'";
	        exist = "true";
	    }
	    if(CTitle != null && !CTitle.equals("why"))
	    {
	    	if(exist == "true")
	    		sql7 = sql7 + " and CTitle = '" + CTitle + "'";
	    	else
	    		sql7 = sql7 + " Ctitle = '" + CTitle + "'";
	        exist = "true";
	    }
	    if(AdminName != null && !AdminName.equals("why"))
	    {
	    	if(exist == "true")
	    		sql7 = sql7 + " and AdminName = '" + AdminName + "'";
	    	else
	    		sql7 = sql7 + " AdminName = '" + AdminName + "'";
	        exist = "true";
	    }
	    if(AC != null && !AC.equals("why"))
	    {
	    	if(exist == "true")
	    		sql7 = sql7 + " and AC = " + AC;
	    	else
	    		sql7 = sql7 + " AC = " + AC;
	        exist = "true";
	    }
	    if(Try != null && !Try.equals("why"))
	    {
	    	if(exist == "true")
	    		sql7 = sql7 + " and Try = " + Try;
	    	else
	    		sql7 = sql7 + " Try = " + Try;
	        exist = "true";
	    }
	    sql7 = sql7 + ";";
	    System.out.println(sql7);
	    request.setAttribute("sql7", sql7);
	    request.setAttribute("exist", exist);
		request.getRequestDispatcher("problem.jsp").forward(request, response);
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
