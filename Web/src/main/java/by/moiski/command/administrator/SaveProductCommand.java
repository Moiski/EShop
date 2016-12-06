package by.moiski.command.administrator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.services.impl.ProductServiceImpl;
import by.moiski.utilits.ConfigurationManager;
import by.moiski.utilits.MessageManager;

public class SaveProductCommand implements ActionCommand {
	
	private static Logger logger = Logger.getLogger(SaveProductCommand.class);
	
	private static final String PRODUCT_NAME = "name";
	private static final String PRODUCT_CATEGORY_ID = "categotyId";
	private static final String PRODUCT_PRODUCT_PRICE = "price";
	private static final String PRODUCT_PRODUCT_IMAGE = "image";
	private static final String PRODUCT_PRODUCT_DICRIPTION = "description";

	@Override
	public String execute(HttpServletRequest request) {
		Product product = new Product();
		product.setName(request.getParameter(PRODUCT_NAME));
		Long categoryId = Long.parseLong(request.getParameter(PRODUCT_CATEGORY_ID));
		ProductCategory productCategory = new ProductCategory();
		productCategory.setCategoryID(categoryId);
		product.setCategory(productCategory);
		Double price = Double.parseDouble(request.getParameter(PRODUCT_PRODUCT_PRICE));
		product.setPrice(price);
		product.setImage(request.getParameter(PRODUCT_PRODUCT_IMAGE));
		product.setDescription(request.getParameter(PRODUCT_PRODUCT_DICRIPTION));
		try {
			ProductServiceImpl.getInstance().addNewProduct(product);
			request.setAttribute("adminInfoMessage", MessageManager.getProperty("message.addnewproduct"));
		} catch (DataException e) {
			logger.error("Error adding to the product database" + SaveProductCommand.class, e);
			request.setAttribute("adminInfoMessage", MessageManager.getProperty("message.erroraddnewproduct"));
		}
		String page = ConfigurationManager.getProperty("path.page.admin");
		return page;
	}

}
