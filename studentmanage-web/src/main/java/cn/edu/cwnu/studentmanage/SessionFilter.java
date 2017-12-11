package cn.edu.cwnu.studentmanage;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {
	
	//不需要过滤的网络地址
	private static final String[] notFilter = new String[] { "index.html", "/check","/js","/img","/images","/css"};
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	         HttpServletResponse response, FilterChain filterChain)
	         throws ServletException, IOException {
		String uri = request.getRequestURI();
	 
		if (needFilter(uri)) {
			Object obj = request.getSession().getAttribute("username");
			if (null != obj) {
				filterChain.doFilter(request, response);
			} else {
				response.sendRedirect("/index.html");
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}
	
	private boolean needFilter(String uri) {
		if (null != uri) {
			for (String s : notFilter) {
			     if (uri.indexOf(s) >= 0) {
			    	 return false;
			     }
			}
			
			return true;
		}
		
		return false;
	}
}
