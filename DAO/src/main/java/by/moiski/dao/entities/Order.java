package by.moiski.dao.entities;

import java.util.Map;

import by.moiski.dao.OrderStates;

public class Order {

	private long userId;
	private OrderStates states;
	private Map<Product, Integer> orderProducts;

	public Order() {
	}

	public Order(long userId, OrderStates states, Map<Product, Integer> orderProducts) {
		super();
		this.userId = userId;
		this.states = states;
		this.orderProducts = orderProducts;
	}

	@Override
	public String toString() {
		return "Order [userId=" + userId + ", states=" + states + ", orderProducts=" + orderProducts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderProducts == null) ? 0 : orderProducts.hashCode());
		result = prime * result + ((states == null) ? 0 : states.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderProducts == null) {
			if (other.orderProducts != null)
				return false;
		} else if (!orderProducts.equals(other.orderProducts))
			return false;
		if (states != other.states)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public OrderStates getStates() {
		return states;
	}

	public void setStates(OrderStates states) {
		this.states = states;
	}

	public Map<Product, Integer> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(Map<Product, Integer> orderProducts) {
		this.orderProducts = orderProducts;
	}

}
