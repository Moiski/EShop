package by.moiski.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import by.moiski.dao.dbutils.HibernateUtil;
import by.moiski.dao.entities.Order;
import by.moiski.dao.exceptions.DaoExeptions;
import by.moiski.dao.impl.OrderDaoImpl;
import by.moiski.services.IOrderService;

public class OrderServiceImpl implements IOrderService {

	private static OrderServiceImpl orderServiceImpl;
	private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
	private HibernateUtil hibernateUtil = HibernateUtil.getInstance();
	private OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
	private Session session = null;
	private Transaction transaction = null;
	
	public static OrderServiceImpl getInstance(){
		if (orderServiceImpl == null) {
			orderServiceImpl = new OrderServiceImpl();
		}
		return orderServiceImpl;
	}
	
	public void saveOrderToDataBase (Order order){
		logger.info("Starting method saveOrderToDataBase " + getClass().getName());
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			orderDaoImpl.saveOrUpdate(order);
			transaction.commit();
		} catch (DaoExeptions e) {
			logger.error("Error save order to data base  " + getClass().getName());
			transaction.rollback();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getAlluserOrders(Long userId){
		logger.info("Starting method getAlluserOrders " + getClass().getName());
		List<Order> orders = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			String hql = "FROM Order o WHERE o.user.userId=:userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", userId);
			orders = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			logger.error("Error getting order list " + getClass().getName());
			transaction.rollback();
		}
		return orders;	
	}
	
}
