package com.skilldistillery.ideas.data;

import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.IdeaLike;
import com.skilldistillery.ideasjpa.entities.Profile;

public interface IdeaDAO {

	public boolean destroy(Idea idea);
	public Idea update(Idea idea);
	public Idea create(Idea idea);
	IdeaLike createLike(Idea idea, Profile profile, Boolean vote);
	IdeaLike updateLike(Idea idea, Profile profile, Boolean vote);
	Idea showIdea(int id);
	Idea makeActive(int id);
	Idea makeInactive(int id);
}
