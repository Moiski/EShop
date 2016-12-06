package by.moiski.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.moiski.dao.enums.UserT;
import by.moiski.utilits.ConfigurationManager;

public class PageRedirectSecurityFilter implements Filter {

	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletRequest.getSession().setAttribute("userType", UserT.GUEST);
		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + ConfigurationManager.getProperty("path.page.index"));
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {	
	}

}
