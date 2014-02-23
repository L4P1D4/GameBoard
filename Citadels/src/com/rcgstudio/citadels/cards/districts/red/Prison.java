package com.rcgstudio.citadels.cards.districts.red;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Prison extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Prison";
	private static int ICON_ID = R.drawable.district_r_prison;
	private static CardColor CARD_COLOUR = CardColor.RED;
	private static int GOLD_TO_BUILD = 2;

	public Prison() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
