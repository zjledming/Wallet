<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'serverconfig.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<table border=0 cellspacing=1 align=center class=list>
			<tr>
				<th colspan=2>
					服务器的有关参数
				</th>
			</tr>
			<tr>
				<td width="20%">
					服务器名：
				</td>
				<td width="80%"><%=request.getServerName()%></td>
			</tr>
			<tr>
				<td width="20%">
					服务器IP：
				</td>
				<td width="80%"><%=request.getRemoteAddr()%></td>
			</tr>
			<tr>
				<td width="20%">
					服务器端口：
				</td>
				<td width="80%"><%=String.valueOf(request.getServerPort())%></td>
			</tr>
			<tr>
				<td width="20%">
					服务器时间：
				</td>
				<td width="80%"><%=(new java.util.Date()).toLocaleString()%></td>
			</tr>
			<tr>
				<td width="20%">
					URI路径：
				</td>
				<td width="80%"><%=request.getRequestURI()%></td>
			</tr>
			<tr>
				<td width="20%">
					Servlet路径：
				</td>
				<td width="80%"><%=request.getServletPath()%></td>
			</tr>
			<tr>
				<td width="20%">
					Context路径：
				</td>
				<td width="80%"><%=request.getContextPath()%></td>
			</tr>
			<tr>
				<td width="20%">
					物理路径：
				</td>
				<td width="80%"><%=application.getRealPath("/")%>
				||
				<%=request.getSession().getServletContext().getRealPath("/")%>
				</td>
			</tr>
			<tr>
				<td width="20%">
					Server Info：
				</td>
				<td width="80%"><%=getServletConfig().getServletContext().getServerInfo()%></td>
			</tr>
		</table>
	</body>
</html>
