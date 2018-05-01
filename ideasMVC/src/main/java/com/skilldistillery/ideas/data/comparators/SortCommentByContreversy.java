package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import com.skilldistillery.ideas.data.CommentDAOImpl;
import com.skilldistillery.ideasjpa.entities.Comment;

public class SortCommentByContreversy implements Comparator<Comment> {

	private CommentDAOImpl dao;

	public SortCommentByContreversy(CommentDAOImpl dao) {
		this.dao = dao;
	}

	@Override
	public int compare(Comment o1, Comment o2) {
		int o1difference = Math.abs(dao.getLikes(o1) - dao.getDislikes(o1));
		int o2difference = Math.abs(dao.getLikes(o2) - dao.getDislikes(o2));
		
		if (dao.getDislikes(o1) == 0 & dao.getLikes(o1) == 0) {
			return 1;
		}
		
		if (o1difference > o2difference) {
			return 1;
		} else if (o1difference < o2difference) {
			return -1;
		} else {
			return 0;
		}

	}

}
