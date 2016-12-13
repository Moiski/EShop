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

import by.moiski.dao.IUserDao;
import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-dao-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserDaoImplTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IUserDao userDao;
	
	private Session session;
	private User userA;
	private User userB;
	private User userC;
	
	@Before
	public void setUp() {
		userA = createUser("lkpo", "123");
		saveUser(userA);
		userB = createUser("wert", "123");
		saveUser(userB);
	}
	
	@After
	public void tearDown() {
		session = sessionFactory.getCurrentSession();
		userA = (User) session.get(User.class, userA.getUserId());
		userB = (User) session.get(User.class, userB.getUserId());
		session.delete(userA);
		session.delete(userB);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetUserByLoginAndPassword(){
		userC = userDao.getUserByLoginAndPassword("lkpo", "123");
		assertEquals(userA, userC);
		userC = userDao.getUserByLoginAndPassword("kostya", "123");
		assertNull(userC);
	}
	
	@Test
	public void testGetAllUsers(){
		List <User> testUsers = userDao.getAllUsers();
		assertTrue(testUsers.size()>=2);
	}
	
	@Test
	public void testGetUserIdByLogin(){
		userC = userDao.getUserIdByLogin(userA.getLogin());
		assertEquals(userA,userC);
		userC = userDao.getUserIdByLogin("lkpo5");
		assertNull(userC);
	}
	
	private void saveUser(User user){
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
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

}
