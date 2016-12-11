package by.moiski.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import by.moiski.dao.IOrderDao;
import by.moiski.dao.entities.Order;

@Repository
public class OrderDaoImpl extends DaoImpl<Order> implements IOrderDao {

	@SuppressWarnings("unchecked")
	public List<Order> getAlluserOrders(Long userId) {
		logger.info("Get all user orders: " + getClass().getName());
		String hql = "FROM Order o WHERE o.user.userId=:userId";
		Query query = getSession().createQuery(hql);
		query.setParameter("userId", userId);
		List<Order> orders = query.list();
		return orders;
	}

}
