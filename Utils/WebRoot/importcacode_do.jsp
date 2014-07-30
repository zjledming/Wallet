<%@page import="cn.jxl.ImportCA"%>
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<%
	String meg = "";
	try {
		ImportCA ca = new ImportCA(request);
		meg = ca.getFile();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<script language="JavaScript" type="text/JavaScript">
	var meg = "<%=meg%>";
	meg = encodeURI(encodeURI(meg));
 	window.location.href = "importcacode.jsp?meg="+meg;
</script>
