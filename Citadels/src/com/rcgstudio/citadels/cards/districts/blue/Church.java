package com.rcgstudio.citadels.cards.districts.blue;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Church extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Church";
	private static int ICON_ID = R.drawable.district_b_church;
	private static CardColor CARD_COLOUR = CardColor.BLUE;
	private static int GOLD_TO_BUILD = 2;

	public Church() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
