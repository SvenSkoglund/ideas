package com.skilldistillery.ideas.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.ideas.data.comparators.SortCommentByContreversy;
import com.skilldistillery.ideas.data.comparators.SortCommentByDateNewFirst;
import com.skilldistillery.ideas.data.comparators.SortCommentByDateOldFirst;
import com.skilldistillery.ideas.data.comparators.SortCommentByDislikes;
import com.skilldistillery.ideas.data.comparators.SortCommentByLikes;
import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.CommentLike;
import com.skilldistillery.ideasjpa.entities.CommentLikeKey;
import com.skilldistillery.ideasjpa.entities.Idea;
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
	public Comment create(Comment comment, Profile profile, Idea idea) {
		// start the transaction
		comment.setProfile(profile);
		comment.setIdea(idea);
		comment.setActive(true);
		em.persist(comment);
		em.flush();
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
	public List<Comment> showCommentsByIdea(int ideaId) {
		String sql = "select c from Comment c where idea.id = :ideaId AND c.active = 1";
		List<Comment> commentsByIdea = em.createQuery(sql, Comment.class).setParameter("ideaId", ideaId)
				.getResultList();
		return commentsByIdea;
	}

	@Override
	public List<Comment> sortCommentsByDateNewFirst(List<Comment> comments) {
		SortCommentByDateNewFirst newFirst = new SortCommentByDateNewFirst();
		comments.sort(newFirst);
		return comments;

	}

	@Override
	public List<Comment> sortCommentsByDateOldFirst(List<Comment> comments) {
		SortCommentByDateOldFirst oldFirst = new SortCommentByDateOldFirst();
		comments.sort(oldFirst);
		return comments;

	}

	@Override
	public int getLikes(Comment comment) {
		int commentId = comment.getId();
		int likeCount;
		String sql = "select cl from CommentLike cl where cl.id.comment.id = :commentId and cl.vote = true";
		List<CommentLike> likes = em.createQuery(sql, CommentLike.class).setParameter("commentId", commentId).getResultList();
		if (!likes.isEmpty()) {
			likeCount = likes.size();
		} else {
			likeCount = 0;
		}
		return likeCount;
	}

	@Override
	public int getDislikes(Comment comment) {
		int commentId = comment.getId();
		int dislikeCount;
		String sql = "select cl from CommentLike cl where cl.id.comment.id = :commentId and cl.vote = false";
		List<CommentLike> dislikes = em.createQuery(sql, CommentLike.class).setParameter("commentId", commentId)
				.getResultList();
		if (!dislikes.isEmpty()) {
			dislikeCount = dislikes.size();
		} else {
			dislikeCount = 0;
		}
		return dislikeCount;
	}

	@Override
	public List<Comment> sortByLikes(List<Comment> comments) {
		SortCommentByLikes mostLikes = new SortCommentByLikes(this);
		comments.sort(mostLikes);
		return comments;
	}

	@Override
	public List<Comment> sortByDisikes(List<Comment> comments) {
		SortCommentByDislikes mostDislikes = new SortCommentByDislikes(this);
		comments.sort(mostDislikes);
		return comments;
	}

	@Override
	public List<Comment> sortByContreversy(List<Comment> comments) {
		SortCommentByContreversy byContreversy = new SortCommentByContreversy(this);
		comments.sort(byContreversy);
		return comments;
	}
}
