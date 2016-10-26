package by.moiski.command.client;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.services.impl.CartServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class AddToCartCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		Integer productId = Integer.parseInt(request.getParameter("productid"));
		Integer categoryId = Integer.parseInt(request.getParameter("categoryid"));
		String login = (String) request.getSession().getAttribute("userLogin");
		CartServiceImpl cartServiceImpl = new CartServiceImpl();
		cartServiceImpl.addToCartProduct(login, productId, categoryId);
		String page = ConfigurationManager.getProperty("path.page.products");
//		request.setAttribute("productAddedMessage", MessageManager.getProperty("message.productadd"));
		return page;
	}

}
