<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.nckuoj.dao.MySQLDBCon" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<link rel="shortcut icon" type="image/ico" href="images/Coding_Html_128px_1185108_easyicon.net.ico">
<title>SCHOOL</title>   
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
</style>
</head> 
<center>
<body> 
<div>
<h3>Please Input Your Sql</h3> 
<form action="school" method="post"> 
<select name="Schoolname" id="Schoolname" style="width:140px; ">
<option value="why">請選擇學校名字</option>   
<%
    Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    try {
        conn = MySQLDBCon.getConn();
        pstmt = conn.prepareStatement("select distinct Schoolname from school;");	
        rs = pstmt.executeQuery();		
        //System.out.println("hello");
	    ResultSetMetaData md = rs.getMetaData();
	    int columnCount = md.getColumnCount(); 
	    while (rs.next()) {    
	        for (int i = 1; i <= columnCount; i++) {  
%> 
<option value="<%=rs.getObject(i).toString()%>"><%=rs.getObject(i).toString()%></option>
<%
	        }   
	    }  
    } catch(Exception ex) {
		ex.printStackTrace();
	} 
%>           
</select>  
<select name="Phonenumber" id="Phonenumber" style="width:140px; ">
<option value="why">請選擇電話號碼</option>   
<%
    Connection conn2 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
    try {
        conn2 = MySQLDBCon.getConn();
        pstmt2 = conn2.prepareStatement("select distinct Phonenumber from school;");	
        rs2 = pstmt2.executeQuery();		
        //System.out.println("hello");
	    ResultSetMetaData md2 = rs2.getMetaData();
	    int columnCount2 = md2.getColumnCount(); 
	    while (rs2.next()) {    
	        for (int i = 1; i <= columnCount2; i++) {  
%> 
<option value="<%=rs2.getObject(i).toString()%>"><%=rs2.getObject(i).toString()%></option>
<%
            }   
	    }  
    } catch(Exception ex) {
		ex.printStackTrace();
	} 
%>           
</select> 
<select name="Email" id="Email" style="width:140px; ">
<option value="why">請選擇郵箱</option>   
<%
    Connection conn3 = null;
	PreparedStatement pstmt3 = null;
	ResultSet rs3 = null;
    try {
        conn3 = MySQLDBCon.getConn();
        pstmt3 = conn3.prepareStatement("select distinct Email from school;");	
        rs3 = pstmt3.executeQuery();		
        //System.out.println("hello");
	    ResultSetMetaData md3 = rs3.getMetaData();
	    int columnCount3 = md3.getColumnCount(); 
	    while (rs3.next()) {    
	        for (int i = 1; i <= columnCount3; i++) {  
%> 
<option value="<%=rs3.getObject(i).toString()%>"><%=rs3.getObject(i).toString()%></option>
<%
            }   
	    }  
    } catch(Exception ex) {
		ex.printStackTrace();
	} 
%>           
</select> 
<button type="submit">TRANSACTION</button>
</form>
<%
    Connection conn4 = null;
    PreparedStatement pstmt4 = null;
    ResultSet rs4 = null;
    try {
        conn4 = MySQLDBCon.getConn();
        pstmt4 = conn4.prepareStatement("select * from school;");	
        rs4 = pstmt4.executeQuery();		
        ResultSetMetaData md4 = rs4.getMetaData();
        int columnCount4 = md4.getColumnCount(); 
        out.print("<form action='insertschool' method='post'>");
        out.print("<table border=2>");
        out.print("<tr>");
        for (int i = 1; i <= columnCount4; i++) {   
            out.println("<th width=100>"+md4.getColumnName(i).toString());
        }     
        out.print("</tr>"); 
        out.print("<tr>");
        for (int i = 1; i <= columnCount4; i++) {   
        	//System.out.println("why");
%>
<th width=100><input type="text" name="<%=md4.getColumnName(i).toString()%>"/>
<%        	
        }   
        out.print("</tr>");
        out.print("</table><br>");
        out.print("<button type='submit'>INSERT</button>");
        out.print("</form>");
    } catch(Exception ex) {
 	    ex.printStackTrace();
    } 
%>
<a href="index.html">return to DBMSNCKUOJ</a>   
</div>
</body>
</center>
</html>
