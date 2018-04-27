package com.skilldistillery.ideasjpa.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="idea_like")
public class IdeaLike {
	// Constructor
	public IdeaLike() {
		
	}
	
	// Fields
	@EmbeddedId
	private IdeaLikeKey id;
	
	private Boolean vote;

	public IdeaLikeKey getId() {
		return id;
	}

	public void setId(IdeaLikeKey id) {
		this.id = id;
	}

	public Boolean isVote() {
		return vote;
	}

	public void setVote(Boolean vote) {
		this.vote = vote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (vote ? 1231 : 1237);
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
		IdeaLike other = (IdeaLike) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (vote != other.vote)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IdeaLike [id=").append(id).append(", vote=").append(vote).append("]");
		return builder.toString();
	}
	
	
	//Gets & Sets
	
}
