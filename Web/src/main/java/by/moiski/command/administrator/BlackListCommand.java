package by.moiski.command.administrator;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.MessageManager;

public class BlackListCommand implements ActionCommand {
	
	private static final String USER_ID = "userid";

	@Override
	public String execute(HttpServletRequest request) {
		String page = null;
		Long userId = Long.parseLong(request.getParameter(USER_ID));
		User user = UserServiceImpl.getInstance().getUserById(userId);
		if (user.getBlackList().equals(BlackList.N)){
			user.setBlackList(BlackList.Y);
			UserServiceImpl.getInstance().updateUserBlackListStatus(user);
			request.setAttribute("adminInfoMessage", MessageManager.getProperty("message.useraddtoblacklist"));
			page = ConfigurationManager.getProperty("path.page.admin");
			return page;
		} else {
			user.setBlackList(BlackList.N);
			UserServiceImpl.getInstance().updateUserBlackListStatus(user);
			request.setAttribute("adminInfoMessage", MessageManager.getProperty("message.userremovetislacklist"));
			page = ConfigurationManager.getProperty("path.page.admin");
			return page;
		}
	}

}
