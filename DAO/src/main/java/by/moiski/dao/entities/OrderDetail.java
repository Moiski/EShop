package by.moiski.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="orderdetails")
@Proxy(lazy = false)
public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long orderDetailId;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "orderDetailId")
	public Long getOrderDetailId() {
		return orderDetailId;
	}
	
	@Column(name = "count")
	private Integer count;
	public Integer getCount() {
		return count;
	}
	
	private Product product;
	@OneToOne
	@JoinColumn(name = "productID")
	public Product getProduct() {
		return product;
	}
	
	public OrderDetail() {
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailId=" + orderDetailId + ", count=" + count + ", product=" + product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((orderDetailId == null) ? 0 : orderDetailId.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (orderDetailId == null) {
			if (other.orderDetailId != null)
				return false;
		} else if (!orderDetailId.equals(other.orderDetailId))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
