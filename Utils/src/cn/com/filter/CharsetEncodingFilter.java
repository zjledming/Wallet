package cn.com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharsetEncodingFilter implements Filter {
	private FilterConfig config = null;
	private String RequestEncoding = null;
	private String ResponseEncoding = null;
	private String mode = "0";

	/*
	 * 初始化参数：取配置信息 (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		this.config = arg0;
		this.RequestEncoding = this.config.getInitParameter("RequestEncoding");
		this.ResponseEncoding = this.config
				.getInitParameter("ResponseEncoding");
		this.mode = this.config.getInitParameter("mode");
		if (this.mode == null)
			this.mode = "0";
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		if (this.config == null) {
			return;
		}

		// 转化成实际类型
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 请求参数
		String filterEnabled = request.getParameter("filterEnabled");

		// 设置header
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1L);
		response.setDateHeader("max-age", 0L);

		if ((filterEnabled != null)
				&& (!filterEnabled.trim().equalsIgnoreCase("true"))) {
			// 通过过滤器
			fc.doFilter(request, response);
			return;
		}

		if (this.mode.equals("0")) {
			CharacterEncodingHttpServletRequestWrapper mrequestw = new CharacterEncodingHttpServletRequestWrapper(
					request, this.RequestEncoding);

			CharacterEncodingHttpServletResponseWrapper wresponsew = new CharacterEncodingHttpServletResponseWrapper(
					response, this.ResponseEncoding);

			fc.doFilter(mrequestw, wresponsew);
		} else if (this.mode.equals("1")) {
			// 必须写在第一次使用request.getParameter()之前，这样才能保证参数是按照已经设置的字符编码来获取。
			request.setCharacterEncoding(this.RequestEncoding);
			// 必须写在PrintWriter out = request.getWriter()之前，这样才能保证out按照已经设置的字符编码来进行字符输出。
			response.setCharacterEncoding(this.RequestEncoding);
			fc.doFilter(request, response);
		} else {
			CharacterEncodingHttpServletRequestWrapper mrequestw = new CharacterEncodingHttpServletRequestWrapper(
					request, this.RequestEncoding);

			CharacterEncodingHttpServletResponseWrapper wresponsew = new CharacterEncodingHttpServletResponseWrapper(
					response, this.ResponseEncoding);

			fc.doFilter(mrequestw, wresponsew);
		}
	}

	public void destroy() {
	}
}