package cn.sax;

/**
 *<p>Title:</p>
 *<p>Description:事项个性化页面配置bean</p>
 *<p>Copyright:Copyright (c) 2013</p>
 *<p>Company:湖南科创</p>
 *@author liang.deng
 *@version 1.0
 *@date 2013-4-9
 */

public class ItemIndividualPageCfgBean {
	private String page_id; 
	private String ec_id;
	private String page_name;
	private String page_alias;
	private String page_path;
	private String is_valid;
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public String getPage_alias() {
		return page_alias;
	}
	public void setPage_alias(String page_alias) {
		this.page_alias = page_alias;
	}
	public String getPage_path() {
		return page_path;
	}
	public void setPage_path(String page_path) {
		this.page_path = page_path;
	}
	public String getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(String is_valid) {
		this.is_valid = is_valid;
	}
	public void setEc_id(String ec_id) {
		this.ec_id = ec_id;
	}
	public String getEc_id() {
		return ec_id;
	}
	
}
