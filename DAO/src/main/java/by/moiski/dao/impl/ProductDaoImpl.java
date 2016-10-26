package by.moiski.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.moiski.dao.IProductDao;
import by.moiski.dao.connectionPool.ConnectionPool;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;

public class ProductDaoImpl extends DaoImpl<Product> implements IProductDao {
	
	private Logger log = Logger.getLogger(getClass());
	
	public static final String SQL_QUERRY_GET_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products WHERE categoryID = ?;";
	
//	private final String COLUMN_NUMBER_CATEGORY_NAME = "categoryname";
	private final String COLUMN_NUMBER_PRODUCT_NAME = "name";
	private final String COLUMN_NUMBER_DESCRIPTION_PRODUCT = "description";
	private final String COLUMN_NUMBER_PRICE = "price";	
	private final String COLUMN_NUMDER_IMAGE = "image";
//	private final String COLUMN_NUMBER_CATEGORY_ID = "categoryID";
	private final String COLUMN_NUMBER_PRODUCT_ID = "ID";

	public List<Product> getAllProducts() {
		return null;
	}

	public List<Product> getProductsByCategoryId(int categoryId) {
		List <Product> products = new ArrayList<Product>();
		Product product = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryId(categoryId);
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERRY_GET_PRODUCTS_BY_CATEGORY_ID);
			preparedStatement.setInt(1, categoryId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				product = new Product();
				product.setName(resultSet.getString(COLUMN_NUMBER_PRODUCT_NAME));
				product.setDescription(resultSet.getString(COLUMN_NUMBER_DESCRIPTION_PRODUCT));
				product.setPrice(resultSet.getDouble(COLUMN_NUMBER_PRICE));
				product.setImage(resultSet.getString(COLUMN_NUMDER_IMAGE));
				product.setProductId(resultSet.getInt(COLUMN_NUMBER_PRODUCT_ID));
				product.setCategoryID(categoryId);
				products.add(product);
			}
		} catch (SQLException e) {
			log.error("SQL exception to a list of goods" + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					log.error("SQL exception closing resultSet: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}
		return products;
	}

}
