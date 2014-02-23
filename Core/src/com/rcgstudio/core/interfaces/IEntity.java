package com.rcgstudio.core.interfaces;

import java.io.Serializable;

public interface IEntity extends Serializable {
	
	public long getId();

	public boolean equals(IEntity entityToCompare);
	
	public void setId(int idToSet);
	
}
