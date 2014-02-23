package com.rcgstudio.diplomacy.entities;

import com.rcgstudio.core.entities.Game;
import com.rcgstudio.core.enums.GameStatus;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.diplomacy.R;

public class DiplomacyGame extends Game {

	private static final long serialVersionUID = 1L;
	IUser _admin;

	public DiplomacyGame(IUser admin, String gameName, String gamePassword, DiplomacyGameConfiguration gameConfiguration) {
		super(gameName, gamePassword, R.drawable.diplomacyicon, gameConfiguration);
		_admin = admin;
		addPlayer(admin);
	}

	public Boolean getIsOpen() {
		if (_gameStatus == GameStatus.OPEN) {
			return true;
		}
		return false;
	}

	public void StartGame() {
		// TODO Auto-generated method stub

	}

	public void startGame() {
		// TODO Auto-generated method stub

	}

	public void addPlayer(IUser newPlayer) {
		newPlayer.addUserGame(this);
		addPlayerStatus(new DiplomacyPlayerStatus(newPlayer));
	}
}
