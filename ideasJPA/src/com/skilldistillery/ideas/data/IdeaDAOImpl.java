package com.skilldistillery.ideas.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.IdeaLike;
import com.skilldistillery.ideasjpa.entities.CommentLikeKey;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.IdeaLike;
import com.skilldistillery.ideasjpa.entities.IdeaLikeKey;
import com.skilldistillery.ideasjpa.entities.Profile;

public class IdeaDAOImpl implements IdeaDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ideas");
	private EntityManager em = emf.createEntityManager();

	@Override
	public boolean destroy(Idea idea) {
		em.getTransaction().begin();
		Idea ideaToDelete = em.find(Idea.class, idea.getId());
		if (ideaToDelete == null) {
			em.getTransaction().commit();
			return false;
		}
		System.out.println(ideaToDelete);
		em.remove(ideaToDelete);
		em.flush();
		em.getTransaction().commit();
		return true;
	}

	@Override
	public Idea update(Idea idea) {
		em.getTransaction().begin();
		Idea managed = em.find(Idea.class, idea.getId());
		managed.setContent(idea.getContent());
		managed.setName(idea.getName());
		em.getTransaction().commit();
		return managed;

	}

	@Override
	public Idea makeActive(int id) {
		em.getTransaction().begin();
		Idea managed = em.find(Idea.class, id);
		managed.setActive(true);
		em.flush();
		em.getTransaction().commit();
		return managed;
		
	}
	@Override
	public Idea makeInactive(int id) {
		em.getTransaction().begin();
		Idea managed = em.find(Idea.class, id);
		managed.setActive(false);
		em.flush();
		em.getTransaction().commit();
		return managed;
	}
	@Override
	public Idea create(Idea idea) {
		// start the transaction
		em.getTransaction().begin();
		// write the customer to the database
		List<Comment> comments = new ArrayList<>();
		idea.setComments(comments);
		em.persist(idea);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();
		return idea;
	}
	@Override
	public IdeaLike createLike(Idea idea, Profile profile, Boolean vote) {
		em.getTransaction().begin();
		IdeaLikeKey ilk = new IdeaLikeKey();
		ilk.setIdea(idea);
		ilk.setProfile(profile);
		
		IdeaLike il = new IdeaLike();
		il.setId(ilk);
		il.setVote(vote);
		em.persist(il);
		em.flush();
		em.getTransaction().commit();
		return il;
	}

	@Override
	public IdeaLike updateLike(Idea idea, Profile profile, Boolean vote) {
		em.getTransaction().begin();
		IdeaLikeKey ilk = new IdeaLikeKey();
		ilk.setIdea(idea);
		ilk.setProfile(profile);
		IdeaLike managed = em.find(IdeaLike.class, ilk);
		managed.setVote(vote);
		em.flush();
		em.getTransaction().commit();
		return managed;
	}
	@Override
	public Idea showIdea(int id ) {
		return em.find(Idea.class, id);
	}
}
