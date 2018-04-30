package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.ideas.data.IdeaDAOImpl;
import com.skilldistillery.ideasjpa.entities.Idea;

public class SortIdeaByLikes implements Comparator<Idea> {

	private IdeaDAOImpl dao;
	
	public SortIdeaByLikes(IdeaDAOImpl dao) {
		this.dao = dao;
	}
	
	@Override
	public int compare(Idea o1, Idea o2) {
		return dao.getLikes(o1) - dao.getLikes(o2);
	}

}
