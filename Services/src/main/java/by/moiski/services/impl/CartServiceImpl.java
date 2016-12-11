package by.moiski.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.ICartDao;
import by.moiski.dao.IUserDao;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.User;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.services.ICartService;
import by.moiski.services.exceptions.ErrorAddProductToCartServiceExeption;
import by.moiski.services.exceptions.ErrorDeleteProductFromCartServiceExeption;

@Service
@Transactional
public class CartServiceImpl implements ICartService {
	
	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ICartDao cartDao;
	@Autowired
	private IUserDao userDao;

	public void addToCartProduct(String login, Product product) throws ErrorAddProductToCartServiceExeption {
		logger.info("Starting method addToCartProduct(): " + getClass().getName());
		try {
			User user = userDao.getUserIdByLogin(login);
			Cart cart = new Cart();
			cart.setProduct(product);
			cart.setUser(user);
			cartDao.saveOrUpdate(cart);
		} catch (DaoExeptions e) {
			logger.error("Error adding product to cart: " + CartServiceImpl.class, e);
			throw new ErrorAddProductToCartServiceExeption();
		}
		logger.info("Ending method addToCartProduct(): " + getClass().getName());
	}

	public List<Product> getCartUser(String login) {
		logger.info("Starting method getCartUser(): " + getClass().getName());
		List<Product> productList = new ArrayList<>();
		try {
			productList = cartDao.showCartUser(login);
		} catch (HibernateException e) {
			logger.error("Error getting products list in: " + CartServiceImpl.class, e);
		}
		logger.info("Ending method getCartUser(): " + getClass().getName());
		return productList;
	}
	
	public void removeToCartProduct(String login, Long productId) throws ErrorDeleteProductFromCartServiceExeption{
		logger.info("Starting method removeToCartProduct(): " + getClass().getName());
		Cart cart = null;
		try {
			User user = userDao.getUserIdByLogin(login);
			cart = cartDao.getCartByUserIdAndProductId(user.getUserId(), productId);
			cartDao.delete(cart);
		} catch (DaoExeptions e) {
			logger.error("Error removing the product from the cart:  " + CartServiceImpl.class, e);
			throw new ErrorDeleteProductFromCartServiceExeption();
		}
		logger.info("Ending method removeToCartProduct(): " + getClass().getName() );
	}
	
	public List<Cart> getCartByUserId (Long userId){
		logger.info("Starting method getCartByUserId():  " + getClass().getName());
		List <Cart> cartUser = new ArrayList<>();
		try {
			cartUser = cartDao.getCartByUserId(userId);	
		} catch (HibernateException e) {
			logger.error("Error getting cart by userID:  " + CartServiceImpl.class, e);
		}
		logger.info("Ending method getCartByUserId(): " + getClass().getName());
		return cartUser;
	}
	
}


