package com.nckuoj.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MySQLDBCon {
	private static Connection conn = null; 
	public static Connection getConn(){
		try{
			Class.forName("com.mysql.jdbc.Driver");					
			String user = "root";									
			String pwd = "111111";										
			String url = "jdbc:mysql://localhost:3306/NCKUOJ";	
			conn = DriverManager.getConnection(url,user,pwd);	
			//System.out.println("Success connect Mysql server!");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return conn;
	}
}
