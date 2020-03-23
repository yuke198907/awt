package com.simpo.tracker.frame;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.simpo.tracker.web.user.entity.UserInfo;
/**
 * 过滤器
 *
 */
public class LoginFilter implements Filter {
	private FilterConfig config;
	private String mess = "";
	private String loginPath = "";
	private String loginAction = "";

	// private LoginServlet loginServlet;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
	    String request_uri = req.getRequestURI();
	    // 得到web应用程序的上下文路径
	    String ctxPath = req.getContextPath();
	    // 去除上下文路径，得到剩余部分的路径
	    String uri = request_uri.substring(ctxPath.length());
	    //System.out.println(uri + req.getParameter("method"));
	    
	    UserInfo user = (UserInfo)req.getSession().getAttribute("USERINFO");
	    
	    if(user == null){
			if(loginPath.equals(uri) || loginAction.equals(uri) || uri.startsWith("/bgui/app") || uri.startsWith("/h5/")){
				try {
					arg2.doFilter(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
		    }else{
		    	
				if((uri.endsWith(".do") || uri.endsWith(".jsp"))){
					resp.setContentType("text/html; charset=UTF-8");
					StringBuffer result = new StringBuffer();
					result.append("<script type='text/javascript'>");
					result.append("top.window.location='" + req.getContextPath() + loginPath+ "';");
					result.append("</script>");
					PrintWriter writer = resp.getWriter();
					writer.write(result.toString());
					writer.flush();
					writer.close();
		    	}else{
		    		try {
		    			arg2.doFilter(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
		    	}
		    }
			return;
		}else{
			try {
				arg2.doFilter(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
	}

	public void init(FilterConfig config) throws ServletException {
		loginPath = config.getInitParameter("loginPath");
		loginAction = config.getInitParameter("loginAction");
		this.config = config;
	}

}