package com.skilldistillery.ideas.data;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.skilldistillery.ideasjpa.entities.User;

public interface UserDAO {

	public boolean destroy(User user);
	public User update(User user) throws MySQLIntegrityConstraintViolationException;
	public User create(User user);
	User makeActive(int id);
	User makeInactive(int id);
	User findUserByUsernameAndPassword(String username, String password);
	Boolean checkForExistingUsername(String username);
	Boolean checkForExistingEmail(String email);
}
