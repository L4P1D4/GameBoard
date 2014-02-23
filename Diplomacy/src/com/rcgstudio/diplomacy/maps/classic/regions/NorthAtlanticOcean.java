package com.rcgstudio.diplomacy.maps.classic.regions;

import java.util.ArrayList;

import com.rcgstudio.diplomacy.entities.DiplomacyCountry;
import com.rcgstudio.diplomacy.entities.DiplomacyRegion;

public class NorthAtlanticOcean extends DiplomacyRegion {

	private static long _regionId = 1;
	private static String _regionName = "North Atlantic Ocean";
	private static boolean _isSupplyCenter = false;
	private static boolean _isNavigable = true;
	private static boolean _changesRegionColour = false;
	private static DiplomacyCountry _ownerCountry = null;
	private static ArrayList<DiplomacyRegion> _linkedRegions;
	private static String _initialColourCode = "89E9F7";

	public NorthAtlanticOcean() {
		super(_regionId, _regionName, _isSupplyCenter, _isNavigable, _changesRegionColour, _linkedRegions, _initialColourCode,
				_ownerCountry);
	}

}
