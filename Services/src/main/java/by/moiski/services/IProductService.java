package by.moiski.services;

import java.io.Serializable;
import java.util.List;

import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.services.exceptions.ErrorSaveProductServiceExeption;

public interface IProductService {
	
	List<Product> getProductsByCategoryId(Long categoryId);
	
	List<Product> getAllProducts();
	
	void addNewProduct(Product product) throws ErrorSaveProductServiceExeption;
	
	Product getProductById(Serializable productId);
	
	List<Product> getProductsByClientFilter(ClientFilter clientFilter);
	
	Long getTotalCount(ClientFilter clientFilter);

}
