<%@page import="com.jluzh.jw.DownLoad"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>    
    <title>Java开发数据爬虫</title>
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="This is my page">
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="css/bootstrap-theme.css" />
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/animate.min.css" type="text/css"></link>
    <style type="text/css">
    	*{margin:0;}
    	body{background:url('image/back.gif');}
    	h1{font-family:'微软雅黑';text-align:center}
    	.url{width: 540px;height: 37px;margin: 6px auto;}
    	.url span{text-align:center;font-family:'微软雅黑';font-size:16px}
    	.url .text{height: 34px;width: 280px;border: 2px solid #06f;}
    	.url .btn{width:60px;height:34px;margin-left:6px;background:#06f;color:#fff;border:0}
    </style>
   </head>
    
    
    <%
    	String url = request.getParameter("url");
    	List<HashMap<String,String>> maps = DownLoad.getJobInfo(url, "utf-8");
     %>
  	
  <body>
   <h1>智联招聘职位抓取</h1>
  <form action="index.jsp">
  	<div class="url animated  bounceInUp">
  		<span >请输入智联招聘的URL：</span><input type="text" name="url" class="text" ><input type="submit" value="提交" class="btn"/>
  	</div>
  </form>
  <table class="table table-striped table-bordered table-hover" style="width:90%;margin:auto;">
	<thead>
		<tr>
		</tr>
	</thead>
	<%
		for(HashMap<String ,String> map :maps){
	 %>
	<tr class="animated bounceInRight">
		<td><%=map.get("jobName") %></td>
		<td><%=map.get("comName") %></td>
		<td><%=map.get("money") %></td>
		<td><%=map.get("date") %></td>
	</tr>
	<%
		}
	 %>
  </table>
  </body>
</html>
