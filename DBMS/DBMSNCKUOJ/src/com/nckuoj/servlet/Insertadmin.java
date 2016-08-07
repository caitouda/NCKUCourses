package com.nckuoj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.nckuoj.dao.MySQLDBCon;

//import com.nckuoj.bean.UserInfo;

public class Insertadmin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Insertadmin() {
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
	    Connection conn = null;
		PreparedStatement pstmt = null;
		String AdminID = request.getParameter("AdminID");
		String Adminname = request.getParameter("Adminname");
		String Password = request.getParameter("Password");
		String Email = request.getParameter("Email");
		String Give = request.getParameter("Give");
		String Test = request.getParameter("Test");
		String sql = "insert into admin values(" + AdminID + ",'" + Adminname + "','" + Password + "','" + Email + "'," + Give + "," + Test+ ");";
		System.out.println(sql);
		try {
	        conn = MySQLDBCon.getConn();
		    pstmt = conn.prepareStatement(sql);	
		    pstmt.executeUpdate();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
		request.getRequestDispatcher("admin.jsp").forward(request, response);
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
