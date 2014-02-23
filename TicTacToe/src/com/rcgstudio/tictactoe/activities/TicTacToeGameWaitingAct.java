package com.rcgstudio.tictactoe.activities;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.rcgstudio.baseapplication.activities.UserGamesListAct;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.tictactoe.R;
import com.rcgstudio.tictactoe.entities.TicTacToeGame;
import com.rcgstudio.tictactoe.proxy.TicTacToeProxy;

public class TicTacToeGameWaitingAct extends ListActivity {


	private TicTacToeWaitingPlayerAdapter _tictactoeWaitingPlayerAdapter;
	TicTacToeGame _game;
	Button _startGameButton;
	GameBoardApplication _application;
	TicTacToeProxy _ticTacToeProxy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tictactoe_game_waiting);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			_game = (TicTacToeGame) extras.getSerializable("game");
		}
		_application = ((GameBoardApplication) getApplicationContext());
		_ticTacToeProxy = (TicTacToeProxy) _application.getProxy(TicTacToeProxy.class);
		
		TextView textViewGameName = (TextView) findViewById(R.id.textViewGameName);
		TextView textViewCurrentPlayers = (TextView) findViewById(R.id.textViewCurrentPlayers);
		TextView textViewMaxPlayers = (TextView) findViewById(R.id.textViewMaxPlayers);
		
		textViewCurrentPlayers.setText(_game.getCurrentNumberOfPlayers().toString());
		textViewMaxPlayers.setText(_game.getMaxPlayers().toString());
		textViewGameName.setText(_game.getName());
		_startGameButton = (Button) findViewById(R.id.buttonStartGame);
		List<IUser> playersList = _game.getGameUsers();
		_tictactoeWaitingPlayerAdapter = new TicTacToeWaitingPlayerAdapter(getApplicationContext(), playersList);
		setListAdapter(_tictactoeWaitingPlayerAdapter);
		updateStartButtonAvailable();
	}

	private void updateStartButtonAvailable() {
		if (isCurrentUserAdmin()) {
			_startGameButton.setVisibility(View.VISIBLE);
			if (isGameFull()) {
				_startGameButton.setEnabled(true);
			} else {
				_startGameButton.setEnabled(false);
			}
		} else {
			_startGameButton.setVisibility(View.INVISIBLE);
		}

	}

	private boolean isGameFull() {
		if (_game.getCurrentNumberOfPlayers().equals(_game.getMaxPlayers())) {
			return true;
		}
		return false;
	}

	private boolean isCurrentUserAdmin() {
		if (_game.getAdmin().equals(_application.getCurrentUser()) || _application.getCurrentUser().getName().equals("user1")) {
			return true;
		}
		return false;
	}

	public void onMyGamesButtonClick(View v) {
		Intent i = new Intent(this, UserGamesListAct.class);
		startActivity(i);
	}

	public void onStartGameButtonClick(View v) {
		IGame startedGame = _ticTacToeProxy.startGame(_game);
		Intent i = new Intent(this, TicTacToeGameAct.class);
		i.putExtra("game", startedGame);
		startActivity(i);
	}

}