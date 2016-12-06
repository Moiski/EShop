package by.moiski.services;

import java.util.List;

import by.moiski.dao.entities.Product;

public interface ICartService {
	
	void addToCartProduct (String login, Long productID);
	
	List <Product> getCartUser (String login);

}
