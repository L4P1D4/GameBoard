package com.rcgstudio.tictactoe.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.tictactoe.R;
import com.rcgstudio.tictactoe.proxy.TicTacToeProxy;

public class TicTacToeGameConfigurationAct extends Activity {

	EditText _editTextGameName;
	EditText _editTextGamePassword;
	TextView _textViewGameName;
	Button _createButton;
	GameBoardApplication _application;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_tictactoe_game_configuration);
		
		_application= ((GameBoardApplication)getApplicationContext());

		GetControlsFromLayout();

		SetHandlers();

		SetDefaultValues();

	}

	private void GetControlsFromLayout() {
		_textViewGameName = (TextView) findViewById(R.id.textViewGameName);
		_editTextGameName = (EditText) findViewById(R.id.editTextGameName);
		_editTextGamePassword = (EditText) findViewById(R.id.editTextGamePassword);
		_createButton = (Button) findViewById(R.id.buttonCreateNewGame);
	}

	private void SetHandlers() {
		_editTextGameName.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				if (_editTextGameName.getText().toString().length() > 0) {
					_textViewGameName.setTextColor(Color.GRAY);
					_createButton.setEnabled(true);
				} else {
					_textViewGameName.setTextColor(Color.RED);
					_createButton.setEnabled(false);
				}
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
	}

	private void SetDefaultValues() {
		_textViewGameName.setTextColor(Color.RED);
		_createButton.setEnabled(false);
	}

	

	public void onCreateButtonClick(View v) {
		String gameName = _editTextGameName.getText().toString();
		String gamePassword = _editTextGamePassword.getText().toString();

		IGame newGame = TicTacToeProxy.createTicTacToeGame(_application.getCurrentUser(), gameName, gamePassword);
		
		TicTacToeProxy.saveGame(newGame);

		Intent i = new Intent(this, TicTacToeGameWaitingAct.class);
		i.putExtra("game", newGame);
		startActivity(i);
	}

}
