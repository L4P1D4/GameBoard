package com.rcgstudio.tictactoe.proxy;

import java.util.List;

import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IGameProxy;
import com.rcgstudio.core.interfaces.IMessaging;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.tictactoe.activities.TicTacToeGameConfigurationAct;
import com.rcgstudio.tictactoe.activities.TicTacToeGameWaitingAct;
import com.rcgstudio.tictactoe.entities.TicTacToeGame;
import com.rcgstudio.tictactoe.entities.TicTacToeGameConfiguration;

public class TicTacToeProxy implements IGameProxy {

	static IMessaging _messagingProxy;

	public TicTacToeProxy(IMessaging messagingProxy) {
		_messagingProxy = messagingProxy;
	}

	public static IGame createTicTacToeGame(IUser administrator,
			String gameName, String gamePassword) {

		TicTacToeGameConfiguration gameConfiguration = new TicTacToeGameConfiguration(administrator.getId(), 2);
		TicTacToeGame newGame = TicTacToeGameFactory.createTicTacToeGame(
				gameName, gamePassword, administrator, gameConfiguration);
		
		_messagingProxy.saveGame(newGame);

		return newGame;

	}

	public Class<?> getConfigureGameClass() {
		return TicTacToeGameConfigurationAct.class;
	}

	public String getGameName() {
		return "TicTacToe";
	}

	public Class<?> getGameClass() {
		return TicTacToeGame.class;
	}

	public Class<?> getNewGameConfigurationClass() {
		return TicTacToeGameConfigurationAct.class;
	}

	public Class<?> getWaitingGameClass() {
		return TicTacToeGameWaitingAct.class;
	}

	public List<IGame> getOpenedGames(IUser user) {
		return _messagingProxy.getOpenedGames(user, getGameClass());
	}

	public boolean addPlayerToGame(IGame game, IUser player) {
		game.addPlayer(player);
		_messagingProxy.saveUser(player);
		return _messagingProxy.saveGame(game);
	}

	public static boolean saveGame(IGame game) {
		return _messagingProxy.saveGame(game);
	}

	public IGame startGame(TicTacToeGame game) {
		game.startGame();
		_messagingProxy.saveGame(game);
		return game;
	}

}
