package by.moiski.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.entities.ProductCategory;
import by.moiski.services.IProductCategoryService;

@Service
@Transactional
public class ProductCategoryServiceImpl implements IProductCategoryService {
	
	private static Logger logger = Logger.getLogger(ProductCategoryServiceImpl.class);
	@Autowired
	private IProductCategoryService categoryDaoImpl;

	public List<ProductCategory> getAllProductCategories() {
		logger.info("Starting method getAllProductCategories()  " + getClass().getName());
		List <ProductCategory> categories = new ArrayList<>();
		try {
			categories = categoryDaoImpl.getAllProductCategories();
		} catch (Exception e) {
			logger.error("Error ending method getAllProductCategories()" + ProductCategoryServiceImpl.class, e);
		}
		logger.info("Ending method getAllProductCategories(): " + getClass().getName());
		return categories;
	}

}
