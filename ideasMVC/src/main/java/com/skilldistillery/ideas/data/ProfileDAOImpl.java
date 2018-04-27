package com.skilldistillery.ideas.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.skilldistillery.ideasjpa.entities.Profile;

@Transactional
@Component
public class ProfileDAOImpl implements ProfileDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean destroy(Profile profile) {
		Profile profileToDelete = em.find(Profile.class, profile.getId());
		if (profileToDelete == null) {
			return false;
		}
		System.out.println(profileToDelete);
		em.remove(profileToDelete);
		return true;
	}

	@Override
	public Profile update(Profile profile) {
		Profile managed = em.find(Profile.class, profile.getId());
		managed.setBio(profile.getBio());
		managed.setProfilePic(profile.getProfilePic());
		return managed;
	
	}


}
