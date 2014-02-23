package com.rcgstudio.diplomacy.entities;

import java.util.ArrayList;

public class DiplomacyRegion {

	long _regionId;
	String _regionName;
	boolean _isSupplyCenter;
	boolean _isNavigable;
	boolean _changesRegionColour;
	DiplomacyCountry _ownerCountry;
	ArrayList<DiplomacyRegion> _linkedRegions;

	public DiplomacyRegion(long regionId, String regionName, boolean isSupplyCenter, boolean isNavigable, boolean changesRegionColour,
			ArrayList<DiplomacyRegion> linkedRegions, String initialColourCode, DiplomacyCountry ownerCountry) {
		_regionId = regionId;
		_regionName = regionName;
		_isSupplyCenter = isSupplyCenter;
		_isNavigable = isNavigable;
		_changesRegionColour = changesRegionColour;
		_linkedRegions = linkedRegions;
		_ownerCountry = ownerCountry;
	}

	public long getRegionId() {
		return _regionId;
	}

	public String getRegionName() {
		return _regionName;
	}

	public boolean getIsSupplyCenter() {
		return _isSupplyCenter;
	}

	public boolean getIsNavigable() {
		return _isNavigable;
	}

	public ArrayList<DiplomacyRegion> getLinkedRegions() {
		return _linkedRegions;
	}

	public void setOwner(DiplomacyCountry ownerCountry) {
		_ownerCountry = ownerCountry;
	}

}
