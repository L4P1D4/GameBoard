package com.rcgstudio.core.entities;

import com.rcgstudio.core.interfaces.IGameConfiguration;

public abstract class GameConfiguration implements IGameConfiguration {

	private static final long serialVersionUID = 1L;
	private int _numberOfPlayers;

	public GameConfiguration(int numberOfPlayers)
	{
		_numberOfPlayers = numberOfPlayers;
	}
	
	public abstract String getGameTypeName();

	public Integer getNumberOfPlayers() {
		return _numberOfPlayers;

	}

}
