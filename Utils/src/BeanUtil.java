import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @ClassName: BeanUtil
 * @Description: Bean操作工具类
 * @author XiMing.Fu
 * @date 2014-3-10 上午11:44:50
 * 
 */
public class BeanUtil {

	private static final Log logger = LogFactory.getLog(BeanUtil.class
			.getName());

	public static Object reBuild(Object object2, Class clazz) {
		Object obj1;
		try {
			obj1 = clazz.newInstance();
			copy(obj1, object2);
			return obj1;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: copy
	 * @Description: 将object2中属性值拷贝给object1,如果object1的属性非空,则不拷贝。
	 * @param object1
	 * @param object2
	 * @throws Exception
	 * @return void
	 * @throws
	 * @author XiMing.Fu
	 */
	public static void copy(Object object1, Object object2) throws Exception {
		Class myclass = object1.getClass();
		Field[] Fields = myclass.getDeclaredFields();
		for (int i = 0; i < Fields.length; i++) {
			String property = Fields[i].getName();
			property = property.substring(0, 1).toUpperCase()
					+ property.substring(1);
			String getMethodName = "get" + property;
			try {
				Method checkMethod = object1.getClass().getMethod(
						getMethodName, new Class[0]);
				Object checkValue = checkMethod
						.invoke(object1, new Object[] {});
				if (checkValue != null)
					continue;
				Method getMethod = object2.getClass().getMethod(getMethodName,
						new Class[0]);
				Object value = getMethod.invoke(object2, new Object[] {});
				String setMethodName = "set" + property;
				Method setMethod = object1.getClass().getMethod(setMethodName,
						new Class[] { getMethod.getReturnType() });
				setMethod.invoke(object1, new Object[] { value });
				// FIXME yiping.huang
			} catch (NoSuchMethodException e1) {
				// 不记录异常
			} catch (Exception e) {
				logger.info(e);
			}
		}
	}

	/** 
	 * @Title: copyAndOverlay 
	 * @Description: 将object2中属性值拷贝给object1,无视object1的属性是否已有值。
	 * @param object1
	 * @param object2 
	 * @return void 
	 * @throws 
	 * @author XiMing.Fu
	 */
	public static void copyAndOverlay(Object object1, Object object2) {
		Class myclass = object1.getClass();
		Field[] Fields = myclass.getDeclaredFields();
		for (int i = 0; i < Fields.length; i++) {
			String property = Fields[i].getName();
			property = property.substring(0, 1).toUpperCase()
					+ property.substring(1);
			String getMethodName = "get" + property;
			try {
				Method getMethod = object2.getClass().getMethod(getMethodName,
						new Class[0]);
				Object value = getMethod.invoke(object2, new Object[] {});
				String setMethodName = "set" + property;
				Method setMethod = object1.getClass().getMethod(setMethodName,
						new Class[] { getMethod.getReturnType() });
				setMethod.invoke(object1, new Object[] { value });
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
	}

	// 打印 Bean属性值
	public static void printPropertyValue(Object object1)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class myclass = object1.getClass();
		Field[] Fields = myclass.getDeclaredFields();
		for (int i = 0; i < Fields.length; i++) {
			String property = Fields[i].getName();
			property = property.substring(0, 1).toUpperCase()
					+ property.substring(1);
			String getMethodName = "get" + property;
			try {
				Method getMethod = object1.getClass().getMethod(getMethodName,
						new Class[0]);
				Object value = getMethod.invoke(object1, new Object[] {});
			} catch (Exception e) {

			}
		}
	}

}
