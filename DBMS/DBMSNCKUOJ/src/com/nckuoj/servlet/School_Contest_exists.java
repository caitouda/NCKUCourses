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

public class School_Contest_exists extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public School_Contest_exists() {
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
		String sql16 = "select * from contest where exists(select * from school where contest.SchoolName = school.Schoolname and";
		String exist4 = "false";
	    if(Schoolname != null && !Schoolname.equals("why"))
	    {
	        sql16 = sql16 + " Schoolname = '" + Schoolname + "'";
	        exist4 = "true";
	    }
	    if(Phonenumber != null && !Phonenumber.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Phonenumber = '" + Phonenumber + "'";
	    	else
	    		sql16 = sql16 + " Phonenumber = '" + Phonenumber + "'";
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
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist4 == "true")
	    		sql16 = sql16 + " and Email = '" + Email + "'";
	    	else
	    		sql16 = sql16 + " Email = '" + Email + "'";
	        exist4 = "true";
	    }
	    if(CID != null && !CID.equals("why"))
	    {
	    	if(exist4 == "true")
	            sql16 = sql16 + " and CID = " + CID;
	    	else 
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
	    sql16 = sql16 + ");";
	    //System.out.println(sql16);
	    request.setAttribute("sql16", sql16);
	    request.setAttribute("exist4", exist4);
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
