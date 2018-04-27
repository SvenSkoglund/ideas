package com.skilldistillery.ideas.test;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideas.data.CommentDAOImpl;
import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.Profile;

class CommentsTestDAO {
	private EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;
	private CommentDAOImpl commenttest;
	
	@BeforeEach
	public void setUp() throws Exception {
		emf = Persistence.createEntityManagerFactory("ideas");
		em = emf.createEntityManager();
		commenttest = new CommentDAOImpl();
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		emf.close();
	}

	

//	@Test
//	void test_create_comment() {
//		comment = new Comment();
//		comment.setContent("my comment");
//		comment.setIdea(em.find(Idea.class, 1));
//		comment.setProfile(em.find(Profile.class, 1));
//		comment = commenttest.create(comment);
//		assertEquals("my comment", em.find(Comment.class, comment.getId()).getContent());
//	}
	
	
//	@Test
//	void test_update_comment() {
//		comment = em.find(Comment.class, 6);
//		comment.setContent("dummy string 2");
//		comment.setProfile(em.find(Profile.class, 3));
//		comment.setIdea(em.find(Idea.class, 2));
//		comment = commenttest.update(comment);
//		assertEquals("dummy string 2", em.find(Comment.class, 6).getContent());
//		
//	}
//	
//	@Test
//	void test_delete_comment() {
//		comment = em.find(Comment.class, 6);
//		boolean deleted = commenttest.destroy(comment);
//		assertTrue(deleted);
//		
//	}
	
	
//	@Test
//	void test_like_create() {
//		comment = em.find(Comment.class, 2);
//		Profile profile = em.find(Profile.class, 1);
//		assertNotNull(commenttest.createLike(comment, profile, true));
//		
//	}
	
	@Test
	void test_like_update() {
		comment = em.find(Comment.class, 2);
		Profile profile = em.find(Profile.class, 1);
		assertNotNull(commenttest.updateLike(comment, profile, false));
		
	}
}
