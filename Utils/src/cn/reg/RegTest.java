package cn.reg;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.junit.Test;

public class RegTest {

	public static void main1(String[] args) {
		// [\\s\\S]*?：匹配任意字符任意次数
		// [\\s\\S]:匹配任意字符 \s：空白字符 \S：非空白字符
		// *?：出现任意次 *：0次或者多次 ?:0或1次
		Pattern pattern = Pattern.compile("(<MSG>[\\s\\S]*?</MSG>)");
		Matcher matcher = pattern.matcher("绯闻绯闻<MSG>让绯闻绯闻	s</MSG>绯闻绯闻");
		while (matcher.find()) {
			String textarea = matcher.group(1);
			System.out.println(textarea);
		}
	}

	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 功能描述：判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是不是合法字符 c 要判断的字符
	 */
	public static boolean isLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述：判断输入的字符串是否符合Email样式.
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是Email样式返回true,否则返回false
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.length() < 1 || email.length() > 256) {
			return false;
		}
		Pattern pattern = Pattern
				.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
		return pattern.matcher(email).matches();
	}

	/**
	 * 功能描述：判断输入的字符串是否为纯汉字
	 * 
	 * @param str
	 *            传入的字符窜
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 功能描述：判断是不是合法的手机号码
	 * 
	 * @param handset
	 * @return boolean
	 */
	public static boolean isHandset(String handset) {
		try {
			String regex = "^1[\\d]{10}$";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(handset);
			return matcher.matches();

		} catch (RuntimeException e) {
			return false;
		}
	}

	/**
	 * Number转换字符
	 * 
	 * @param1: 转换数据
	 * @param2: 格式：##.##
	 */
	public static String convertNumberToString(double number, String pattern) {
		String returnValue = "";
		try {
			DecimalFormat df = new DecimalFormat(pattern);
			returnValue = df.format(number);
		} catch (Exception e) {
		}
		return returnValue;
	}

	String sqlTemplate;

	/**
	 * 从模板中提取变量
	 * 
	 * @param template
	 * @return
	 */
	public List<String> getVariables() {
		List<String> resultList = new ArrayList<String>();
		Pattern pattern = Pattern.compile("\\$\\{([^\\}]*)");//
		Matcher matcher = pattern.matcher(this.sqlTemplate);
		while (matcher.find()) {
			resultList.add(matcher.group(1));
		}
		return resultList;
	}

	/**
	 * 用”?”替换模板中的变量
	 * 
	 * @param template
	 * @return
	 */
	public String toPreparedSQL() {
		Pattern pattern = Pattern.compile("\\$\\{([^\\}]*)\\}");//
		Matcher matcher = pattern.matcher(this.sqlTemplate);
		return matcher.replaceAll("?");
	}

	public static void main(String[] args) {
		String resutl = StringFilter("'fewfew/''fewfewfewf'");
		System.out.println(resutl);

		// 测试2:匹配出所有的<IMG...>
		String temp = "<P><IMG border=0 src='http://172.16.17.232:8080/creatorepp/spmanager/eWebEditor/uploadfile/20140121143133876.jpg'>fwefewwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwewfewfwe<IMG border=0 src='http://172.16.17.232:8080/creatorepp/spmanager/eWebEditor/uploadfile/20140121152222534.jpg'>fwfewfew<IMG border=0 src='http://172.16.17.232:8080/creatorepp/spmanager/eWebEditor/uploadfile/20140121152232577.jpg'></P>";
		String regEx = ".*http://.*.jpg.*";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(temp);
		// System.out.println(m.group());

		// 查找以Java开头,任意结尾的字符串
		Pattern pattern = Pattern.compile("^Java.*");
		Matcher matcher = pattern.matcher("Java不是人");
		boolean b = matcher.matches();
		// 当条件满足时，将返回tr，否则返回false
		System.out.println(b);

		String[] arrString = temp.split("IMG");
		System.out.println(arrString.length);
		if (arrString.length > 1) {
			for (int i = 0; i < arrString.length; i++) {
				System.out.println(arrString[i]);

			}
		}

		// 截取 http或者https ... .jpg
		String s = "啊啊啊啊啊啊啊  https://192.168.0.102/relativeEventDetail.do?mode=view&id=895\nhttps://192.168.0.102/relativeEventDetail.do?mode=view&id=844";
		// p = Pattern.compile("((https|http)://\\S{1,})\\s*'\\>");
		p = Pattern.compile("((https|http)://\\S{1,})\\s*'\\>");
		m = p.matcher(temp);
		String path;
		while (m.find()) {
			System.out.println("--" + m.group(1));
			path = m.group(1) + ".jpg";
			System.out.println("--" + path);

		}

	}

	/**
	 * @Title: StringFilter
	 * @Description: 过滤单双引号
	 * @param str
	 * @return
	 * @throws PatternSyntaxException
	 * @return String
	 * @throws
	 * @author XiMing.Fu
	 */
	public static String StringFilter_(String str)
			throws PatternSyntaxException {
		String regEx = "[\'\"]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * @Title: String
	 * @Description: 验证三位一逗号
	 * @return void
	 * @throws
	 * @author XiMing.Fu
	 */
	@Test
	public void vailidation() {

		/*
		 * [0-9]{1,3}\\：1-3位数字,开头； ([0-9]{3}\\,)*：3位数字,中间
		 * 出现0次或多次；[0-9]{3}：3位有效数字；((\\.)[0-9]+)?：.+1位数字 出现1次或者多次
		 */
		Pattern pattern = Pattern
				.compile("[0-9]{1,3}\\,([0-9]{3}\\,)*[0-9]{3}((\\.)[0-9]+)?");
		String content = "10,014";
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			System.out.println(matcher.group());
		}

	}

	/**
	 * @Title: vailidation1
	 * @Description: 判断是否是 1到4 由逗号分隔的字符串
	 * @return void
	 * @throws
	 * @author XiMing.Fu
	 */
	@Test
	public void vailidation1() {

		/*
		 * ^([1-4]+[,]))*：1-4之间的数字+, 出现0次或多次 ，开头； ([1-4]+)$：1-4之间的数字 出现1次或者多次，结尾
		 */
		String reg = "^([1-4]+[,])*([1-4]+)$";
		Pattern pattern = Pattern.compile(reg);
		String str = "1,2,3,3";
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
		while (matcher.find()) {
			System.out.println(matcher.group());
		}

	}
	
	/**
	 * 从
	 * <?xml version="1.0" encoding="gbk"?>
<Context crossContext="true" path="/creatorepp" docBase="E:\workspace\hyxfs\creatorepp" reloadable="false">
	<ResourceLink name="reportsource" global="reportsource" type="javax.sql.DataSource" />
</Context>
	 * 中取"E:\workspace\hyxfs\creatorepp"
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String getByReg(String content) throws Exception{
		StringBuffer sb = new StringBuffer();
		String reg = "docBase=\".*creatorepp\"";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			sb.append(matcher.group(0));
		}
		return sb.toString();
	}
	
}
