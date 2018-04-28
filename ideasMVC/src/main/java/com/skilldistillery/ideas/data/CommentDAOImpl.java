package com.skilldistillery.ideas.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.CommentLike;
import com.skilldistillery.ideasjpa.entities.CommentLikeKey;
import com.skilldistillery.ideasjpa.entities.Profile;
@Transactional
@Component
public class CommentDAOImpl implements CommentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean destroy(Comment comment) {
		Comment commentToDelete = em.find(Comment.class, comment.getId());
		if (commentToDelete == null) {
			em.getTransaction().commit();
			return false;
		}
		System.out.println(commentToDelete);
		em.remove(commentToDelete);
		return true;
	}

	@Override
	public Comment update(Comment comment) {
		Comment managed = em.find(Comment.class, comment.getId());
		managed.setContent(comment.getContent());
		managed.setDateCreated(comment.getDateCreated());
		managed.setIdea(comment.getIdea());
		managed.setProfile(comment.getProfile());
		return managed;

	}

	@Override
	public Comment create(Comment comment) {
		// start the transaction
		// write the customer to the database
		em.persist(comment);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		return comment;
	}

	@Override
	public CommentLike createLike(Comment comment, Profile profile, Boolean vote) {
		CommentLikeKey clk = new CommentLikeKey();
		clk.setComment(comment);
		clk.setProfile(profile);
		
		CommentLike cl = new CommentLike();
		cl.setId(clk);
		cl.setVote(vote);
		em.persist(cl);
		em.flush();
		return cl;
	}

	@Override
	public CommentLike updateLike(Comment comment, Profile profile, Boolean vote) {
		CommentLikeKey clk = new CommentLikeKey();
		clk.setComment(comment);
		clk.setProfile(profile);
		CommentLike managed = em.find(CommentLike.class, clk);
		managed.setVote(vote);
		return managed;
	}
	
	@Override
	public Comment makeActive(int id) {
		Comment managed = em.find(Comment.class, id);
		managed.setActive(true);
		return managed;
		
	}
	@Override
	public Comment makeInactive(int id) {
		Comment managed = em.find(Comment.class, id);
		managed.setActive(false);
		return managed;
	}
	
	@Override
	public Comment showComment(int id) {
		return em.find(Comment.class, id);
	}
	@Override
	public List<Comment> showCommentsByIdea(int ideaId){
		String sql = "select c from Comment c where idea.id = :ideaId";
		List<Comment> commentsByIdea = em.createNativeQuery(sql, Comment.class).setParameter("ideaId", ideaId).getResultList();
		return commentsByIdea;
	}

}
