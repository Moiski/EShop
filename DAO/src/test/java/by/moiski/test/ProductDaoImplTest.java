package by.moiski.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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

import by.moiski.dao.IProductCategoryDao;
import by.moiski.dao.IProductDao;
import by.moiski.dao.entities.ClientFilter;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-dao-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductDaoImplTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IProductDao  productDao;
	@Autowired
	private IProductCategoryDao  categoryDao;
	
	private Session session;
	private Product productA;
	private Product productB;
	private Product productC;
	private Product expectedProductA;
	private Product expectedProductB;
	private Product expectedProductC;
	private ClientFilter clientFilterA;
	private ClientFilter clientFilterB;
	private ClientFilter clientFilterC;
	private ProductCategory productCategoryA;
	private ProductCategory productCategoryB;
	private ProductCategory expectedProductCategoryA;
	private ProductCategory expectedProductCategoryB;
	private static final String ALL_CATEGORIES = "0";
	private static final String SORTING_NO_SORTING = "0";
	private static final String SORTING_LOW_TO_HIGH = "1";
	private static final String SORTING_HIGH_TO_LOW = "2";
	private static final Integer ITEMS_PER_PAGE_TOTAL_COUNT = 2;
	private static final Integer ITEMS_PER_PAGE = 3;
	
	@Before
	public void setUp() {
		productCategoryA = TestEntity.createProductCategory("categoryA");
		saveCategory(productCategoryA);
		productA = createProduct("Kindleww",productCategoryA);
		saveProduct(productA);
		productCategoryB = TestEntity.createProductCategory("categoryB");
		saveCategory(productCategoryB);
		productB = createProduct("FireAdd", productCategoryB);
		saveProduct(productB);
		productC = createProduct("FireBdd", productCategoryB);
		saveProduct(productC);
	}
	
	@After
	public void tearDown() {
		session = sessionFactory.getCurrentSession();
		productCategoryA = (ProductCategory) session.get(ProductCategory.class, productCategoryA.getCategoryID());
		productCategoryB = (ProductCategory) session.get(ProductCategory.class, productCategoryB.getCategoryID());
		session.delete(productCategoryA);
		session.delete(productCategoryB);
		productA = (Product) session.get(Product.class, productA.getProductID());
		productB = (Product) session.get(Product.class, productB.getProductID());
		productC = (Product) session.get(Product.class, productC.getProductID());
		session.delete(productA);
		session.delete(productB);
		session.delete(productC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetProductsByCategoryId(){
		session = sessionFactory.getCurrentSession();
		List <Product> products = productDao.getProductsByCategoryId(productCategoryB.getCategoryID());
		expectedProductB = products.get(0);
		expectedProductC = products.get(1);
		assertTrue(products.size()>=2);
		assertEquals(expectedProductB, productB);
		assertEquals(expectedProductC, productC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetAllProducts(){
		session = sessionFactory.getCurrentSession();
		List<Product> products = productDao.getAllProducts();
		expectedProductA = products.get(0);
		expectedProductB = products.get(1);
		expectedProductC = products.get(2);
		assertTrue(products.size()>=3);
		assertEquals(expectedProductA, productA);
		assertEquals(expectedProductB, productB);
		assertEquals(expectedProductC, productC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testgetTotalCount(){
		session = sessionFactory.getCurrentSession();
		List<ProductCategory> categories = categoryDao.getAllProductCategories();
		expectedProductCategoryA = categories.get(3);
		expectedProductCategoryB = categories.get(4);
		assertTrue(categories.size()>=5);
		assertEquals(expectedProductCategoryA, productCategoryA);
		assertEquals(expectedProductCategoryB, productCategoryB);
		clientFilterA = TestEntity.createClientFilter(SORTING_NO_SORTING, ALL_CATEGORIES, ITEMS_PER_PAGE_TOTAL_COUNT);
		Long expectedTotalCount = productDao.getTotalCount(clientFilterA, categories);
		assertTrue(expectedTotalCount == 3);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetProductsByClientFilter(){
		session = sessionFactory.getCurrentSession();
		List<ProductCategory> categories = categoryDao.getAllProductCategories();
		expectedProductCategoryA = categories.get(3);
		expectedProductCategoryB = categories.get(4);
		assertTrue(categories.size()>=5);
		assertEquals(expectedProductCategoryA, productCategoryA);
		assertEquals(expectedProductCategoryB, productCategoryB);
		
		clientFilterA = TestEntity.createClientFilter(SORTING_NO_SORTING, ALL_CATEGORIES, ITEMS_PER_PAGE);
		List<Product> productsA = productDao.getProductsByClientFilter(clientFilterA, categories);
		expectedProductA = productsA.get(0);
		expectedProductB = productsA.get(1);
		expectedProductC = productsA.get(2);
		assertTrue(productsA.size()>=3);
		assertEquals(expectedProductA, productA);
		assertEquals(expectedProductB, productB);
		assertEquals(expectedProductC, productC);
		
		String categoryA = String.valueOf(productCategoryA.getCategoryID());
		clientFilterB = TestEntity.createClientFilter(SORTING_LOW_TO_HIGH, categoryA , ITEMS_PER_PAGE);
		List<Product> productsB = productDao.getProductsByClientFilter(clientFilterB, categories);
		expectedProductA = productsB.get(0);
		assertTrue(productsB.size()>=1);
		assertEquals(expectedProductA, productA);
		
		String categoryB = String.valueOf(productCategoryB.getCategoryID());
		clientFilterC = TestEntity.createClientFilter(SORTING_HIGH_TO_LOW, categoryB, ITEMS_PER_PAGE);
		List<Product> productsC = productDao.getProductsByClientFilter(clientFilterC, categories);
		expectedProductB = productsC.get(0);
		expectedProductC = productsC.get(1);
		assertTrue(productsC.size()>=2);
		assertEquals(expectedProductB, productB);
		assertEquals(expectedProductC, productC);
		session.flush();
		session.clear();
	}
	
	private void saveCategory(ProductCategory category) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
		session.flush();
		session.clear();
	}
	
	private void saveProduct(Product product){
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		session.clear();
	}
	
	private Product createProduct(String nameProduct, ProductCategory category){
		Product product = new Product();
		product.setCategory(category);
		product.setDescription("description");
		product.setImage("image");
		product.setName(nameProduct);
		product.setPrice(99.99);	
		return product;
	}

}
