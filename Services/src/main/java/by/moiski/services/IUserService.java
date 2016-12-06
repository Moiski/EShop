package by.moiski.services;

import by.moiski.dao.entities.User;

public interface IUserService {
	
	User getUserByLoginPassword (String login, String password);
	
	void saveUserToDataBase(User user);

}
