package com.rcgstudio.tictactoe.proxy;

import com.rcgstudio.core.entities.GameConfiguration;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.tictactoe.entities.TicTacToeGame;

public class TicTacToeGameFactory {

	public static TicTacToeGame createTicTacToeGame(String gameName, String gamePassword, IUser administrator, GameConfiguration gameConfiguration) {
		TicTacToeGame newGame = new TicTacToeGame(administrator, gameName, gamePassword, gameConfiguration);
		return newGame;
	}

}
