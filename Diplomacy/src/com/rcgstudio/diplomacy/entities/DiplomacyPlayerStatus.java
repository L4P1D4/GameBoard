package com.rcgstudio.diplomacy.entities;

import com.rcgstudio.core.entities.PlayerStatus;
import com.rcgstudio.core.interfaces.IUser;

public class DiplomacyPlayerStatus extends PlayerStatus {

	private static final long serialVersionUID = 1L;
	private boolean _hasTurn;
	private boolean _isReady;
	private boolean _isSaved;
	private DiplomacyCountry _countrySelected;

	public DiplomacyPlayerStatus(IUser user) {
		super(user);
	}

	public Boolean hasTurn() {
		return _hasTurn;
	}

	public boolean isReady() {
		return _isReady;
	}

	public boolean getIsSaved() {
		return _isSaved;
	}

	public DiplomacyCountry getCountrySelected() {
		return _countrySelected;
	}
}
