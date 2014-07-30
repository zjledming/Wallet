package bean;
/**
 *<p>Title:收费响应消息Bean</p>
 *<p>Description:</p>
 *<p>Copyright:Copyright (c) 2012</p>
 *<p>Company:湖南科创</p>
 *@author 刘龙
 *@version 1.0
 *@date 2012-11-20
 */

public class SocketMsg {
private String msgID;//消息ID (0-2147483648：2的31次方)
private String msgClass;//消息类型  请求、响应、通知（1/2/0）
private String cmdType;//消息命令 公共部分(1xxx)、政务中心部分(2xxx)、执收系统部分(3xxx)
private String bianMa;//单位或大厅编码
private String tmType;//传输类型  1－实时消息;2-FTP文件
private String wbxtlsh;//外部系统流水号
private String xmbm;//非税系统中项目编码
private String kpxx;//开票信息
private String errCode;//请求结果
private String errString;//错误描述
private String jksbh;//缴款书编号
private String dprq;//非税打票日期
private String dprxm;//非税打票人姓名
private String exp1;//交款类型
private String dwmc;//单位名称
private String applyname;//缴款人
private String trnType;
private String cltType;
private String areaCode;
public String getMsgID() {
	return msgID;
}
public void setMsgID(String msgID) {
	this.msgID = msgID;
}
public String getMsgClass() {
	return msgClass;
}
public void setMsgClass(String msgClass) {
	this.msgClass = msgClass;
}
public String getCmdType() {
	return cmdType;
}
public void setCmdType(String cmdType) {
	this.cmdType = cmdType;
}
public String getBianMa() {
	return bianMa;
}
public void setBianMa(String bianMa) {
	this.bianMa = bianMa;
}
public String getTmType() {
	return tmType;
}
public void setTmType(String tmType) {
	this.tmType = tmType;
}
public String getWbxtlsh() {
	return wbxtlsh;
}
public void setWbxtlsh(String wbxtlsh) {
	this.wbxtlsh = wbxtlsh;
}
public String getXmbm() {
	return xmbm;
}
public void setXmbm(String xmbm) {
	this.xmbm = xmbm;
}
public String getKpxx() {
	return kpxx;
}
public void setKpxx(String kpxx) {
	this.kpxx = kpxx;
}
public String getErrCode() {
	return errCode;
}
public void setErrCode(String errCode) {
	this.errCode = errCode;
}
public String getErrString() {
	return errString;
}
public void setErrString(String errString) {
	this.errString = errString;
}
public String getJksbh() {
	return jksbh;
}
public void setJksbh(String jksbh) {
	this.jksbh = jksbh;
}
public String getDprq() {
	return dprq;
}
public void setDprq(String dprq) {
	this.dprq = dprq;
}
public String getDprxm() {
	return dprxm;
}
public void setDprxm(String dprxm) {
	this.dprxm = dprxm;
}
public String getExp1() {
	return exp1;
}
public void setExp1(String exp1) {
	this.exp1 = exp1;
}
public String getDwmc() {
	return dwmc;
}
public void setDwmc(String dwmc) {
	this.dwmc = dwmc;
}
public String getApplyname() {
	return applyname;
}
public void setApplyname(String applyname) {
	this.applyname = applyname;
}
public String getTrnType() {
	return trnType;
}
public void setTrnType(String trnType) {
	this.trnType = trnType;
}
public String getCltType() {
	return cltType;
}
public void setCltType(String cltType) {
	this.cltType = cltType;
}
public String getAreaCode() {
	return areaCode;
}
public void setAreaCode(String areaCode) {
	this.areaCode = areaCode;
}

}
