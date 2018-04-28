package com.skilldistillery.ideas.data;

import java.util.List;

import com.skilldistillery.ideasjpa.entities.Idea;

public interface IdeaDAO {

	public boolean destroy(Idea idea);
	public Idea update(Idea idea);
	public Idea create(Idea idea);
	List<Idea> showIdeasByProfile(int profileId);
	Idea makeActive(int id);
	Idea makeInactive(int id);
}
