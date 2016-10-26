package by.moiski.command.administrator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.User;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowAllUsersCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		List <User> users = userServiceImpl.getALLUsers();	
		request.setAttribute("usersList", users);
		request.setAttribute("adminPath", ConfigurationManager.getProperty("path.fragment.allusers"));
		String page = ConfigurationManager.getProperty("path.page.admin");
		return page;
	}
	
}
