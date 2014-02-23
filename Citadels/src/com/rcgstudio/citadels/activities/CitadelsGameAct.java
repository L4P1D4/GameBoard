package com.rcgstudio.citadels.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rcgstudio.adapters.CitadelCardsViewAdapter;
import com.rcgstudio.adapters.CitadelsPlayerAdapter;
import com.rcgstudio.adapters.MyAdapter;
import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsCard;
import com.rcgstudio.citadels.entities.CitadelsCharacter;
import com.rcgstudio.citadels.entities.CitadelsGame;
import com.rcgstudio.citadels.entities.CitadelsPlayerStatus;
import com.rcgstudio.citadels.proxy.CitadelsProxy;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IUser;

public class CitadelsGameAct extends Activity {

	CitadelsGame _game;
	GameBoardApplication _application;
	CitadelsProxy _citadelsProxy;
	ImageView _selectedCardImageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.citadels_game);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			_game = (CitadelsGame) extras.getSerializable("game");
		}
		_application = ((GameBoardApplication) getApplicationContext());
		_citadelsProxy = (CitadelsProxy) _application.getProxy(CitadelsProxy.class);

		TextView gameNameTextView = (TextView) findViewById(R.id.textViewGameName);
		TextView currentPlayerTextView = (TextView) findViewById(R.id.textViewCurrentPlayer);
		gameNameTextView.setText(_game.getName());
		IUser currentPlayer = _game.getCurrentPlayer().getUser();
		if (currentPlayer != null) {
			currentPlayerTextView.setText(currentPlayer.getName());
		}
		setNotAvailableLayout();
		setNotChoosenLayout();
		setPlayersLayout();
		setCurrentPlayerLayout();
	}

	private void setCurrentPlayerLayout() {

		Gallery currentPlayerCardsGallery = (Gallery) findViewById(R.id.horizontallistviewPlayerCards);

		CitadelsPlayerStatus currentPlayerStatus = _game.getPlayerStatus(_application.getCurrentUser());

		CitadelCardsViewAdapter citadelsCardAdapter = new CitadelCardsViewAdapter(getBaseContext(), currentPlayerStatus.getHandCards());
		currentPlayerCardsGallery.setAdapter(citadelsCardAdapter);

		currentPlayerCardsGallery.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString() + " Clicked", Toast.LENGTH_LONG).show();
				_selectedCardImageView.setImageResource(((CitadelsCard)parent.getItemAtPosition(position)).getIconId());
			}

		});
	}

	private void setPlayersLayout() {

		ListView playersListView = (ListView) findViewById(R.id.listViewPlayers);

		CitadelsPlayerAdapter playersAdapter = new CitadelsPlayerAdapter(this, _game.getPlayerStatusList());
		playersListView.setAdapter(playersAdapter);
	}

	private void setNotAvailableLayout() {

		Gallery notAvailableHorizontalListView = (Gallery) findViewById(R.id.horizontallistviewNotAvailable);
		
		ArrayList<CitadelsCharacter> fullNotAvailableList = new ArrayList<CitadelsCharacter>();
		
		for (CitadelsCharacter citadelsCharacter : _game.getNotAvailableCharactersVisibleList()) {
			fullNotAvailableList.add(citadelsCharacter);
		}
		for (CitadelsCharacter citadelsCharacter : _game.getNotAvailableCharactersHiddenList()) {
			citadelsCharacter.setIsTurned(true);
			fullNotAvailableList.add(citadelsCharacter);
		}
		
		CitadelCardsViewAdapter notAvailableCardViewAdapter = new CitadelCardsViewAdapter(this, fullNotAvailableList);
		notAvailableHorizontalListView.setAdapter(notAvailableCardViewAdapter);
		notAvailableHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(CitadelsGameAct.this, parent.getItemAtPosition(position).toString() + " Clicked", Toast.LENGTH_LONG).show();
				_selectedCardImageView.setImageResource(((CitadelsCard)parent.getItemAtPosition(position)).getIconId());
			}
		});
	}

	private void setNotChoosenLayout() {

		Gallery notChoosenHorizontalListView = (Gallery) findViewById(R.id.horizontallistviewNotChoosen);
		MyAdapter notChoosenCardViewAdapter = new MyAdapter(this);
		notChoosenHorizontalListView.setAdapter(notChoosenCardViewAdapter);
		notChoosenHorizontalListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(CitadelsGameAct.this, parent.getItemAtPosition(position).toString() + " Clicked", Toast.LENGTH_LONG).show();
				_selectedCardImageView.setImageResource(((CitadelsCard)parent.getItemAtPosition(position)).getIconId());
			}
		});
	}

	public void onGameInfoClick(View v) {

		Dialog gameInfoDialog = new Dialog(this);
		gameInfoDialog.setTitle("Game Info");
		gameInfoDialog.setContentView(R.layout.game_info_dialog);

		TextView administratorTextView = (TextView) gameInfoDialog.findViewById(R.id.textViewAdministratorValue);
		TextView numberOfPlayersTextView = (TextView) gameInfoDialog.findViewById(R.id.textViewNumberOfPlayersValue);
		administratorTextView.setText(_game.getGameConfiguration().getAdministrator().getName());
		numberOfPlayersTextView.setText(_game.getGameConfiguration().getNumberOfPlayers().toString());

		gameInfoDialog.show();

	}

}