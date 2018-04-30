package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import com.skilldistillery.ideasjpa.entities.Comment;

public class SortCommentByDateOldFirst implements Comparator<Comment> {

	@Override
	public int compare(Comment o1, Comment o2) {
		return -o1.getDateCreated().compareTo(o2.getDateCreated());
	}

}
