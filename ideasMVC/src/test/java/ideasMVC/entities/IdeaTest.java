package ideasMVC.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideasjpa.entities.Idea;

class IdeaTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Idea idea;

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
		idea = em.find(Idea.class, 1);
		System.out.println(idea);
		idea = em.find(Idea.class, 2);
		System.out.println(idea);
		idea = em.find(Idea.class, 3);
		System.out.println(idea);
	}

}
