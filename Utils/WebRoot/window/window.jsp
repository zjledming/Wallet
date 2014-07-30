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

		<title>My JSP 'window.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	function t1() {
		var returnVal = window
				.showModalDialog(
						"w1.jsp",
						window, // 改参数用于在子窗口操作父窗口
						"font-family:Verdana; font-size:12; status:no; unadorned:no; scroll:no; resizable:yes;dialogWidth:420px; dialogHeight:350px");
		if (returnVal) {
			alert(returnVal);
		}

	}
	function t1() {
		window
				.open(
						"w2.jsp",
						"doinghtml",
						"height="
								+ (screen.availHeight - 200)
								+ ",width="
								+ (screen.availWidth - 300)
								+ ",top=100,left=150,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
		// 177: newwind = window.open(nonTaxUrl,"_blank" ,"height=0, width=0, top=2000, left=2000,toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		// 440: winOpen = window.open("about:blank","win","scrollbars=no,status=no,titlebar=no,toolbar=no,z-lock=yes,width=616,height=500,top=130,left=210");
		// 39: window.open('sysmanager/user/orgTree.jsp','newWin','scrollbars=no,status=no,titlebar=no,toolbar=no,z-lock=yes,width=616,height=500,top=150,left=250')
		// 123: winOpen = window.open("about:blank","win","scrollbars=no,status=no,titlebar=no,toolbar=no,z-lock=yes,width=616,height=500,top=130,left=210");
		// 关闭后刷新当前页面
		// window.showModalDialog(url,window,"dialogWidth:500px;dialogHeight:300px;scroll:no;location:no;status:no;");
		//window.location.href = window.location.href;
	}
	function showdetail(message_info_id){ 
		var printpath = "sendmessagedetail.jsp?message_info_id="+message_info_id+"&showtype=1";
		window.showModalDialog(printpath,window,"dialogWidth:850px;dialogHeight:300px;scroll:auto;status:no;location:no;");
		
	}

	// 打开全屏窗口查看报表
	function windowView() {
		var url = $("chartIframe").src + "&fullscreenFlag=y&title="
				+ tempstartTime + "至" + tempendTime + areaNameV + title;
		var width = screen.width;
		var height = screen.height;
		var featrues = "width="
				+ width
				+ "px,height="
				+ height
				+ "px,status=yes,menubar=no,scrollbars=no,resizable=no,location=no";

		window.open(url, '全屏', featrues);
		;

	}
</script>
	</head>

	<body>
		This is my JSP page.
		<br>
		<form name="form1" action="">
			<input type="text" name="name1" />
			<input type="text" id="name2" name="name2" />
		</form>
		<a href="javascript:t1();">showModalDialog</a>
		<a href="javascript:t1();">window.open</a>


	</body>
</html>
