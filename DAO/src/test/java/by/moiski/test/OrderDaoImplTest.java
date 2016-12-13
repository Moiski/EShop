package by.moiski.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import by.moiski.dao.IOrderDao;
import by.moiski.dao.entities.Order;
import by.moiski.dao.entities.OrderDetail;
import by.moiski.dao.entities.Product;
import by.moiski.dao.entities.ProductCategory;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.OrderState;
import by.moiski.dao.enums.UserT;

public class OrderDaoImplTest {
	

	private SessionFactory sessionFactory;

	private IOrderDao orderDao;
	
	private Session session;
	private Product productA;
	private Product productB;
	private ProductCategory productCategoryA;
	private User userA;
	private Order orderA;
	private Order orderB;
	private Order expectedOrderA;
	private Order expectedOrderB;
	

	public void setUp(){
		productCategoryA = TestEntity.createProductCategory("categoryA");
		saveCategory(productCategoryA);
		productA = createProduct("Kindle11", productCategoryA);
		saveProduct(productA);
		productB = createProduct("Fire22", productCategoryA);
		saveProduct(productB);
		userA = createUser("qqqqq", "123");
		saveUser(userA);
		orderA = createOrder(productA, userA);
		saveOrder(orderA);
		orderB = createOrder(productB, userA);
		saveOrder(orderB);
	}
	

	public void tearDown(){
		session = sessionFactory.getCurrentSession();
		productCategoryA = (ProductCategory) session.get(ProductCategory.class, productCategoryA.getCategoryID());
		session.delete(productCategoryA);
		productA = (Product) session.get(Product.class, productA.getProductID());
		productB = (Product) session.get(Product.class, productB.getProductID());
		session.delete(productA);
		session.delete(productB);
		userA = (User) session.get(User.class, userA.getUserId());
		session.delete(userA);
		orderA = (Order) session.get(Order.class, orderA.getOrderId());
		orderB = (Order) session.get(Order.class, orderB.getOrderId());
		session.delete(orderA);
		session.delete(orderB);
		session.flush();
		session.clear();
	}
	
	public void testGetAlluserOrders(){
		session = sessionFactory.getCurrentSession();
		List<Order> orders = orderDao.getAlluserOrders(userA.getUserId());
		assertTrue(orders.size()>=2);
		expectedOrderA = orders.get(0);
		expectedOrderB = orders.get(1);
		assertEquals(expectedOrderA, orderA);
		assertEquals(expectedOrderB, orderB);
		session.flush();
		session.clear();
	}
	
	private Order createOrder(Product product, User user){
		Order order = new Order();
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(product);
		List<OrderDetail> details = new ArrayList<>();
		details.add(orderDetail);
		order.setUser(user);
		order.setOrderDetails(details);
		order.setOrderState(OrderState.OPEN);
		order.setAmountPurchase(product.getPrice());
		return order;
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
	
	private void saveOrder(Order order){
		session = sessionFactory.getCurrentSession();
		session.save(order);
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
	
	private void saveUser(User user){
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
		session.flush();
		session.clear();
	}
	
}
