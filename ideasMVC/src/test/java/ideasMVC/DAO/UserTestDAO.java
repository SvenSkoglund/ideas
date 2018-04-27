package ideasMVC.DAO;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideas.data.UserDAOImpl;
import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.User;

class UserTestDAO {
	private EntityManagerFactory emf;
	private EntityManager em;
	private User user;
	private UserDAOImpl usertest;

	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("ideas");
		em = emf.createEntityManager();
		usertest = new UserDAOImpl();
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
//
//	@Test
//	void test_create_user_and_profile() {
//		user = new User();
//		user.setUsername("testusername3");
//		user.setPassword("qwerty");
//		user.setEmail("luke3@theforce.com");
//		user.setAdmin(false);
//		user = usertest.create(user);
//		assertEquals(user, em.find(User.class, user.getId()));
//	}

//	@Test
//	void test_delete_profile_and_user() {
//		user = em.find(User.class, 10);
//		assertTrue(usertest.destroy(user));
//
//	}

	@Test
	void test_update_user() {
		user = em.find(User.class, 9);
		user.setAdmin(true);
		user.setEmail("luke5@theforce.com");
		user.setPassword("hello2");
		usertest.update(user);
		assertEquals("hello2", em.find(User.class, 9).getPassword());
	}
}
