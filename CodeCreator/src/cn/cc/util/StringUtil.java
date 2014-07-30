package cn.cc.util;

public class StringUtil {
	/**
	 * 功能描述：是否为空白,包括null和""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0 || "null".equals(str);
	}

	/**
	 * 过滤掉为null的字符串
	 * 
	 * @param: String param
	 * @return: String
	 */
	public static String deNull(String param) {
		if (nullOrBlank(param))
			return "";
		return param.trim();
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param: String param
	 * @return: boolean
	 */
	public static boolean nullOrBlank(String param) {
		return (param == null || param.trim().equals("") || param
				.equals("null")) ? true : false;
	}

	/**
	 * 把第一个字母变为大写，其它小写<br>
	 * 如：userDao ——> Userdao
	 * 
	 * @param str
	 * @return
	 */
	public static String getUppercaseChar(String str) {
		if (!isBlank(str)) {
			if (str.length() > 1) {
				return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
			} else {
				return str.toUpperCase();
			}
		}
		return "";
	}

	/**
	 * 把第一个字母变为小写，其它小写<br>
	 * 如：UserDao ——> userdao
	 * 
	 * @param str
	 * @return
	 */
	public static String getLowercaseChar(String str) {
		if (!isBlank(str)) {
			if (str.length() > 1) {
				return str.substring(0, 1).toLowerCase() + str.substring(1).toLowerCase();
			} else {
				return str.toLowerCase();
			}
		}
		return "";
	}

	/**
	 * 按“.”分割，获取路径的最后面字符串<br>
	 * 如： str = "com.b510.base.bean.User"<br>
	 * return "User";
	 * 
	 * @param str
	 * @return
	 */
	public static String getLastChar(String str) {
		if (!isBlank(str)) {
			int dot = str.lastIndexOf('.');
			if ((dot > -1) && (dot < (str.length() - 1))) {
				return str.substring(dot + 1);
			}
		}
		return "";
	}
}