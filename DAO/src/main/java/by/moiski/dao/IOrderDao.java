package by.moiski.dao;

import java.util.List;

import by.moiski.dao.entities.Order;

public interface IOrderDao extends IDao<Order> {
	
	List<Order> getAlluserOrders(Long userId);

}
