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

		<title>My JSP 'MyJsp.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	function queryData() {
		if(!checkInput()) {
	        return false;//如果验证不通过，则返回
	    }
	    var jsobstr = "{'iteminsname':'"+$('iteminsname').value+"','acceptorgname':'"+$('acceptorgname').value+"','acceptorgid':'"+$('acceptorgid').value+"','itemname':'"+$('itemname').value+"','instancecode':'"+$('instancecode').value+"','status':'"+$('status').value+"','acceptstarttime':'"+$('acceptstarttime').value+"','acceptendtime':'"+$('acceptendtime').value+"','applytype':'"+$('applytype').value+"','applyname':'"+$('applyname').value+"','orgid':'<%=orgid%>','orgname':'<%=orgname%>','orglevelfile':'<%=orglevelfile%>','orgvalue':'<%=orgvalue%>'}";
	 
	     jsobstr = descape(jsobstr);
	   	document.all.form1.jsobject.value = jsobstr;
		document.all.form1.submit();
	}
</script>

	</head>

	<body>


		项目名称:
		<INPUT id=iteminsname name=iteminsname value="<%=iteminsname%>"
			class=cText_out type="text">

		<BUTTON id=btnQuery class=cButton type="button" onclick='queryData()'>
			查&nbsp;询
		</BUTTON>
	</body>
</html>
