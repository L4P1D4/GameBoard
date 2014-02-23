package com.rcgstudio.diplomacy.entities;

import com.rcgstudio.core.entities.GameConfiguration;

public class DiplomacyGameConfiguration extends GameConfiguration {

	private static final long serialVersionUID = 1L;
	private long _administratorId;
	private DiplomacyMap _diplomacyMap;

	public DiplomacyGameConfiguration(long administratorId, int numberOfPlayers, DiplomacyMap diplomacyMap) {
		super(numberOfPlayers);
		_administratorId = administratorId;
		_diplomacyMap = diplomacyMap;
	}

	private String NAME = "Diplomacy";

	@Override
	public String getGameTypeName() {
		return NAME;
	}

	public Long getAdministratorId() {
		return _administratorId;
	}
	
	public DiplomacyMap getMap() {
		return _diplomacyMap;
	}
}
