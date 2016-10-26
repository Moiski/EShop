package by.moiski.command;

import javax.servlet.http.HttpServletRequest;

import by.moiski.dao.entities.User;
import by.moiski.dao.entities.UserT;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.FormDataValidator;
import by.moiski.utilits.MessageManager;;

public class RegistrCommand implements ActionCommand {
	
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";
	private static final String FIRSTNAME = "firstName";
	private static final String LASTNAME = "lastName";
	private static final String ADRESS = "shipAddress";

//	private Logger logger = Logger.getLogger(getClass());

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
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.saveUserToDataBase(user);
		
//		if(){
//			
//		}else{
//			
//		}
		
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

}
