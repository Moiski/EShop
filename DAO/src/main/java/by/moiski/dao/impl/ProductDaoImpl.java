package by.moiski.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import by.moiski.dao.IProductDao;
import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;

@Repository
public class ProductDaoImpl extends DaoImpl<Product> implements IProductDao {
	
	private Logger logger = Logger.getLogger(ProductDaoImpl.class);
	private static final String ALL_CATEGORIES = "0";
	private static final String SORTING_NO_SORTING = "0";
	private static final String SORTING_LOW_TO_HIGH = "1";
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategoryId(Long categoryId){
		logger.info("Get products by id category: " + getClass().getName());
		String hql = "FROM Product p WHERE p.category.categoryID=:productCategoryId";
		Query query = getSession().createQuery(hql);
		query.setParameter("productCategoryId", categoryId);
		query.setCacheable(true);
		List<Product> results = query.list();
		return results;	
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		logger.info("Get all products: " + getClass().getName());
		String sql = "FROM Product p";
		Query query = getSession().createQuery(sql);
		query.setCacheable(true);
		List<Product> results = query.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getProductsByClientFilter(ClientFilter clientFilter, List<ProductCategory> categoriesList){
		logger.info("Get products by clent filter: " + getClass().getName());
		Criteria criteria = getSession().createCriteria(Product.class);
		createCriteriaByClientFilter(clientFilter, criteria, categoriesList);
		int startPosition = (clientFilter.getCurrentPage()-1)*clientFilter.getItemsPerPage();
		criteria.setFirstResult(startPosition);
		int maxResult = clientFilter.getItemsPerPage();
		criteria.setMaxResults(maxResult);
		criteria.setCacheable(true);
		List<Product> results = criteria.list();
		return results;
	}
	
	public void createCriteriaByClientFilter(ClientFilter clientFilter, Criteria criteria, List<ProductCategory> categoriesList){
		logger.info("Create criteria by clent filter: " + getClass().getName());
		Disjunction disjunction = Restrictions.disjunction();
		String categotyId = clientFilter.getCategoryId();
		if (categotyId.equals(ALL_CATEGORIES)) {
			for (ProductCategory productCategory : categoriesList) {
				Long categoryId = productCategory.getCategoryID();
				Criterion criterion = Restrictions.eq("category.categoryID", categoryId);
				disjunction.add(criterion);
				criteria.add(Restrictions.or(disjunction));
			}
		} else {
			criteria.add(Restrictions.eq("category.categoryID", Long.parseLong(clientFilter.getCategoryId())));
		}
		if (clientFilter.getSorting().equals(SORTING_NO_SORTING)) {
			criteria.addOrder(Order.asc("category"));
		} else {
			if (clientFilter.getSorting().equals(SORTING_LOW_TO_HIGH)) {
				criteria.addOrder(Order.asc("price"));
			} else {
				criteria.addOrder(Order.desc("price"));
			}
		}
	}
	
	public Long getTotalCount(ClientFilter clientFilter, List<ProductCategory> categoriesList){
		logger.info("get total count by clent filter: " + getClass().getName());
		Criteria criteria = getSession().createCriteria(Product.class);
		createCriteriaByClientFilter(clientFilter, criteria, categoriesList);
		criteria.setProjection(Projections.rowCount());
		criteria.setCacheable(true);
		Long totalCount = (Long) criteria.uniqueResult();
		return totalCount;
	}
	
}
