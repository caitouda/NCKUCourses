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

public class Contest_Problem_all extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest_Problem_all() {
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
		String sql6 = "select * from contest, problem where problem.Ctitle = contest.CTitle and";
		String exist2 = "false";
	    if(CID != null && !CID.equals("why"))
	    {
	        sql6 = sql6 + " CID = " + CID;
	        exist2 = "true";
	    }
	    if(Ctitle != null && !Ctitle.equals("why"))
	    {
	    	if(exist2 == "true")
	            sql6 = sql6 + " and Ctitle = '" + Ctitle + "'";
	    	else 
	    		sql6 = sql6 + " Ctitle = '" + Ctitle + "'";
	        exist2 = "true";
	    }
	    if(Starttime != null && !Starttime.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Starttime = '" + Starttime + "'";
	    	else
	    		sql6 = sql6 + " Starttime = '" + Starttime + "'";
	        exist2 = "true";
	    }
	    if(Endtime != null && !Endtime.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Endtime = '" + Endtime + "'";
	    	else
	    		sql6 = sql6 + " Endtime = '" + Endtime + "'";
	        exist2 = "true";
	    }
	    if(Status != null && !Status.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Status = '" + Status + "'";
	    	else
	    		sql6 = sql6 + " Status = '" + Status + "'";
	        exist2 = "true";
	    }
	    if(Access != null && !Access.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Access = '" + Access + "'";
	    	else
	    		sql6 = sql6 + " Access = '" + Access + "'";
	        exist2 = "true";
	    }
	    if(SchoolName != null && !SchoolName.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and SchoolName = '" + SchoolName + "'";
	    	else
	    		sql6 = sql6 + " SchoolName = '" + SchoolName + "'";
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
