package com.rcgstudio.citadels.cards.districts.yellow;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Castle extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Castle";
	private static int ICON_ID = R.drawable.district_y_castle;
	private static CardColor CARD_COLOUR = CardColor.YELLOW;
	private static int GOLD_TO_BUILD = 4;

	public Castle() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
