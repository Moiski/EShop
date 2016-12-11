package by.moiski.services;

import java.io.Serializable;
import java.util.List;

import by.moiski.dao.entities.User;
import by.moiski.services.exceptions.ErrorDataAccessServiceExeption;
import by.moiski.services.exceptions.ErrorSavingUserServiceExeption;
import by.moiski.services.exceptions.LoginAlreadyBusyServiceExeption;

public interface IUserService {
	
	User getUserByLoginPassword(String login, String password) throws ErrorDataAccessServiceExeption;
	
	void saveUserToDataBase(User user) throws LoginAlreadyBusyServiceExeption, ErrorSavingUserServiceExeption;
	
	List<User> getUserByLogin(String login);
	
	List<User> getALLUsers();
	
	User getUserById(Serializable userId);
	
	void updateUserBlackListStatus (User user);
	
	User getUserIdByLogin(String login);

}
