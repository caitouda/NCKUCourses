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

public class Contest_Problem_aggregate extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Contest_Problem_aggregate() {
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
		String Aggregate = request.getParameter("Aggregate"); 
		request.setAttribute("Aggregate", Aggregate);
		String sql18 = "";
		String exist6 = "false";
	    if(Aggregate.equals("AC"))
	    {
			sql18 = "select count(*), sum(AC), max(AC), min(AC), avg(AC) from contest, problem where contest.Ctitle = problem.CTitle;";
	        exist6 = "true";
	    }
	    if(Aggregate.equals("Try"))
	    {
			sql18 = "select count(*), sum(Try), max(Try), min(Try), avg(Try) from contest, problem where contest.Ctitle = problem.CTitle;";
	        exist6 = "true";
	    }
	    //System.out.println(sql18);
	    request.setAttribute("sql18", sql18);
	    request.setAttribute("exist6", exist6);
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
