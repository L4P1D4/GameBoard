package com.rcgstudio.diplomacy.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class DiplomacyMap implements Serializable{

	private static final long serialVersionUID = 1L;
	String _mapKey;
	String _mapName;
	ArrayList<DiplomacyCountry> _countriesList = new ArrayList<DiplomacyCountry>();
	ArrayList<DiplomacyRegion> _regionsList = new ArrayList<DiplomacyRegion>();

	public DiplomacyMap(String mapKey, String mapName) {
		_mapName = mapName;
		_mapKey = mapKey;
	}

	public String GetMapName() {
		return _mapName;
	}
	
	public String GetMapKey() {
		return _mapKey;
	}

	public ArrayList<DiplomacyCountry> getCountries() {
		return _countriesList;
	}

	protected void addCountry(DiplomacyCountry countryToAdd) {
		_countriesList.add(countryToAdd);
	}
	protected void addRegion(DiplomacyRegion regionToAdd) {
		_regionsList.add(regionToAdd);
	}


}
