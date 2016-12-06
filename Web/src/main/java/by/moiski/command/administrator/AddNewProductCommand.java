package by.moiski.command.administrator;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.utilits.ConfigurationManager;

public class AddNewProductCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		request.setAttribute("adminPath", ConfigurationManager.getProperty("path.page.addproduct"));
		String page = ConfigurationManager.getProperty("path.page.admin");
		return page;
	}

}
