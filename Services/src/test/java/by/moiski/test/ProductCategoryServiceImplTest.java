package by.moiski.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.entities.ProductCategory;
import by.moiski.services.IProductCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-services-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductCategoryServiceImplTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IProductCategoryService categoryService;
	
	private Session session;
	private ProductCategory productCategoryA;
	private ProductCategory productCategoryB;
	private ProductCategory expectedProductCategoryA;
	private ProductCategory expectedProductCategoryB;
	
	@Before
	public void setUp() {
		productCategoryA = TestEntity.createProductCategory("categoryAAAA");
		saveCategory(productCategoryA);
		productCategoryB = TestEntity.createProductCategory("categoryBBBB");
		saveCategory(productCategoryB);
	}

	@After
	public void tearDown() {
		session = sessionFactory.getCurrentSession();
		productCategoryA = (ProductCategory) session.get(ProductCategory.class, productCategoryA.getCategoryID());
		productCategoryB = (ProductCategory) session.get(ProductCategory.class, productCategoryB.getCategoryID());
		session.delete(productCategoryA);
		session.delete(productCategoryB);
		session.flush();
		session.clear();
	}

	@Test
	public void testGetAllProductCategories() {
		session = sessionFactory.getCurrentSession();
		List<ProductCategory> testCategories = categoryService.getAllProductCategories();
		expectedProductCategoryA = testCategories.get(0);
		expectedProductCategoryB = testCategories.get(1);
		assertTrue(testCategories.size() >= 2);
		assertEquals(expectedProductCategoryA, productCategoryA);
		assertEquals(expectedProductCategoryB, productCategoryB);
		session.flush();
		session.clear();
	}

	private void saveCategory(ProductCategory category) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
		session.flush();
		session.clear();
	}

}
