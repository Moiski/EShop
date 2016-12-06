package by.moiski.services.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import by.moiski.dao.dbutils.HibernateUtil;
import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.dao.impl.ProductDaoImpl;
import by.moiski.services.IProductService;

public class ProductServiceImpl implements IProductService {

	private static ProductServiceImpl productServiceImpl;
	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	private HibernateUtil hibernateUtil = HibernateUtil.getInstance();
	private ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	private Session session = null;
	private Transaction transaction = null;

	private static final String ALL_CATEGORIES = "0";
	private static final String SORTING_NO_SORTING = "0";
	private static final String SORTING_LOW_TO_HIGH = "1";

	public static ProductServiceImpl getInstance() {
		if (productServiceImpl == null) {
			productServiceImpl = new ProductServiceImpl();
		}
		return productServiceImpl;
	}

	public List<Product> getProductsByCategoryId(Long categoryId) {
		logger.info("Starting method getProductsByCategoryId(long categoryId" + getClass().getName());
		List<Product> products = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			products = productDaoImpl.getProductsByCategoryId(categoryId);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error(
					"Error getting products by category = " + categoryId + " in class: " + ProductServiceImpl.class, e);
			transaction.rollback();
		}
		logger.info("Ending method getProductsByCategoryId(long categoryId) in " + ProductServiceImpl.class);
		return products;
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("Starting method getAllProducts  " + getClass().getName());
		List<Product> products = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			products = productDaoImpl.getAllProducts();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting products list in  " + ProductServiceImpl.class, e);
			transaction.rollback();
		}
		logger.info("Error ending method getAllProducts() in " + ProductServiceImpl.class);
		return products;
	}

	public void addNewProduct(Product product) {
		logger.info("Starting method addNewProduct  " + getClass().getName());
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			productDaoImpl.saveOrUpdate(product);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error adding new product to database  " + ProductServiceImpl.class);
			transaction.rollback();
		}
	}

	public Product getProductById(Serializable productId) {
		logger.info("Starting method getProductById()  " + getClass().getName());
		Product product = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			product = productDaoImpl.get(productId);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error getting product by Id  " + getClass().getName());
			transaction.rollback();
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByClientFilter(ClientFilter clientFilter) {
		logger.info("Starting method getAllProductsByClientFilter()  " + getClass().getName());
		List<Product> resultList = null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Product.class);
			createCriteriaByClientFilter(clientFilter, criteria);
			int startPosition = (clientFilter.getCurrentPage()-1)*clientFilter.getItemsPerPage();
			criteria.setFirstResult(startPosition);
			int maxResult = clientFilter.getItemsPerPage();
			criteria.setMaxResults(maxResult);
			transaction = session.beginTransaction();
			criteria.setCacheable(true);
			resultList = criteria.list();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting products list in  " + ProductServiceImpl.class, e);
			transaction.rollback();
		}
		return resultList;
	}

	public void createCriteriaByClientFilter(ClientFilter clientFilter, Criteria criteria) {
		Disjunction disjunction = Restrictions.disjunction();
		String categotyId = clientFilter.getCategoryId();
		List<ProductCategory> categories = ProductCategoryServiceImpl.getInstance().getAllProductCategories();
		if (categotyId.equals(ALL_CATEGORIES)) {
			for (ProductCategory productCategory : categories) {
				Long categoryId = productCategory.getCategoryID();
				Criterion criterion = Restrictions.eq("category.categoryID", categoryId);
				disjunction.add(criterion);
				criteria.add(Restrictions.or(disjunction));
			}
		} else {
			criteria.add(Restrictions.eq("category.categoryID", Long.parseLong(clientFilter.getCategoryId())));
		}
		if (clientFilter.getSorting().equals(SORTING_NO_SORTING)) {
			criteria.addOrder(Order.asc("category"));
		} else {
			if (clientFilter.getSorting().equals(SORTING_LOW_TO_HIGH)) {
				criteria.addOrder(Order.asc("price"));
			} else {
				criteria.addOrder(Order.desc("price"));
			}
		}
	}

	public Long getTotalCount(ClientFilter clientFilter) {
		Long totalCount = null;
		try {
			session = hibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Product.class);
			createCriteriaByClientFilter(clientFilter, criteria);
			criteria.setProjection(Projections.rowCount());
			transaction = session.beginTransaction();
			criteria.setCacheable(true);
			totalCount = (Long) criteria.uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting total product count by client filter", e);
			transaction.rollback();
		}
		return totalCount;
	}

}
