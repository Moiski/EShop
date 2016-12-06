package by.moiski.command.client;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Product;
import by.moiski.services.impl.CartServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowCartCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		String login = (String) request.getSession().getAttribute("userLogin");
		List <Product> cartlist = CartServiceImpl.getInstance().getCartUser(login);
		request.getSession().setAttribute("cartlistuser", cartlist);
		String page = ConfigurationManager.getProperty("path.page.cart");
		return page;
	}

}
