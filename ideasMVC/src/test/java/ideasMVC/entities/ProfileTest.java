package ideasMVC.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideasjpa.entities.Profile;

class ProfileTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Profile profile;

	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("ideas");
		em = emf.createEntityManager();
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}
	@Test
	void test() {
		profile = em.find(Profile.class, 1);
		System.out.println(profile);
		profile = em.find(Profile.class, 2);
		System.out.println(profile);
		profile = em.find(Profile.class, 3);
		System.out.println(profile);
	}

}
