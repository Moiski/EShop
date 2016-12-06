package by.moiski.services.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.moiski.dao.dbutils.HibernateUtil;
import by.moiski.dao.entities.User;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.dao.impl.UserDaoImpl;
import by.moiski.services.IUserService;

public class UserServiceImpl implements IUserService {

	private static UserServiceImpl userServiceImpl;
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private HibernateUtil hibernateUtil = HibernateUtil.getInstance();
	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	private Session session = null;
	private Transaction transaction = null;

	public static UserServiceImpl getInstance() {
		if (userServiceImpl == null) {
			userServiceImpl = new UserServiceImpl();
		}
		return userServiceImpl;
	}

	public User getUserByLoginPassword(String login, String password) {
		logger.info("Starting method getUserByLoginPassword" + getClass().getName());
		User user = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			user = userDaoImpl.getUserByLoginAndPassword(login, password);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting entity user by login and password:  " + UserServiceImpl.class, e);
			transaction.rollback();
		}
		return user;
	}

	public void saveUserToDataBase(User user) {
		logger.info("Starting method saveUserToDataBase " + getClass().getName());
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			userDaoImpl.saveOrUpdate(user);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error saving user to database = " + UserServiceImpl.class, e);
			transaction.rollback();
		}
	}

	public List<User> getUserByLogin(String login) {
		logger.info("Starting method getUserByLogin  " + getClass().getName());
		List<User> users = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			users = userDaoImpl.getUserByLogin(login);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting login = " + login + " in class: " + UserServiceImpl.class, e);
			transaction.rollback();
		}
		return users;
	}

	public List<User> getALLUsers() {
		logger.info("Starting method getALLUsers  " + getClass().getName());
		List<User> users = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			users = userDaoImpl.getAllUsers();
		} catch (HibernateException e) {
			logger.error("Error geting all users  " + getClass().getName());
		}
		return users;
	}

	public User getUserById(Serializable userId) {
		logger.info("Starting method getUserById  " + getClass().getName());
		User user = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			user = userDaoImpl.get(userId);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error geting user by userId  " + getClass().getName());
			transaction.rollback();
		}
		return user;
	}
	
	public void updateUserBlackListStatus (User user){
		logger.info("Starting method updateUserBlackListStatus  " + getClass().getName());
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			userDaoImpl.saveOrUpdate(user);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error update user" + getClass().getName());
			transaction.rollback();
		}
	}
	
	public User getUserIdByLogin(String login){
		logger.info("Starting method getUserIdByLogin   " + getClass().getName());
		User user = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			user = userDaoImpl.getUserIdByLogin(login);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting userId by login  " + getClass().getName());
			transaction.rollback();
		}
		return user;
	}

}
