package by.moiski.services;

import java.util.List;

import by.moiski.dao.entities.ProductCategory;

public interface IProductCategoryService {

	ProductCategory getCategoryById();

	List<ProductCategory> getAllProductCategories();

}
