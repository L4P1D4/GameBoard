package com.rcgstudio.tictactoe.entities;

import com.rcgstudio.core.entities.GameConfiguration;

public class TicTacToeGameConfiguration extends GameConfiguration {

	private static final long serialVersionUID = 1L;
	private long _administratorId;

	public TicTacToeGameConfiguration(long administratorId, int numberOfPlayers) {
		super(numberOfPlayers);
		_administratorId = administratorId;
	}

	private String NAME = "Diplomacy";

	@Override
	public String getGameTypeName() {
		return NAME;
	}

	public Long getAdministratorId() {
		return _administratorId;
	}
}
