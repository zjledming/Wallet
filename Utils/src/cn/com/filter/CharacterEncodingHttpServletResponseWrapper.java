package cn.com.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharacterEncodingHttpServletResponseWrapper extends
		HttpServletResponseWrapper {
	private String newecoding = null;

	public CharacterEncodingHttpServletResponseWrapper(
			HttpServletResponse response, String encoding) {
		super(response);
		this.newecoding = encoding;
	}

	public void setContentType(String value) {
		super.setContentType(value);
	}
}