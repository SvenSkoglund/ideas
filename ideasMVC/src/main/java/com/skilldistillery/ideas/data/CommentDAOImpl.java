package com.skilldistillery.ideas.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.CommentLike;
import com.skilldistillery.ideasjpa.entities.CommentLikeKey;
import com.skilldistillery.ideasjpa.entities.Profile;

public class CommentDAOImpl implements CommentDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ideas");
	private EntityManager em = emf.createEntityManager();

	@Override
	public boolean destroy(Comment comment) {
		em.getTransaction().begin();
		Comment commentToDelete = em.find(Comment.class, comment.getId());
		if (commentToDelete == null) {
			em.getTransaction().commit();
			return false;
		}
		System.out.println(commentToDelete);
		em.remove(commentToDelete);
		em.flush();
		em.getTransaction().commit();
		return true;
	}

	@Override
	public Comment update(Comment comment) {
		em.getTransaction().begin();
		Comment managed = em.find(Comment.class, comment.getId());
		managed.setContent(comment.getContent());
		managed.setDateCreated(comment.getDateCreated());
		managed.setIdea(comment.getIdea());
		managed.setProfile(comment.getProfile());
		em.flush();
		em.getTransaction().commit();
		return managed;

	}

	@Override
	public Comment create(Comment comment) {
		// start the transaction
		em.getTransaction().begin();
		// write the customer to the database
		em.persist(comment);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();
		return comment;
	}

	@Override
	public CommentLike createLike(Comment comment, Profile profile, Boolean vote) {
		em.getTransaction().begin();
		CommentLikeKey clk = new CommentLikeKey();
		clk.setComment(comment);
		clk.setProfile(profile);
		
		CommentLike cl = new CommentLike();
		cl.setId(clk);
		cl.setVote(vote);
		em.persist(cl);
		em.flush();
		em.getTransaction().commit();
		return cl;
	}

	@Override
	public CommentLike updateLike(Comment comment, Profile profile, Boolean vote) {
		em.getTransaction().begin();
		CommentLikeKey clk = new CommentLikeKey();
		clk.setComment(comment);
		clk.setProfile(profile);
		CommentLike managed = em.find(CommentLike.class, clk);
		managed.setVote(vote);
		em.flush();
		em.getTransaction().commit();
		return managed;
	}

}
