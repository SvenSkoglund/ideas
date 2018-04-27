package com.skilldistillery.ideas.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideas.data.IdeaDAOImpl;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Profile;

class IdeaTestDAO {
	private EntityManagerFactory emf;
	private EntityManager em;
	private Idea idea;
	private IdeaDAOImpl ideatest;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("ideas");
		em = emf.createEntityManager();
		ideatest = new IdeaDAOImpl();
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	

//	@Test
//	void test_create_idea() {
//		idea = new Idea();
//		idea.setContent("my idea");
//		idea.setName("Idea 1");
//		idea.setProfile(em.find(Profile.class, 1));
//		idea = ideatest.create(idea);
//		assertEquals("my idea", em.find(Idea.class, idea.getId()).getContent());
//	}
	
//	
//	@Test
//	void test_update_idea() {
//		idea = em.find(Idea.class, 5);
//		idea.setContent("dummy string");
//		idea.setName("Jo");
//		idea.setProfile(em.find(Profile.class, 3));
//		idea = ideatest.update(idea);
//		assertEquals("dummy string", em.find(Idea.class, 5).getContent());
//		assertEquals("Jo", em.find(Idea.class, 5).getName());
//		
//	}
//	
//	@Test
//	void test_delete_idea() {
//		idea = em.find(Idea.class, 7);
//		boolean deleted = ideatest.destroy(idea);
//		assertTrue(deleted);
//		
//	}
	
//	@Test
//	void test_like_create() {
//		idea = em.find(Idea.class, 2);
//		Profile profile = em.find(Profile.class, 1);
//		assertNotNull(ideatest.createLike(idea, profile, true));
//		
//	}
	
	@Test
	void test_like_update() {
		idea = em.find(Idea.class, 2);
		Profile profile = em.find(Profile.class, 1);
		assertNotNull(ideatest.updateLike(idea, profile, false));
	}
}
