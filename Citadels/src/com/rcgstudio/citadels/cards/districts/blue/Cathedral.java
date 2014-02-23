package com.rcgstudio.citadels.cards.districts.blue;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Cathedral extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Cathedral";
	private static int ICON_ID = R.drawable.district_b_cathedral;
	private static CardColor CARD_COLOUR = CardColor.BLUE;
	private static int GOLD_TO_BUILD = 5;

	public Cathedral() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}
	
}
