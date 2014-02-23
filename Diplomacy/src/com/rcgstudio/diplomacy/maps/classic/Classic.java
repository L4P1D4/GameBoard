package com.rcgstudio.diplomacy.maps.classic;

import com.rcgstudio.diplomacy.entities.DiplomacyMap;
import com.rcgstudio.diplomacy.enums.DiplomacyMaps;
import com.rcgstudio.diplomacy.maps.classic.countries.Austria;
import com.rcgstudio.diplomacy.maps.classic.countries.Britain;
import com.rcgstudio.diplomacy.maps.classic.countries.France;
import com.rcgstudio.diplomacy.maps.classic.countries.Germany;
import com.rcgstudio.diplomacy.maps.classic.countries.Italy;
import com.rcgstudio.diplomacy.maps.classic.countries.Russia;
import com.rcgstudio.diplomacy.maps.classic.countries.Turkey;

public class Classic extends DiplomacyMap {

	private static final long serialVersionUID = 1L;
	private final static String MAPNAME = "Classic";

	public Classic() {
		super(DiplomacyMaps.CLASSIC.toString(), MAPNAME);

		addCountry(new Britain());
		addCountry(new France());
		addCountry(new Germany());
		addCountry(new Italy());
		addCountry(new Turkey());
		addCountry(new Russia());
		addCountry(new Austria());
	}

}
