package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import com.skilldistillery.ideas.data.IdeaDAOImpl;
import com.skilldistillery.ideasjpa.entities.Idea;

public class SortIdeaByDislikes implements Comparator<Idea> {

	private IdeaDAOImpl dao;

	public SortIdeaByDislikes(IdeaDAOImpl dao) {
		this.dao = dao;

	}

	@Override
	public int compare(Idea o1, Idea o2) {
		IdeaDAOImpl dao = new IdeaDAOImpl();
		return dao.getDislikes(o1) - dao.getDislikes(o2);
	}

}
