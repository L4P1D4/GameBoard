package com.rcgstudio.tictactoe.activities;

import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.entities.GameMove;
import com.rcgstudio.tictactoe.R;
import com.rcgstudio.tictactoe.R.color;
import com.rcgstudio.tictactoe.entities.TicTacToeGame;
import com.rcgstudio.tictactoe.entities.TicTacToeGameMove;
import com.rcgstudio.tictactoe.entities.TicTacToePlayerStatus;
import com.rcgstudio.tictactoe.proxy.Coordinate;
import com.rcgstudio.tictactoe.proxy.TicTacToeProxy;

import android.R.bool;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class TicTacToeGameAct extends Activity {

	TicTacToeGame _game;
	GameBoardApplication _application;
	TicTacToeProxy _ticTacToeProxy;
	ImageView imageViewTopLeft;
	ImageView imageViewTopCenter;
	ImageView imageViewTopRight;
	ImageView imageViewCenterLeft;
	ImageView imageViewCenterCenter;
	ImageView imageViewCenterRight;
	ImageView imageViewBottomLeft;
	ImageView imageViewBottomCenter;
	ImageView imageViewBottomRight;
	ImageView imageViewPlayerOneIcon;
	ImageView imageViewPlayerTwoIcon;

	TextView textViewPlayerOne;
	TextView textViewPlayerTwo;
	Coordinate lastValidCoordinatesClicked;
	TableRow tableRowPlayerOne;
	TableRow tableRowPlayerTwo;
	
	Button buttonOK;

	ImageView imageViewThisTurnMoved;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tictactoe_game);
		_application = ((GameBoardApplication) getApplicationContext());
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			_game = (TicTacToeGame) extras.getSerializable("game");
		}
		_ticTacToeProxy = (TicTacToeProxy) _application.getProxy(TicTacToeProxy.class);

		imageViewTopLeft = (ImageView) findViewById(R.id.imageViewTopLeft);
		imageViewTopCenter = (ImageView) findViewById(R.id.imageViewTopCenter);
		imageViewTopRight = (ImageView) findViewById(R.id.imageViewTopRight);
		imageViewCenterLeft = (ImageView) findViewById(R.id.imageViewCenterLeft);
		imageViewCenterCenter = (ImageView) findViewById(R.id.imageViewCenterCenter);
		imageViewCenterRight = (ImageView) findViewById(R.id.imageViewCenterRight);
		imageViewBottomLeft = (ImageView) findViewById(R.id.imageViewBottomLeft);
		imageViewBottomCenter = (ImageView) findViewById(R.id.imageViewBottomCenter);
		imageViewBottomRight = (ImageView) findViewById(R.id.imageViewBottomRight);
		buttonOK = (Button) findViewById(R.id.buttonOK);
		imageViewPlayerOneIcon = (ImageView) findViewById(R.id.imageViewPlayerOneIcon);
		imageViewPlayerTwoIcon = (ImageView) findViewById(R.id.imageViewPlayerTwoIcon);
		textViewPlayerOne = (TextView) findViewById(R.id.textViewPlayerOne);
		textViewPlayerTwo = (TextView) findViewById(R.id.textViewPlayerTwo);
		tableRowPlayerOne = (TableRow) findViewById(R.id.tableRowPlayerRowOne);
		tableRowPlayerTwo = (TableRow) findViewById(R.id.tableRowPlayerRowTwo);

		setInitialLayout();
	}

	private void setInitialLayout() {
		buttonOK.setEnabled(false);

		textViewPlayerOne.setText(_game.getPlayerStatus(1).getUser().getName());
		textViewPlayerTwo.setText(_game.getPlayerStatus(2).getUser().getName());
		imageViewPlayerOneIcon.setImageResource(((TicTacToePlayerStatus) _game.getPlayerStatus(1)).getIcon());
		imageViewPlayerTwoIcon.setImageResource(((TicTacToePlayerStatus) _game.getPlayerStatus(2)).getIcon());

		refreshGUI();
	}

	public void onPossitionClicked(View v) {

		if (_game.hasTurn(_application.getCurrentUser().getId())) {

			Coordinate coordinateClicked = getImageCoordinate((ImageView) v);

			if (checkIfValidPossition(coordinateClicked)) {
				lastValidCoordinatesClicked = coordinateClicked;
				if (imageViewThisTurnMoved != null) {
					imageViewThisTurnMoved.setImageResource(0);
				}
				((ImageView) v).setImageResource(((TicTacToePlayerStatus) _game.getPlayerStatus(_application.getCurrentUser().getId())).getIcon());
				imageViewThisTurnMoved = ((ImageView) v);
				buttonOK.setEnabled(true);
			}
		}
	}

	public void onOkClicked(View v) {
		TicTacToeGame currentGame = _game;
		try {
			GameMove newGameMove = new TicTacToeGameMove(lastValidCoordinatesClicked);
			_game.addGameMove(newGameMove);

			boolean gameFinished = _game.checkGameFinished();

			if (gameFinished) {
				_game.finishGame();
			} else {
				if (passTurn()) {
					if (TicTacToeProxy.saveGame(_game) == true) {
						setInitialLayout();
					}
				}
			}

			refreshGUI();

		} catch (Exception e) {
			// TODO: handle exception
			_game = currentGame;
		}
	}

	private void refreshGUI() {

		if (_game.getPlayerStatus(1).hasTurn()) {
			tableRowPlayerOne.setBackgroundColor(color.grey);
		}
		if (_game.getPlayerStatus(2).hasTurn()) {
			tableRowPlayerTwo.setBackgroundColor(color.grey);
		}

	}

	private boolean checkGameFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean passTurn() {
		int nextPlayerNumber = _game.getCurrentPlayer().getPlayerNumber();
		if (nextPlayerNumber >= _game.getMaxPlayers()) {
			nextPlayerNumber = 0;
		}
		nextPlayerNumber++;
		_game.getPlayerStatus(nextPlayerNumber).setHasTurn(true);
		_game.getCurrentPlayer().setHasTurn(false);

		return true;
	}

	private boolean checkIfValidPossition(Coordinate coordinate) {
		TicTacToeGameMove newGameMove = new TicTacToeGameMove(coordinate);
		return _game.validateGameMove(newGameMove);
	}

	private Coordinate getImageCoordinate(ImageView imageViewSelected) {
		if (imageViewSelected.equals(imageViewTopLeft)) {
			return new Coordinate(0, 0);
		} else if (imageViewSelected.equals(imageViewTopCenter)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewTopRight)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewCenterLeft)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewCenterCenter)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewCenterRight)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewBottomLeft)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewBottomCenter)) {
			return new Coordinate(0, 1);
		} else if (imageViewSelected.equals(imageViewBottomRight)) {
			return new Coordinate(0, 1);
		} else {
			return new Coordinate(0, 0);
		}
	}
}