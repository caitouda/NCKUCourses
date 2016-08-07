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
<title>SCHOOL+CONTEST</title>   
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
<form> 
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
</select><br><br> 
<select name="CID" id="CID" style="width:140px; ">
<option value="why">請選擇比賽ID</option>   
<%
    Connection conn7 = null;
	PreparedStatement pstmt7 = null;
	ResultSet rs7 = null;
    try {
        conn7 = MySQLDBCon.getConn();
        pstmt7 = conn7.prepareStatement("select distinct CID from contest;");	
        rs7 = pstmt7.executeQuery();		
	    ResultSetMetaData md7 = rs7.getMetaData();
	    int columnCount7 = md7.getColumnCount(); 
	    while (rs.next()) {    
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
<select name="Ctitle" id="Ctitle" style="width:140px; ">
<option value="why">請選擇比賽名字</option>   
<%
    Connection conn8 = null;
	PreparedStatement pstmt8 = null;
	ResultSet rs8 = null;
    try {
        conn8 = MySQLDBCon.getConn();
        pstmt8 = conn8.prepareStatement("select distinct Ctitle from contest;");	
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
<select name="Starttime" id="Starttime" style="width:140px; ">
<option value="why">請選擇開始時間</option>   
<%
    Connection conn9 = null;
	PreparedStatement pstmt9 = null;
	ResultSet rs9 = null;
    try {
        conn9 = MySQLDBCon.getConn();
        pstmt9 = conn9.prepareStatement("select distinct Starttime from contest;");	
        rs9 = pstmt9.executeQuery();	
	    ResultSetMetaData md9 = rs9.getMetaData();
	    int columnCount9 = md9.getColumnCount(); 
	    while (rs9.next()) {    
	        for (int i = 1; i <= columnCount9; i++) {  
%> 
<option value="<%=rs9.getObject(i).toString()%>"><%=rs9.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="Endtime" id="Endtime" style="width:140px; ">
<option value="why">請選擇結束時間</option>   
<%
    Connection conn10 = null;
	PreparedStatement pstmt10 = null;
	ResultSet rs10 = null;
    try {
        conn10 = MySQLDBCon.getConn();
        pstmt10 = conn10.prepareStatement("select distinct Endtime from contest;");	
        rs10 = pstmt10.executeQuery();		
	    ResultSetMetaData md10 = rs10.getMetaData();
	    int columnCount10 = md10.getColumnCount(); 
	    while (rs10.next()) {    
	        for (int i = 1; i <= columnCount10; i++) {  
%> 
<option value="<%=rs10.getObject(i).toString()%>"><%=rs10.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="Status" id="Status" style="width:140px; ">
<option value="why">請選擇狀態</option>   
<%
    Connection conn11 = null;
	PreparedStatement pstmt11 = null;
	ResultSet rs11 = null;
    try {
        conn11 = MySQLDBCon.getConn();
        pstmt11 = conn11.prepareStatement("select distinct Status from contest;");	
        rs11 = pstmt11.executeQuery();		
	    ResultSetMetaData md11 = rs11.getMetaData();
	    int columnCount11 = md11.getColumnCount(); 
	    while (rs11.next()) {    
	        for (int i = 1; i <= columnCount11; i++) {  
%> 
<option value="<%=rs11.getObject(i).toString()%>"><%=rs11.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="Access" id="Access" style="width:140px; ">
<option value="why">請選擇訪問類型</option>   
<%
    Connection conn12 = null;
	PreparedStatement pstmt12 = null;
	ResultSet rs12 = null;
    try {
        conn12 = MySQLDBCon.getConn();
        pstmt12 = conn12.prepareStatement("select distinct Access from contest;");	
        rs12 = pstmt12.executeQuery();		
	    ResultSetMetaData md12 = rs12.getMetaData();
	    int columnCount12 = md12.getColumnCount(); 
	    while (rs12.next()) {    
	        for (int i = 1; i <= columnCount12; i++) {  
%> 
<option value="<%=rs12.getObject(i).toString()%>"><%=rs12.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select> 
<select name="Schoolname" id="Schoolname" style="width:140px; ">
<option value="why">請選擇舉辦學校</option>   
<%
    Connection conn13 = null;
	PreparedStatement pstmt13 = null;
	ResultSet rs13 = null;
    try {
        conn13 = MySQLDBCon.getConn();
        pstmt13 = conn13.prepareStatement("select distinct Schoolname from contest;");	
        rs13 = pstmt13.executeQuery();		
	    ResultSetMetaData md13 = rs13.getMetaData();
	    int columnCount13 = md13.getColumnCount(); 
	    while (rs13.next()) {    
	        for (int i = 1; i <= columnCount13; i++) {  
%> 
<option value="<%=rs13.getObject(i).toString()%>"><%=rs13.getObject(i).toString()%></option>
<%
            }   
	    }  
        } catch(Exception ex) {
		    ex.printStackTrace();
	    } 
%>           
</select><br><br> 
<button formaction="school_Contest_all" method="post" type="submit">ALL</button>
<button formaction="school_Contest_in" method="post" type="submit">IN</button>
<button formaction="school_Contest_notin" method="post" type="submit">NOT IN</button>
<button formaction="school_Contest_exists" method="post" type="submit">EXISTS</button>
<button formaction="school_Contest_notexists" method="post" type="submit">NOT EXISTS</button>
</form>
<%
    Connection conn5 = null;
	PreparedStatement pstmt5 = null;
	ResultSet rs5 = null;
	String sql5 = (String)request.getAttribute("sql5");
	//System.out.println(sql5);
	String exist = (String)request.getAttribute("exist");
    if(exist == "true")
    {
        try {
            conn5 = MySQLDBCon.getConn();
	        pstmt5 = conn5.prepareStatement(sql5);	
	        rs5 = pstmt5.executeQuery();		
	        ResultSetMetaData md5 = rs5.getMetaData();
	        int columnCount5 = md5.getColumnCount(); 
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount5; i++) {   
	            out.println("<th width=100>"+md5.getColumnName(i).toString());
	            }     
	        out.print("</tr>");
	        while (rs5.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount5; i++) {   
	                out.println("<th width=100>"+rs5.getObject(i).toString()); 
	                }   
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
	//System.out.println(sql6);
	String exist2 = (String)request.getAttribute("exist2");
    if(exist2 == "true")
    {
        try {
            conn6 = MySQLDBCon.getConn();
	        pstmt6 = conn6.prepareStatement(sql6);	
	        rs6 = pstmt6.executeQuery();		
	        ResultSetMetaData md6 = rs6.getMetaData();
	        int columnCount6 = md6.getColumnCount(); 
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount6; i++) {   
	            out.println("<th width=100>"+md6.getColumnName(i).toString());
	            }     
	        out.print("</tr>");
	        while (rs6.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount6; i++) {   
	                out.println("<th width=100>"+rs6.getObject(i).toString()); 
	                }   
	            out.print("</tr>");
	            }  
	        out.print("</table><br>");
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
    }
%>   
<%
    Connection conn15 = null;
	PreparedStatement pstmt15 = null;
	ResultSet rs15 = null;
	String sql15 = (String)request.getAttribute("sql15");
	//System.out.println(sql15);
	String exist3 = (String)request.getAttribute("exist3");
    if(exist3 == "true")
    {
        try {
            conn15 = MySQLDBCon.getConn();
	        pstmt15 = conn15.prepareStatement(sql15);	
	        rs15 = pstmt15.executeQuery();		
	        ResultSetMetaData md15 = rs15.getMetaData();
	        int columnCount15 = md15.getColumnCount(); 
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount15; i++) {   
	            out.println("<th width=100>"+md15.getColumnName(i).toString());
	            }     
	        out.print("</tr>");
	        while (rs15.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount15; i++) {   
	                out.println("<th width=100>"+rs15.getObject(i).toString()); 
	                }   
	            out.print("</tr>");
	            }  
	        out.print("</table><br>");
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
    }
%>   
<%
    Connection conn16 = null;
	PreparedStatement pstmt16 = null;
	ResultSet rs16 = null;
	String sql16 = (String)request.getAttribute("sql16");
	//System.out.println(sql16);
	String exist4 = (String)request.getAttribute("exist4");
    if(exist4 == "true")
    {
        try {
            conn16 = MySQLDBCon.getConn();
	        pstmt16 = conn16.prepareStatement(sql16);	
	        rs16 = pstmt16.executeQuery();		
	        ResultSetMetaData md16 = rs16.getMetaData();
	        int columnCount16 = md16.getColumnCount(); 
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount16; i++) {   
	            out.println("<th width=100>"+md16.getColumnName(i).toString());
	            }     
	        out.print("</tr>");
	        while (rs16.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount16; i++) {   
	                out.println("<th width=100>"+rs16.getObject(i).toString()); 
	                }   
	            out.print("</tr>");
	            }  
	        out.print("</table><br>");
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
    }
%>   
<%
    Connection conn17 = null;
	PreparedStatement pstmt17 = null;
	ResultSet rs17 = null;
	String sql17 = (String)request.getAttribute("sql17");
	//System.out.println(sql17);
	String exist5 = (String)request.getAttribute("exist5");
    if(exist5 == "true")
    {
        try {
            conn17 = MySQLDBCon.getConn();
	        pstmt17 = conn17.prepareStatement(sql17);	
	        rs17 = pstmt17.executeQuery();		
	        ResultSetMetaData md17 = rs17.getMetaData();
	        int columnCount17 = md17.getColumnCount(); 
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount17; i++) {   
	            out.println("<th width=100>"+md17.getColumnName(i).toString());
	            }     
	        out.print("</tr>");
	        while (rs17.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount17; i++) {   
	                out.println("<th width=100>"+rs17.getObject(i).toString()); 
	                }   
	            out.print("</tr>");
	            }  
	        out.print("</table><br>");
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } 
    }
%>
<a href="index.html">return to DBMSNCKUOJ</a>   
</div>
</body>
</center>
</html>
