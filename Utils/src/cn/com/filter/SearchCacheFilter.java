package cn.com.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title:查询条件缓存拦载器
 * </p>
 * <p>
 * Description:通过查询列表进入详细页面后，详细页面返回时，需返回到有记录查询条件及分页的查询列表
 */
public class SearchCacheFilter implements Filter {
	@Override
	public void destroy() {

	}

	/**
	 * @throws IOException
	 *             , ServletException
	 * @Description 记录查询条件及分页以及进行页面跳转
	 * @return 返回ApplicantBean对象
	 * @exception
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// request.setCharacterEncoding("gbk");
		Map<String, String[]> paramsMap = request.getParameterMap();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (paramsMap.get("searchCache") != null) {
			String params = (String) httpRequest.getSession().getAttribute(
					"searchCache");
			if (params == null || "null".equals(params)) {
				params = "";
			}
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect(httpRequest.getRequestURL() + params);
		} else {
			String params = changeMapToString(paramsMap);
			httpRequest.getSession().setAttribute("searchCache", params);
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * @description 拼接参数
	 * @param paramsMap
	 *            通过request获取参数map
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	private String changeMapToString(Map<String, String[]> paramsMap)
			throws UnsupportedEncodingException {
		StringBuffer url = new StringBuffer();
		url.append("?");
		for (Map.Entry<String, String[]> entry : paramsMap.entrySet()) {
			String key = entry.getKey();
			String[] value = entry.getValue();
			url.append(key + "=" + encodeUrl(key, Arrays.toString(value)));
			url.append("&");
		}
		return url.toString();
	}

	/**
	 * @description 字符转码
	 * @param value
	 *            需要转码的字符
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	private String encodeUrl(String key, String value)
			throws UnsupportedEncodingException {
		String result = "";
		result = value.replaceAll("\\[", "").replaceAll("\\]", "");
		// result=java.net.URLDecoder.decode(result,"gbk");
		// result=new String(result.getBytes("ISO-8859-1"));
		return result;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
