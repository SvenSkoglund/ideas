package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import com.skilldistillery.ideasjpa.entities.Idea;

public class SortIdeaByDateNewFirst implements Comparator<Idea> {

	@Override
	public int compare(Idea o1, Idea o2) {
		return - o1.getDateCreated().compareTo(o2.getDateCreated());
	}

}
