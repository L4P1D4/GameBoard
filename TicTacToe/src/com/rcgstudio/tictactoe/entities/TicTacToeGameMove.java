package com.rcgstudio.tictactoe.entities;

import com.rcgstudio.core.entities.GameMove;
import com.rcgstudio.core.interfaces.IGameMove;
import com.rcgstudio.tictactoe.proxy.Coordinate;

public class TicTacToeGameMove extends GameMove implements IGameMove {
	
	Coordinate _coordinate;

	public TicTacToeGameMove(Coordinate coordinate) {
		super();
		_coordinate = coordinate;
	}

	public Coordinate getCoordinate(){
		return _coordinate;
	}	
}
