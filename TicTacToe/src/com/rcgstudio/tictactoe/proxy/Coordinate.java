package com.rcgstudio.tictactoe.proxy;

public class Coordinate {
	private int _x;
	private int _y;

	public Coordinate(int x, int y) {
		_x = x;
		_y = y;
	}

	public int getY() {
		return _y;
	}

	public void setY(int _y) {
		this._y = _y;
	}

	public int getX() {
		return _x;
	}

	public void setX(int _x) {
		this._x = _x;
	}
}
