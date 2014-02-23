package com.rcgstudio.tictactoe.entities;

import com.rcgstudio.core.entities.PlayerStatus;
import com.rcgstudio.core.interfaces.IUser;

public class TicTacToePlayerStatus extends PlayerStatus {

	private static final long serialVersionUID = 1L;
	private boolean _isReady;
	private int _iconId;

	public TicTacToePlayerStatus(IUser user) {
		super(user);
	}

	public boolean isReady() {
		return _isReady;
	}

	public void setIcon(int iconId) {
		_iconId = iconId;
	}

	public int getIcon() {
		return _iconId;
	}

}
