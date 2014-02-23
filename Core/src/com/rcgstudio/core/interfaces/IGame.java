package com.rcgstudio.core.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.rcgstudio.core.enums.GameStatus;

public interface IGame extends IEntity{

	public String getName();
	
	public GameStatus getGameStatus();
	
	public Boolean validateGameMove(IGameMove gameMove);
	
	public Boolean hasPassword();
	
	public Boolean validatePassword(String passwordToCheck);
	
	public Boolean hasTurn(long userId);

	public Integer getIconId();
	
	public Integer getCurrentNumberOfPlayers();
	
	public Integer getMaxPlayers();
	
	public ArrayList<IUser> getGameUsers();
	
	public void addPlayer(IUser newPlayer);
	
	public Boolean getIsOpen();
	
	public void startGame();
	
	public Boolean addGameMove(IGameMove newGameMove) throws Exception;
	
	public IPlayerStatus getCurrentPlayer();
	
	public IGameData getGameData(long order);
	
	public IGameData getFirstGameData();
	
	public IGameData getLastGameData();	
	
	public IPlayerStatus getPlayerStatus(int playerId);
	
	public List<? extends IPlayerStatus> getPlayerStatusList();
	
	public void sortRandomPlayerStatus();
}
