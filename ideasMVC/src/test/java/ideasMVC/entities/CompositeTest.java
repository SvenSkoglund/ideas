package ideasMVC.entities;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.CommentLike;
import com.skilldistillery.ideasjpa.entities.CommentLikeKey;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.IdeaLike;
import com.skilldistillery.ideasjpa.entities.IdeaLikeKey;
import com.skilldistillery.ideasjpa.entities.Profile;

class CompositeTest {
	private EntityManagerFactory emf;
	private EntityManager em;

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
	void test_comopsite_idea_like() {
		IdeaLikeKey ilk = new IdeaLikeKey();
		
		Idea idea = em.find(Idea.class, 1);
		Profile profile = em.find(Profile.class, 1);
		
		ilk.setIdea(idea);
		ilk.setProfile(profile);
		
		IdeaLike il = em.find(IdeaLike.class, ilk);
		assertEquals(1,il.getId().getIdea().getId());
	}
	@Test
	void test_comopsite_comment_like() {
		CommentLikeKey clk = new CommentLikeKey();
		
		Comment comment = em.find(Comment.class, 3);
		Profile profile = em.find(Profile.class, 1);
		
		clk.setComment(comment);
		clk.setProfile(profile);
		
		CommentLike cl = em.find(CommentLike.class, clk);
		assertEquals(3,cl.getId().getComment().getId());
	}
}
