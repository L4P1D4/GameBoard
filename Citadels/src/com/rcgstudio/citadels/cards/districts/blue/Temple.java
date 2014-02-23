package com.rcgstudio.citadels.cards.districts.blue;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Temple extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Temple";
	private static int ICON_ID = R.drawable.district_b_temple;
	private static CardColor CARD_COLOUR = CardColor.BLUE;
	private static int GOLD_TO_BUILD = 1;

	public Temple() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
