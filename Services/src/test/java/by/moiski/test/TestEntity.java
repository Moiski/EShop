package by.moiski.test;

import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.ProductCategory;

public class TestEntity {
	
	public static ProductCategory createProductCategory(String categoryName){
		ProductCategory productCategory = new ProductCategory();
		productCategory.setName(categoryName);
		return productCategory;
	}
	
	public static ClientFilter createClientFilter(String sorting, String categoryId, Integer itemsPerPage ){
		ClientFilter clientFilter = new ClientFilter();
		clientFilter.setSorting(sorting);
		clientFilter.setCategoryId(categoryId);
		clientFilter.setItemsPerPage(itemsPerPage);
		return clientFilter;
	}

}
