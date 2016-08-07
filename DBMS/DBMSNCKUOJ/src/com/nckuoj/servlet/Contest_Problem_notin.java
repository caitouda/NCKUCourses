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

public class Contest_Problem_notin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest_Problem_notin() {
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
		String sql15 = "select * from problem where CTitle not in(select Ctitle from contest where";
		String exist3 = "false";
		if(CID != null && !CID.equals("why"))
	    {
	        sql15 = sql15 + " CID = " + CID;
	        exist3 = "true";
	    }
	    if(Ctitle != null && !Ctitle.equals("why"))
	    {
	    	if(exist3 == "true")
	            sql15 = sql15 + " and Ctitle = '" + Ctitle + "'";
	    	else 
	    		sql15 = sql15 + " Ctitle = '" + Ctitle + "'";
	        exist3 = "true";
	    }
	    if(Starttime != null && !Starttime.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Starttime = '" + Starttime + "'";
	    	else
	    		sql15 = sql15 + " Starttime = '" + Starttime + "'";
	        exist3 = "true";
	    }
	    if(Endtime != null && !Endtime.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Endtime = '" + Endtime + "'";
	    	else
	    		sql15 = sql15 + " Endtime = '" + Endtime + "'";
	        exist3 = "true";
	    }
	    if(Status != null && !Status.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Status = '" + Status + "'";
	    	else
	    		sql15 = sql15 + " Status = '" + Status + "'";
	        exist3 = "true";
	    }
	    if(Access != null && !Access.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Access = '" + Access + "'";
	    	else
	    		sql15 = sql15 + " Access = '" + Access + "'";
	        exist3 = "true";
	    }
	    if(SchoolName != null && !SchoolName.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and SchoolName = '" + SchoolName + "'";
	    	else
	    		sql15 = sql15 + " SchoolName = '" + SchoolName + "'";
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
