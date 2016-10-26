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
import javax.servlet.http.HttpSession;

import by.moiski.dao.entities.UserT;
import by.moiski.utilits.ConfigurationManager;

public class ServletSecurityFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession httpSession = req.getSession();
		UserT userT = (UserT) httpSession.getAttribute("userType");
		if (userT == null){
			userT = UserT.GUEST;
			httpSession.setAttribute("userType", userT);
			res.sendRedirect(req.getContextPath() + ConfigurationManager.getProperty("path.page.main"));
			return;
		}
		chain.doFilter(request, response);	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
