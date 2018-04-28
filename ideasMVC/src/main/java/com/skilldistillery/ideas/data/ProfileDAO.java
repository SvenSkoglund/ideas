package com.skilldistillery.ideas.data;

import java.util.List;

import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Profile;

public interface ProfileDAO {

	public boolean destroy(Profile profile);
	public Profile update(Profile profile);
//	public Profile create(Profile profile);
	Profile showProfile(int id);
	Profile makeActive(int id);
	Profile makeInactive(int id);
}
