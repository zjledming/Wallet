package cn.jdbc;
import java.util.HashMap;

/**
 * @ClassName: DBColumnMapper
 * @Description: 数据库字段操作类
 * @author XiMing.Fu
 * @date 2014-3-10 下午01:54:29
 * 
 */
public class DBColumnMapper {
	private static HashMap<String, String> dataTypes = new HashMap<String, String>();

	static {
		dataTypes.put("VARCHAR2", "java.lang.String");
		dataTypes.put("DATE", "java.sql.Timestamp");
		dataTypes.put("TIMESTAMP", "java.sql.Timestamp");
		dataTypes.put("CLOB", byte[].class.getName());
	}

	public static Object getDBUtilColumValue(Object obj, String columType) {

		// initMapper();
		String javaType = getJavaType(columType);
		if (javaType.equals("java.lang.String")) {
			return obj == null ? null : obj.toString();
		} else {
			return obj;
		}

	}

	public static String getJavaType(String type) {
		// initMapper();
		String javaType = dataTypes.get(type);
		if (javaType == null) {
			javaType = "java.lang.String";
		}
		return javaType;
	}

	/**
	 * 转换列名成getter形式 如：COLUMN_NAME 转换成Column_name
	 * 
	 * @param columnName
	 * @return
	 */
	public static String getSetterName(String columnName) {
		String head = "";
		String tail = "";
		int len = columnName.length();

		head = columnName.substring(0, 1).toUpperCase();
		if (len > 1) {
			tail = columnName.substring(1).toLowerCase();
		}
		return head + tail;
	}

	/**
	 * 与getSetterName(String columnName)功能相同
	 * 
	 * @param columnName
	 * @return
	 */
	public static String getGetterName(String columnName) {
		return getSetterName(columnName);
	}
}
