package by.moiski.dao.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="carts")
@Proxy(lazy = false)
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long cartId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCartId() {
		return cartId;
	}
	
	private User user;
	@OneToOne
	@JoinColumn(name = "userID")
	public User getUser() {
		return user;
	}
	
	private Product product;
	@ManyToOne
	@JoinColumn(name = "productID")
	public Product getProduct() {
		return product;
	}

	public Cart() {
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", user=" + user + ", product=" + product + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null) ? 0 : cartId.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Cart other = (Cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		} else if (!cartId.equals(other.cartId))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
