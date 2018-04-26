package com.skilldistillery.ideasjpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="idea_like")
public class IdeaLike {
	// Constructor
	private IdeaLike() {
		
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
	@JoinColumn(name="idea_id")
	private Idea idea;
	
	
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

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	// HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idea == null) ? 0 : idea.hashCode());
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
		IdeaLike other = (IdeaLike) obj;
		if (idea == null) {
			if (other.idea != null)
				return false;
		} else if (!idea.equals(other.idea))
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
		return "IdeaLike [profileId=" + profileId + ", ideaId=" + ideaId + ", like=" + like + ", profile=" + profile
				+ ", idea=" + idea + "]";
	}

	

	
	
	
	
	
	
	
}
