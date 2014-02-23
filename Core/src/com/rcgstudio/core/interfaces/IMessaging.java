package com.rcgstudio.core.interfaces;

import java.util.List;


public interface IMessaging {

	public IUser getUser(String userName, String password);

	public List<IGame> getOpenedGames(IUser user, Class<?> gameType);
	
	public boolean saveGame(IGame game);
	
	public IUser saveUser(IUser user);
}
