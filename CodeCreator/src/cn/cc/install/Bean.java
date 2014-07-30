/**
 * 
 */
package cn.cc.install;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * bean类
 * 
 * @author ximing.fu<br>
 * @date 2014-6-29
 */
public class Bean {
	/** bean 名称 */
	private String name;
	/** bean 名称 */
	private String pkName;
	/** bean备注 */
	private String beanRemark;
	/** packageUrl */
	private String packageUrl;
	/** bean 首字母小写名称 */
	private String lowerName;
	/** bean 首字母大写名称 */
	private String upperName;
	/** bean 路径 */
	private String beanUrl;
	/** dao name */
	private String beanDaoName;
	/** dao 路径 */
	private String beanDaoUrl;
	/** dao 实现路径 */
	private String beanDaoImplUrl;
	/** service name */
	private String beanServiceName;
	/** service 路径 */
	private String beanServiceUrl;
	/** service 实现路径 */
	private String beanServiceImplUrl;

	/** 字段集合 */
	private List<String> columnList;
	private Map<String, String> columnMap;
	
	/** util路径 */
	private String utilUrl;
	private String stringutilUrl;

	/** 从表信息 */
	private Map<String, String> rkMap;
	/** 主表信息 */
	// private List<Map<String, String>> pkMapList;
	private List<String[]> pkMapList = new ArrayList<String[]>();

	/** 序列名称 */
	private String seqName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLowerName() {
		return lowerName;
	}

	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public String getBeanUrl() {
		return beanUrl;
	}

	public void setBeanUrl(String beanUrl) {
		this.beanUrl = beanUrl;
	}

	public String getBeanDaoUrl() {
		return beanDaoUrl;
	}

	public void setBeanDaoUrl(String beanDaoUrl) {
		this.beanDaoUrl = beanDaoUrl;
	}

	public String getBeanDaoImplUrl() {
		return beanDaoImplUrl;
	}

	public void setBeanDaoImplUrl(String beanDaoImplUrl) {
		this.beanDaoImplUrl = beanDaoImplUrl;
	}

	public String getBeanServiceUrl() {
		return beanServiceUrl;
	}

	public void setBeanServiceUrl(String beanServiceUrl) {
		this.beanServiceUrl = beanServiceUrl;
	}

	public String getBeanServiceImplUrl() {
		return beanServiceImplUrl;
	}

	public void setBeanServiceImplUrl(String beanServiceImplUrl) {
		this.beanServiceImplUrl = beanServiceImplUrl;
	}

	public String getUpperName() {
		return upperName;
	}

	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}

	public String getBeanDaoName() {
		return beanDaoName;
	}

	public void setBeanDaoName(String beanDaoName) {
		this.beanDaoName = beanDaoName;
	}

	public String getBeanServiceName() {
		return beanServiceName;
	}

	public void setBeanServiceName(String beanServiceName) {
		this.beanServiceName = beanServiceName;
	}

	public List<String> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<String> columnList) {
		this.columnList = columnList;
	}

	public Map<String, String> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, String> columnMap) {
		this.columnMap = columnMap;
	}

	public String getPackageUrl() {
		return packageUrl;
	}

	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}

	public String getBeanRemark() {
		return beanRemark;
	}

	public void setBeanRemark(String beanRemark) {
		this.beanRemark = beanRemark;
	}

	public String getSeqName() {
		return seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public Map<String, String> getRkMap() {
		return rkMap;
	}

	public void setRkMap(Map<String, String> rkMap) {
		this.rkMap = rkMap;
	}

	public List<String[]> getPkMapList() {
		return pkMapList;
	}

	public void setPkMapList(List<String[]> pkMapList) {
		this.pkMapList = pkMapList;
	}

	public String getUtilUrl() {
		return utilUrl;
	}

	public void setUtilUrl(String utilUrl) {
		this.utilUrl = utilUrl;
	}

	public String getStringutilUrl() {
		return stringutilUrl;
	}

	public void setStringutilUrl(String stringutilUrl) {
		this.stringutilUrl = stringutilUrl;
	}
	

}
