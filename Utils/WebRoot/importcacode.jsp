<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>

<html>
	<head>
		<link type='text/css' rel='stylesheet'
			href='../../../resources/css/creatorBlue/style_right.css' />
		<link type='text/css' rel='stylesheet'
			href='../../../resources/css/creatorBlue/allStyle.css' />
		<script language="JavaScript" type="text/JavaScript">
	function check() {
		var fileurl = document.Form1.File1.value;
		var extname = fileurl.substring(fileurl.indexOf("."), fileurl.length);
		if (!fileurl) {
			alert('请选择CA证书导入文件');
			return false;
		}
		if (".xls" != extname) {
			alert('请选择excel文件');
			return false;
		} else {
			document.Form1.submit();
		}
	}
	
	function importCA() {
		var filename = document.getElementById("affix").value;
		if (!filename.endWith(".xls")) {
			alert("请选择正确的excel表格!");
			return;
		}
		document.all.form1.submit();
	}

	String.prototype.endWith = function(str) {
		if (str == null || str == "" || this.length == 0
				|| str.length > this.length)
			return false;
		if (this.substring(this.length - str.length) == str)
			return true;
		else
			return false;
	}
</script>
		<title>导入ca证书编号</title>
	</head>
	<body>
		<form name="Form1" enctype="multipart/form-data" method="post"
			action="importcacode_do.jsp">
			<table width='100%' border='1' class=table_function>
				<tr>
					<td height='30' colspan='2' class=all_title align='center'>
						CA证书导入
					</td>
				</tr>
				<tr>
					<td height='30' width='30%' class=color>
						选择Excel文件 ：
					</td>
					<td align='left'>
						<input type="file" name="File1" size="25" maxlength="20">
					</td>
				</tr>
				<tr>
					<td height='30' colspan='2'>
						注意：1.请确保选中的为Excel格式文件，Excel文件名不能包含空格。2.请确保登录名输入正确。
					</td>
				</tr>
				<tr>
					<td height='30' colspan='2' align='center'>
						<input type="button" class=sub_btn Onclick="check()" value="提交">
						<input type="reset" class=sub_btn value="重置">
						<input type="button" class=sub_btn value="返回"
							onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</body>