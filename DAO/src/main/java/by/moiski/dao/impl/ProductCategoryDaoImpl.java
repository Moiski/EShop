package by.moiski.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.moiski.dao.IProductCategoryDao;
import by.moiski.dao.entities.ProductCategory;

@Repository
public class ProductCategoryDaoImpl extends DaoImpl<ProductCategory> implements IProductCategoryDao {
	
	@SuppressWarnings("unchecked")
	public List<ProductCategory> getAllProductCategories(){
		String hql = "From ProductCategory c";
		Query query = getSession().createQuery(hql);
		List <ProductCategory> results = query.list();
		return results;
	}

}
