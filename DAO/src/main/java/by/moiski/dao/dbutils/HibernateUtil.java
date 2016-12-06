package by.moiski.dao.dbutils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private volatile static HibernateUtil util = null;
	private static Logger log = Logger.getLogger(HibernateUtil.class);
	private SessionFactory sessionFactory = null;
	private final ThreadLocal<Session> sessions = new ThreadLocal<>();

	private HibernateUtil() {
		try {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
		} catch (Throwable e) {
			log.error("Initial SessionFactory creation failed:" + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public Session getSession() {
		Session session = (Session) sessions.get();
		if (session == null) {
			session = sessionFactory.openSession();
			sessions.set(session);
		}
		return session;
	}

	public static HibernateUtil getInstance() {
		if (util == null)
			synchronized (HibernateUtil.class) {
				if (util == null)
					util = new HibernateUtil();
			}
		return util;
	}

	public ThreadLocal<Session> getSessions() {
		return sessions;
	}

}
