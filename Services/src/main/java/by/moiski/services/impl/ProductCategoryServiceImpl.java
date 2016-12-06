package by.moiski.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.moiski.dao.dbutils.HibernateUtil;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.dao.impl.ProductCategoryDaoImpl;
import by.moiski.services.IProductCategoryService;

public class ProductCategoryServiceImpl implements IProductCategoryService {
	
	private static ProductCategoryServiceImpl productCategoryServiceImpl;
	private static Logger logger = Logger.getLogger(ProductCategoryServiceImpl.class);
	private HibernateUtil hibernateUtil = HibernateUtil.getInstance();
	private ProductCategoryDaoImpl productCategoryDaoImpl = new ProductCategoryDaoImpl();
	private Session session = null;
	private Transaction transaction = null;
	
	public static ProductCategoryServiceImpl getInstance(){
		if (productCategoryServiceImpl == null) {
			productCategoryServiceImpl = new ProductCategoryServiceImpl();
		}
		return productCategoryServiceImpl;
	}

	@Override
	public List<ProductCategory> getAllProductCategories() {
		logger.info("Starting method getAllProductCategories()  " + getClass().getName());
		List <ProductCategory> categories = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			categories = productCategoryDaoImpl.getAllProductCategories();
			transaction.commit();
		} catch (Exception e) {
			logger.error("Error ending method getAllProductCategories()" + ProductCategoryServiceImpl.class);
			transaction.rollback();
		}
		return categories;
	}

}
