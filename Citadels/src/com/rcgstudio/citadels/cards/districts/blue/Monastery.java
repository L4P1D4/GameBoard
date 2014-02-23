package com.rcgstudio.citadels.cards.districts.blue;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Monastery extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Monastery";
	private static int ICON_ID = R.drawable.district_b_monastery;
	private static CardColor CARD_COLOUR = CardColor.BLUE;
	private static int GOLD_TO_BUILD = 3;

	public Monastery() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
