package com.skilldistillery.ideas.data;

import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.CommentLike;
import com.skilldistillery.ideasjpa.entities.Profile;

public interface CommentDAO {

	public boolean destroy(Comment comment);
	public Comment update(Comment comment);
	public Comment create(Comment comment);
	public CommentLike createLike(Comment comment, Profile profile, Boolean vote);
	public CommentLike updateLike(Comment comment, Profile profile, Boolean vote);
}
