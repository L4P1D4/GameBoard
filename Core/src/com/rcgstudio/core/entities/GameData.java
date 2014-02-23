package com.rcgstudio.core.entities;

import com.rcgstudio.core.interfaces.IGameData;
import com.rcgstudio.core.interfaces.IGameMove;
import com.rcgstudio.core.interfaces.IUser;

public class GameData extends Entity implements IGameData {

	private static final long serialVersionUID = 1L;
	protected long _order;
	protected IGameMove _gameMove; 
	protected IUser _user;

	public GameData(Long order, IGameMove gameMove, IUser user) {
		super(-1);
		_order = order;
		_gameMove = gameMove;
		_user = user;
	}
	
	public long getDataOrder() {
		return _order;
	}

	public IUser getPlayer() {
		return _user;
	}
	
	public IGameMove getGameDataMove() {
		return _gameMove;
	}
}
