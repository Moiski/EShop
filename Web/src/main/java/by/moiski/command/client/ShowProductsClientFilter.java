package by.moiski.command.client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.moiski.command.ActionCommand;
import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.services.impl.ProductServiceImpl;
import by.moiski.utilits.ConfigurationManager;

public class ShowProductsClientFilter implements ActionCommand {

	private static ClientFilter clientFilter;

	private static final String REQUEST_PRODUCT_CATEGORY_ID = "categoryid";
	private static final String REQUEST_PROFUCT_SORT_BY_PRICE = "sorting";
	private static final String REQUEST_IS_FILTER_CHANGE = "isFilterChanged";
	private static final String REQUST_ITEMS_PER_PAGE = "itemsPerPage";
	private static final String REQUEST_CURRENT_PAGE = "currentPage";
	private static final String SESSON_CLIENT_FILTER = "clientfilter";
	private static final String SESSION_PAGE_NUMBER_LIST = "pageNumberList";

	private static final Integer DEFAULT_CURRENT_PAGE = 1;

	private List<Product> products;

	@Override
	public String execute(HttpServletRequest request) {
		clientFilter = createClientFiler(request);
		products = ProductServiceImpl.getInstance().getProductsByClientFilter(clientFilter);
		long totalCount = ProductServiceImpl.getInstance().getTotalCount(clientFilter);
		List<Integer> pageNumberList = createPageNumberList(totalCount, clientFilter);
		request.getSession().setAttribute("productslist", products);
		request.getSession().setAttribute(SESSION_PAGE_NUMBER_LIST, pageNumberList);
		String page = ConfigurationManager.getProperty("path.page.products");
		return page;
	}

	public ClientFilter createClientFiler(HttpServletRequest request) {
		clientFilter = (ClientFilter) request.getSession().getAttribute(SESSON_CLIENT_FILTER);
		String categoryId = request.getParameter(REQUEST_PRODUCT_CATEGORY_ID);
		String sort = request.getParameter(REQUEST_PROFUCT_SORT_BY_PRICE);
		String itemsPerPage = request.getParameter(REQUST_ITEMS_PER_PAGE);
		if (clientFilter == null) {
			clientFilter = new ClientFilter();
			clientFilter.setCategoryId(categoryId);
			clientFilter.setSorting(sort);
			clientFilter.setItemsPerPage(Integer.parseInt(itemsPerPage));
		} else {
			changeClientFilter(request);
		}
		request.getSession().setAttribute(SESSON_CLIENT_FILTER, clientFilter);
		return clientFilter;
	}

	public void changeClientFilter(HttpServletRequest request) {
		String categoryId = request.getParameter(REQUEST_PRODUCT_CATEGORY_ID);
		String sort = request.getParameter(REQUEST_PROFUCT_SORT_BY_PRICE);
		String isFilterChange = request.getParameter(REQUEST_IS_FILTER_CHANGE);
		String itemsPerPage = request.getParameter(REQUST_ITEMS_PER_PAGE);
		setCurrentPage(request);
		if (isFilterChange != null) {
			clientFilter.setCategoryId(categoryId);
			clientFilter.setSorting(sort);
			clientFilter.setItemsPerPage(Integer.parseInt(itemsPerPage));
			clientFilter.setCurrentPage(DEFAULT_CURRENT_PAGE);
		}
	}

	public void setCurrentPage(HttpServletRequest request) {
		String currentPage = request.getParameter(REQUEST_CURRENT_PAGE);	
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

}
