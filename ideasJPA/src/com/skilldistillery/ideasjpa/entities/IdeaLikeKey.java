package com.skilldistillery.ideasjpa.entities;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class IdeaLikeKey implements Serializable{
	public IdeaLikeKey() {
		
	}
	
	@ManyToOne
	@JoinColumn(name = "idea_id")
	private Idea idea;

	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;

	public Idea getIdea() {
		return idea;
	}

	public void setIdea(Idea idea) {
		this.idea = idea;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idea == null) ? 0 : idea.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdeaLikeKey other = (IdeaLikeKey) obj;
		if (idea == null) {
			if (other.idea != null)
				return false;
		} else if (!idea.equals(other.idea))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdeaLikeKey [idea=" + idea + ", profile=" + profile + "]";
	}
	
	
}
