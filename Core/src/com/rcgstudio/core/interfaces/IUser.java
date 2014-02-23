package com.rcgstudio.core.interfaces;

import java.util.List;

public interface IUser extends IEntity {

	public String getName();

//	public void setName(String string);

	public String getEmail();

//	public void setEmail(String string);

	public String getPassword();

//	public void setPassword(String password);

	public List<IGame> getUserGameList();

	public void addUserGame(IGame gameToAdd);
	
	public Integer getScore(Class<?> gameClass);
}
