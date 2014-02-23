package com.rcgstudio.baseapplication.activities;

import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.rcgstudio.baseapplication.R;
import com.rcgstudio.baseapplication.adapters.OpenedGameItemAdapter;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IGameProxy;

public class OpenedGamesListAct extends Activity {

	ListView _openedGamesListView;
	private OpenedGameItemAdapter _openedGameItemAdapter;
	GameBoardApplication _application;
	Class<IGameProxy> _gameProxyClass;
	IGame _selectedGame;
	View _lastSelectedGameView;
	Button _joinButton;
	EditText _passwordEditText;
	String _selectedGamePassword;
	Dialog _passwordDialog;
	IGameProxy _gameProxy;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.openedgameslist);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			_gameProxyClass = (Class<IGameProxy>) extras.getSerializable("gameProxyClass");
		}
		_application = ((GameBoardApplication) getApplicationContext());
		_gameProxy = (IGameProxy) _application.getProxy(_gameProxyClass);

		_joinButton = (Button) findViewById(R.id.buttonJoin);
		_joinButton.setEnabled(false);
		_openedGamesListView = (ListView) findViewById(R.id.listViewNotStartedGameList);

		List<IGame> gamesList = _gameProxy.getOpenedGames(_application.getCurrentUser());
		_openedGameItemAdapter = new OpenedGameItemAdapter(getApplicationContext(), gamesList);
		_openedGamesListView.setItemsCanFocus(false);
		_openedGamesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		_openedGamesListView.setAdapter(_openedGameItemAdapter);
		_openedGamesListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				_selectedGame = (IGame) _openedGamesListView.getItemAtPosition(position);
				if (_lastSelectedGameView != null) {
					_lastSelectedGameView.setBackgroundResource(R.color.grey);
				}
				_lastSelectedGameView = v;
				_lastSelectedGameView.setBackgroundResource(R.color.slateBlue);
				_joinButton.setEnabled(isJoinEnabled());
			}
		});

	}

	private boolean isJoinEnabled() {
		return (_lastSelectedGameView != null);
	}

	public void onJoinButtonClick(View v) {

		if (_selectedGame.hasPassword()) {
			_passwordDialog = new Dialog(this);
			_passwordDialog.setTitle("Password");
			_passwordDialog.setContentView(R.layout.passworddialog);
			_passwordEditText = (EditText) _passwordDialog.findViewById(R.id.editTextPassword);

			_passwordEditText.setOnEditorActionListener(new OnEditorActionListener() {
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
					if (actionId == EditorInfo.IME_ACTION_DONE) {
						_selectedGamePassword = _passwordEditText.getText().toString();
						validatePassword();
						return true;
					}
					return false;
				}
			});
			
			_passwordDialog.show();

		} else {
			joinGame();
		}
	}

	void joinGame() {
		_gameProxy.addPlayerToGame(_selectedGame, _application.getCurrentUser());
		Intent i = new Intent(this, _gameProxy.getWaitingGameClass());
		i.putExtra("game", _selectedGame);
		startActivity(i);
	}

	void validatePassword() {
		if (_selectedGame.validatePassword(_passwordEditText.getText().toString())) {
			joinGame();
		} else {
			_passwordDialog.dismiss();
		}

	}
}