package by.moiski.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.moiski.dao.entities.User;
import by.moiski.dao.entities.UserT;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.MessageManager;

public class LoginCommand implements ActionCommand {

	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String SESSION_ATTRIBUTE_USER_LOGIN = "userLogin";
	private static final String SESSION_ATTRIBUTE_USER_TYPE = "userType";	

	public String execute(HttpServletRequest request) {
		String page = null;
		String login = request.getParameter(LOGIN);
		String password = request.getParameter(PASSWORD);
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		User user = userServiceImpl.getUserByLoginPassword(login, password);
		HttpSession httpSession = request.getSession(true);
		if ((user != null) && user.getRole().equals(UserT.ADMIN) ){
			httpSession.setAttribute(SESSION_ATTRIBUTE_USER_LOGIN, user.getLogin());
			httpSession.setAttribute(SESSION_ATTRIBUTE_USER_TYPE, UserT.ADMIN);
			page = ConfigurationManager.getProperty("path.page.admin");
		} else if ((user != null) && user.getRole().equals(UserT.CLIENT)) {
			httpSession.setAttribute(SESSION_ATTRIBUTE_USER_LOGIN, user.getLogin());
			httpSession.setAttribute(SESSION_ATTRIBUTE_USER_TYPE, UserT.CLIENT);
			page = ConfigurationManager.getProperty("path.page.products");
		} else {
			request.setAttribute("errorLogin", MessageManager.getProperty("message.errorlogin"));
			httpSession.setAttribute(SESSION_ATTRIBUTE_USER_TYPE, UserT.GUEST);
			page = ConfigurationManager.getProperty("path.page.main");
		}
		return page;
	}

}
