package com.skilldistillery.ideas.data;

import com.skilldistillery.ideasjpa.entities.User;

public interface UserDAO {

	public boolean destroy(User user);
	public User update(User user);
	public User create(User user);
}
