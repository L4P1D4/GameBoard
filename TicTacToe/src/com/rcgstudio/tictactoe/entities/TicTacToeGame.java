package com.rcgstudio.tictactoe.entities;

import java.util.List;

import com.rcgstudio.core.entities.Game;
import com.rcgstudio.core.entities.GameConfiguration;
import com.rcgstudio.core.enums.GameStatus;
import com.rcgstudio.core.interfaces.IGameMove;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IPlayerStatus;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.tictactoe.R;

public class TicTacToeGame extends Game implements IGame {

	private static final long serialVersionUID = 1L;
	IUser _admin;
	private long[][] _positions = new long[3][3];
	IPlayerStatus _activePlayerStatus;

	public TicTacToeGame(IUser admin, String gameName, String gamePassword, GameConfiguration gameConfiguration) {
		super(gameName, gamePassword, R.drawable.tictactoeicon, gameConfiguration);
		_admin = admin;
		addPlayer(admin);

		// Initialises the empty/free spaces.
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				_positions[i][j] = 0;
			}
		}
	}

	public Boolean getIsOpen() {
		if (_gameStatus == GameStatus.OPEN) {
			return true;
		}
		return false;
	}

	public IUser getAdmin() {
		return _admin;
	}

	public void startGame() {

		_gameStatus = GameStatus.IN_PROGRESS;

		sortRandomPlayerStatus();

		((TicTacToePlayerStatus) getPlayerStatus(1)).setHasTurn(true);

		((TicTacToePlayerStatus) getPlayerStatus(1)).setIcon(R.drawable.circle);
		((TicTacToePlayerStatus) getPlayerStatus(2)).setIcon(R.drawable.equis);
	}

	public void finishGame() {

		_gameStatus = GameStatus.FINISHED;
		
		for (IPlayerStatus playerStatus : getPlayerStatusList()) {
			playerStatus.setHasTurn(false);
		}
	}

	@SuppressWarnings("unchecked")
	public List<TicTacToePlayerStatus> getPlayerStatusList() {
		return (List<TicTacToePlayerStatus>) (List<?>) super.getPlayerStatusList();
	}

	public void addPlayer(IUser newPlayer) {
		newPlayer.addUserGame(this);
		addPlayerStatus(new TicTacToePlayerStatus(newPlayer));
	}

	public Boolean addGameMove(IGameMove newGameMove) {
		try {
			int xPos = ((TicTacToeGameMove) newGameMove).getCoordinate().getX();
			int yPos = ((TicTacToeGameMove) newGameMove).getCoordinate().getY();

			if (addGameMove(newGameMove)) {
				_positions[xPos][yPos] = getCurrentPlayer().getUser().getId();
			}

			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public Boolean validateGameMove(IGameMove gameMove) {
		try {
			TicTacToeGameMove move = (TicTacToeGameMove) gameMove;
			if ((_positions[move.getCoordinate().getX()][move.getCoordinate().getY()]) == 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
