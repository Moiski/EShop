package by.moiski.dao;

import java.util.List;

import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;

public interface ICartDao extends IDao<Cart> {
	
	List<Product> showCartUser(String login);
	
	Cart getCartByUserIdAndProductId (Long userId, Long productId);
	
	List<Cart> getCartByUserId (Long userId);

}
