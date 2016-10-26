package by.moiski.services.impl;

import java.util.List;

import by.moiski.dao.entities.User;
import by.moiski.dao.impl.UserDaoImpl;
import by.moiski.services.IUserService;

public class UserServiceImpl implements IUserService {

	public User getUserByLoginPassword(String login, String password) {
		
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			User user = userDaoImpl.getUserByLoginAndPassword(login, password);
		return user;
	}

	public void saveUserToDataBase(User user) {
		
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<User> users = userDaoImpl.getUserByLogin(user.getLogin());
		if (users.isEmpty()){
			userDaoImpl.saveUserToDataBase(user);
		} else{
		}
	}
	
	public List<User> getALLUsers(){
		UserDaoImpl userDaoImpl = new UserDaoImpl();
		List<User> users = userDaoImpl.getAllUsers();
		return users;
	}
	
}
