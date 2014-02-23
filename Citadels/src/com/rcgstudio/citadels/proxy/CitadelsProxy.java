package com.rcgstudio.citadels.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rcgstudio.citadels.activities.CitadelsGameConfigurationAct;
import com.rcgstudio.citadels.activities.CitadelsGameWaitingAct;
import com.rcgstudio.citadels.entities.CitadelsCharacter;
import com.rcgstudio.citadels.entities.CitadelsDistrict;
import com.rcgstudio.citadels.entities.CitadelsGame;
import com.rcgstudio.citadels.entities.CitadelsGameConfiguration;
import com.rcgstudio.core.entities.GameConfiguration;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IGameProxy;
import com.rcgstudio.core.interfaces.IMessaging;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.core.utils.MathUtils;

public class CitadelsProxy implements IGameProxy {
	
	private static Map<Class<?>, CitadelsCharacter> _allCharactersMap;
	static ArrayList<CitadelsDistrict> _allDistricts;
	static ArrayList<CitadelsCharacter> _allCharactersList;
	static IMessaging _messagingProxy;
	
	public CitadelsProxy(IMessaging messagingProxy) {
		_allCharactersMap = CharactersFactory.getAllCharacters();
		_allCharactersList = allCharactersToList(_allCharactersMap);
		_allDistricts = DistrictsFactory.getAllDistricts();
		_messagingProxy = messagingProxy;
	}

	private ArrayList<CitadelsCharacter> allCharactersToList(Map<Class<?>, CitadelsCharacter> charactersMap) {
		ArrayList<CitadelsCharacter> charactersList = new ArrayList<CitadelsCharacter>();
		for (CitadelsCharacter character : charactersMap.values()) {
			charactersList.add(character);
		}
		return charactersList;
	}

	public static IGame createCitadelsGame(IUser administrator, String gameName, String gamePassword, int numberOfPlayers, int numberOfDistricts,
			ArrayList<CitadelsCharacter> charactersList) {
		GameConfiguration gameConfiguration = new CitadelsGameConfiguration(administrator, numberOfPlayers, numberOfDistricts, charactersList);
		CitadelsGame newGame = new CitadelsGame(administrator, gameName, gamePassword, gameConfiguration);
		
		_messagingProxy.saveGame(newGame);
		
		return newGame;
	}
	
	public static CitadelsCharacter getCharacter(Class<? extends CitadelsCharacter> characterClass)
	{
		return _allCharactersMap.get(characterClass);
	}
	
	public static ArrayList<CitadelsDistrict> getAllDistricts()
	{
		return _allDistricts;
	}
	
	public static ArrayList<CitadelsCharacter> getAllCharacters()
	{
		return _allCharactersList;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<CitadelsCharacter> getRandomCharacters(int numberOfCharacters)
	{
		ArrayList<CitadelsCharacter> randomChars = new ArrayList<CitadelsCharacter>(numberOfCharacters);
		ArrayList<CitadelsCharacter> tempAllChars = (ArrayList<CitadelsCharacter>)_allCharactersList.clone();
		for (int i = 0; i < numberOfCharacters; i++) {
			randomChars.add(MathUtils.drawRandomFromList(tempAllChars));
		}
		
		return randomChars;
	}

	public Class<?> getConfigureGameClass() {
		return CitadelsGameConfigurationAct.class;
	}
	
	public Class<?> getGameClass() {
		return CitadelsGame.class;
	}
	
	public Class<?> getNewGameConfigurationClass() {
		return CitadelsGameConfigurationAct.class;
	}
	
	public Class<?> getWaitingGameClass() {
		return CitadelsGameWaitingAct.class;
	}
	
	public String getGameName(){
		return "Citadels";
	}
	
	public static void saveGame(IGame game)
	{
		_messagingProxy.saveGame(game);
	}
	
	public List<IGame> getOpenedGames(IUser user) {
		return _messagingProxy.getOpenedGames(user, getGameClass());
	}

	public boolean addPlayerToGame(IGame game, IUser player) {
		game.addPlayer(player);
		_messagingProxy.saveUser(player);
		_messagingProxy.saveGame(game);
		return true;
	}
	
	public IGame startGame(IGame game)
	{
		game.startGame();
		_messagingProxy.saveGame(game);
		return game;
	}

}
