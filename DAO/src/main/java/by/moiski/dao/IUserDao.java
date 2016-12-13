package by.moiski.dao;

import java.util.List;

import by.moiski.dao.entities.User;

public interface IUserDao extends IDao<User> {

	User getUserByLoginAndPassword(String login, String password);

	List<User> getAllUsers();
	
	User getUserIdByLogin(String login);
	
	List<User> getUserByLogin(String login);
	
}
