package com.rcgstudio.core.interfaces;

import java.io.Serializable;

public interface IPlayerStatus extends Serializable{

	public IUser getUser();
	
	Boolean hasTurn();
	
	void setHasTurn(boolean hasTurn);
	
	void setPlayerNumber(int playerNumber);
	
	int getPlayerNumber();
}
