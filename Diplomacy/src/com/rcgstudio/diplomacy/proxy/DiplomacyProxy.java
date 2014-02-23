package com.rcgstudio.diplomacy.proxy;

import java.util.HashMap;
import java.util.List;

import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IGameProxy;
import com.rcgstudio.core.interfaces.IMessaging;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.diplomacy.activities.DiplomacyGameConfigurationAct;
import com.rcgstudio.diplomacy.entities.DiplomacyGame;
import com.rcgstudio.diplomacy.entities.DiplomacyGameConfiguration;
import com.rcgstudio.diplomacy.entities.DiplomacyMap;
import com.rcgstudio.diplomacy.maps.classic.Classic;

public class DiplomacyProxy implements IGameProxy {

	static HashMap<String, DiplomacyMap> _mapsHashMap = new HashMap<String, DiplomacyMap>();
	static DiplomacyMap _classic = new Classic();
	static DiplomacyGameFactory _diplomacyGameFactory;
	static IMessaging _messagingProxy;

	public DiplomacyProxy(IMessaging messagingProxy) {
		_messagingProxy = messagingProxy;
	}

	public static DiplomacyMap getDiplomacyMap(String mapKey) {
		_mapsHashMap.put(_classic.GetMapKey(), _classic);
		return _mapsHashMap.get(mapKey);
	}

	public static IGame createDiplomacyGame(IUser administrator, String gameName, String gamePassword, DiplomacyMap mapSelected) {

		DiplomacyGameConfiguration gameConfiguration = new DiplomacyGameConfiguration(administrator.getId(), mapSelected.getCountries().size(), mapSelected);
		DiplomacyGame newGame = DiplomacyGameFactory.createDiplomacyGame(gameName, gamePassword, administrator, mapSelected, gameConfiguration);

		_messagingProxy.saveGame(newGame);

		return newGame;

	}

	public Class<?> getConfigureGameClass() {
		return DiplomacyGameConfigurationAct.class;
	}

	public String getGameName() {
		return "Diplomacy";
	}

	public Class<?> getGameClass() {
		return DiplomacyGame.class;
	}

	public Class<?> getNewGameConfigurationClass() {
		return DiplomacyGameConfigurationAct.class;
	}

	public Class<?> getWaitingGameClass() {
		return null;
	}

	public List<IGame> getOpenedGames(IUser user) {
		return _messagingProxy.getOpenedGames(user, getGameClass());
	}

	public boolean addPlayerToGame(IGame game, IUser player) {
		// TODO Auto-generated method stub
		return true;
	}

}
