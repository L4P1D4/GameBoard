package com.rcgstudio.citadels.entities;

import com.rcgstudio.citadels.enums.CardColor;

public abstract class CitadelsSpecialDistrict extends CitadelsDistrict{

	private static final long serialVersionUID = 1L;

	public CitadelsSpecialDistrict(String name, int iconId, int goldToBuild) {
		super(name, iconId, goldToBuild, CardColor.PURPLE);
	}

	

}
