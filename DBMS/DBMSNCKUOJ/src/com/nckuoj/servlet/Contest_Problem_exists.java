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

public class Contest_Problem_exists extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest_Problem_exists() {
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
		String sql16 = "select * from problem where exists(select * from contest where problem.CTitle = contest.Ctitle and";
		String exist4 = "false";
		if(CID != null && !CID.equals("why"))
	    {
	        sql16 = sql16 + " CID = " + CID;
	        exist4 = "true";
	    }
	    if(Ctitle != null && !Ctitle.equals("why"))
	    {
	    	if(exist4 == "true")
	            sql16 = sql16 + " and Ctitle = '" + Ctitle + "'";
	    	else 
	    		sql16 = sql16 + " Ctitle = '" + Ctitle + "'";
	        exist4 = "true";
	    }
	    if(Starttime != null && !Starttime.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Starttime = '" + Starttime + "'";
	    	else
	    		sql16 = sql16 + " Starttime = '" + Starttime + "'";
	        exist4 = "true";
	    }
	    if(Endtime != null && !Endtime.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Endtime = '" + Endtime + "'";
	    	else
	    		sql16 = sql16 + " Endtime = '" + Endtime + "'";
	        exist4 = "true";
	    }
	    if(Status != null && !Status.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Status = '" + Status + "'";
	    	else
	    		sql16 = sql16 + " Status = '" + Status + "'";
	        exist4 = "true";
	    }
	    if(Access != null && !Access.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Access = '" + Access + "'";
	    	else
	    		sql16 = sql16 + " Access = '" + Access + "'";
	        exist4 = "true";
	    }
	    if(SchoolName != null && !SchoolName.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and SchoolName = '" + SchoolName + "'";
	    	else
	    		sql16 = sql16 + " SchoolName = '" + SchoolName + "'";
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
