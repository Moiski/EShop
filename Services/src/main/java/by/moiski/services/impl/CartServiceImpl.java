package by.moiski.services.impl;

import java.util.List;

import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;
import by.moiski.dao.impl.CartDaoImpl;
import by.moiski.services.ICartService;

public class CartServiceImpl implements ICartService {

	public void addToCartProduct(String login, int productID, int categoryID) {
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		cartDaoImpl.addToCartProduct(login, productID, categoryID);
	}

	public List<Product> getCartUser(String login) {
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		List <Product> cartList = cartDaoImpl.showCartUser(login);
		return cartList;
	}

	public Cart getCartId(String login, Integer productId) {
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		Cart cart = cartDaoImpl.getcartID(productId, login);
		return cart;
	}


	public void removeProductFromCart(Cart cart) {
		CartDaoImpl cartDaoImpl = new CartDaoImpl();
		cartDaoImpl.removeToCartProduct(cart);
	}
	

}
