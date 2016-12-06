package by.moiski.dao.entities;

public class ClientFilter {
	
	private String sorting;
	private String categoryId;
	private Integer itemsPerPage = 1;
	private Integer currentPage = 1;
	
	public ClientFilter() {
	}

	@Override
	public String toString() {
		return "ClientFilter [sorting=" + sorting + ", categoryId=" + categoryId + ", itemsPerPage=" + itemsPerPage
				+ ", currentPage=" + currentPage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((currentPage == null) ? 0 : currentPage.hashCode());
		result = prime * result + ((itemsPerPage == null) ? 0 : itemsPerPage.hashCode());
		result = prime * result + ((sorting == null) ? 0 : sorting.hashCode());
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
		ClientFilter other = (ClientFilter) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (currentPage == null) {
			if (other.currentPage != null)
				return false;
		} else if (!currentPage.equals(other.currentPage))
			return false;
		if (itemsPerPage == null) {
			if (other.itemsPerPage != null)
				return false;
		} else if (!itemsPerPage.equals(other.itemsPerPage))
			return false;
		if (sorting == null) {
			if (other.sorting != null)
				return false;
		} else if (!sorting.equals(other.sorting))
			return false;
		return true;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
}
