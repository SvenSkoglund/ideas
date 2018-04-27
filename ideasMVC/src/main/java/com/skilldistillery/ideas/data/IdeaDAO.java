package com.skilldistillery.ideas.data;

import com.skilldistillery.ideasjpa.entities.Idea;

public interface IdeaDAO {

	public boolean destroy(Idea idea);
	public Idea update(Idea idea);
	public Idea create(Idea idea);
}
