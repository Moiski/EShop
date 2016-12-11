package by.moiski.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.IUserDao;
import by.moiski.dao.entities.User;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.services.IUserService;
import by.moiski.services.exceptions.ErrorDataAccessServiceExeption;
import by.moiski.services.exceptions.ErrorSavingUserServiceExeption;
import by.moiski.services.exceptions.LoginAlreadyBusyServiceExeption;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserDao userDao;
	@Autowired
	private MessageSource messageSource;

	public User getUserByLoginPassword(String login, String password) throws ErrorDataAccessServiceExeption {
		logger.info("Starting method getUserByLoginPassword(): " + getClass().getName());
		User user = null;
		try {
			user = userDao.getUserByLoginAndPassword(login, password);
		} catch (HibernateException e) {
			logger.error("Error getting entity user by login and password:  " + UserServiceImpl.class, e);
			throw new ErrorDataAccessServiceExeption(messageSource.getMessage("error.user.data.access", null, "This user has with the login and password can not be found.", Locale.getDefault()));
		}
		logger.info("Ending method getUserByLoginPassword(): " + getClass().getName());
		return user;
	}

	public void saveUserToDataBase(User user) throws LoginAlreadyBusyServiceExeption, ErrorSavingUserServiceExeption {
		logger.info("Starting method saveUserToDataBase(): " + getClass().getName());
		try {
			String login = user.getLogin();
			List <User> users = userDao.getUserByLogin(login);
			if (users.isEmpty()){
				userDao.saveOrUpdate(user);
			} else {
				throw new LoginAlreadyBusyServiceExeption();
			}
		} catch (DaoExeptions e) {
			logger.error("Error saving user to database: " + UserServiceImpl.class, e);
			throw new ErrorSavingUserServiceExeption();
		}
		logger.info("Ending method saveUserToDataBase(): " + getClass().getName());
	}

	public List<User> getUserByLogin(String login) {
		logger.info("Starting method getUserByLogin(): " + getClass().getName());
		List<User> users = new ArrayList<>();
		try {
			users = userDao.getUserByLogin(login);
		} catch (HibernateException e) {
			logger.error("Error getting login = " + login + " in class: " + UserServiceImpl.class, e);
		}
		logger.info("Ending method  getUserByLogin(): " + getClass().getName());
		return users;
	}

	public List<User> getALLUsers() {
		logger.info("Starting method getALLUsers(): " + getClass().getName());
		List<User> users = new ArrayList<>();
		try {
			users = userDao.getAllUsers();
		} catch (HibernateException e) {
			logger.error("Error geting all users: " + UserServiceImpl.class, e);
		}
		logger.info("Ending method getALLUsers(): " + getClass().getName());
		return users;
	}

	public User getUserById(Serializable userId) {
		logger.info("Starting method getUserById(): " + getClass().getName());
		User user = null;
		try {
			user = userDao.get(userId);
		} catch (DaoExeptions e) {
			logger.error("Error geting user by userId: " + UserServiceImpl.class, e);
		}
		logger.info("Ending method getUserById(): " + getClass().getName());
		return user;
	}
	
	public void updateUserBlackListStatus (User user){
		logger.info("Starting method updateUserBlackListStatus(): " + getClass().getName());
		try {
			userDao.saveOrUpdate(user);
		} catch (DaoExeptions e) {
			logger.error("Error update user: " + UserServiceImpl.class, e);
		}
		logger.info("Ending method updateUserBlackListStatus(): " + getClass().getName());
	}
	
	public User getUserIdByLogin(String login){
		logger.info("Starting method getUserIdByLogin(): " + getClass().getName());
		User user = null;
		try {
			user = userDao.getUserIdByLogin(login);
		} catch (HibernateException e) {
			logger.error("Error getting userId by login: " + UserServiceImpl.class, e);
		}
		logger.info("Ending method getUserIdByLogin(): " + getClass().getName());
		return user;
	}

}
