package by.moiski.dao.entities;

public class Cart {

	private int cartID;
	private long usersID;
	private int productID;
	private ProductCategory category;

	public Cart() {
	}

	public Cart(int cartID, long usersID, int productID, ProductCategory category) {
		super();
		this.cartID = cartID;
		this.usersID = usersID;
		this.productID = productID;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Cart [cartID=" + cartID + ", usersID=" + usersID + ", productID=" + productID + ", category=" + category
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartID;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + productID;
		result = prime * result + (int) (usersID ^ (usersID >>> 32));
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
		if (cartID != other.cartID)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (productID != other.productID)
			return false;
		if (usersID != other.usersID)
			return false;
		return true;
	}

	public int getCartID() {
		return cartID;
	}

	public void setCartID(int cartID) {
		this.cartID = cartID;
	}

	public long getUsersID() {
		return usersID;
	}

	public void setUsersID(long usersID) {
		this.usersID = usersID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

}
