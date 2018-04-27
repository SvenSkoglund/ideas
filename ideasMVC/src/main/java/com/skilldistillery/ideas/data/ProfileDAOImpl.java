package com.skilldistillery.ideas.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.Profile;

public class ProfileDAOImpl implements ProfileDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ideas");
	private EntityManager em = emf.createEntityManager();

	@Override
	public boolean destroy(Profile profile) {
		em.getTransaction().begin();
		Profile profileToDelete = em.find(Profile.class, profile.getId());
		if (profileToDelete == null) {
			em.getTransaction().commit();
			return false;
		}
		System.out.println(profileToDelete);
		em.remove(profileToDelete);
		em.flush();
		em.getTransaction().commit();
		return true;
	}

	@Override
	public Profile update(Profile profile) {
		em.getTransaction().begin();
		Profile managed = em.find(Profile.class, profile.getId());
		managed.setBio(profile.getBio());
		managed.setProfilePic(profile.getProfilePic());
		em.flush();
		em.getTransaction().commit();
		return managed;
	
	}

	@Override
	public Profile create(Profile profile) {
		// start the transaction
		em.getTransaction().begin();
		// write the customer to the database
		em.persist(profile);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();	
		return profile;
	}

}
