package by.moiski.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import by.moiski.dao.IProductDao;
import by.moiski.dao.entities.Product;

public class ProductDaoImpl extends DaoImpl<Product> implements IProductDao {
	
	private Logger logger = Logger.getLogger(ProductDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategoryId(Long categoryId){
		logger.info("Get products by id category" + getClass().getName());
		String hql = "FROM Product p WHERE p.category.categoryID=:productCategoryId";
		Query query = hibernateUtil.getSession().createQuery(hql);
		query.setParameter("productCategoryId", categoryId);
		query.setCacheable(true);
		List<Product> products = query.list();
		return products;	
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		logger.info("Get all products  " + getClass().getName());
		String sql = "FROM Product p";
		Query query = hibernateUtil.getSession().createQuery(sql);
		query.setCacheable(true);
		List<Product> products = query.list();
		return products;
	}
}
