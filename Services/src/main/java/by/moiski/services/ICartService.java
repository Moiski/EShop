package by.moiski.services;

import java.util.List;

import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;
import by.moiski.services.exceptions.ErrorAddProductToCartServiceExeption;
import by.moiski.services.exceptions.ErrorDeleteProductFromCartServiceExeption;

public interface ICartService {
	
	void addToCartProduct(String login, Product product) throws ErrorAddProductToCartServiceExeption;
	
	List <Product> getCartUser (String login);
	
	void removeToCartProduct(String login, Long productId) throws ErrorDeleteProductFromCartServiceExeption;
	
	List<Cart> getCartByUserId (Long userId);

}
