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

public class Insertuser extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Insertuser() {
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
		String UserID = request.getParameter("UserID");
		String Username = request.getParameter("Username");
		String Password = request.getParameter("Password");
		String Email = request.getParameter("Email");
		String Rank = request.getParameter("Rank");
		String School = request.getParameter("School");
		String Accepted = request.getParameter("Accepted");
		String sql = "insert into user values(" + UserID + ",'" + Username + "','" + Password + "','" + Email + "'," + Rank + ",'" + School + "'," + Accepted + ");";
		System.out.println(sql);
		try {
	        conn = MySQLDBCon.getConn();
		    pstmt = conn.prepareStatement(sql);	
		    pstmt.executeUpdate();
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
		request.getRequestDispatcher("user.jsp").forward(request, response);
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
