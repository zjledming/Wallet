package cn.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: JDBCutil
 * @Description: JDBC连接工具类
 * @author XiMing.Fu
 * @date 2014-3-10 下午05:27:08
 * 
 */
public final class JDBCutil {

	private static final Log Log = LogFactory.getLog(JDBCutil.class.getName());
	private static JDBCutil instance = null;
	private String url;
	private String user;
	private String password;
	private String className;

	public JDBCutil() {

	}

	public static JDBCutil getInstance() {
		if (instance == null) {
			synchronized (JDBCutil.class) {
				if (instance == null) {
					instance = new JDBCutil();
				}
			}
		}
		return instance;
	}

	// static {
	// try {
	// // 注册驱动
	// Class.forName(className).newInstance();// 推荐
	// } catch (Exception e) {
	// Log.error(e);
	// }
	// }

	public Connection getConnection() throws SQLException {
		// 注册驱动
		try {
			Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 推荐
		return DriverManager.getConnection(url, user, password);
	}

	public void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			Log.error(e);
		}
	}
}