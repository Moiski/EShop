package by.moiski.dao.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "productcategories")
@Proxy(lazy = false)
public class ProductCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long categoryID;
	@Id
	@Column(name = "categoryId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCategoryID() {
		return categoryID;
	}

	private String name;
	@Column(name = "categoryname")
	public String getName() {
		return name;
	}

	public ProductCategory() {
	}

	@Override
	public String toString() {
		return "ProductCategory [categoryID=" + categoryID + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryID == null) ? 0 : categoryID.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ProductCategory other = (ProductCategory) obj;
		if (categoryID == null) {
			if (other.categoryID != null)
				return false;
		} else if (!categoryID.equals(other.categoryID))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public void setName(String name) {
		this.name = name;
	}

}
