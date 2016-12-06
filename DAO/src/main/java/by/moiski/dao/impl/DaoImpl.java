package by.moiski.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;

import by.moiski.dao.IDao;
import by.moiski.dao.dbutils.HibernateUtil;
import by.moiski.dao.exceptions.DaoExeptions;

public class DaoImpl<T> implements IDao<T> {

	private static Logger logger = Logger.getLogger(DaoImpl.class);
	protected HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	public void saveOrUpdate(T t) throws DaoExeptions {
		logger.info("Save or update entity: " + getClass().getName());
		try {
			Session session = hibernateUtil.getSession();
			session.saveOrUpdate(t);
			logger.info("Save or update entity: " + getClass().getName());
		} catch (HibernateException e) {
			logger.error("Error save or update entity of" + t.getClass().getName() + "class in DAO" + e);
			throw new DaoExeptions(e);
		}
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) throws DaoExeptions {
		logger.info("Get entity by id:" + id);
		T t = null;
		try {
			Session session = hibernateUtil.getSession();
			t = (T) session.get(getPersistentClass(), id);
			logger.info("Get entity: " + t);
		} catch (HibernateException e) {
			logger.error("Error getting entity of " + getPersistentClass() + "class in Dao" + e);
			throw new DaoExeptions(e);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) throws DaoExeptions {
		logger.info("Load entity by id:" + id);
		T t = null;
		try {
			Session session = hibernateUtil.getSession();
			t = (T) session.load(getPersistentClass(), id);
			logger.info("Loaded entity:" + t);
		} catch (ObjectNotFoundException e) {
			logger.error("Error getting entity of " + getPersistentClass() + "class in Dao" + e);
			throw new DaoExeptions(e);
		} catch (HibernateException e) {
			logger.error("Error getting entity of " + getPersistentClass() + "class in Dao" + e);
			throw new DaoExeptions(e);
		}
		return t;
	}

	public void delete(T t) throws DaoExeptions {
		logger.info("Delete entity: " + t);
		try {
			Session session = hibernateUtil.getSession();
			session.delete(t);
			logger.info("Deleted entity:" + t);
		} catch (HibernateException e) {
			logger.error("Error deleting entity of " + t.getClass().getName() + " class in Dao" + e);
			throw new DaoExeptions(e);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}