package com.skilldistillery.ideas.data;

import java.util.List;

import com.skilldistillery.ideasjpa.entities.Idea;

public interface IdeaDAO {

	public boolean destroy(Idea idea);
	public Idea update(Idea idea);
	public Idea create(Idea idea);
	public List<Idea> showIdeasByProfile(int profileId);
	public Idea makeActive(int id);
	public Idea makeInactive(int id);
	public List<Idea> showAllIdeas();
}
