package by.moiski.dao;

import java.util.List;

import by.moiski.dao.entities.Product;

public interface IProductDao extends IDao<Product> {
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategoryId(int categoryId);

}
