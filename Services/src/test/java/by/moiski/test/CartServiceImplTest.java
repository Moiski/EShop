package by.moiski.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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

import by.moiski.dao.entities.Cart;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;
import by.moiski.services.ICartService;
import by.moiski.services.exceptions.ErrorDeleteProductFromCartServiceExeption;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-services-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class CartServiceImplTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ICartService cartService;
	
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
		List<Product> productsA = cartService.getCartUser(userA.getLogin());
		expectedProductA = productsA.get(0);
		assertTrue(productsA.size()>=1);
		assertEquals(expectedProductA, productA);
		List<Product> productsB = cartService.getCartUser(userB.getLogin());
		expectedProductB = productsB.get(0);
		expectedProductC = productsB.get(1);
		assertTrue(productsB.size()>=2);
		assertEquals(expectedProductB, productB);
		assertEquals(expectedProductC, productC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetCartByUserId(){
		session = sessionFactory.getCurrentSession();
		List<Cart> carts = cartService.getCartByUserId(userB.getUserId());
		expectedCartB = carts.get(0);
		expectedCartC = carts.get(1);
		assertTrue(carts.size()>=2);
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
