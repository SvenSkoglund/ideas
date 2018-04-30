package com.skilldistillery.ideas.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.ideasjpa.entities.User;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Profile;
import com.skilldistillery.ideasjpa.entities.User;

@Transactional
@Component
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean destroy(User user) {
		User userToDelete = em.find(User.class, user.getId());
		Profile profileToDelete = em.find(Profile.class, userToDelete.getProfile().getId());
		if (userToDelete == null || profileToDelete == null) {
			return false;
		}
		em.remove(profileToDelete);
		em.remove(userToDelete);
		return true;
	}

	@Override
	public User update(User user) {
		User managed = em.find(User.class, user.getId());
		managed.setUsername(user.getUsername());
		managed.setPassword(user.getPassword());
		managed.setEmail(user.getEmail());
		return managed;
	}

	@Override
	public User makeActive(int id) {
		User managed = em.find(User.class, id);
		managed.setActive(true);
		return managed;

	}

	@Override
	public User makeInactive(int id) {
		User managed = em.find(User.class, id);
		managed.setActive(false);
		return managed;
	}

	@Override
	public User create(User user) {
		// start the transaction
		Profile profile = new Profile();

		em.persist(user);
		em.flush();
		profile.setUser(em.find(User.class, user.getId()));
		em.persist(profile);
		user.setProfile(profile);

		// write the customer to the database
		// update the "local" Customer object
		em.flush();

		// commit the changes (actually perform the operation)

		return user;
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		User user = null;
		String sql = "select u from User u where u.username = :username and u.password = :password";
		List<User> matchingUsers = em.createQuery(sql, User.class).setParameter("username", username)
				.setParameter("password", password).getResultList();
		if (matchingUsers.size() > 0) {
			user = matchingUsers.get(0);
		} else {
			user = null;
		}
		return user;
	}

}
