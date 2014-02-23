package com.rcgstudio.citadels.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rcgstudio.adapters.CitadelsWaitingPlayerAdapter;
import com.rcgstudio.baseapplication.activities.UserGamesListAct;
import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsCard;
import com.rcgstudio.citadels.entities.CitadelsCharacter;
import com.rcgstudio.citadels.entities.CitadelsGame;
import com.rcgstudio.citadels.proxy.CitadelsProxy;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IUser;

public class CitadelsGameWaitingAct extends ListActivity {

	private CitadelsWaitingPlayerAdapter _citadelsWaitingPlayerAdapter;
	ArrayList<CitadelsCharacter> _notAvailableCharactersVisibleList;
	ArrayList<CitadelsCharacter> _notAvailableCharactersHiddenList;
	HashMap<Long, CitadelsCard> _playerStatusList;
	CitadelsGame _game;
	Button _startGameButton;
	GameBoardApplication _application;
	CitadelsProxy _citadelsProxy;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citadels_game_waiting);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			_game = (CitadelsGame) extras.getSerializable("game");
		}
		_application = ((GameBoardApplication) getApplicationContext());
		_citadelsProxy = (CitadelsProxy) _application.getProxy(CitadelsProxy.class);
		
		TextView textViewGameName = (TextView) findViewById(R.id.textViewGameName);
		TextView textViewCurrentPlayers = (TextView) findViewById(R.id.textViewCurrentPlayers);
		TextView textViewMaxPlayers = (TextView) findViewById(R.id.textViewMaxPlayers);
		
		textViewCurrentPlayers.setText(_game.getCurrentNumberOfPlayers().toString());
		textViewMaxPlayers.setText(_game.getMaxPlayers().toString());
		textViewGameName.setText(_game.getName());
		_startGameButton = (Button) findViewById(R.id.buttonStartGame);
		List<IUser> playersList = _game.getGameUsers();
		_citadelsWaitingPlayerAdapter = new CitadelsWaitingPlayerAdapter(getApplicationContext(), playersList);
		setListAdapter(_citadelsWaitingPlayerAdapter);

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
		IGame startedGame = _citadelsProxy.startGame(_game);
		Intent i = new Intent(this, CitadelsGameAct.class);
		i.putExtra("game", startedGame);
		startActivity(i);
	}

}