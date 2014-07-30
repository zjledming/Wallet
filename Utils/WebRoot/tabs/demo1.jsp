<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="tabpane-taglib.tld" prefix="tab"%>
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

		<title>My JSP 'demo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!-- 这个标记将毫不费力地考虑建立HTML <SCRIPT>和<link>标记，把必要的CSS和JavaScript库链接到页面 -->
		<tab:tabConfig />
	</head>

	<body>
		基于本地标签库tabpane-taglib.tld
		<font color="red">存在问题，待解决</font>
		<br>
		<tab:tabContainer id="foolfish">
			<tab:tabPane id="foolfish_1" tabTitle="foolfish_1">
				<iframe
					style="WIDTH: 100%; HEIGHT: 600px; overflow-x: hidden; overflow-y: scroll;"
					frameborder="0" id="mxIframe" src="tabs/foolfish_1.jsp" name="mxIframe"></iframe>
			</tab:tabPane>
			<tab:tabPane id="foolfish_2" tabTitle="foolfish_2">
				<iframe
					style="WIDTH: 100%; HEIGHT: 600px; overflow-x: hidden; overflow-y: scroll;"
					frameborder="0" id="mxIframe" src="tabs/foolfish_2.jsp" name="mxIframe"></iframe>
			</tab:tabPane>
		</tab:tabContainer>
	</body>
</html>
