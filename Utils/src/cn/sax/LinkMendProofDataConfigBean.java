package cn.sax;


/**
 *<p>Title:LinkMendProofDataConfigBean.java</p>
 *<p>Description:缮证中间数据库连接配置实体bean</p>
 *<p>Copyright:Copyright (c) 2013</p>
 *<p>Company:湖南科创</p>
 *@author dongxu.jiang
 *@version 1.0
 *2013-05-16
 */
public class LinkMendProofDataConfigBean {

	 private String url;//地址
	 private String username;// 用户名 
	 private String password;// 密码 
	 private String port;// 端口
	 private String driverClass;// 数据库驱动 
	 private String updateChargeSql;//修改完税证明状态sql
	 private String chargeSql;// 判断地税完税证明sql 
	 private String  linkmendproofSql;// 获取 房管局业务系统打单状态
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUpdateChargeSql() {
		return updateChargeSql;
	}
	public void setUpdateChargeSql(String updateChargeSql) {
		this.updateChargeSql = updateChargeSql;
	}
	public String getChargeSql() {
		return chargeSql;
	}
	public void setChargeSql(String chargeSql) {
		this.chargeSql = chargeSql;
	}
	public String getLinkmendproofSql() {
		return linkmendproofSql;
	}
	public void setLinkmendproofSql(String linkmendproofSql) {
		this.linkmendproofSql = linkmendproofSql;
	}
	
	@Override
	public String toString() {
		return "LinkMendProofDataConfigBean [chargeSql=" + chargeSql
				+ ", driverClass=" + driverClass + ", linkmendproofSql="
				+ linkmendproofSql + ", password=" + password + ", port="
				+ port + ", updateChargeSql=" + updateChargeSql + ", url="
				+ url + ", username=" + username + "]";
	}
}
