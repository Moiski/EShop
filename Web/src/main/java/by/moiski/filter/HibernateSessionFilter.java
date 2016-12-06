package by.moiski.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import by.moiski.dao.dbutils.HibernateUtil;

public class HibernateSessionFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(HibernateSessionFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
			throws IOException, ServletException {
		filter.doFilter(request, response);
		logger.info("Begin to close the session" +  getClass().getName());
		if (HibernateUtil.getInstance().getSessions().get() != null) {
			try {
				HibernateUtil.getInstance().getSession().flush();
				HibernateUtil.getInstance().getSession().clear();
				HibernateUtil.getInstance().getSession().close();	
			} catch (HibernateException e) {
				logger.info("Error closing of hibernate session", e);
			}
			HibernateUtil.getInstance().getSessions().set(null);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
