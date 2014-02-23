package com.rcgstudio.citadels.entities;

import com.rcgstudio.citadels.enums.CardColor;

public class CitadelsBasicDistrict extends CitadelsDistrict {

	private static final long serialVersionUID = 1L;

	public CitadelsBasicDistrict(String name, int iconId, int goldToBuild, CardColor cardColor) {
		super(name, iconId, goldToBuild, cardColor);
	}

}
