package cn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JDBCCRUD {

	public static String driver = "oracle.jdbc.driver.OracleDriver";//"oracle.jdbc.driver.OracleDriver";
	public static String url = "";//"jdbc:oracle:thin:@172.16.17.22:1521:srrd";
	public static String username = "";//"srrz";
	public static String password = "";//"123456";

	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;

	public static void main(String[] args) throws Exception {
		Connection conn = JDBCCRUD.getConn();
		List<Table> list = queryTable("TA_SRRZ_ENTERPRISE_INSTANCE");
		System.out.println(list.size());
		System.out.println("");
	}

	/**
	 * 测试数据库连接是否成�?
	 * 
	 * @return
	 */
	public static boolean test() {
		try {
			conn = getConn();
			String sql = "select 1 from dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				clear();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭资源
	 * 
	 * @throws Exception
	 */
	public static void clear() throws Exception {
		if (pstmt != null) {
			pstmt.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	public static Connection getConn() {

		Connection conn = null;
		try {
			Class.forName(driver);
			// new oracle.jdbc.driver.OracleDriver();
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static int delete(String username) {
		Connection conn = getConn();
		int i = 0;
		String sql = "delete users where username='" + username + "'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	public static Integer getSeq() {
		Connection conn = getConn();
		String sql = "select seq_accept.Nextval from dual";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static int insert(Type type) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into PARAM_DELTAIL (id,param_type,param_name,param_value,locale,seq,remark) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			// Statement stat = conn.createStatement();
			pstmt.setString(1, type.getId());
			pstmt.setString(2, type.getParamType());
			pstmt.setString(3, type.getParamName());
			pstmt.setString(4, type.getParamValue());
			pstmt.setString(5, type.getLocale());
			pstmt.setInt(6, type.getSeq());
			pstmt.setString(7, type.getRemark());
			i = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	public static List<Type> query() {
		Connection conn = getConn();
		String sql = "select t.type_name,t.type,t.remark from class1 t order by t.type_id asc,t.type_name asc";
		PreparedStatement pstmt;
		int count = 0;
		List<Type> typeList = new ArrayList<Type>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Type type = new Type(rs.getString("type").trim(), rs.getString(
						"type_name").trim());
				type.setRemark(rs.getString("remark"));
				typeList.add(type);
				count++;
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return typeList;

	}

	/**
	 * oracle查询表字�?
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static List<Table> queryTable(String tableName) throws Exception {
		conn = getConn();
		String sql = "select t.table_name,t.column_name,t.data_type from cols t where 1=1 and t.table_name = ?";
		List<Table> list = new ArrayList<Table>();
		try {
			pstmt = conn.prepareStatement(sql);
			int j = 1;
			pstmt.setString(j, tableName.toUpperCase());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Table bean = new Table();
				bean.setTable_name(rs.getString("table_name"));
				bean.setData_type(rs.getString("data_type"));
				bean.setColumn_name(rs.getString("column_name"));
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			clear();
		}
		return list;
	}

	public static int update(Type type) {
		Connection conn = getConn();
		int i = 0;
		String sql = "update users set password='" + type.getParamType()
				+ "' where username='" + type.getParamName() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);

			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);

			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}
}
