package com.rcgstudio.citadels.entities;

import junit.framework.Assert;

import com.rcgstudio.citadels.enums.CardColor;

public abstract class CitadelsDistrict extends CitadelsCard {

	int _goldToBuild;
	CardColor _cardColor;

	private static final long serialVersionUID = 1L;

	public CitadelsDistrict(String name, int iconId, int goldToBuild, CardColor cardColor) {
		super(name, iconId);
		_goldToBuild = goldToBuild;
		_cardColor = cardColor;
		Assert.assertNotSame(0, goldToBuild);
	}

	public int getPoints() {
		return _goldToBuild;
	}

	public int getGoldToBuild() {
		return _goldToBuild;
	}

	public CardColor getCardColor() {
		return _cardColor;
	}

}
