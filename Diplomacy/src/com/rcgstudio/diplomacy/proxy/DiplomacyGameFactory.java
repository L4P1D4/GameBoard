package com.rcgstudio.diplomacy.proxy;

import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.diplomacy.entities.DiplomacyGame;
import com.rcgstudio.diplomacy.entities.DiplomacyGameConfiguration;
import com.rcgstudio.diplomacy.entities.DiplomacyMap;

public class DiplomacyGameFactory {

	public static DiplomacyGame createDiplomacyGame(String gameName, String gamePassword, IUser administrator, DiplomacyMap map,
			DiplomacyGameConfiguration gameConfiguration) {
		DiplomacyGame newGame = new DiplomacyGame(administrator, gameName, gamePassword, gameConfiguration);
		return newGame;
	}

}
