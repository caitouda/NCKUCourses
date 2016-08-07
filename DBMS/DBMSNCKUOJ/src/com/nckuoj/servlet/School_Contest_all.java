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

public class School_Contest_all extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public School_Contest_all() {
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
		String Schoolname = request.getParameter("Schoolname"); 
		request.setAttribute("Schoolname", Schoolname);
		String Phonenumber = request.getParameter("Phonenumber"); 
		request.setAttribute("Phonenumber", Phonenumber);
		String Email = request.getParameter("Email"); 
		request.setAttribute("Email", Email);
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
		String sql6 = "select * from school, contest where school.Schoolname = contest.SchoolName and";
		String exist2 = "false";
	    if(Schoolname != null && !Schoolname.equals("why"))
	    {
	        sql6 = sql6 + " Schoolname = '" + Schoolname + "'";
	        exist2 = "true";
	    }
	    if(Phonenumber != null && !Phonenumber.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Phonenumber = '" + Phonenumber + "'";
	    	else
	    		sql6 = sql6 + " Phonenumber = '" + Phonenumber + "'";
	        exist2 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Email = '" + Email + "'";
	    	else
	    		sql6 = sql6 + " Email = '" + Email + "'";
	        exist2 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist2 == "true")
	    		sql6 = sql6 + " and Email = '" + Email + "'";
	    	else
	    		sql6 = sql6 + " Email = '" + Email + "'";
	        exist2 = "true";
	    }
	    if(CID != null && !CID.equals("why"))
	    {
	    	if(exist2 == "true")
	            sql6 = sql6 + " and CID = " + CID;
	    	else 
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
	    sql6 = sql6 + ";";
	    //System.out.println(sql6);
	    request.setAttribute("sql6", sql6);
	    request.setAttribute("exist2", exist2);
		request.getRequestDispatcher("school_contest.jsp").forward(request, response);
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
