package com.rcgstudio.citadels.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.proxy.CitadelsProxy;
import com.rcgstudio.core.entities.Game;
import com.rcgstudio.core.entities.GameConfiguration;
import com.rcgstudio.core.enums.GameStatus;
import com.rcgstudio.core.interfaces.IEntity;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.core.utils.MathUtils;

public class CitadelsGame extends Game implements Serializable {

	private static final long serialVersionUID = 1L;
	static ArrayList<CitadelsDistrict> _districtsDeck = new ArrayList<CitadelsDistrict>();
	private static IUser _admin;
	private Random _random = new Random();
	ArrayList<CitadelsCharacter> _notAvailableCharactersVisibleList;
	ArrayList<CitadelsCharacter> _notAvailableCharactersHiddenList;
	ArrayList<CitadelsCharacter> _availableCharactersList;

	public CitadelsGame(IUser admin, String gameName, String gamePassword, GameConfiguration gameConfiguration) {
		this(admin, gameName, gamePassword, gameConfiguration, CitadelsProxy.getAllDistricts());
	}

	public CitadelsGame(IUser admin, String gameName, String gamePassword, GameConfiguration gameConfiguration, ArrayList<CitadelsDistrict> districtsDeck) {
		super(gameName, gamePassword, R.drawable.ic_citadels, gameConfiguration);
		_districtsDeck = districtsDeck;
		_admin = admin;
		addPlayer(admin);
	}

	public IUser getAdmin() {
		return _admin;
	}

	public void startGame() {
		_gameStatus = GameStatus.IN_PROGRESS;
		ArrayList<Integer> orderList = MathUtils.sortRandomlyNumbers(1, getPlayerStatusList().size());
		int playerNumber = 0;
		for (CitadelsPlayerStatus playerStatus : getPlayerStatusList()) {
			playerStatus.addCardToHand(drawCard());
			playerStatus.addCardToHand(drawCard());
			playerStatus.addCardToHand(drawCard());
			playerStatus.addCardToHand(drawCard());
			playerStatus.setOrderNumber(orderList.get(playerNumber));
			if (playerStatus.getOrderNumber().equals(0)) {
				playerStatus.setTurn();
			}
			playerNumber++;
		}

		setCharactersCardsForNewGame();

	}

	private void setCharactersCardsForNewGame() {
		_notAvailableCharactersVisibleList = new ArrayList<CitadelsCharacter>();
		_notAvailableCharactersHiddenList = new ArrayList<CitadelsCharacter>();
		_availableCharactersList = new ArrayList<CitadelsCharacter>();
		ArrayList<CitadelsCharacter> tempAvailableCharactersList = new ArrayList<CitadelsCharacter>();

		tempAvailableCharactersList = new ArrayList<CitadelsCharacter>(getGameConfiguration().getCharactersList());

		_notAvailableCharactersHiddenList.add(MathUtils.drawRandomFromList(tempAvailableCharactersList));

		int numberOfNotAvailableCharsVisible = getGameConfiguration().getCharactersList().size() - _notAvailableCharactersHiddenList.size() - getGameConfiguration().getNumberOfPlayers() - 1;
		for (int i = 0; i < numberOfNotAvailableCharsVisible; i++) {
			_notAvailableCharactersVisibleList.add(MathUtils.drawRandomFromList(tempAvailableCharactersList));
		}
		for (CitadelsCharacter citadelsCharacter : tempAvailableCharactersList) {
			_availableCharactersList.add(citadelsCharacter);
		}
	}

	public void addPlayer(IUser newPlayer) {
		newPlayer.addUserGame(this);
		addPlayerStatus(new CitadelsPlayerStatus(newPlayer));
	}

	private CitadelsCard drawCard() {
		int positionToDraw = _random.nextInt(_districtsDeck.size());
		return _districtsDeck.remove(positionToDraw);
	}

	public CitadelsGameConfiguration getGameConfiguration() {
		return (CitadelsGameConfiguration) _gameConfiguration;
	}

	public CitadelsPlayerStatus getPlayerStatus(IUser user) {
		for (CitadelsPlayerStatus playerStatus : getPlayerStatusList()) {
			if (((IEntity) playerStatus.getUser()).equals(user)) {
				return playerStatus;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<CitadelsPlayerStatus> getPlayerStatusList() {
		return (List<CitadelsPlayerStatus>) (List<?>) super.getPlayerStatusList();
	}

	public ArrayList<CitadelsCharacter> getNotAvailableCharactersVisibleList() {
		return _notAvailableCharactersVisibleList;
	}

	public ArrayList<CitadelsCharacter> getNotAvailableCharactersHiddenList() {
		return _notAvailableCharactersHiddenList;
	}

}
