package cn.cc.db;

import cn.cc.util.JsonUtil;
import cn.cc.util.StringUtil;

public class Config {

	private String codeUrl = "";
	private String dbUrl = "";
	private String dbUser = "";
	private String dbPassword = "";
	private String tableName = "";
	private String type = "";
	private String charsetName = "";
	
	public static String logFile = "C:\\CodeCreatorMing.log";

	public Config() {
	}

	public Config(String codeUrl, String dbtype, String url, String username, String password,
			String tablename,String charsetName) {
		this.codeUrl = codeUrl;
		this.type = dbtype;
		this.dbUrl = url;
		this.dbUser = username;
		this.dbPassword = password;
		this.tableName = tablename;
		this.charsetName = charsetName;
	}

	/**
	 * 缓存配置
	 * @param config
	 */
	public static void saveConfig(Config config) {
		try {
			String configJson = JsonUtil.fastjson_beanToJson(config);
			System.out.println(configJson);
			JsonUtil.writeStr2File(configJson);
		} catch (Exception e) {
			System.out.println("保存配置信息失败：" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化配置
	 */
	public static Config init()  {
		try {
			String initStr = JsonUtil.read();
			if (StringUtil.isBlank(initStr)) {
				return null;
			}
			return JsonUtil.fastjson_jsonToBean(initStr, Config.class);
		} catch (Exception e) {
			System.out.println("初始配置失败："+e.getMessage());
			e.printStackTrace();
		}
		return null;
		
	}


	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCharsetName() {
		return charsetName;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}



}
