package by.moiski.dao.impl;

import java.util.List;

import org.hibernate.Query;

import by.moiski.dao.IProductCategoryDao;
import by.moiski.dao.entities.ProductCategory;

public class ProductCategoryDaoImpl extends DaoImpl<ProductCategory> implements IProductCategoryDao {
	
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getAllProductCategories(){
		String hql = "From ProductCategory c";
		Query query = hibernateUtil.getSession().createQuery(hql);
		List <ProductCategory> categories = query.list();
		return categories;
	}

}
