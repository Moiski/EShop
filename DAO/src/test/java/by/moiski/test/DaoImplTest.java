package by.moiski.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import by.moiski.dao.IProductCategoryDao;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.dao.exceptions.DaoExeptions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-dao-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class DaoImplTest {

	private static final long WRONG_CATEGORY_ID = 100L;

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IProductCategoryDao categoryDao;

	private Session session;
	private ProductCategory productCategoryA;
	private ProductCategory productCategoryB;
	private ProductCategory productCategoryC;
	private ProductCategory productCategoryD;

	@Test
	public void testSaveOrUpdate() {
		productCategoryA = TestEntity.createProductCategory("Kindle");
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(productCategoryA);
		flushAndClearSession();
		productCategoryA.setName("Tablets");
		session.saveOrUpdate(productCategoryA);
		flushAndClearSession();
		ProductCategory actualCategoryInDB = (ProductCategory) session.get(ProductCategory.class,
				productCategoryA.getCategoryID());
		assertEquals(productCategoryA, actualCategoryInDB);
	}

	@Test
	public void testGet() throws DaoExeptions {
		productCategoryB = TestEntity.createProductCategory("Accessories");
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(productCategoryB);
		flushAndClearSession();
		ProductCategory actualCategoryInDB = categoryDao.get(productCategoryB.getCategoryID());
		assertEquals(productCategoryB, actualCategoryInDB);
		ProductCategory actualCategoryInDBWrong = categoryDao.get(WRONG_CATEGORY_ID);
		assertNull(actualCategoryInDBWrong);
	}

	@Test
	public void testLoad() throws DaoExeptions {
		productCategoryC = TestEntity.createProductCategory("Mobile");
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(productCategoryC);
		flushAndClearSession();
		ProductCategory actualCategoryInDB = categoryDao.load(productCategoryC.getCategoryID());
		assertEquals(productCategoryC, actualCategoryInDB);
	}

	@Test
	public void testDelete() throws DaoExeptions {
		productCategoryD = TestEntity.createProductCategory("Comp");
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(productCategoryD);
		flushAndClearSession();
		ProductCategory productCategoryE = categoryDao.get(productCategoryD.getCategoryID());
		session.delete(productCategoryE);
		flushAndClearSession();
		ProductCategory actualCategoryInDB = categoryDao.get(productCategoryD.getCategoryID());
		assertNull(actualCategoryInDB);
	}

	@Test(expected = DaoExeptions.class)
	public void testLoadWrong() throws DaoExeptions {
		categoryDao.load(WRONG_CATEGORY_ID);
	}

	private void flushAndClearSession() {
		session.flush();
		session.clear();
	}

}
