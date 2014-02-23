package com.rcgstudio.core.interfaces;

import java.util.List;

public interface IGameProxy extends IProxy{

	public Class<?> getConfigureGameClass();
	
	public Class<?> getNewGameConfigurationClass();
	
	public Class<?> getWaitingGameClass();
	
	public Class<?> getGameClass();
	
	public String getGameName();
	
	public List<IGame> getOpenedGames(IUser user);
	
	public boolean addPlayerToGame(IGame game, IUser player);
	
}
