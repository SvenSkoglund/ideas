package com.skilldistillery.ideas.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.ideasjpa.entities.User;

public class UserDAOImpl implements UserDAO {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ideas");
	private EntityManager em = emf.createEntityManager();
	@Override
	public boolean destroy(User user) {
		em.getTransaction().begin();
		User userToDelete = em.find(User.class, user.getId());
		if (userToDelete == null) {
			em.getTransaction().commit();
			return false;
		}
		System.out.println(userToDelete);
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
		em.getTransaction().begin();
		// write the customer to the database
		em.persist(user);
		// update the "local" Customer object
		em.flush();
		// commit the changes (actually perform the operation)
		em.getTransaction().commit();

		return user;
	}

}
