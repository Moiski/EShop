package by.moiski.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.IProductCategoryDao;
import by.moiski.dao.IProductDao;
import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.services.IProductService;
import by.moiski.services.exceptions.ErrorSaveProductServiceExeption;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	@Autowired
	private IProductDao productDao;
	@Autowired
	private IProductCategoryDao categoryDao;

	public List<Product> getProductsByCategoryId(Long categoryId) {
		logger.info("Starting method getProductsByCategoryId(): " + getClass().getName());
		List<Product> products = new ArrayList<>();
		try {
			products = productDao.getProductsByCategoryId(categoryId);
		} catch (HibernateException e) {
			logger.error("Error getting products by category: " + ProductServiceImpl.class, e);
		}
		logger.info("Ending method getProductsByCategoryId(): " + getClass().getName());
		return products;
	}

	public List<Product> getAllProducts() {
		logger.info("Starting method getAllProducts(): " + getClass().getName());
		List<Product> products = new ArrayList<>();
		try {
			products = productDao.getAllProducts();
		} catch (HibernateException e) {
			logger.error("Error getting products list in  " + ProductServiceImpl.class, e);
		}
		logger.info("Ending method getAllProducts(): " + getClass().getName());
		return products;
	}

	public void addNewProduct(Product product) throws ErrorSaveProductServiceExeption {
		logger.info("Starting method addNewProduct(): " + getClass().getName());
		try {
			productDao.saveOrUpdate(product);
		} catch (DaoExeptions e) {
			logger.error("Error adding new product to database:  " + ProductServiceImpl.class, e);
			throw new ErrorSaveProductServiceExeption();
		}
		logger.info("Ending method addNewProduct(): " + getClass().getName());
	}

	public Product getProductById(Serializable productId) {
		logger.info("Starting method getProductById(): " + getClass().getName());
		Product product = null;
		try {
			product = productDao.get(productId);
		} catch (DaoExeptions e) {
			logger.error("Error getting product by Id: " + ProductServiceImpl.class, e);
		}
		logger.info("Ending method getProductById(): " + getClass().getName());
		return product;
	}

	@Transactional(readOnly = true)
	public List<Product> getProductsByClientFilter(ClientFilter clientFilter) {
		logger.info("Starting method getAllProductsByClientFilter()  " + getClass().getName());
		List<Product> resultList = new ArrayList<>();
		try {
			List<ProductCategory> categoriesList = categoryDao.getAllProductCategories();
			resultList = productDao.getProductsByClientFilter(clientFilter, categoriesList);
		} catch (HibernateException e) {
			logger.error("Error getting products list in:  " + ProductServiceImpl.class, e);
		}
		logger.info("Ending method getProductsByClientFilter(): " + getClass().getName());
		return resultList;
	}

	@Transactional(readOnly = true)
	public Long getTotalCount(ClientFilter clientFilter) {
		logger.info("Starting method getTotalCount(): " + getClass().getName());
		Long totalCount = null;
		try {
			List<ProductCategory> categoriesList = categoryDao.getAllProductCategories();
			totalCount = productDao.getTotalCount(clientFilter, categoriesList);
		} catch (HibernateException e) {
			logger.error("Error getting total product count by client filter" + ProductServiceImpl.class, e);
		}
		logger.info("Ending method getTotalCount(): " + getClass().getName());
		return totalCount;
	}

}
