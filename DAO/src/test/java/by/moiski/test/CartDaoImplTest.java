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

import by.moiski.dao.ICartDao;
import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-dao-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class CartDaoImplTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ICartDao cartDao;
	
	private Session session;
	private Product productA;
	private Product productB;
	private Product productC;
	private Product expectedProductA;
	private Product expectedProductB;
	private Product expectedProductC;
	private User userA;
	private User userB;
	private Cart cartA;
	private Cart cartB;
	private Cart cartC;
	private Cart expectedCartA;
	private Cart expectedCartB;
	private Cart expectedCartC;
	
	@Before
	public void setUp() {
		productA = createProduct("KindleA");
		saveProduct(productA);
		productB = createProduct("FireAA");
		saveProduct(productB);
		productC = createProduct("FireBB");
		saveProduct(productC);
		userA = createUser("lkpo", "123");
		saveUser(userA);
		userB = createUser("wert", "123");
		saveUser(userB);
		cartA = createCart(userA, productA);
		saveCart(cartA);
		cartB = createCart(userB, productB);
		saveCart(cartB);
		cartC = createCart(userB, productC);
		saveCart(cartC);
	}
	
	@After
	public void tearDown(){
		session = sessionFactory.getCurrentSession();
		productA = (Product) session.get(Product.class, productA.getProductID());
		productB = (Product) session.get(Product.class, productB.getProductID());
		productC = (Product) session.get(Product.class, productC.getProductID());
		session.delete(productA);
		session.delete(productB);
		session.delete(productC);
		userA = (User) session.get(User.class, userA.getUserId());
		userB = (User) session.get(User.class, userB.getUserId());
		session.delete(userA);
		session.delete(userB);
		cartA = (Cart) session.get(Cart.class, cartA.getCartId());
		cartB = (Cart) session.get(Cart.class, cartB.getCartId());
		cartC = (Cart) session.get(Cart.class, cartC.getCartId());
		session.delete(cartA);
		session.delete(cartB);
		session.delete(cartC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetCartUser(){
		session = sessionFactory.getCurrentSession();
		List<Product> productsA = cartDao.showCartUser(userA.getLogin());
		expectedProductA = productsA.get(0);
		assertTrue(productsA.size()>=1);
		assertEquals(expectedProductA, productA);
		List<Product> productsB = cartDao.showCartUser(userB.getLogin());
		expectedProductB = productsB.get(0);
		expectedProductC = productsB.get(1);
		assertTrue(productsB.size()>=2);
		assertEquals(expectedProductB, productB);
		assertEquals(expectedProductC, productC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetCartByUserIdAndProductId(){
		session = sessionFactory.getCurrentSession();
		Cart expectedCart = new Cart();
		expectedCart  = cartDao.getCartByUserIdAndProductId(userA.getUserId(), productA.getProductID());
		assertEquals(expectedCart, cartA);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetCartByUserId(){
		session = sessionFactory.getCurrentSession();
		List<Cart> cartsA = cartDao.getCartByUserId(userA.getUserId());
		expectedCartA = cartsA.get(0);
		assertTrue(cartsA.size()>=1);
		assertEquals(expectedCartA, cartA);
		List<Cart> cartsB = cartDao.getCartByUserId(userB.getUserId());
		expectedCartB = cartsB.get(0);
		expectedCartC = cartsB.get(1);
		assertTrue(cartsB.size()>=2);
		assertEquals(expectedCartB, cartB);
		assertEquals(expectedCartC, cartC);
		session.flush();
		session.clear();
	}
	
	private void saveProduct(Product product){
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		session.clear();
	}
	
	private void saveUser(User user){
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
		session.clear();
	}
	
	private void saveCart(Cart cart){
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cart);
		session.flush();
		session.clear();
	}
	
	private User createUser(String login, String password){
		User user = new User();
		user.setBlackList(BlackList.N);
		user.setEmail("moiski@yandex.ru");
		user.setFirstname("Kostya");
		user.setLastname("Moiski");
		user.setLogin(login);
		user.setPassword(password);
		user.setRole(UserT.CLIENT);
		user.setShippingAddress("Minsk");
		return user;
	}
	
	private Product createProduct(String nameProduct){
		Product product = new Product();
		product.setDescription("description");
		product.setName(nameProduct);
		product.setPrice(99.99);	
		return product;
	}
	
	private Cart createCart(User user, Product product){
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		return cart;
	}
	
}
