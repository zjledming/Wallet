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
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {
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

	public void doFilter(ServletRequest req_, ServletResponse res_,
			FilterChain chain) throws IOException, ServletException {
		if (this.config == null) {
			return;
		}
		HttpServletRequest req = (HttpServletRequest) req_;
		HttpServletResponse res = (HttpServletResponse) res_;
		HttpSession session = req.getSession();
		if (session.getAttribute("username") != null) {// 登录后才能访问
			chain.doFilter(req_, res_);
		} else {
			res.sendRedirect("../failure.jsp");
		}

	}

	public void destroy() {
	}
}