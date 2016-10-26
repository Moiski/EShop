package by.moiski.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.moiski.dao.BlackList;
import by.moiski.dao.IUserDao;
import by.moiski.dao.connectionPool.ConnectionPool;
import by.moiski.dao.entities.User;
import by.moiski.dao.entities.UserT;


public class UserDaoImpl extends DaoImpl<User> implements IUserDao {

	private Logger log = Logger.getLogger(getClass());

	public static final String SQL_QUERY_GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
	public static final String SQL_QUERY_ADD_USER = "INSERT INTO users (firstName, lastName, login, password, email, shippingAddress) VALUES (?,?,?,?,?,?)";
	public static final String SQL_QUERY_CHECK_LOGIN = "SELECT * FROM users WHERE login = ?";
	public static final String SQL_QUERY_GET_ALL_USERS = "SELECT * FROM users";
	
	private final String COLUMN_NAME_USER_ID = "userID";
	private final String COLUMN_NAME_FIRSTNAME = "firstName";
	private final String COLUMN_NAME_LASTNAME = "lastName";
	private final String COLUMN_NAME_LOGIN = "login";
	private final String COLUMN_NAME_PASSWORD = "password";
	private final String COLUMN_NAME_EMAIL = "email";
	private final String COLUMN_NAME_ADRESS = "shippingAddress";
	private final String COLUMN_NAME_ACCESSLEVEL = "userT";
	private final String COLUMN_NAME_BLACKLIST = "isInBlackList";
	private final String COLUMN_NAME_DATE = "registrDate";

	public User getUserByLoginAndPassword(String login, String password) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_LOGIN_AND_PASSWORD);
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setLogin(resultSet.getString(COLUMN_NAME_LOGIN));
				user.setPassword(resultSet.getString(COLUMN_NAME_PASSWORD));	
				user.setEmail(resultSet.getString(COLUMN_NAME_EMAIL));
				user.setFirstname(resultSet.getString(COLUMN_NAME_FIRSTNAME));	
				user.setLastname(resultSet.getString(COLUMN_NAME_LASTNAME));			
				user.setShippingAddress(resultSet.getString(COLUMN_NAME_ADRESS));		
				user.setRole(UserT.valueOf(resultSet.getString(COLUMN_NAME_ACCESSLEVEL)));
				user.setBlackList(BlackList.valueOf(resultSet.getString(COLUMN_NAME_BLACKLIST)));
			}
		} catch (SQLException e) {
			log.error("SQL Exeption: Error extracting user login and password" + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}
		return user;
	}

	public void saveUserToDataBase(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_USER);
			preparedStatement.setString(1, user.getFirstname());
			preparedStatement.setString(2, user.getFirstname());
			preparedStatement.setString(3, user.getLogin());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getEmail());
			preparedStatement.setString(6, user.getShippingAddress());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQL Exeption: Error adding user" + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}

	}

	public List<User> getAllUsers() {
		List <User> users = new ArrayList<User>();
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;	
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_GET_ALL_USERS);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setUserId(resultSet.getLong(COLUMN_NAME_USER_ID));
				user.setEmail(resultSet.getString(COLUMN_NAME_EMAIL));
				user.setFirstname(resultSet.getString(COLUMN_NAME_FIRSTNAME));
				user.setLastname(resultSet.getString(COLUMN_NAME_LASTNAME));
				user.setLogin(resultSet.getString(COLUMN_NAME_LOGIN));
				user.setPassword(resultSet.getString(COLUMN_NAME_PASSWORD));
				user.setBlackList(BlackList.valueOf(resultSet.getString(COLUMN_NAME_BLACKLIST).toUpperCase().trim()));
				user.setShippingAddress(resultSet.getString(COLUMN_NAME_ADRESS));
				user.setRole(UserT.valueOf(resultSet.getString(COLUMN_NAME_ACCESSLEVEL).toUpperCase().trim()));
				user.setRegistrDate(resultSet.getDate(COLUMN_NAME_DATE));
				users.add(user);
			}
		} catch (SQLException e) {
			log.error("SQL exception extraction of all users" + e);
		}finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error("SQL exception closing resultSet: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}
		return users;
	}
	
	public List<User> getUserByLogin (String login){
		List <User> listUsers = new ArrayList<User>();
		User user = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_CHECK_LOGIN);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setLogin(resultSet.getString(COLUMN_NAME_LOGIN));
				listUsers.add(user);
			}
		} catch (SQLException e) {
			log.error("SQL exception: this user with such password missing: " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error("SQL exception closing resultSet: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}
		return listUsers;
	}

}
