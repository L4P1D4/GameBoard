package com.rcgstudio.citadels.cards.districts.purple;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsSpecialDistrict;

public class HauntedCity extends CitadelsSpecialDistrict {

	private static final long serialVersionUID = 1L;
	private static String NAME = "Haunted City";
	private static int GOLD_TO_BUILD = 2;

	public HauntedCity() {
		super(NAME, R.drawable.district_p_hauntedcity, GOLD_TO_BUILD);
	}

}
