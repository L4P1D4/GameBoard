package com.rcgstudio.core.entities;

import com.rcgstudio.core.interfaces.IEntity;

public abstract class Entity implements IEntity {

	private static final long serialVersionUID = 1L;
	private long _id;

	public Entity(long id) {
		_id = id;
	}

	public long getId() {

		return _id;
	}

	public void setId(int idToSet) {
		_id = idToSet;
	}

	public boolean equals(IEntity entityToCompare) {
		if (entityToCompare.getId() == -1 || this.getId() == -1) {
			throw new IllegalArgumentException("Id == -1");
		}

		if (this.getId() == entityToCompare.getId()) {
			return true;
		}
		return false;
	}

}
