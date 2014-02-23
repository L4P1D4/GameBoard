package com.rcgstudio.core.entities;

import com.rcgstudio.core.interfaces.IPlayerStatus;
import com.rcgstudio.core.interfaces.IUser;

public class PlayerStatus implements IPlayerStatus {

	private IUser _user;
	private boolean _hasTurn;
	private int _playerNumber;

	private static final long serialVersionUID = 1L;

	public PlayerStatus(IUser user) {
		_user = user;
	}

	public IUser getUser() {
		return _user;
	}

	public Boolean hasTurn() {
		return _hasTurn;
	}
	
	public void setHasTurn(boolean hasTurn) {
		_hasTurn = hasTurn;
	}

	public int getPlayerNumber() {
		return _playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		_playerNumber = playerNumber;
	}
}
