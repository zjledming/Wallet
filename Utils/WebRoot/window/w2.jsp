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

		<title>My JSP 'w2.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	function dowin() {
		var name1 = window.opener.document.all.item("name1").value;
		alert(name1);
	}

	function father() {
		window.opener.location = "";
		if (window.opener) {
		}
		window.setTimeout("reloadOpener()", 3000);

		window.opener.location = window.opener.location;
		parent.window.opener.location = parent.window.opener.location;

		opener.document.forms[0].orgName.value = backstring;
		opener.document.forms[0].orgName.focus;
		opener.changebox();

		parent.window.opener.location = parent.window.opener.location;
		var url = window.opener.document.forms(formName).meetingContenthref.value;
		window.opener.document.all.item("roleids").value = roleid;
		window.opener.location.reload();
		opener.document.location.reload();
		parent.opener.openNewDiv();
		window.parent.opener.closeload();

		parent.parent.opener.doTemp(1);// 暂存数据  

		// 关闭页面刷新父页面  <body onload=init() onunload="opener.refresh();">  
		if ((typeof window.opener) == 'object') {
		}
		if (window.opener && window.opener.refreshSelf) {
		}

	}
</script>
	</head>

	<body>
		This is my JSP page.
		<br>
		<a href="javascript:dowin();">关闭</a>
	</body>
</html>
