package com.rcgstudio.core.interfaces;

import java.io.Serializable;

public interface IGameConfiguration extends Serializable {

	public String getGameTypeName();
	
	public Integer getNumberOfPlayers();

}
