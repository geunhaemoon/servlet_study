package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("*")
public class CorsFilter extends HttpFilter implements Filter {
  
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
		
		chain.doFilter(request, response);
		// chain.doFilter(A,B)
		// 두 필터의 전 과 후 
		// F > S > 리스폰스 응답
		// 응답 가기 전 후처리 
	}



}
