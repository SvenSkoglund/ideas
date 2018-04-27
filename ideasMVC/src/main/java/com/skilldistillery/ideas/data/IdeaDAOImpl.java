package com.skilldistillery.ideas.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.ideasjpa.entities.Idea;
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
		managed.setComments(idea.getComments());
		managed.setContent(idea.getContent());
		managed.setDateCreated(idea.getDateCreated());
		managed.setName(idea.getName());
		managed.setProfile(idea.getProfile());
		em.flush();
		em.getTransaction().commit();
		return managed;

	}

	@Override
	public Idea create(Idea idea) {
		// start the transaction
		em.getTransaction().begin();
		// write the customer to the database
		em.persist(idea);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();
		return idea;
	}

}
