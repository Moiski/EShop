package by.moiski.services;

import java.util.List;

import by.moiski.dao.entities.Order;
import by.moiski.services.exceptions.ErrorAddOrderServiceExeption;
import by.moiski.services.exceptions.ErrorCartUserIsEmptyServiceExeption;

public interface IOrderService {
	
	void saveOrderToDataBase (Order order);
	
	List<Order> getAlluserOrders(String login);
	
	void addOrder (String login) throws ErrorAddOrderServiceExeption, ErrorCartUserIsEmptyServiceExeption;

}
