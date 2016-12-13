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

import by.moiski.dao.entities.User;
import by.moiski.dao.enums.BlackList;
import by.moiski.dao.enums.UserT;
import by.moiski.services.IUserService;
import by.moiski.services.exceptions.ErrorDataAccessServiceExeption;
import by.moiski.services.exceptions.ErrorSavingUserServiceExeption;
import by.moiski.services.exceptions.LoginAlreadyBusyServiceExeption;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-services-test-config.xml")
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImplTest {
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private IUserService userService;
	
	private Session session;
	private User userA;
	private User userB;
	private User userC;
	private User expectedUserA;
	private User expectedUserB;
	private User expectedUserC;
	
	@Before
	public void setUp() {
		userA = createUser("userA", "123");
		saveUser(userA);
		userB = createUser("userB", "123");
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
	public void testGetUserByLoginPassword() throws ErrorDataAccessServiceExeption{
		session = sessionFactory.getCurrentSession();
		userC = userService.getUserByLoginPassword("userA", "123");
		assertEquals(userA, userC);
		userC = userService.getUserByLoginPassword("userC", "123");
		assertNull(userC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testSaveUserToDataBase() throws LoginAlreadyBusyServiceExeption, ErrorSavingUserServiceExeption{
		session = sessionFactory.getCurrentSession();
		userC = createUser("userC", "123");
		userService.saveUserToDataBase(userC);
		expectedUserC = (User) session.get(User.class, userC.getUserId());
		assertEquals(expectedUserC, userC);
		session.delete(expectedUserC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetALLUsers(){
		session = sessionFactory.getCurrentSession();
		List <User> testUsers = userService.getALLUsers();
		expectedUserA = testUsers.get(0);
		expectedUserB = testUsers.get(1);
		assertTrue(testUsers.size()>=2);
		assertEquals(expectedUserA, userA);
		assertEquals(expectedUserB, userB);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetUserIdByLogin(){
		session = sessionFactory.getCurrentSession();
		userC = userService.getUserIdByLogin(userA.getLogin());
		assertEquals(userA,userC);
		userC = userService.getUserIdByLogin("lkpo5");
		assertNull(userC);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testGetUserById(){
		session = sessionFactory.getCurrentSession();
		expectedUserA = userService.getUserById(userA.getUserId());
		assertEquals(expectedUserA,userA);
		session.flush();
		session.clear();
	}
	
	@Test
	public void testUpdateUserBlackListStatus(){
		session = sessionFactory.getCurrentSession();
		userC = (User) session.get(User.class, userA.getUserId());
		userC.setBlackList(BlackList.Y);
		session.saveOrUpdate(userC);
		expectedUserC = (User) session.get(User.class, userC.getUserId());
		assertEquals(expectedUserC, userC);
		session.flush();
		session.clear();
	}
	
	@Test(expected = LoginAlreadyBusyServiceExeption.class)
	public void testSaveUserToDataBaseWrongLogin() throws LoginAlreadyBusyServiceExeption, ErrorSavingUserServiceExeption{
		session = sessionFactory.getCurrentSession();
		User expectedUser = createUser("userA", "123");
		userService.saveUserToDataBase(expectedUser);
		session.flush();
		session.clear();
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
