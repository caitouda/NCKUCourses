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

public class Contest_Problem_in extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest_Problem_in() {
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
		String sql5 = "select * from problem where CTitle in(select Ctitle from contest where";
		String exist = "false";
		if(CID != null && !CID.equals("why"))
	    {
	        sql5 = sql5 + " CID = " + CID;
	        exist = "true";
	    }
	    if(Ctitle != null && !Ctitle.equals("why"))
	    {
	    	if(exist == "true")
	            sql5 = sql5 + " and Ctitle = '" + Ctitle + "'";
	    	else 
	    		sql5 = sql5 + " Ctitle = '" + Ctitle + "'";
	        exist = "true";
	    }
	    if(Starttime != null && !Starttime.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Starttime = '" + Starttime + "'";
	    	else
	    		sql5 = sql5 + " Starttime = '" + Starttime + "'";
	        exist = "true";
	    }
	    if(Endtime != null && !Endtime.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Endtime = '" + Endtime + "'";
	    	else
	    		sql5 = sql5 + " Endtime = '" + Endtime + "'";
	        exist = "true";
	    }
	    if(Status != null && !Status.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Status = '" + Status + "'";
	    	else
	    		sql5 = sql5 + " Status = '" + Status + "'";
	        exist = "true";
	    }
	    if(Access != null && !Access.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Access = '" + Access + "'";
	    	else
	    		sql5 = sql5 + " Access = '" + Access + "'";
	        exist = "true";
	    }
	    if(SchoolName != null && !SchoolName.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and SchoolName = '" + SchoolName + "'";
	    	else
	    		sql5 = sql5 + " SchoolName = '" + SchoolName + "'";
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
