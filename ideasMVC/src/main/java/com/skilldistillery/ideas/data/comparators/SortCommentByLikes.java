package com.skilldistillery.ideas.data.comparators;

import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.ideas.data.CommentDAOImpl;
import com.skilldistillery.ideas.data.IdeaDAOImpl;
import com.skilldistillery.ideasjpa.entities.Comment;

public class SortCommentByLikes implements Comparator<Comment> {

	private CommentDAOImpl dao;
	
	public SortCommentByLikes(CommentDAOImpl dao) {
		this.dao = dao;
	}
	
	@Override
	public int compare(Comment o1, Comment o2) {
		return dao.getLikes(o2) - dao.getLikes(o1);
	}

}
