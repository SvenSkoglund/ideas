package com.skilldistillery.ideas.data;

import com.skilldistillery.ideasjpa.entities.Profile;

public interface ProfileDAO {

	public boolean destroy(Profile profile);
	public Profile update(Profile profile);
	public Profile create(Profile profile);
}
