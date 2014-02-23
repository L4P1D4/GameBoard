package com.rcgstudio.diplomacy.entities;

import java.io.Serializable;

public abstract class DiplomacyCountry implements Serializable{

	private static final long serialVersionUID = 1L;
	private String _name;
	private String _colourCode;
	private long _flagResourceId;

	public DiplomacyCountry(String name, String colourCode, long flagResourceId){
		_name = name;
		_colourCode = colourCode;
		_flagResourceId = flagResourceId;
	}
	
	public String getName() {
		return _name;
	}
	public String getColourCode() {
		return _colourCode;
	}
	public long getFlagResourceId() {
		return _flagResourceId;
	}
}
