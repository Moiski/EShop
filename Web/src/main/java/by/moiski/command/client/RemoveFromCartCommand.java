package by.moiski.command.client;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.UserT;
import by.moiski.services.impl.CartServiceImpl;
import by.moiski.services.impl.UserServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.MessageManager;

public class RemoveFromCartCommand implements ActionCommand {

	private static final String REQUEST_PARAM_NAME_PRODUCTID = "productid";
	private static final String SESSION_ATTRIBUTE_USER_TYPE = "userType";

	public String execute(HttpServletRequest request) {
		String page = null;
		UserT userT = (UserT) request.getSession().getAttribute(SESSION_ATTRIBUTE_USER_TYPE);
		String login = (String) request.getSession().getAttribute("userLogin");
		Long productId = Long.parseLong(request.getParameter(REQUEST_PARAM_NAME_PRODUCTID));
		if (userT.equals(UserT.CLIENT)) {
			User user = UserServiceImpl.getInstance().getUserIdByLogin(login);
			Cart cart = CartServiceImpl.getInstance().getCartByUserIdAndProductId(user.getUserId(), productId);
			CartServiceImpl.getInstance().removeToCartProduct(cart);
			request.setAttribute("cartInfoMessage", MessageManager.getProperty("message.removeproductfromcart"));
			page = ConfigurationManager.getProperty("path.page.main");
		} else {
			request.setAttribute("errorAccessMessage", ConfigurationManager.getProperty("message.accessdenied"));
			page = ConfigurationManager.getProperty("path.page.accessdenied");
		}
		return page;
	}

}
