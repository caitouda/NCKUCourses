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
<title>ADMIN</title>   
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
<form action="admin" method="post"> 
<select name="AdminID" id="AdminID" style="width:140px; ">
<option value="why">請選擇管理員ID</option>   
<%
    Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    try {
        conn = MySQLDBCon.getConn();
        pstmt = conn.prepareStatement("select distinct AdminID from admin;");	
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
<select name="Adminname" id="Adminname" style="width:140px; ">
<option value="why">請選擇管理員名字</option>   
<%
    Connection conn2 = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs2 = null;
    try {
        conn2 = MySQLDBCon.getConn();
        pstmt2 = conn2.prepareStatement("select distinct Adminname from admin;");	
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
        pstmt3 = conn3.prepareStatement("select distinct Password from admin;");	
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
        pstmt4 = conn4.prepareStatement("select distinct Email from admin;");	
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
<select name="Give" id="Give" style="width:140px; ">
<option value="why">請選擇出題數</option>   
<%
    Connection conn7 = null;
	PreparedStatement pstmt7 = null;
	ResultSet rs7 = null;
    try {
        conn7 = MySQLDBCon.getConn();
        pstmt7 = conn7.prepareStatement("select distinct Give from admin;");	
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
<select name="Test" id="Test" style="width:140px; ">
<option value="why">請選擇測題數</option>   
<%
    Connection conn8 = null;
	PreparedStatement pstmt8 = null;
	ResultSet rs8 = null;
    try {
        conn8 = MySQLDBCon.getConn();
        pstmt8 = conn8.prepareStatement("select distinct Test from admin;");	
        rs8 = pstmt8.executeQuery();		
	    ResultSetMetaData md8 = rs8.getMetaData();
	    int columnCount8 = md8.getColumnCount(); 
	    while (rs8.next()) {    
	        for (int i = 1; i <= columnCount8; i++) {  
%> 
<option value="<%=rs8.getObject(i).toString()%>"><%=rs8.getObject(i).toString()%></option>
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
<button onclick="window.location='insertadmin.jsp';">INSERT</button><br><br>
<%
    Connection conn5 = null;
	PreparedStatement pstmt5 = null;
	ResultSet rs5 = null;
	String sql5 = (String)request.getAttribute("sql5");
	System.out.println(sql5);
	String exist = (String)request.getAttribute("exist");
    if(exist == "true")
    {
        try {
            conn5 = MySQLDBCon.getConn();
	        pstmt5 = conn5.prepareStatement(sql5);	
	        rs5 = pstmt5.executeQuery();		
	        ResultSetMetaData md5 = rs5.getMetaData();
	        int columnCount5 = md5.getColumnCount(); 
	        String AdminID[] = new String[20];
	        int j = 0;
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount5; i++) {   
	            out.println("<th width=100>"+md5.getColumnName(i).toString());
	            }     
	        out.println("<th width=100>"+"Operation");
	        out.print("</tr>");
	        while (rs5.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount5; i++) {   
	                out.println("<th width=100>"+rs5.getObject(i).toString()); 
	                }   
	            AdminID[j] = rs5.getObject(1).toString();
%>  	            
	            <th width=100>
	              <a href="deleteByAdminID?AdminID=<%=AdminID[j] %>">刪除</a>
	              <a href="searchByAdminID?AdminID=<%=AdminID[j] %>">更新</a>
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
    Connection conn6 = null;
    PreparedStatement pstmt6 = null;
    ResultSet rs6 = null;
	String sql6 = (String)request.getAttribute("sql6");
    try {
        conn6 = MySQLDBCon.getConn();
        pstmt6 = conn6.prepareStatement(sql6);	
        rs6 = pstmt6.executeQuery();		
        ResultSetMetaData md6 = rs6.getMetaData();
        int columnCount6 = md6.getColumnCount(); 
        out.print("<form action='updateadmin' method='post'>");
        out.print("<table border=2>");
        out.print("<tr>");
        for (int i = 1; i <= columnCount6; i++) {   
            out.println("<th width=100>"+md6.getColumnName(i).toString());
        }     
        out.print("</tr>");
        while (rs6.next()) {    
            out.print("<tr>");
            for (int i = 1; i <= columnCount6; i++) {   
%>
<th width=100><input type="text" name="<%=md6.getColumnName(i).toString()%>" value="<%=rs6.getObject(i).toString() %>"/>
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
