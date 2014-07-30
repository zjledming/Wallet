/**
 * 
 */
package cn.cc.install;

/**
 * @author ximing.fu<br>
 * @date 2014-6-29
 */
public class BeanUtilsTest {
	public static void main(String[] args) throws Exception {
		BeanUtilsTest beanUtilTest = new BeanUtilsTest();
//		beanUtilTest.beanTool("d:\\Documents\\git\\CodeCreator\\src\\cn\\cc",
//				"wifi");
		//C:\Users\android\git\CodeCreator\src
		beanUtilTest.beanTool("C:\\Users\\android\\git\\CodeCreator\\src\\cn\\cc","wife");
	}

	/**
	 * 根据bean生成相应的文件
	 * 
	 * @param beanUtils
	 * @param c
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void beanTool(String descPath, String cName) throws Exception {
		BeanUtils beanUtils = new BeanUtils();
		cName = cName.toLowerCase();
//		beanUtils.init(descPath, cName,"表说明","seq_user");
		beanUtils.createBean(descPath, cName);
		beanUtils.createBeanDao(descPath, cName);
		beanUtils.createBeanDaoImpl(descPath, cName);
		beanUtils.createBeanService(descPath, cName);
		beanUtils.createBeanServiceImpl(descPath, cName);
	}
}
