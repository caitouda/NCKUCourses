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
<title>QUERY</title>   
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css"> 
body {     
    font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif;     
	font-size:14px; 
}   

h1 {     
    margin-top:0px;     
    margin-bottom:8px; 
}   

/* link */
a {     
    text-decoration:none;     
	color:#1c00ff; 
}   

a:hover {     
    color:#5f00e4; 
} 

fieldset.query {     
    padding: 0px;     
	border: none;     
	width: 232px;     
	background:#e0e0e0; 
}   

fieldset.query:hover {     
    background: #a8a8a8; 
} 
.query input, .query button {     
    border: none;     
	float: left; 
} 
.query input.box {     
    height: 28px;     
	width: 200;     
	margin-right: 0px;     
	padding-right: 0px;     
	background: #e0e0e0;     
	margin: 1px; 
} 
.query input.box:focus {     
    background: #e8e8e8 ;     
	outline: none; 
} 
.query button.btn {     
    border: none;     
	width: 28px;     
	height: 28px;     
	margin: 0px auto;     
	margin: 1px;     
	background: url(http://sandbox.runjs.cn/uploads/rs/339/livk7pl5/search_blue.png)
	} 
.query button.btn:hover { 
    background: url(http://sandbox.runjs.cn/uploads/rs/339/livk7pl5/search_black.png) no-repeat bottom right; 
} 
  
/* base style */ 
.article { 
  
} 
</style>
</head> 
<center>
<body> 
<div> 
<h3>Please input your sql</h3> 
<form action="query" method="post"> 
    <fieldset class="query" style="width: 585px; "> 
         <input type="text" class="box" name="s" id="s" class="inputText" placeholder="sql" style="width: 550px; "> 
         <button class="btn" title="QUERY" type="submit"> </button> 
    </fieldset>
</form> 
<%
    Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
    String sql = (String)request.getAttribute("sql");
    System.out.println(sql);
    //System.out.println("OK");
    //不能在這裡寫方法或者函數
    try {
        conn = MySQLDBCon.getConn();
        //System.out.println("HELLO2");
	    pstmt = conn.prepareStatement(sql);	
	    //pstmt = conn.prepareStatement("show tables;");
	    //System.out.println("HELLO3");
	    //if(sql.equalsIgnoreCase("select") || sql.equalsIgnoreCase("show")) 
        if(sql.indexOf("select") != -1 || sql.indexOf("show") != -1)
        {  
            rs = pstmt.executeQuery();		
            //System.out.println("HELLO4");
	        ResultSetMetaData md = rs.getMetaData();
	        int columnCount = md.getColumnCount(); 
	        //Map rowData = new HashMap(); //轉為list  
	        out.print("<table border=2>");
	        out.print("<tr>");
	        for (int i = 1; i <= columnCount; i++) {   
	            //System.out.println(md.getColumnName(i).toString());
	            //System.out.println(rs.getObject(i).toString());
		        out.println("<th width=100>"+md.getColumnName(i).toString());
		        }     
	        out.print("</tr>");
	        /*if (rs.next()) {  
	            for (int i = 1; i <= columnCount; i++) {   
	                //System.out.println(md.getColumnName(i).toString());
	                //System.out.println(rs.getObject(i).toString());
	    	        out.println("<th width=100>"+md.getColumnName(i).toString());
	    	        out.print("</tr>");
	    	    }     
	        }  
	        for (int i = 1; i <= columnCount; i++) {   
	    	    //System.out.println(md.getColumnName(i).toString());
	            //System.out.println(rs.getObject(i).toString());
	            out.print("<tr>");
	            out.println("<th width=100>"+rs.getObject(i).toString());   
	            out.print("</tr>");
	            }   
	        */
	        //System.out.println("NO");
	        while (rs.next()) {    
	            out.print("<tr>");
	            for (int i = 1; i <= columnCount; i++) {   
	                //System.out.println(rs.getObject(i).toString());  
	     	        out.println("<th width=100>"+rs.getObject(i).toString()); 
	     	       }   
	            out.print("</tr>");
	        }  
	        out.print("</table><br>");
	    }  
	    else
		    pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } 
        /*finally {
		    try {
			    pstmt.close();
			    conn.close();
		    } catch (SQLException e) {
			    e.printStackTrace();
		    }
	    }*/
%>       
<a href="index.html">return to DBMSNCKUOJ</a>
</div> 
</body>
</center>
</html>
