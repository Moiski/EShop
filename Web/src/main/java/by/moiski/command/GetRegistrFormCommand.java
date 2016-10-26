package by.moiski.command;

import javax.servlet.http.HttpServletRequest;

import by.moiski.utilits.ConfigurationManager;

public class GetRegistrFormCommand implements ActionCommand {

	@Override
	public String execute (HttpServletRequest request) {
		String page = ConfigurationManager.getProperty("path.page.registration");
		return page;
	}

}
