package by.moiski.dao.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "products")
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long productID;
	@Id
	@Column(name = "productID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getProductID() {
		return productID;
	}

	private String name;
	@Column(name = "name")
	public String getName() {
		return name;
	}

	private String description;
	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	private ProductCategory category;
	@OneToOne
	@JoinColumn(name = "categoryID")
	public ProductCategory getCategory() {
		return category;
	}

	private double price;
	@Column(name = "price", precision = 10, scale = 2)
	public double getPrice() {
		return price;
	}

	private String image;
	@Column(name = "image")
	public String getImage() {
		return image;
	}
	
	private List<Cart> carts;
	@OneToMany(mappedBy = "product")
	public List<Cart> getCarts() {
		return carts;
	}

	public Product() {
	}


	@Override
	public String toString() {
		return "Product [productID=" + productID + ", name=" + name + ", description=" + description + ", category="
				+ category + ", price=" + price + ", image=" + image + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
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
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		return true;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

}
