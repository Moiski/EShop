package by.moiski.services;

import java.util.List;

import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;

public interface ICartService {
	
	void addToCartProduct (String login, int productID, int categoryID);
	
	List <Product> getCartUser (String login);
	
	void removeProductFromCart (Cart cart);
	
	Cart getCartId (String login, Integer productId);

}
