package com.rcgstudio.citadels.entities;

import java.util.ArrayList;

import com.rcgstudio.core.entities.GameConfiguration;
import com.rcgstudio.core.interfaces.IUser;

public class CitadelsGameConfiguration extends GameConfiguration {

	private static final long serialVersionUID = 1L;
	private String NAME = "Citadels";
	private IUser _administrator;
	private int _numberOfDistricts;
	private ArrayList<CitadelsCharacter> _charactersList;

	public CitadelsGameConfiguration(IUser administrator, int numberOfPlayers, int numberOfDistricts, ArrayList<CitadelsCharacter> charactersList) {
		super(numberOfPlayers);
		_administrator = administrator;
		_numberOfDistricts = numberOfDistricts;
		_charactersList = charactersList;
	}

	@Override
	public String getGameTypeName() {
		return NAME;
	}

	public IUser getAdministrator() {
		return _administrator;
	}

	public int getNumberOfDistricts() {
		return _numberOfDistricts;
	}

	public ArrayList<CitadelsCharacter> getCharactersList() {
		return _charactersList;
	}
}
