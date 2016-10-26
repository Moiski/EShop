package by.moiski.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.moiski.dao.ICartDao;
import by.moiski.dao.connectionPool.ConnectionPool;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;

public class CartDaoImpl extends DaoImpl<Cart> implements ICartDao {

	private Logger log = Logger.getLogger(getClass());

	public static final String SQL_QUERY_ADD_TO_CART_PRODUCT = "INSERT INTO cart (users_userID, products_ID, products_categoryID) VALUES ((SELECT users.userID FROM users WHERE users.login = ?),?,?)";
	public static final String SQL_QUERY_SHOW_ALL_PRODUCTS_FROM_CART = "SELECT name, description, price, ID FROM products WHERE ID IN (SELECT products_ID FROM cart WHERE users_userID IN (SELECT userID FROM users WHERE login = ?))";
	public static final String SQL_QUERY_GET_CARTID = "SELECT cartID FROM cart WHERE products_ID = ? AND users_userID = (SELECT userID FROM users WHERE login = ?)";
	public static final String SQL_QUERY_REMOVE_PRODUCT_FROM_CART = "DELETE FROM cart WHERE cartID = ?";;
	
	private final String COLUMN_NAME_PRODUCT_NAME = "name";
	private final String COLUMN_NAME_PRODUCT_DESCRIPTION = "description";
	private final String COLUMN_NAME_PRODUCT_COST = "price";
	private final String COLUMN_NAME_PRODUCT_ID = "ID";
	private final String COLUMN_NAME_CARTID = "cartID";

	public void addToCartProduct(String login, int productID, int categoryID) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_TO_CART_PRODUCT);
			preparedStatement.setString(1, login);
			preparedStatement.setInt(2, productID);
			preparedStatement.setInt(3, categoryID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQL exception when you add an item" + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}
	}

	public List<Product> showCartUser(String login) {
		List<Product> cartList = new ArrayList<>();
		Product product = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_SHOW_ALL_PRODUCTS_FROM_CART);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				product = new Product();
				product.setName(resultSet.getString(COLUMN_NAME_PRODUCT_NAME));
				product.setDescription(resultSet.getString(COLUMN_NAME_PRODUCT_DESCRIPTION));
				product.setPrice(resultSet.getDouble(COLUMN_NAME_PRODUCT_COST));
				product.setProductId(resultSet.getInt(COLUMN_NAME_PRODUCT_ID));
				cartList.add(product);
			}
		} catch (SQLException e) {
			log.error("SQL exception: cart user don't find: " + e);
			e.printStackTrace();
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
		return cartList;
	}

	public Cart getcartID(Integer productID, String login) {
		Cart cart = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_GET_CARTID);
			preparedStatement.setInt(1, productID);
			preparedStatement.setString(2, login);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cart = new Cart();
				cart.setCartID(resultSet.getInt(COLUMN_NAME_CARTID));
			}
		} catch (SQLException e) {
			log.error("SQL exception: cartID don't find: " + e);
			e.printStackTrace();
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
		return cart;
	}

	public void removeToCartProduct(Cart car) {
		int cartID = car.getCartID();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(SQL_QUERY_REMOVE_PRODUCT_FROM_CART);
			preparedStatement.setInt(1, cartID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			log.error("SQL exception: don't remove the product from the cart: " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					log.error("SQL exception closing preparedStatement: " + e);
				}
			}
			ConnectionPool.releaseConnection(connection);
		}
	}

}
