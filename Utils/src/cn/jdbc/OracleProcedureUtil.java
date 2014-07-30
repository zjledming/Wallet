package cn.jdbc;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

/**
 * @ClassName: OracleProcedureUtil
 * @Description: jdbc链接oracle，并执行存储过程
 * @author XiMing.Fu
 * @date 2014-3-10 下午02:38:10
 * 
 */
public class OracleProcedureUtil {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@10.55.19.160:1521:gxq";
	private String user = "app_1";
	private String password = "123456";
	private Connection conn = null;

	public OracleProcedureUtil() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			// System.out.println("连接成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet getResultSet(String sql) {
		try {
			CallableStatement cs = conn
					.prepareCall("{ call row_to_col_func2(?,?) }");
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.setString(2, sql);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		OracleProcedureUtil dao = new OracleProcedureUtil();
		List<List<String>> it = dao.getSurveyData("", "", "", "");
		for (List<String> list : it) {
			for (String str : list) {
				System.out.print(str + ":");
			}
			System.out.println();
		}

	}

	public List<List<String>> getSurveyData(String orgIds, String serType,
			String startTime, String endTime) {
		String sql = "select m.org_name,  m.dic_survey_name, m.server_state_name, (select num from (select a.org_name,"
				+ " a.dic_survey_name, a.server_state_name,  count(server_state_name) as num from (select (select org_name"
				+ " from ta_spxm where approve_id in (select approve_id  from app_1.ta_sp_instance "
				+ " where instance_id = s.item_insid)) as org_name, d.dic_survey_name,  t.server_state_name"
				+ " from app_1.TA_SATISFACTION_SURVEY s left join app_1.TA_DIC_SATISFACTION_SURVEY d "
				+ " on s.dic_survey_id =  d.dic_survey_id left join app_1.ta_satisfaction_survey_stat t on t.ser_state_id ="
				+ " s.survey_value  where 1=1 ";
		if (null != startTime && !"".equals(startTime)
				&& !"null".equals(startTime)) {
			sql = sql + "and to_char(s.survey_time,'yyyy-mm-dd') >= '"
					+ startTime + "'";
		}
		if (null != endTime && !"".equals(endTime) && !"null".equals(endTime)) {
			sql = sql + "and to_char(s.survey_time,'yyyy-mm-dd') <= '"
					+ endTime + "'";
		}
		sql = sql
				+ "  ) a group by a.org_name, a.dic_survey_name, a.server_state_name) b  where m.org_name = b.org_name"
				+ " and b.dic_survey_name = m.dic_survey_name and m.server_state_name = b.server_state_name) as numb"
				+ " from (select o.org_name, t.dic_survey_name, s.server_state_name  from app_5.td_sm_organization o,"
				+ " app_1.TA_DIC_SATISFACTION_SURVEY  t, app_1.ta_satisfaction_survey_stat s"
				+ " where 1=1 ";
		if (null != orgIds && !"".equals(orgIds) && !"null".equals(orgIds)) {
			sql = sql + " and o.org_id in(" + orgIds + ") ";
		}
		if (null != serType && !"".equals(serType) && !"null".equals(serType)) {
			sql = sql + " and t.DIC_SURVEY_ID ='" + serType + "' ";
		}
		sql = sql + "order by o.org_name, t.dic_survey_name, s.state_code) m";
		System.out.println(sql);
		ResultSet rs = getResultSet(sql);

		ResultSetMetaData rsmd;
		int count = 0;
		int currentRow = 1;
		List<List<String>> lt = new ArrayList<List<String>>();
		List<String> list = null;
		try {
			rsmd = rs.getMetaData();
			count = rsmd.getColumnCount();
			if (count <= 0) {
				return null;
			}
			list = new ArrayList<String>();
			for (int i = 1; i <= count; i++) {
				list.add(rsmd.getColumnName(i));
			}
			lt.add(list);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		try {
			while (rs.next()) {
				list = new ArrayList<String>();
				for (int i = 1; i <= count; i++) {
					list.add(rs.getString(i));
				}
				lt.add(list);
				currentRow++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lt;
	}
}
