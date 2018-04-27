package ideasMVC.entities;

import static org.junit.jupiter.api.Assertions.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.ideasjpa.entities.Comment;

class CommentTest {

	private EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

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
		comment = em.find(Comment.class, 1);
		System.out.println(comment);
		comment = em.find(Comment.class, 2);
		System.out.println(comment);
		comment = em.find(Comment.class, 3);
		System.out.println(comment);
	}

}
