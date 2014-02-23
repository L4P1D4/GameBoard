package com.rcgstudio.citadels.cards.districts.green;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Harbor extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Harbor";
	private static int ICON_ID = R.drawable.district_g_harbor;
	private static CardColor CARD_COLOUR = CardColor.GREEN;
	private static int GOLD_TO_BUILD = 4;

	public Harbor() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
