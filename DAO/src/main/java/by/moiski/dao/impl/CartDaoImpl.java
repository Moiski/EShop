package by.moiski.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import by.moiski.dao.ICartDao;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;

public class CartDaoImpl extends DaoImpl<Cart> implements ICartDao {
	
	private Logger logger = Logger.getLogger(CartDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> showCartUser(String login) {
		logger.info("Get cart list by login " + getClass().getName());
		String hql ="SELECT p.name, p.description, p.price, p.productID FROM products AS p WHERE p.productID IN "
				+ "(SELECT c.productID FROM carts AS c WHERE c.userID IN "
				+ "(SELECT u.userID FROM users As u WHERE u.login =:userlogin))";
		List <Product> products =  (List<Product>) hibernateUtil.getSession().createSQLQuery(hql)
				.addScalar("name", StringType.INSTANCE)
				.addScalar("description", StringType.INSTANCE)
				.addScalar("productID", LongType.INSTANCE)
				.addScalar("price", DoubleType.INSTANCE)
				.setParameter("userlogin", login)
				.setResultTransformer(Transformers.aliasToBean(Product.class)).list();
		return products;
	}
	
	public Cart getCartByUserIdAndProductId (Long userId, Long productId){
		logger.info("Get cart by userId and productId" + getClass().getName());
		String hql = "SELECT c FROM Cart c WHERE c.user.userId=:userId AND c.product.productID=:productID";
		Query query = hibernateUtil.getSession().createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("productID", productId);
		Cart cart = (Cart) query.uniqueResult();
		return cart;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cart> getCartByUserId (Long userId){
		logger.info("Get cart by user id  " +getClass().getName() );
		String hql = "SELECT c FROM Cart c WHERE c.user.userId=:userId";
		Query query = hibernateUtil.getSession().createQuery(hql);
		query.setParameter("userId", userId);
		List<Cart> carts = query.list();
		return carts;
	}
	
}
