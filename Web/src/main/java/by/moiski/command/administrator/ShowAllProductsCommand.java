package by.moiski.command.administrator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Product;
import by.moiski.services.impl.ProductServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowAllProductsCommand implements ActionCommand {

	@Override
	public String execute(HttpServletRequest request) {
		
		List<Product> products = ProductServiceImpl.getInstance().getAllProducts();
		request.setAttribute("productsList", products);
		request.setAttribute("adminPath", ConfigurationManager.getProperty("path.fragment.allproducts"));
		String page = ConfigurationManager.getProperty("path.page.admin");
		return page;
	}

}
