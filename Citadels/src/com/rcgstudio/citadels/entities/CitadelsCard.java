package com.rcgstudio.citadels.entities;

import java.io.Serializable;

import com.rcgstudio.citadels.R;

public class CitadelsCard implements Serializable{

	private static final long serialVersionUID = 1L;
	String _name;
	int _iconId;
	boolean _isTurned;
	public static int IconIdTurned = R.drawable.ic_card_back;
	
	public CitadelsCard(String name, int iconId) {
		_name = name;
		_iconId = iconId;
		_isTurned = false;
	}
	
	public int getIconId()
	{
		return _iconId;
	}
	public String getName()
	{
		return _name;
	}
	public boolean getIsTurned()
	{
		return _isTurned;
	}
	public void setIsTurned(boolean isTurned)
	{
		_isTurned = isTurned;
	}
}
