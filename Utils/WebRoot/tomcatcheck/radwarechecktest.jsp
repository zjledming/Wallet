<%@page import="java.util.Date"%>
<%@page import="com.chinacreator.v2.xzsp.util.monitoring.Constant"%>
<%@page contentType="text/html;charset=GBK"%>

<%
	//String radchecktest = "checksussess";
	/*
	* 检测结果标识：success：正常    | error：异常
	*/
	String success = "checksussess";
	String error = "checkerror";

	// 监控业务
	try {
		// 如果结果标识为false，永远输出失败结果 
		if(!Constant.MONITORING_RESULT){
			out.println(error);
		}else{
			if (Constant.MONITORING_TIME_LIST.size()>0) {
				// 设计思路：不能直接remove i ，因为在你代码执行的过程中，不断有新的监控点被添加进来，Constant.MONITORING_TIME_LIST.size()在不断变大，如果remove i的话？...
				int j = 0;
				for (int i = 0; i < Constant.MONITORING_TIME_LIST.size(); i++) {
					long nowTime = new Date().getTime();
					long temp = Constant.MONITORING_TIME_LIST.get(j);
					/*
					 * 遍历容器中的所有异常发生点：
					 * 	1：如果大于设定时长，证明该异常发生在时间之外，移出容器
					 * 	2：如果小于等于设定时长，统计异常个数
					 */
					if (Math.abs(nowTime - temp) > Constant.MONITORING_TIME_LONG) {
						Constant.MONITORING_TIME_LIST.remove(temp);
						j--;
					}
					j++;
				}
				// 如果异常数大于等于监控最大异常数，赋值监控结果为false
				if (Constant.MONITORING_TIME_LIST.size() >= Constant.MONITORING_MAX_EXCEPTION) {
					Constant.MONITORING_RESULT = false;
					out.println(error);
				}else{
					out.println(success);
				}
			}else{
				out.println(success);
			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
%>