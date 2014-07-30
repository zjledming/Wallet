package cn.com.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName: CharacterEncodingHttpServletRequestWrapper
 * @Description: 
 *               拓展类，在该类中重写getParameter，getParameterValues等方法同意解决乱码问题，还可以在这里去掉前后空格等操作
 * @author XiMing.Fu
 * @date 2014-3-24 上午11:43:40
 * 
 */
public class CharacterEncodingHttpServletRequestWrapper extends
		HttpServletRequestWrapper {
	private String newecoding = null;
	private String oldEncoding = null;
	private static final String system_encoding = System
			.getProperty("sun.jnu.encoding");

	public CharacterEncodingHttpServletRequestWrapper(
			HttpServletRequest request, String encoding) {
		super(request);
		this.newecoding = (encoding != null ? encoding : system_encoding);
		this.oldEncoding = request.getCharacterEncoding();
	}

	public String getParameter(String name) {
		String value_ = super.getParameter(name);
		if (value_ == null)
			return null;
		try {
			if (((this.oldEncoding == null) || (isIOS88591(this.oldEncoding)))
					&& (value_ != null)) {
				return new String(value_.getBytes("iso-8859-1"),
						this.newecoding);
			}

			return value_;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value_;
	}

	public String[] getParameterValues(String name) {
		String[] tempArray = super.getParameterValues(name);
		try {
			if (tempArray == null) {
				return tempArray;
			}

			String[] clone = new String[tempArray.length];
			for (int i = 0; i < tempArray.length; i++) {
				if (((this.oldEncoding == null) || (isIOS88591(this.oldEncoding)))
						&& (tempArray[i] != null)) {
					clone[i] = new String(tempArray[i].getBytes("iso-8859-1"),
							this.newecoding);
				}
			}
			return clone;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempArray;
	}

	private boolean isIOS88591(String endcoding) {
		endcoding = endcoding.toLowerCase();
		return (endcoding.startsWith("iso"))
				&& (endcoding.indexOf("8859") != -1)
				&& (endcoding.endsWith("1"));
	}
}