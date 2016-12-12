package by.moiski.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.services.IProductService;
import by.moiski.services.exceptions.ErrorSaveProductServiceExeption;
import by.moiski.utilits.ConfigurationManager;

@Controller
public class ProductController {

	private static Logger logger = Logger.getLogger(ProductController.class);

	private static final String SESSION_ATTRIBUTE_ADMIN_PATH = "adminPath";
	private static final String SESSON_ATTRIBUTE_CLIENT_FILTER = "clientfilter";
	private static final String SESSION_ATTRIBUTE_PAGE_NUMBER_LIST = "pageNumberList";
	private static final String REQUEST_PARAMETR_CAYEGORY_ID = "categoryId";
	private static final String REQUEST_PARAMETR_PROFUCT_SORT_BY_PRICE = "sorting";
	private static final String REQUEST_PARAMETR_ITEMS_PER_PAGE = "itemsPerPage";
	private static final String REQUEST_PARAMETR_IS_FILTER_CHANGE = "isFilterChanged";
	private static final String REQUEST_PARAMETR_CURRENT_PAGE = "currentPage";
	private static final String ADMIN_INFO_MESSAGE = "adminInfoMessage";
	private static final Integer DEFAULT_CURRENT_PAGE = 1;

	@Autowired
	private IProductService productService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(path = "/products/add", method = RequestMethod.GET)
	public String getAddProductForm(Model model) {
		model.addAttribute(SESSION_ATTRIBUTE_ADMIN_PATH, ConfigurationManager.getProperty("path.fragment.add.product"));
		return "admin";
	}

	@RequestMapping(path = "/products/add/new/product", method = RequestMethod.POST)
	public String addProduct(Product product, HttpServletRequest request, Locale locale) {
		ProductCategory productCategory = new ProductCategory();
		Long categoryId = Long.parseLong(request.getParameter(REQUEST_PARAMETR_CAYEGORY_ID));
		productCategory.setCategoryID(categoryId);
		product.setCategory(productCategory);
		try {
			productService.addNewProduct(product);
			request.setAttribute(ADMIN_INFO_MESSAGE, getMessage("message.add.new.product", locale));
		} catch (ErrorSaveProductServiceExeption e) {
			logger.info("Error saving user to database:" + getClass().getName());
			request.setAttribute(ADMIN_INFO_MESSAGE, getMessage("message.error.add.new.product", locale));
		}
		return "admin";
	}

	@RequestMapping(path = "/products/all", method = RequestMethod.GET)
	public String allProducts(Model model) {
		List<Product> products = productService.getAllProducts();
		model.addAttribute("productsList", products);
		model.addAttribute(SESSION_ATTRIBUTE_ADMIN_PATH,
				ConfigurationManager.getProperty("path.fragment.all.products"));
		return "admin";
	}

	@RequestMapping(path = "/products/clientfilter", method = RequestMethod.GET)
	public String addProductByClientFilter(HttpServletRequest request) {
		ClientFilter clientFilter = createClientFiler(request);
		List<Product> products = productService.getProductsByClientFilter(clientFilter);
		long totalCount = productService.getTotalCount(clientFilter);
		List<Integer> pageNumberList = createPageNumberList(totalCount, clientFilter);
		request.getSession().setAttribute("productslist", products);
		request.getSession().setAttribute(SESSION_ATTRIBUTE_PAGE_NUMBER_LIST, pageNumberList);
		return "products";
	}

	public ClientFilter createClientFiler(HttpServletRequest request) {
		ClientFilter clientFilter = (ClientFilter) request.getSession().getAttribute(SESSON_ATTRIBUTE_CLIENT_FILTER);
		String categoryId = request.getParameter(REQUEST_PARAMETR_CAYEGORY_ID);
		String sort = request.getParameter(REQUEST_PARAMETR_PROFUCT_SORT_BY_PRICE);
		String itemsPerPage = request.getParameter(REQUEST_PARAMETR_ITEMS_PER_PAGE);
		if (clientFilter == null) {
			clientFilter = new ClientFilter();
			clientFilter.setCategoryId(categoryId);
			clientFilter.setSorting(sort);
			clientFilter.setItemsPerPage(Integer.parseInt(itemsPerPage));
		} else {
			changeClientFilter(request, clientFilter);
		}
		request.getSession().setAttribute(SESSON_ATTRIBUTE_CLIENT_FILTER, clientFilter);
		return clientFilter;
	}

	public void changeClientFilter(HttpServletRequest request, ClientFilter clientFilter) {
		String categoryId = request.getParameter(REQUEST_PARAMETR_CAYEGORY_ID);
		String sort = request.getParameter(REQUEST_PARAMETR_PROFUCT_SORT_BY_PRICE);
		String isFilterChange = request.getParameter(REQUEST_PARAMETR_IS_FILTER_CHANGE);
		String itemsPerPage = request.getParameter(REQUEST_PARAMETR_ITEMS_PER_PAGE);
		setCurrentPage(request, clientFilter);
		if (isFilterChange != null) {
			clientFilter.setCategoryId(categoryId);
			clientFilter.setSorting(sort);
			clientFilter.setItemsPerPage(Integer.parseInt(itemsPerPage));
			clientFilter.setCurrentPage(DEFAULT_CURRENT_PAGE);
		}
	}

	public void setCurrentPage(HttpServletRequest request, ClientFilter clientFilter) {
		String currentPage = request.getParameter(REQUEST_PARAMETR_CURRENT_PAGE);
		if (currentPage != null) {
			clientFilter.setCurrentPage(Integer.parseInt(currentPage));
		}
	}

	private List<Integer> createPageNumberList(Long totalCount, ClientFilter clientFilter) {
		List<Integer> list = new ArrayList<>();
		int length = (int) (totalCount / clientFilter.getItemsPerPage());
		if (totalCount % clientFilter.getItemsPerPage() != 0) {
			length = length + 1;
		}
		for (int i = 0; i < length; i++) {
			list.add(i + 1);
		}
		return list;
	}
	
	private String getMessage(String key, Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}

}
