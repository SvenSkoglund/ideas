package com.skilldistillery.ideasjpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment_like")
public class CommentLike {
	// Constructor
	private CommentLike() {
		
	}
	
	// Fields
	@Column(name="profile_id")
	private int profileId;

	@Column(name="idea_id")
	private int ideaId;
	
	private boolean like;
	
	@ManyToOne
	@JoinColumn(name="profile_id")
	private Profile profile;

	@ManyToOne
	@JoinColumn(name="comment_id")
	private Comment comment;
	
	
	//Gets & Sets
	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public int getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(int ideaId) {
		this.ideaId = ideaId;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	//HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ideaId;
		result = prime * result + (like ? 1231 : 1237);
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		result = prime * result + profileId;
		return result;
	}

	// .equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentLike other = (CommentLike) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (ideaId != other.ideaId)
			return false;
		if (like != other.like)
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		if (profileId != other.profileId)
			return false;
		return true;
	}
	
	// toString
	@Override
	public String toString() {
		return "CommentLike [profileId=" + profileId + ", ideaId=" + ideaId + ", like=" + like + ", profile=" + profile
				+ ", comment=" + comment + "]";
	}


	
	
	
	
	
}
