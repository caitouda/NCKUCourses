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

public class School_Contest_notin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public School_Contest_notin() {
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
		String sql15 = "select * from contest where SchoolName not in(select Schoolname from school where";
		String exist3 = "false";
	    if(Schoolname != null && !Schoolname.equals("why"))
	    {
	        sql15 = sql15 + " Schoolname = '" + Schoolname + "'";
	        exist3 = "true";
	    }
	    if(Phonenumber != null && !Phonenumber.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Phonenumber = '" + Phonenumber + "'";
	    	else
	    		sql15 = sql15 + " Phonenumber = '" + Phonenumber + "'";
	        exist3 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Email = '" + Email + "'";
	    	else
	    		sql15 = sql15 + " Email = '" + Email + "'";
	        exist3 = "true";
	    }
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist3 == "true")
	    		sql15 = sql15 + " and Email = '" + Email + "'";
	    	else
	    		sql15 = sql15 + " Email = '" + Email + "'";
	        exist3 = "true";
	    }
	    if(CID != null && !CID.equals("why"))
	    {
	    	if(exist3 == "true")
	            sql15 = sql15 + " and CID = " + CID;
	    	else 
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
	    sql15 = sql15 + ");";
	    //System.out.println(sql15);
	    request.setAttribute("sql15", sql15);
	    request.setAttribute("exist3", exist3);
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
