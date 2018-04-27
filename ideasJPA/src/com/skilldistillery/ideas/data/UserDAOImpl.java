package com.skilldistillery.ideas.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.User;

public class UserDAOImpl implements UserDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ideas");
	private EntityManager em = emf.createEntityManager();
	@Override
	public boolean destroy(User user) {
		em.getTransaction().begin();
		User userToDelete = em.find(User.class, user.getId());
		Profile profileToDelete = em.find(Profile.class, userToDelete.getProfile().getId());
		if (userToDelete == null || profileToDelete == null) {
			em.getTransaction().commit();
			return false;
		}
		em.remove(profileToDelete);
		em.remove(userToDelete);
		em.flush();
		em.getTransaction().commit();
		return true;
	}
	@Override
	public User update(User user) {
		em.getTransaction().begin();
		User managed = em.find(User.class, user.getId());
		managed.setUsername(user.getUsername());
		managed.setPassword(user.getPassword());
		managed.setEmail(user.getEmail());
		em.flush();
		em.getTransaction().commit();
		return managed;
	}
	@Override
	public User create(User user) {
		// start the transaction
		Profile profile = new Profile();
		
		em.getTransaction().begin();
		em.persist(user);
		em.flush();
		profile.setUser(em.find(User.class, user.getId()));
		em.persist(profile);
		user.setProfile(profile);
		
		// write the customer to the database
		// update the "local" Customer object
		em.flush();
		
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();

		return user;
	}

}
