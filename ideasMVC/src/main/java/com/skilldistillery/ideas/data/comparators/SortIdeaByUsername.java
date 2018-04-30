package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import com.skilldistillery.ideas.data.IdeaDAOImpl;
import com.skilldistillery.ideasjpa.entities.Idea;

public class SortIdeaByUsername implements Comparator<Idea> {

	@Override
	public int compare(Idea o1, Idea o2) {
		return o1.getProfile().getUser().getUsername().compareTo(o2.getProfile().getUser().getUsername());
	}

}
