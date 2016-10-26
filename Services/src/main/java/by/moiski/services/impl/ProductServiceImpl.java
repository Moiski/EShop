package by.moiski.services.impl;

import java.util.List;

import by.moiski.dao.entities.Product;
import by.moiski.dao.impl.ProductDaoImpl;
import by.moiski.services.IProductService;

public class ProductServiceImpl implements IProductService {

	public List<Product> getProductsByCategoryId(int categoryId) {
		ProductDaoImpl productDaoImpl = new ProductDaoImpl();
		List<Product> products = productDaoImpl.getProductsByCategoryId(categoryId);
		return products;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
