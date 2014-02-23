package com.rcgstudio.citadels.entities;

import java.util.ArrayList;

import com.rcgstudio.core.entities.PlayerStatus;
import com.rcgstudio.core.interfaces.IPlayerStatus;
import com.rcgstudio.core.interfaces.IUser;

public class CitadelsPlayerStatus extends PlayerStatus implements IPlayerStatus {

	private static final long serialVersionUID = 1L;
	private Boolean _hasTurn;
	private Integer _gold;
	private Integer _orderNumber;
	private Boolean _turnPlayed;
	ArrayList<CitadelsCard> _handCards;
	ArrayList<CitadelsDistrict> _tableCards;

	public CitadelsPlayerStatus(IUser user) {
		super(user);
		_handCards = new ArrayList<CitadelsCard>();
		_tableCards = new ArrayList<CitadelsDistrict>();
		_gold = 0;
		_hasTurn = false;
	}

	public Integer getCurrentGold() {
		return _gold;
	}

	public Boolean hasTurn() {
		return _hasTurn;
	}

	public Boolean getTurnPlayed() {
		return _turnPlayed;
	}

	public Integer getOrderNumber() {
		return _orderNumber;
	}

	public void addCardToHand(CitadelsCard card) {
		_handCards.add(card);
	}

	public void RemoveCardOfHand(CitadelsCard card) {
		_handCards.remove(card);
	}

	public ArrayList<CitadelsCard> getHandCards() {
		return _handCards;
	}

	public ArrayList<CitadelsDistrict> getTableCards() {
		return _tableCards;
	}

	public void setTurn() {
		_hasTurn = true;
	}

	public void setOrderNumber(int orderNumber) {
		_orderNumber = orderNumber;
	}

	public Integer getPoints() {
		Integer totalPoints = 0;
		for (CitadelsDistrict district : _tableCards) {
			totalPoints = totalPoints + district.getPoints();
		}
		return totalPoints;
	}
}
