package com.skilldistillery.ideasjpa.entities;

//sql date does not need @Temporal annotation
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Profile {

	// Constructor
	public Profile() {

	}

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String bio;

	@Column(name = "profile_pic")
	private String profilePic;

	@Column(name = "created_date")
	private Date createdDate;
	
	private Boolean active;

	// @OneToMany(mappedBy = "profile")
	// private List<Idea> ideas;
	//
	// @OneToMany(mappedBy = "profile")
	// private List<Comment> comments;

	// private int reputation;
	//
	//
	// private int postCount;

	// Gets & Sets
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((bio == null) ? 0 : bio.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((profilePic == null) ? 0 : profilePic.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Profile other = (Profile) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (bio == null) {
			if (other.bio != null)
				return false;
		} else if (!bio.equals(other.bio))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (id != other.id)
			return false;
		if (profilePic == null) {
			if (other.profilePic != null)
				return false;
		} else if (!profilePic.equals(other.profilePic))
			return false;
		return true;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	// public List<Idea> getIdeas() {
	// return ideas;
	// }
	//
	// public void setIdeas(List<Idea> ideas) {
	// this.ideas = ideas;
	// }
	//
	// public List<Comment> getComments() {
	// return comments;
	// }
	//
	// public void setComments(List<Comment> comments) {
	// this.comments = comments;
	// }

	public int getId() {
		return id;
	}



	// public int getReputation() {
	// return reputation;
	// }
	//
	// public void setReputation(int reputation) {
	// this.reputation = reputation;
	// }
	//
	// public int getPostCount() {
	// return postCount;
	// }
	//
	// public void setPostCount(int postCount) {
	// this.postCount = postCount;
	// }

	// HashCode
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", user=" + user + ", bio=" + bio + ", profilePic=" + profilePic + ", createdDate="
				+ createdDate + ", active=" + active + "]";
	}

}
