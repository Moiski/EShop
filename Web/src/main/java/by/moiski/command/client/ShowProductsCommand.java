package by.moiski.command.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Product;
import by.moiski.services.impl.ProductServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowProductsCommand implements ActionCommand {
	
	@Override
	public String execute(HttpServletRequest request) {
		List<Product> products = ProductServiceImpl.getInstance().getAllProducts();
		request.getSession().setAttribute("productslist", products);
		String page = ConfigurationManager.getProperty("path.page.products");
		return page;
	}
	
}
