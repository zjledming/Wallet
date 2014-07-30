package cn.cc.util;

public class Constant {

	public static final String BEAN_TEMPLATE_VM_PATH = "src/vms/bean.vm";
	public static final String BEAN_DAO_TEMPLATE_VM_PATH = "src/vms/beanDao.vm";
	public static final String BEAN_DAO_IMPL_TEMPLATE_VM_PATH = "src/vms/beanDaoImpl.vm";
	public static final String BEAN_SERVICE_TEMPLATE_VM_PATH = "src/vms/beanService.vm";
	public static final String BEAN_SERVICE_IMPL_TEMPLATE_VM_PATH = "src/vms/beanServiceImpl.vm";
	public static final String BEAN_UTIL_STRINGUTIL = "src/vms/StringUtil.vm";

	public static String oracle = "jdbc:oracle:thin:@host:port:database";
	public static String mysql = "jdbc:mysql://host:port/database";
	public static String sqlserver = "jdbc:sqlserver://host:port/database";
	public static String informix = "jdbc:informix-sqli://host:port/database";

}
