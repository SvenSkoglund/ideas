package ideasMVC.DAO;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideas.data.ProfileDAOImpl;
import com.skilldistillery.ideas.data.UserDAOImpl;
import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.User;

class ProfileTestDAO {
	private EntityManagerFactory emf;
	private EntityManager em;
	private Profile profile;
	private ProfileDAOImpl profiletest;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("ideas");
		em = emf.createEntityManager();
		profiletest = new ProfileDAOImpl();
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	@Test
	void test_update_user() {
		profile = em.find(Profile.class, 5);
		profile.setBio("my bio");
		profile.setProfilePic("were");
		profiletest.update(profile);
		assertEquals("my bio", em.find(Profile.class, 5).getBio());
		assertEquals("were", em.find(Profile.class, 5).getProfilePic());
	}
}
