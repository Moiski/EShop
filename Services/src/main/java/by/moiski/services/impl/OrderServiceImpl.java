package by.moiski.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.ICartDao;
import by.moiski.dao.IOrderDao;
import by.moiski.dao.IUserDao;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Order;
import by.moiski.dao.entities.OrderDetail;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.OrderState;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.services.IOrderService;
import by.moiski.services.exceptions.ErrorAddOrderServiceExeption;
import by.moiski.services.exceptions.ErrorCartUserIsEmptyServiceExeption;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private IOrderDao orderDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private ICartDao cartDao;
	
	public void saveOrderToDataBase (Order order){
		logger.info("Starting method saveOrderToDataBase(): " + getClass().getName());
		try {
			orderDao.saveOrUpdate(order);
		} catch (DaoExeptions e) {
			logger.error("Error save order to data base: " + OrderServiceImpl.class, e);
		}
		logger.info("Ending method saveOrderToDataBase(): " + getClass().getName());
	}
	
	public List<Order> getAlluserOrders(String login){
		logger.info("Starting method getAlluserOrders " + getClass().getName());
		List<Order> orders = null;
		try {
			User user = userDao.getUserIdByLogin(login);
			orders = orderDao.getAlluserOrders(user.getUserId());
		} catch (HibernateException e) {
			logger.error("Error getting order list: " + OrderServiceImpl.class, e);
		}
		logger.info("Ending method getAlluserOrders(): " + getClass().getName());
		return orders;	
	}
	
	public void addOrder (String login) throws ErrorAddOrderServiceExeption, ErrorCartUserIsEmptyServiceExeption{
		logger.info("Starting method addOrder: " + getClass().getName());
		try {
			List<Product> cartProductList = cartDao.showCartUser(login);
			User user = userDao.getUserIdByLogin(login);
			List<Cart> cartUser = cartDao.getCartByUserId(user.getUserId());
			if (cartProductList.isEmpty()){
				throw new ErrorCartUserIsEmptyServiceExeption();
			} else {
				Order order = new Order();
				OrderDetail orderDetail = new OrderDetail();
				double sum = 0;
				List <OrderDetail> orderDetails = new ArrayList<>(); 
				for (Product product : cartProductList) {
					orderDetail.setProduct(product);
					orderDetail.setCount(cartProductList.size());
					orderDetails.add(orderDetail);
					sum = sum + product.getPrice();
				}
				order.setUser(user);
				order.setAmountPurchase(sum);		
				order.setOrderState(OrderState.OPEN);
				order.setOrderDetails(orderDetails);
				orderDao.saveOrUpdate(order);
				for (Cart cart : cartUser) {
					cartDao.delete(cart);
				}
			}
		} catch (DaoExeptions e) {
			logger.error("Error order entry :" + OrderServiceImpl.class, e );
			throw new ErrorAddOrderServiceExeption();
		}
	}
	
}
