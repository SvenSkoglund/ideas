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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String bio;

	@Column(name = "profile_pic")
	private String profilePic;

	@Column(name = "created_date")
	private Date createdDate;

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
	
	// toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Profile [id=").append(id).append(", bio=").append(bio)
				.append(", profilePic=").append(profilePic).append(", createdDate=").append(createdDate).append("]");
		return builder.toString();
	}
	
}
