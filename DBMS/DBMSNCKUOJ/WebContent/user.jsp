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
<title>USER</title>   
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
<form action="user" method="post"> 
<select name="UserID" id="UserID" style="width:140px; ">
<option value="why">請選擇用戶ID</option>   
<%
    Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    try {
        conn = MySQLDBCon.getConn();
        pstmt = conn.prepareStatement("select distinct UserID from user;");	
        rs = pstmt.executeQuery();		
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
<select name="Username" id="Username" style="width:140px; ">
<option value="why">請選擇用戶名字</option>   
<%
    Connection conn2 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
    try {
        conn2 = MySQLDBCon.getConn();
        pstmt2 = conn2.prepareStatement("select distinct Username from user;");	
        rs2 = pstmt2.executeQuery();	
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
<select name="Password" id="Password" style="width:140px; ">
<option value="why">請選擇密碼</option>   
<%
    Connection conn3 = null;
	PreparedStatement pstmt3 = null;
	ResultSet rs3 = null;
    try {
        conn3 = MySQLDBCon.getConn();
        pstmt3 = conn3.prepareStatement("select distinct Password from user;");	
        rs3 = pstmt3.executeQuery();	
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
<select name="Email" id="Email" style="width:140px; ">
<option value="why">請選擇郵箱</option>   
<%
    Connection conn4 = null;
	PreparedStatement pstmt4 = null;
	ResultSet rs4 = null;
    try {
        conn4 = MySQLDBCon.getConn();
        pstmt4 = conn4.prepareStatement("select distinct Email from user;");	
        rs4 = pstmt4.executeQuery();		
	    ResultSetMetaData md4 = rs4.getMetaData();
	    int columnCount4 = md4.getColumnCount(); 
	    while (rs4.next()) {    
	        for (int i = 1; i <= columnCount4; i++) {  
%> 
<option value="<%=rs4.getObject(i).toString()%>"><%=rs4.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="Rank" id="Rank" style="width:150px; ">
<option value="why">請選擇排名</option>   
<%
    Connection conn5 = null;
	PreparedStatement pstmt5 = null;
	ResultSet rs5 = null;
    try {
        conn5 = MySQLDBCon.getConn();
        pstmt5 = conn5.prepareStatement("select distinct Rank from user;");	
        rs5 = pstmt5.executeQuery();		
	    ResultSetMetaData md5 = rs5.getMetaData();
	    int columnCount5 = md5.getColumnCount(); 
	    while (rs5.next()) {    
	        for (int i = 1; i <= columnCount5; i++) {  
%> 
<option value="<%=rs5.getObject(i).toString()%>"><%=rs5.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="School" id="School" style="width:160px; ">
<option value="why">請選擇學校</option>   
<%
    Connection conn6 = null;
	PreparedStatement pstmt6 = null;
	ResultSet rs6 = null;
    try {
        conn6 = MySQLDBCon.getConn();
        pstmt6 = conn6.prepareStatement("select distinct School from user;");	
        rs6 = pstmt6.executeQuery();		
	    ResultSetMetaData md6 = rs6.getMetaData();
	    int columnCount6 = md6.getColumnCount(); 
	    while (rs6.next()) {    
	        for (int i = 1; i <= columnCount6; i++) {  
%> 
<option value="<%=rs6.getObject(i).toString()%>"><%=rs6.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="Accepted" id="Accepted" style="width:170px; ">
<option value="why">請選擇過題數</option>   
<%
    Connection conn7 = null;
	PreparedStatement pstmt7 = null;
	ResultSet rs7 = null;
    try {
        conn7 = MySQLDBCon.getConn();
        pstmt7 = conn7.prepareStatement("select distinct Accepted from user;");	
        rs7 = pstmt7.executeQuery();		
	    ResultSetMetaData md7 = rs7.getMetaData();
	    int columnCount7 = md7.getColumnCount(); 
	    while (rs7.next()) {    
	        for (int i = 1; i <= columnCount7; i++) {  
%> 
<option value="<%=rs7.getObject(i).toString()%>"><%=rs7.getObject(i).toString()%></option>
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
<button onclick="window.location='insertuser.jsp';">INSERT</button><br><br>
<%
    Connection conn8 = null;
	PreparedStatement pstmt8 = null;
	ResultSet rs8 = null;
	String sql8 = (String)request.getAttribute("sql8");
	System.out.println(sql8);
	String exist = (String)request.getAttribute("exist");
    if(exist == "true")
    {
        try {
            conn8 = MySQLDBCon.getConn();
	        pstmt8 = conn8.prepareStatement(sql8);	
	        rs8 = pstmt8.executeQuery();		
	        ResultSetMetaData md8 = rs8.getMetaData();
	        int columnCount8 = md8.getColumnCount(); 
	        String UserID[] = new String[20];
	        int j = 0;
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount8; i++) {   
	            out.println("<th width=100>"+md8.getColumnName(i).toString());
	            }     
	        out.println("<th width=100>"+"Operation");
	        out.print("</tr>");
	        while (rs8.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount8; i++) {   
	                out.println("<th width=100>"+rs8.getObject(i).toString()); 
	                }   
	            UserID[j] = rs8.getObject(1).toString();
%>  	            
	            <th width=100>
	              <a href="deleteByUserID?UserID=<%=UserID[j] %>">刪除</a>
	              <a href="searchByUserID?UserID=<%=UserID[j] %>">更新</a>
<%
                j++;
	            out.print("</tr>");
	            }  
	        out.print("</table><br>");
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
    }
%>   
<%
    Connection conn9 = null;
    PreparedStatement pstmt9 = null;
    ResultSet rs9 = null;
	String sql9 = (String)request.getAttribute("sql9");
    try {
        conn9 = MySQLDBCon.getConn();
        pstmt9 = conn9.prepareStatement(sql9);	
        rs9 = pstmt9.executeQuery();		
        ResultSetMetaData md9 = rs9.getMetaData();
        int columnCount9 = md9.getColumnCount(); 
        out.print("<form action='updateuser' method='post'>");
        out.print("<table border=2>");
        out.print("<tr>");
        for (int i = 1; i <= columnCount9; i++) {   
            out.println("<th width=100>"+md9.getColumnName(i).toString());
        }     
        out.print("</tr>");
        while (rs9.next()) {    
            out.print("<tr>");
            for (int i = 1; i <= columnCount9; i++) {   
%>
<th width=100><input type="text" name="<%=md9.getColumnName(i).toString()%>" value="<%=rs9.getObject(i).toString() %>"/>
<%        	
            }   
            out.print("</tr>");
        }  
        out.print("</table><br>");
        out.print("<button type='submit'>UPDATE</button>");
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
