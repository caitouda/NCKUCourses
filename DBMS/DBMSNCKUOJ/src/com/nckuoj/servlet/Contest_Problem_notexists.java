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

public class Contest_Problem_notexists extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest_Problem_notexists() {
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
		String CID = request.getParameter("CID"); 
		request.setAttribute("CID", CID);
		String Ctitle = request.getParameter("Ctitle"); 
		request.setAttribute("Ctitle", Ctitle);
		String Starttime = request.getParameter("Starttime"); 
		request.setAttribute("Starttime", Starttime);
		String Endtime = request.getParameter("Endtime"); 
		request.setAttribute("Endtime", Endtime);
		String Status = request.getParameter("Status"); 
		request.setAttribute("Status", Status);
		String Access = request.getParameter("Access"); 
		request.setAttribute("Access", Access);
		String SchoolName = request.getParameter("SchoolName"); 
		request.setAttribute("SchoolName", SchoolName);
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
		String sql17 = "select * from problem where not exists(select * from contest where problem.CTitle = contest.Ctitle and";
		String exist5 = "false";
		if(CID != null && !CID.equals("why"))
	    {
	        sql17 = sql17 + " CID = " + CID;
	        exist5 = "true";
	    }
	    if(Ctitle != null && !Ctitle.equals("why"))
	    {
	    	if(exist5 == "true")
	            sql17 = sql17 + " and Ctitle = '" + Ctitle + "'";
	    	else 
	    		sql17 = sql17 + " Ctitle = '" + Ctitle + "'";
	        exist5 = "true";
	    }
	    if(Starttime != null && !Starttime.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Starttime = '" + Starttime + "'";
	    	else
	    		sql17 = sql17 + " Starttime = '" + Starttime + "'";
	        exist5 = "true";
	    }
	    if(Endtime != null && !Endtime.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Endtime = '" + Endtime + "'";
	    	else
	    		sql17 = sql17 + " Endtime = '" + Endtime + "'";
	        exist5 = "true";
	    }
	    if(Status != null && !Status.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Status = '" + Status + "'";
	    	else
	    		sql17 = sql17 + " Status = '" + Status + "'";
	        exist5 = "true";
	    }
	    if(Access != null && !Access.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and Access = '" + Access + "'";
	    	else
	    		sql17 = sql17 + " Access = '" + Access + "'";
	        exist5 = "true";
	    }
	    if(SchoolName != null && !SchoolName.equals("why"))
	    {
	    	if(exist5 == "true")
	    		sql17 = sql17 + " and SchoolName = '" + SchoolName + "'";
	    	else
	    		sql17 = sql17 + " SchoolName = '" + SchoolName + "'";
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
		request.getRequestDispatcher("contest_problem.jsp").forward(request, response);
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
