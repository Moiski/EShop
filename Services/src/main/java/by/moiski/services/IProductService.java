package by.moiski.services;

import java.util.List;

import by.moiski.dao.entities.Product;

public interface IProductService {
	
	List<Product> getProductsByCategoryId(int categoryId);
	
	List<Product> getAllProducts();

}
