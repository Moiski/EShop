package by.moiski.dao;

import java.util.List;

import org.hibernate.Criteria;

import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;

public interface IProductDao extends IDao<Product> {
	
	List<Product> getProductsByCategoryId(Long categoryId);
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByClientFilter(ClientFilter clientFilter, List<ProductCategory> categoriesList);
	
	void createCriteriaByClientFilter(ClientFilter clientFilter, Criteria criteria, List<ProductCategory> categoriesList);
	
	Long getTotalCount(ClientFilter clientFilter, List<ProductCategory> categoriesList);

}
