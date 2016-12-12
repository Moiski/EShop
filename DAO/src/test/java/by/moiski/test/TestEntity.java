package by.moiski.test;

import by.moiski.dao.entities.ProductCategory;

public class TestEntity {
	
	public static ProductCategory createProductCategory(String categoryName){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName(categoryName);
		return productCategory;
	}

}
