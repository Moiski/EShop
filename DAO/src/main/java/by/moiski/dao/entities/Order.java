package by.moiski.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Proxy;

import by.moiski.dao.enums.OrderState;


@Entity
@Table(name="orders")
@Proxy(lazy = false)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long orderId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID")
	public Long getOrderId() {
		return orderId;
	}
	
	private User user;
	@OneToOne
	@JoinColumn(name = "userID")
	public User getUser() {
		return user;
	}
	
	private Double amountPurchase;
	@Column(name="amountPurchase")
	public Double getAmountPurchase() {
		return amountPurchase;
	}
	
	private OrderState orderState;
	@Column(name = "orderState", columnDefinition = "enum('OPEN','CLOSE')")
	@Enumerated(EnumType.STRING)
	public OrderState getOrderState() {
		return orderState;
	}
	
	private List<OrderDetail> orderDetails;
	@OneToMany
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinTable(
			name = "orders_orderDetail",
			joinColumns = @JoinColumn(name = "orderId"),
			inverseJoinColumns = @JoinColumn(name ="orderDetailId") 
			)
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", user=" + user + ", amountPurchase=" + amountPurchase + ", orderState="
				+ orderState + ", orderDetails=" + orderDetails + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountPurchase == null) ? 0 : amountPurchase.hashCode());
		result = prime * result + ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((orderState == null) ? 0 : orderState.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (amountPurchase == null) {
			if (other.amountPurchase != null)
				return false;
		} else if (!amountPurchase.equals(other.amountPurchase))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderState != other.orderState)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setAmountPurchase(Double amountPurchase) {
		this.amountPurchase = amountPurchase;
	}
	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
