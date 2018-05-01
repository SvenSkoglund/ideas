package com.skilldistillery.ideas.data;

import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.IdeaLike;
import com.skilldistillery.ideasjpa.entities.Profile;

public interface IdeaDAO {

	public boolean destroy(Idea idea);
	public Idea update(Idea idea);
	public List<Idea> showIdeasByProfile(int profileId);
	public Idea makeActive(int id);
	public Idea makeInactive(int id);
	public List<Idea> showAllIdeas();
	Idea showIdea(int id);
	IdeaLike createLike(Idea idea, Profile profile, Boolean vote) throws MySQLIntegrityConstraintViolationException;
	IdeaLike updateLike(Idea idea, Profile profile, Boolean vote);
	List<Idea> sortIdeasByDateNewFirst(List<Idea> ideas);
	int getLikes(Idea idea);
	int getDislikes(Idea idea);
	List<Idea> sortByLikes(List<Idea> ideas);
	List<Idea> sortByDisikes(List<Idea> ideas);
	List<Idea> sortByUsername(List<Idea> ideas);
	List<Idea> sortByContreversy(List<Idea> ideas);
	List<Idea> sortIdeasByDateOldFirst(List<Idea> ideas);
	Idea create(Idea idea, Profile profile);
	public Idea assignLikes(Idea idea);
	public List<Idea> searchIdea(String ideaKeyword);
}
