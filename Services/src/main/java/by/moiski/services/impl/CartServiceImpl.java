package by.moiski.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.moiski.dao.dbutils.HibernateUtil;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.User;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.dao.impl.CartDaoImpl;
import by.moiski.dao.impl.ProductDaoImpl;
import by.moiski.dao.impl.UserDaoImpl;
import by.moiski.services.ICartService;

public class CartServiceImpl implements ICartService {
	
	private static CartServiceImpl cartServiceImpl;
	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	private HibernateUtil hibernateUtil = HibernateUtil.getInstance();
	private CartDaoImpl cartDaoImpl = new CartDaoImpl();
	private UserDaoImpl userDaoImpl = new UserDaoImpl();
	private ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	private Session session = null;
	private Transaction transaction = null;
	
	public static CartServiceImpl getInstance(){
		if (cartServiceImpl == null) {
			cartServiceImpl = new CartServiceImpl();
		}
		return cartServiceImpl;
	}

	public void addToCartProduct(String login, Long  productID) {
		logger.info("Starting method addToCartProduct  " + getClass().getName());
		User user = null;
		Product product = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			user = userDaoImpl.getUserIdByLogin(login);
			product = productDaoImpl.get(productID);
			Cart cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cartDaoImpl.saveOrUpdate(cart);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error adding product to cart  " + CartServiceImpl.class);
			transaction.rollback();
		}
	}

	@Override
	public List<Product> getCartUser(String login) {
		logger.info("Starting method getCartUser  " + getClass().getName());
		List<Product> productList = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			productList = cartDaoImpl.showCartUser(login);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting products list in  " + CartServiceImpl.class, e);
			transaction.rollback();
		}
		return productList;
	}
	
	public void removeToCartProduct(Cart cart){
		logger.info("Starting method removeToCartProduct  " + getClass().getName());
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			cartDaoImpl.delete(cart);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error removing the product from the cart  " + getClass().getName());
			transaction.rollback();
		}
	}
	
	public Cart getCartByUserIdAndProductId (Long userId, Long productId){
		logger.info("Starting method getCartByUserIdAndProductId  " + getClass().getName());
		Cart cart = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			cart = cartDaoImpl.getCartByUserIdAndProductId(userId, productId);
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting entity cart by userId and productId  " + getClass().getName());
			transaction.rollback();
		}
		return cart;	
	}
	
	public List<Cart> getCartByUserId (Long userId){
		logger.info("Starting method getCartByUserId  " + getClass().getName());
		List <Cart> cartUser = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			cartUser = cartDaoImpl.getCartByUserId(userId);
			transaction.commit();		
		} catch (HibernateException e) {
			logger.error("Error getting cart by userID  " + getClass().getName());
			transaction.rollback();
		}
		return cartUser;
	}
	
	public void deleteCartUser (Cart cart){
		logger.info("Starting method deleteCartUser  " + getClass().getName());
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			cartDaoImpl.delete(cart);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error deleting cart " + getClass().getName());
			transaction.rollback();
		}
	}
	
}


