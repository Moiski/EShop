package by.moiski.command.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Product;
import by.moiski.services.impl.ProductServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowProductsCommand implements ActionCommand {
	
	private static final String REQUEST_CATEGORYID = "categoryid";

	@Override
	public String execute(HttpServletRequest request) {
		
		int categoryId = Integer.parseInt(request.getParameter(REQUEST_CATEGORYID));		
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		List<Product> products = productServiceImpl.getProductsByCategoryId(categoryId);		
		request.getSession().setAttribute("productslist", products);
		String page = ConfigurationManager.getProperty("path.page.products");
		return page;
	}

}
