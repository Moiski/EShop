package by.moiski.dao;

import java.util.List;

import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;

public interface ICartDao extends IDao<Cart> {

	public void addToCartProduct(String login, int productID, int categoryID);

	List<Product> showCartUser(String login);
	
	public Cart getcartID (Integer productID, String login);
	
	public void removeToCartProduct (Cart car);

}
