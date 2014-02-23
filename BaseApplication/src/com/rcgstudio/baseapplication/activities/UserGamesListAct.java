package com.rcgstudio.baseapplication.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.rcgstudio.baseapplication.R;
import com.rcgstudio.baseapplication.adapters.UserGameItemAdapter;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGame;

public class UserGamesListAct extends Activity {

	ListView _userGamesListView;
	GameBoardApplication _application;
	IGame _selectedGame;

	
	private UserGameItemAdapter _userGameItemAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.usergameslist);
		_application = ((GameBoardApplication) getApplicationContext());

		_userGamesListView = (ListView) findViewById(R.id.listViewUserGames);

		List<IGame> gamesList = _application.getCurrentUser().getUserGameList();
		_userGameItemAdapter = new UserGameItemAdapter(getApplicationContext(), gamesList);

		_userGamesListView.setAdapter(_userGameItemAdapter);
		_userGamesListView.setItemsCanFocus(false);
		_userGamesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		_userGamesListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				_selectedGame = (IGame) _userGamesListView.getItemAtPosition(position);
			}
		});
	}

	public void onNewGameButtonClick(View v) {
		Intent i = new Intent(this, ChooseNewGameTypeAct.class);
		startActivity(i);
	}

	public void onJoinButtonClick(View v) {
		Intent i = new Intent(this, ChooseJoinGameTypeAct.class);
		startActivity(i);
	}
}