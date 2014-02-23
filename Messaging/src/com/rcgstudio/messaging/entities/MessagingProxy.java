package com.rcgstudio.messaging.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rcgstudio.citadels.proxy.CitadelsProxy;
import com.rcgstudio.core.entities.User;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IMessaging;
import com.rcgstudio.core.interfaces.IProxy;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.diplomacy.entities.DiplomacyMap;
import com.rcgstudio.diplomacy.enums.DiplomacyMaps;
import com.rcgstudio.diplomacy.proxy.DiplomacyProxy;
import com.rcgstudio.tictactoe.proxy.TicTacToeProxy;

public class MessagingProxy implements IMessaging, IProxy {

	HashMap<String, IUser> _usersList = new HashMap<String, IUser>();
	List<IGame> _gamesList = new ArrayList<IGame>();
	int _entityCounter;

	public MessagingProxy() {
	}

	public void populateMockDatabase() {
		populateUsers();
		populateGames();
	}

	private void populateGames() {

		DiplomacyMap classic = DiplomacyProxy
				.getDiplomacyMap(DiplomacyMaps.CLASSIC.toString());
		IUser user1 = getUser("user1", "user1");
		IUser user2 = getUser("user2", "user2");
		IUser user3 = getUser("user3", "user3");
		IUser user4 = getUser("user4", "user4");
		IUser user5 = getUser("user5", "user5");
		IUser user6 = getUser("user6", "user6");
		// IUser user7 = getUser("user7", "user7");
		// IUser user8 = getUser("user8", "user8");

		DiplomacyProxy.createDiplomacyGame(user1, "Juego 1", "password",
				classic).setId(_entityCounter++);
		DiplomacyProxy.createDiplomacyGame(user1, "Juego 2", null, classic)
				.setId(_entityCounter++);
		DiplomacyProxy.createDiplomacyGame(user2, "Juego 3", "password",
				classic).setId(_entityCounter++);
		CitadelsProxy.createCitadelsGame(user1, "Juego 4", "test", 6, 8,
				CitadelsProxy.getRandomCharacters(8)).setId(_entityCounter++);
		DiplomacyProxy.createDiplomacyGame(user1, "Juego 5", "test", classic)
				.setId(_entityCounter++);
		DiplomacyProxy.createDiplomacyGame(user2, "Juego 6", null, classic)
				.setId(_entityCounter++);
		CitadelsProxy.createCitadelsGame(user2, "Juego 7", "test", 6, 8,
				CitadelsProxy.getRandomCharacters(8)).setId(_entityCounter++);
		CitadelsProxy.createCitadelsGame(user1, "Juego 8", "test", 6, 8,
				CitadelsProxy.getRandomCharacters(8)).setId(_entityCounter++);
		DiplomacyProxy.createDiplomacyGame(user1, "Juego 9", "password",
				classic).setId(_entityCounter++);
		DiplomacyProxy.createDiplomacyGame(user1, "Juego 10", null, classic)
				.setId(_entityCounter++);
		IGame game11 = CitadelsProxy.createCitadelsGame(user2, "Juego 11",
				null, 6, 8, CitadelsProxy.getRandomCharacters(8));
		game11.setId(_entityCounter++);
		game11.addPlayer(user3);
		game11.addPlayer(user4);
		game11.addPlayer(user5);
		game11.addPlayer(user6);
		TicTacToeProxy.createTicTacToeGame(user2, "TicTacToe1", null);

	}

	private void populateUsers() {
		IUser user1 = new User(1, "user1", "user1", "test@test.com");
		IUser user2 = new User(2, "user2", "user2", "test@test.com");
		IUser user3 = new User(3, "user3", "user3", "test@test.com");
		IUser user4 = new User(4, "user4", "user4", "test@test.com");
		IUser user5 = new User(5, "user5", "user5", "test@test.com");
		IUser user6 = new User(6, "user6", "user6", "test@test.com");
		IUser user7 = new User(7, "user7", "user7", "test@test.com");
		IUser user8 = new User(8, "user8", "user8", "test@test.com");
		user1.setId(_entityCounter++);
		user2.setId(_entityCounter++);
		user3.setId(_entityCounter++);
		user4.setId(_entityCounter++);
		user5.setId(_entityCounter++);
		user6.setId(_entityCounter++);
		user7.setId(_entityCounter++);
		user8.setId(_entityCounter++);
		_usersList.put(user1.getName(), user1);
		_usersList.put(user2.getName(), user2);
		_usersList.put(user3.getName(), user3);
		_usersList.put(user4.getName(), user4);
		_usersList.put(user5.getName(), user5);
		_usersList.put(user6.getName(), user6);
		_usersList.put(user7.getName(), user7);
		_usersList.put(user8.getName(), user8);
	}

	public IUser getUser(String userName, String password) {
		IUser user = _usersList.get(userName);

		if (user != null) {
			if (userName.equals(user.getName())
					&& password.equals(user.getPassword())) {
				return _usersList.get(userName);

			}
		}
		return null;

	}

	public List<IGame> getOpenedGames(IUser user, Class<?> gameClass) {
		List<IGame> openedGamesListResult = new ArrayList<IGame>();
		for (IGame game : _gamesList) {
			if (game.getClass().equals(gameClass) && game.getIsOpen()) {

				if (!user.getUserGameList().contains(game)) {
					openedGamesListResult.add(game);
				}
			}
		}

		return openedGamesListResult;
	}

	public boolean saveGame(IGame game) {
		boolean result = true;
		try {
			if (game.getId() == -1) {
				game.setId(_entityCounter++);
				_gamesList.add(game);
			} else {
				if (_gamesList.contains(game)) {
					for (IGame gameLoop : _gamesList) {
						if (gameLoop.equals(game)) {
							gameLoop = game;
						}
					}
				} else {

					_gamesList.add(game);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		}
		return result;
	}

	public IUser saveUser(IUser user) {
		if (_usersList.containsKey(user.getName())) {
			return null;
		} else {

			_usersList.put(user.getName(), user);
			return user;
		}
	}
}
