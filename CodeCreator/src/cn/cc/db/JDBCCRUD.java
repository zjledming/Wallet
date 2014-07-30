package cn.cc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.cc.install.Bean;
import cn.cc.util.Constant;
import cn.cc.util.StringUtil;

public class JDBCCRUD {

	public static String driver = "oracle.jdbc.driver.OracleDriver";// "oracle.jdbc.driver.OracleDriver";
	public static String url = "";// "jdbc:oracle:thin:@172.16.17.22:1521:srrd";
	public static String username = "";// "srrz";
	public static String password = "";// "123456";
	public static String dbtype = "oracle";// "123456";
	public static String database = "";// 
	public static String charsetName = "GBK";// 编码格式

	private static Connection conn = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;

	public static void main(String[] args) throws Exception {
		String uString = "jdbc:mysql://localhost:3306/traininginforms";
		System.out.println(uString.substring(uString.lastIndexOf("/")+1));
		
		Connection conn = JDBCCRUD.getConn();
		// List<Table> list = queryTable("TA_SRRZ_ENTERPRISE_INSTANCE");
		List list = queryALLTableName("");
		System.out.println(list.size());
		System.out.println("");
	}

	/**
	 * 测试数据库连接是否成功
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
	 * @throws Exception
	 */
	public static Connection getConn() {
		setDriver();
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

	/**
	 * 设置驱动
	 */
	public static void setDriver() {
		try {
			if (dbtype.equals("MySQL")) {
				driver = "com.mysql.jdbc.Driver";
				database = url.substring(url.lastIndexOf("/")+1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	/**
	 * 取当前用户下所有的表信息 oralce
	 * 
	 * @return
	 */
	public static List<Bean> queryALLTableName(String tableName)
			throws Exception {
		Connection conn = getConn();

		String sql = "select t.table_name,t.comments,t1.column_name from user_tab_comments t left join ( select  cu.table_name,cu.column_name "
				+ " from user_cons_columns cu, user_constraints au "
				+ " where cu.constraint_name = au.constraint_name "
				+ " and au.constraint_type = 'P' and  cu.table_name not like 'BIN$%') t1 on t1.table_name = t.table_name where t.table_name not like 'BIN$%' and t.table_type = 'TABLE'";
		if (dbtype.equals("MySQL")) {
			sql = "select distinct(t.table_name),'' comments,'' column_name from information_schema.columns t where t.table_schema = '"+StringUtil.deNull(database)+"'";
		}
		if (!StringUtil.isBlank(tableName)) {
			sql += " and t.table_name = ?";
		}
		PreparedStatement pstmt;
		int count = 0;
		List<Bean> list = new ArrayList<Bean>();
		try {
			pstmt = conn.prepareStatement(sql);
			if (!StringUtil.isBlank(tableName)) {
				pstmt.setString(1, StringUtil.deNull(tableName).toUpperCase());
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean bean = new Bean();
				bean.setName(StringUtil.deNull(rs.getString("table_name")));
				bean.setBeanRemark(StringUtil.deNull(rs.getString("comments")));
				bean.setPkName(StringUtil.getUppercaseChar(rs.getString("column_name")));
				/**
				 * 作为从表，取他对应的主表信息 <br>
				 * 查询：单个查询即可 <br>
				 * 删除：直接删除 <br>
				 * 修改：直接修改 <br>
				 * 新增：要先增加所有主表，在增加自身，并且要set外键信息
				 */
				if (dbtype.equals("oracle")) {
					bean.setPkMapList(queryPKinfo(bean));
				}
				/**
				 * 作为主表，取他对应的从表信息 <br>
				 * 查询：1.查询自身， 2.根据主键查询从表信息 <br>
				 * 删除：先删除所有从表，再删除自身 <br>
				 * 修改：直接修改 新增：直接新增
				 */
				list.add(bean);
				count++;
			}
			System.out.println("表count：" + count);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	/**
	 * 查询主表信息
	 * 
	 * @param bean
	 * @return
	 */
	private static List<String[]> queryPKinfo(Bean bean) {
		List<String[]> list = new ArrayList<String[]>();
		Connection conn = getConn();
		String sql = "select t.column_name as mycolumn_name,c.table_name as yotable_name,c.column_name as yocolumn_name from                                                                "
				+ "(select A.table_name, B.column_name, A.constraint_name, A.r_constraint_name                               "
				+ "  from user_cons_columns B                                                                                "
				+ "  left join user_constraints A                                                                            "
				+ "    on A.constraint_name = B.constraint_name                                                              "
				+ " where B.table_name = ?                                                                        "
				+ "   and A.constraint_type = 'R') t left join user_cons_columns c on c.constraint_name = t.r_constraint_name";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName().toUpperCase());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String[] arr = new String[3];
				arr[0] = StringUtil.deNull(rs.getString("mycolumn_name")).toLowerCase();
				arr[1] = StringUtil.deNull(rs.getString("yotable_name")).toLowerCase();
				arr[2] = StringUtil.deNull(rs.getString("yocolumn_name")).toLowerCase();
				list.add(arr);
				count++;
			}
			System.out.println("外键找主表：共查询到记录（条）" + count);
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	/**
	 * 查询从表信息
	 * 
	 * @param bean
	 * @return
	 */
	private static Map<String, String> queryRKinfo(Bean bean) {
		return null;

	}

	/**
	 * oracle查询表字段
	 * 
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public static List<Table> queryTable(String tableName) throws Exception {
		conn = getConn();
		String sql = "select t.table_name,t.column_name,t.data_type from cols t where 1=1 and t.table_name = ?";
		if (dbtype.equals("MySQL")) {
			sql="select t.table_name,t.column_name,t.data_type from information_schema.columns t where t.table_schema = '"+StringUtil.deNull(database)+"' and t.table_name = ?";
		}
		List<Table> list = new ArrayList<Table>();
		try {
			pstmt = conn.prepareStatement(sql);
			int j = 1;
			pstmt.setString(j, StringUtil.deNull(tableName).toUpperCase());
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
