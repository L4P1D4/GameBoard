package com.rcgstudio.core.interfaces;

public interface IGameData extends IEntity{

	public long getDataOrder();
	
	public IUser getPlayer();
	
	public IGameMove getGameDataMove();
}
