package com.skilldistillery.ideas.data;

import java.util.List;

import com.skilldistillery.ideasjpa.entities.Idea;
import com.skilldistillery.ideasjpa.entities.User;

public interface UserDAO {

	public boolean destroy(User user);
	public User update(User user);
	public User create(User user);
	User makeActive(int id);
	User makeInactive(int id);
	User findUserByUsernameAndPassword(String username, String password);
}
