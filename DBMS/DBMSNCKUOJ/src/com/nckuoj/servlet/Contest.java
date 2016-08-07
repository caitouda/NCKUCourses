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

public class Contest extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest() {
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
		String sql8 = "select * from contest where";
		String exist = "false";
	    if(CID != null && !CID.equals("why"))
	    {
	        sql8 = sql8 + " CID = " + CID;
	        exist = "true";
	    }
	    if(Ctitle != null && !Ctitle.equals("why"))
	    {
	    	if(exist == "true")
	            sql8 = sql8 + " and Ctitle = '" + Ctitle + "'";
	    	else
	    		sql8 = sql8 + " Ctitle = '" + Ctitle + "'";
	        exist = "true";
	    }
	    if(Starttime != null && !Starttime.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Starttime = '" + Starttime + "'";
	    	else
	    		sql8 = sql8 + " Starttime = '" + Starttime + "'";
	        exist = "true";
	    }
	    if(Endtime != null && !Endtime.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Endtime = '" + Endtime + "'";
	    	else
	    		sql8 = sql8 + " Endtime = '" + Endtime + "'";
	        exist = "true";
	    }
	    if(Status != null && !Status.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Status = '" + Status + "'";
	    	else
	    		sql8 = sql8 + " Status = '" + Status + "'";
	        exist = "true";
	    }
	    if(Access != null && !Access.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and Access = '" + Access + "'";
	    	else
	    		sql8 = sql8 + " Access = '" + Access + "'";
	        exist = "true";
	    }
	    if(SchoolName != null && !SchoolName.equals("why"))
	    {
	    	if(exist == "true")
	    		sql8 = sql8 + " and SchoolName = '" + SchoolName + "'";
	    	else
	    		sql8 = sql8 + " SchoolName = '" + SchoolName + "'";
	        exist = "true";
	    }
	    sql8 = sql8 + ";";
	    //System.out.println(sql8);
	    request.setAttribute("sql8", sql8);
	    request.setAttribute("exist", exist);
		request.getRequestDispatcher("contest.jsp").forward(request, response);
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
