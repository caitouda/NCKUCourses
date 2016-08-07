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

public class School_Contest_in extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public School_Contest_in() {
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
		String sql5 = "select * from contest where SchoolName in(select Schoolname from school where";
		String exist = "false";
	    if(Schoolname != null && !Schoolname.equals("why"))
	    {
	        sql5 = sql5 + " Schoolname = '" + Schoolname + "'";
	        exist = "true";
	    }
	    if(Phonenumber != null && !Phonenumber.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Phonenumber = '" + Phonenumber + "'";
	    	else
	    		sql5 = sql5 + " Phonenumber = '" + Phonenumber + "'";
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
	    if(Email != null && !Email.equals("why"))
	    {
	    	if(exist == "true")
	    		sql5 = sql5 + " and Email = '" + Email + "'";
	    	else
	    		sql5 = sql5 + " Email = '" + Email + "'";
	        exist = "true";
	    }
	    if(CID != null && !CID.equals("why"))
	    {
	    	if(exist == "true")
	            sql5 = sql5 + " and CID = " + CID;
	    	else 
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
	    sql5 = sql5 + ");";
	    //System.out.println(sql5);
	    request.setAttribute("sql5", sql5);
	    request.setAttribute("exist", exist);
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
