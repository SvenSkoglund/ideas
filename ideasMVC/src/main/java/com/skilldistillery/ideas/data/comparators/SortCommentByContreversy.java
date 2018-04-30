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
		return (dao.getLikes(o1) - dao.getDislikes(o1)) - (dao.getLikes(o2) - dao.getDislikes(o2));
	}

}
