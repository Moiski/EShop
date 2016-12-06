package by.moiski.command;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.FormDataValidator;
import by.moiski.utilits.MessageManager;;

public class RegistrCommand implements ActionCommand {
	
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String FIRSTNAME = "firstName";
	private static final String LASTNAME = "lastName";
	private static final String ADRESS = "shipAddress";

	public String execute(HttpServletRequest request) {
		User user = new User();
		String page = null;
		if (!inputDataValidate(request)){
			request.setAttribute("regisrtationInfoMassege", MessageManager.getProperty("message.invaliddata"));
			return page;
		}	
		user.setLogin(request.getParameter(LOGIN));
		user.setPassword(request.getParameter(PASSWORD));
		user.setEmail(request.getParameter(EMAIL));
		user.setFirstname(request.getParameter(FIRSTNAME));
		user.setLastname(request.getParameter(LASTNAME));
		user.setShippingAddress(request.getParameter(ADRESS));
		user.setRole(UserT.CLIENT);
		user.setBlackList(BlackList.N);
		user.setRegistrDate(new Date());
		synchronized (this) {
			if (checkLoginTofreedom(user.getLogin())){
				UserServiceImpl.getInstance().saveUserToDataBase(user);
				request.setAttribute("mainInfoMessage", MessageManager.getProperty("message.registration"));
				page = ConfigurationManager.getProperty("path.page.main");
			} else {
				request.setAttribute("mainInfoMessage", MessageManager.getProperty("message.loginisnotfree"));
				page = ConfigurationManager.getProperty("path.page.registration");
			}	
		}
		return page;
	}

	private boolean inputDataValidate(HttpServletRequest request) {

		if (!FormDataValidator.loginValadate(request.getParameter(LOGIN))) {
			return false;
		}
		if (!FormDataValidator.passwordValadate(request.getParameter(PASSWORD))) {
			return false;
		}
		if (!FormDataValidator.emailValadate(request.getParameter(EMAIL))) {
			return false;
		}
		if (!FormDataValidator.firstNameValadate(request.getParameter(FIRSTNAME))) {
			return false;
		}
		if (!FormDataValidator.lastNameValadate(request.getParameter(LASTNAME))) {
			return false;
		}
		return true;
	}
	
	private boolean checkLoginTofreedom (String login){
		List<User> users = UserServiceImpl.getInstance().getUserByLogin(login);
		boolean isLoginFree = true;
		for (User user : users) {
			if (user.getLogin().equals(login)){
				isLoginFree = false;
				return isLoginFree;
			}
		}
		return isLoginFree ;
	}

}
