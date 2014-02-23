package com.rcgstudio.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rcgstudio.core.enums.GameStatus;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IGameData;
import com.rcgstudio.core.interfaces.IGameMove;
import com.rcgstudio.core.interfaces.IPlayerStatus;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.core.utils.MathUtils;

public abstract class Game extends Entity implements IGame {

	private static final long serialVersionUID = 1L;
	protected GameStatus _gameStatus = GameStatus.OPEN;;
	protected GameConfiguration _gameConfiguration;
	protected int _iconId;
	protected String _gameName;
	protected String _gamePassword;
	private List<IPlayerStatus> _playerStatusList;
	private HashMap<Long, IGameData> _gameDataMap;

	public Game(String gameName, String gamePassword, Integer iconId, GameConfiguration gameConfiguration) {
		super(-1);
		_gameName = gameName;
		_gamePassword = gamePassword;
		_iconId = iconId;
		_gameConfiguration = gameConfiguration;
		_playerStatusList = new ArrayList<IPlayerStatus>(gameConfiguration.getNumberOfPlayers());
		_gameDataMap = new HashMap<Long, IGameData>(0);
	}

	public String getName() {
		return _gameName;
	}

	public Boolean getIsOpen() {
		if (getCurrentNumberOfPlayers() < getMaxPlayers()) {
			return true;
		}
		return false;
	}

	public Boolean hasPassword() {
		if (_gamePassword == null)
			return false;
		if (_gamePassword.equals(""))
			return false;
		return true;
	}

	public GameConfiguration getGameType() {
		return _gameConfiguration;
	}

	public GameStatus getGameStatus() {
		return _gameStatus;
	}

	public Boolean validateGameMove(IGameMove gameMove) {
		return true;
	}

	public Boolean hasTurn(long playerId) {
		IPlayerStatus playerStatus = getPlayerStatus(playerId);
		if (playerStatus != null && playerStatus.hasTurn()) {
			return true;
		}
		return false;
	}

	public Integer getIconId() {
		return _iconId;
	}

	public Boolean validatePassword(String passwordToCheck) {
		if (_gamePassword == null) {
			return true;
		} else if (_gamePassword.equals(passwordToCheck)) {
			return true;
		}
		return false;
	}

	public Integer getCurrentNumberOfPlayers() {
		return _playerStatusList.size();
	}

	public Integer getMaxPlayers() {
		return _gameConfiguration.getNumberOfPlayers();
	}

	public List<? extends IPlayerStatus> getPlayerStatusList() {
		return _playerStatusList;
	}

	public void addPlayerStatus(IPlayerStatus playerStatusToAdd) {
		_playerStatusList.add(playerStatusToAdd);
	}

	public IPlayerStatus getPlayerStatus(int playerNumber) {
		for (IPlayerStatus playerStatus : _playerStatusList) {
			if (playerStatus.getPlayerNumber() == playerNumber) {
				return playerStatus;
			}
		}
		return null;
	}

	public ArrayList<IUser> getGameUsers() {
		ArrayList<IUser> usersInGameArrayList = new ArrayList<IUser>();
		for (IPlayerStatus playerStatus : _playerStatusList) {
			usersInGameArrayList.add(playerStatus.getUser());
		}
		return usersInGameArrayList;
	}

	public HashMap<Long, IGameData> getGameDatas() {
		return _gameDataMap;
	}

	public Boolean addGameMove(IGameMove newGameMove) throws Exception {
		try {
			IGameData gameData = new GameData((long) _gameDataMap.size() + 1, newGameMove, getCurrentPlayer().getUser());
			_gameDataMap.put(gameData.getDataOrder(), gameData);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public IPlayerStatus getCurrentPlayer() {
		for (IPlayerStatus playerStatus : _playerStatusList) {
			if (playerStatus.hasTurn()) {
				return playerStatus;
			}
		}
		return null;
	}

	public IGameData getGameData(long order) {
		return _gameDataMap.get(order);
	}

	public IGameData getFirstGameData() {
		return _gameDataMap.get((long) 1);
	}

	public IGameData getLastGameData() {
		return _gameDataMap.get((long) _gameDataMap.size());
	}

	public IPlayerStatus getPlayerStatus(long playerId) {
		for (IPlayerStatus playerStatus : _playerStatusList) {
			if (playerStatus.getUser().getId() == playerId) {
				return playerStatus;
			}
		}
		return null;
	}

	public void sortRandomPlayerStatus() {
		ArrayList<Integer> orderList = MathUtils.sortRandomlyNumbers(1, getPlayerStatusList().size());
		int count = 0;
		for (IPlayerStatus playerStatus : _playerStatusList) {
			playerStatus.setPlayerNumber(orderList.get(count));
			count++;
		}
	}
}
