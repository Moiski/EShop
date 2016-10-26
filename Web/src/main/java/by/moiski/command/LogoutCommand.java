package by.moiski.command;

import javax.servlet.http.HttpServletRequest;

import by.moiski.utilits.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.main");
		request.getSession().invalidate();
		return page;
	}

}
