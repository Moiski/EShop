package by.moiski.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.moiski.dao.IUserDao;
import by.moiski.dao.entities.User;

@Repository
public class UserDaoImpl extends DaoImpl<User> implements IUserDao {

	private Logger logger = Logger.getLogger(UserDaoImpl.class);

	public User getUserByLoginAndPassword(String login, String password) {
		logger.info("Get user by login and password" + getClass().getName());
		String hql = "SELECT u FROM User u WHERE u.login=:userLogin AND u.password=:userPassword";
		Query query = getSession().createQuery(hql);
		query.setParameter("userLogin", login);
		query.setParameter("userPassword", password);
		query.setCacheable(true);
		User result = (User) query.uniqueResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		logger.info("Get all users  " + getClass().getName());
		String sql = "FROM User u";
		Query query = getSession().createQuery(sql);
		query.setCacheable(true);
		List<User> results = query.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserByLogin(String login) {
		logger.info("Get user bu login  " + getClass().getName());
		String hql = "FROM User u WHERE u.login=:loginUser";
		Query query = getSession().createQuery(hql);
		query.setParameter("loginUser", login);
		query.setCacheable(true);
		List <User> results = query.list();
		return results;
	}
	
	public User getUserIdByLogin(String login){
		logger.info("Get user by login" + getClass().getName());
		String hql = "SELECT u FROM User u WHERE u.login=:userLogin";
		Query query = getSession().createQuery(hql);
		query.setParameter("userLogin", login);
		query.setCacheable(true);
		User result = (User) query.uniqueResult();
		return result;
	}

}
