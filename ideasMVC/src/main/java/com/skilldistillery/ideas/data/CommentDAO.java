package com.skilldistillery.ideas.data;

import java.util.List;

import com.skilldistillery.ideasjpa.entities.Comment;
import com.skilldistillery.ideasjpa.entities.CommentLike;
import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.Profile;

public interface CommentDAO {

	public boolean destroy(Comment comment);
	public Comment update(Comment comment);
	public CommentLike createLike(Comment comment, Profile profile, Boolean vote);
	public CommentLike updateLike(Comment comment, Profile profile, Boolean vote);
	Comment showComment(int id);
	List<Comment> showCommentsByIdea(int ideaId);
	Comment makeInactive(int id);
	Comment makeActive(int id);
	List<Comment> sortCommentsByDateNewFirst(List<Comment> comments);
	List<Comment> sortCommentsByDateOldFirst(List<Comment> comments);
	int getLikes(Comment comment);
	int getDislikes(Comment comment);
	List<Comment> sortByLikes(List<Comment> comments);
	List<Comment> sortByDisikes(List<Comment> comments);
	List<Comment> sortByContreversy(List<Comment> comments);
	Comment create(Comment comment, Profile profile, Idea idea);
	public Comment assignLikes(Comment comment);
}
