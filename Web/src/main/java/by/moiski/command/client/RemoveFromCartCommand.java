package by.moiski.command.client;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.UserT;
import by.moiski.services.impl.CartServiceImpl;
import by.moiski.utilits.ConfigurationManager;


public class RemoveFromCartCommand implements ActionCommand {
	
	private static final String REQUEST_PARAM_NAME_PRODUCTID = "productid";
	private static final String SESSION_ATTRIBUTE_USER_TYPE = "userType";	
	
	
	public String execute(HttpServletRequest request) {
		String page = null;
		UserT userT = (UserT) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER_TYPE);
		String login = (String) request.getSession().getAttribute("userLogin");
		int productId = Integer.parseInt(request.getParameter(REQUEST_PARAM_NAME_PRODUCTID));	
		if (userT.equals(UserT.CLIENT)){
			CartServiceImpl cartServiceImpl = new CartServiceImpl();
			Cart cart = cartServiceImpl.getCartId(login, productId);
			cartServiceImpl.removeProductFromCart(cart);
			page = ConfigurationManager.getProperty("path.page.products");
		} else {
			request.setAttribute("errorAccessMessage", ConfigurationManager.getProperty("message.accessdenied"));
			page = ConfigurationManager.getProperty("path.page.accessdenied");
		}	
		return page;
	}

}
