package by.moiski.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	
	@Test
	public void testGetUserByLoginAndPassword(){
		User userA = createUser("lkpo", "123");
		saveUser(userA);
		User userB = userDao.getUserByLoginAndPassword("lkpo", "123");
		assertEquals(userA, userB);
		User userC = userDao.getUserByLoginAndPassword("wert", "123");
		assertNull(userC);
	}
	
	@Test
	public void testGetAllUsers(){
		User userA1 = createUser("lkpo1", "123");
		saveUser(userA1);
		User userA2 = createUser("lkpo2", "123");
		saveUser(userA2);
		User userA3 = createUser("lkpo3", "123");
		saveUser(userA3);
		List <User> testUsers = userDao.getAllUsers();
		assertTrue(testUsers.size()>=3);
	}
	
	@Test
	public void testGetUserIdByLogin(){
		User userC = createUser("lkpo4", "123");
		saveUser(userC);
		User userD = userDao.getUserIdByLogin(userC.getLogin());
		assertEquals(userC,userD);
		User userF = userDao.getUserIdByLogin("lkpo5");
		assertNull(userF);
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
