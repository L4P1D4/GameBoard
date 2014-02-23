package com.rcgstudio.citadels.cards.districts.red;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsBasicDistrict;
import com.rcgstudio.citadels.enums.CardColor;

public class Battlefield extends CitadelsBasicDistrict {

	private static final long serialVersionUID = 1L;

	private static String NAME = "Battlefield";
	private static int ICON_ID = R.drawable.district_r_battlefield;
	private static CardColor CARD_COLOUR = CardColor.RED;
	private static int GOLD_TO_BUILD = 3;

	public Battlefield() {
		super(NAME, ICON_ID, GOLD_TO_BUILD, CARD_COLOUR);
	}

}
