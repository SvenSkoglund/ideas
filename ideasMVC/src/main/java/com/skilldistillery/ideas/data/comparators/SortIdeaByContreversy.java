package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import com.skilldistillery.ideas.data.IdeaDAOImpl;
import com.skilldistillery.ideasjpa.entities.Idea;

public class SortIdeaByContreversy implements Comparator<Idea> {

	private IdeaDAOImpl dao;

	public SortIdeaByContreversy(IdeaDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	public int compare(Idea o1, Idea o2) {
		return (dao.getLikes(o1) - dao.getDislikes(o1)) - (dao.getLikes(o2) - dao.getDislikes(o2));
	}

}
