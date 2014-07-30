<%@page import="cn.jxl.Jxlutil"%>
<%@page import="java.io.OutputStream"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="java.util.Vector"%>
<%
	/*
	 *由于jsp container在处理完成请求后会调用releasePageContet方法释放所用的PageContext object,
	     并且同时调用getWriter方法,
	     由于getWriter方法与在jsp页面中使用流相关的getOutputStream方法冲突,所以会造成这种异常,
	     解决办法是:
	    	 只需要在jsp页面的最后加上两条语句:  
	    		 out.clear();
				 out=pageContext.pushBody();即可(其中out,pageContext均为jsp内置对象!)
	 */
	out.clear();
	out = pageContext.pushBody();

	response.setContentType("application/ms-excel");
	response.setHeader("Content-Disposition", "attachment;Filename="
			+ new String("导出excel.xls".getBytes("gb2312"), "iso8859-1"));
	OutputStream os = null;

	// 数据
	Vector<Vector<String>> v = new Vector();
	Vector v01 = new Vector();
	v01.add("中國人");
	v01.add("美國人");
	v01.add("貓");
	v01.add("紅");
	v01.add("綠");
	v01.add("綠");
	v.add(v01);
	Vector v02 = new Vector();
	v02.add("aaaaa");
	v02.add("bbbbb");
	v02.add("cccc");
	v02.add("");
	v02.add("eeeee");
	v02.add("fffff");
	v.add(v02);

	Vector v03 = new Vector();
	v03.add("aaaaa01");
	v03.add("");
	v03.add("cccc");
	v03.add("dddd");
	v03.add("eeeee");
	v03.add("fffff");
	v.add(v03);
	Vector v04 = new Vector();
	v04.add("");
	v04.add("bbbbb");
	v04.add("cccc");
	v04.add("dddd");
	v04.add("eeeee");
	v04.add("fffff");
	v.add(v04);
	Vector v05 = new Vector();
	v05.add("aaaaa");
	v05.add("bbbbb01");
	v05.add("cccc");
	v05.add("dddd");
	v05.add("eeeee");
	v05.add("fffff");
	v.add(v05);
	Vector v06 = new Vector();
	v06.add("aaaaa");
	v06.add("bbbbb");
	v06.add("cccc");
	v06.add("dddd01");
	v06.add("eeeee");
	v06.add("fffff");
	v.add(v06);

	try {
		os = response.getOutputStream(); //创建输出流  
		Jxlutil.export(os, v, null, null);
		os.flush();
		response.reset();
	} catch (Exception e) {
		if (os != null) {
			try {
				os.close();
			} catch (Exception ee) {

			}
		}
	} finally {
		if (os != null) {
			try {
				os.close();
			} catch (Exception ee) {

			}
		}
	}
%>