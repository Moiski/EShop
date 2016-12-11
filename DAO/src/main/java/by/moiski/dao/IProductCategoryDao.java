package by.moiski.dao;

import java.util.List;

import by.moiski.dao.entities.ProductCategory;

public interface IProductCategoryDao extends IDao<ProductCategory> {
	
	List<ProductCategory> getAllProductCategories();

}
