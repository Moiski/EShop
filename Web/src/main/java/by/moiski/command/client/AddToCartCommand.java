package by.moiski.command.client;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;

import by.moiski.command.ActionCommand;
import by.moiski.services.impl.CartServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.MessageManager;

public class AddToCartCommand implements ActionCommand {
	
	private static Logger logger = Logger.getLogger(AddToCartCommand.class);

	@Override
	public String execute(HttpServletRequest request) {
		
		Long productId = Long.parseLong(request.getParameter("productid"));
		String login = (String) request.getSession().getAttribute("userLogin");
		try {
			CartServiceImpl.getInstance().addToCartProduct(login, productId);
			request.setAttribute("productAddedMessage", MessageManager.getProperty("message.productadd"));
		} catch (DataException e) {
			logger.error("Error adding product in cart  " + AddToCartCommand.class, e);
			request.setAttribute("productAddedMessage", MessageManager.getProperty("message.errorproductadd"));
		}
		String page = ConfigurationManager.getProperty("path.page.products");
		return page;
	}
}
