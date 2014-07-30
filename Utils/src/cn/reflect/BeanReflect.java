package cn.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.sax.StringUtil;

/**
 * @author ximing.fu
 * 
 */
public class BeanReflect {
	
	/**
	 * 通过执行set()，执行sql，根据bean的字段set对应的值
	 * @param projectid
	 * @return
	 * @throws Exception
	 */
//	public LandInfoBean getLandByProjectid(String projectid) throws Exception {
//		String sql = "select * from td_hyxfc_land_info t where t.project_id = ?";
//		PreparedDBUtil pdb = new PreparedDBUtil();
//		pdb.preparedSelect(dsName, sql);
//		pdb.setString(1, projectid);
//		pdb.executePrepared();
//		LandInfoBean bean = new LandInfoBean();
//		if (pdb.size() > 0) {
//			Class c = Class
//					.forName("com.chinacreator.v2.xzsp.entity.houseintegration.LandInfoBean");// 获取该实体的元类型
//			Field[] fields = c.getDeclaredFields();
//			for (Field field : fields) {
//				String fieldName = field.getName();
//				String setter = "set"
//						+ field.getName().substring(0, 1).toUpperCase()
//						+ field.getName().substring(1);
//				// 获取方法名为setName的方法，field.getType())获取它的参数数据类型
//				Method method = c.getDeclaredMethod(setter, field.getType());
//				// 调用该方法，指定参数值
//				method.invoke(bean,
//						StringUtil.deNull(pdb.getString(0, fieldName)));
//			}
//		}
//		return bean;
//	}
	
	
	/**
	 * 执行get方法，获取实体bean中字段的值
	 * @throws Exception
	 */
	private void executeGet(Object bean) throws Exception {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			// 取值
			String getter = "get"
					+ field.getName().substring(0, 1).toUpperCase()
					+ field.getName().substring(1);
			Method method = bean.getClass().getMethod(getter,
					new Class[] {});
			Object value = method.invoke(bean, new Object[] {});
			if (value == null) {
				value = "";
			}
			if (value != null && !StringUtil.isBlank(value.toString())) {
				System.out.println(StringUtil.deNull(value.toString()));
			}
		}
	}
	

}
