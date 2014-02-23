package com.rcgstudio.citadels.cards.districts.yellow;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Palace extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Palace";
	private static int ICON_ID = R.drawable.district_y_palace;
	private static CardColor CARD_COLOUR = CardColor.YELLOW;
	private static int GOLD_TO_BUILD = 5;

	public Palace() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
