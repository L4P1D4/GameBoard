package com.rcgstudio.citadels.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.cards.characters.*;
import com.rcgstudio.citadels.entities.CitadelsCharacter;
import com.rcgstudio.citadels.entities.CitadelsGame;
import com.rcgstudio.citadels.proxy.CitadelsProxy;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGame;

public class CitadelsGameConfigurationAct extends Activity {

	private static final String DEFAULT_DISTRICTS = "8";
	private static final String DEFAULT_NUMBER_OF_PLAYERS = "6";
	EditText _editTextGameName;
	EditText _editTextGamePassword;
	TextView _textViewGameName;
	Spinner _spinnerNumberOfPlayers;
	Spinner _spinnerNumberOfDistricts;
	RadioButton _radioButtonAssasin;
	RadioButton _radioButtonThief;
	RadioButton _radioButtonMagician;
	RadioButton _radioButtonKing;
	RadioButton _radioButtonBishop;
	RadioButton _radioButtonMerchant;
	RadioButton _radioButtonArchitect;
	RadioButton _radioButtonWarlord;
	RadioButton _radioButtonWitch;
	RadioButton _radioButtonTaxCollector;
	RadioButton _radioButtonWizard;
	RadioButton _radioButtonEmperor;
	RadioButton _radioButtonAbbot;
	RadioButton _radioButtonAlchemist;
	RadioButton _radioButtonNavigator;
	RadioButton _radioButtonDiplomat;
	RadioButton _radioButtonArtist;
	RadioButton _radioButtonQueen;
	Button _createButton;
	GameBoardApplication _application;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_citadels_configuration);
		
		_application= ((GameBoardApplication)getApplicationContext());

		GetControlsFromLayout();

		SetSpinnerAdapters();

		SetHandlers();

		SetDefaultValues();

	}

	private void GetControlsFromLayout() {
		_textViewGameName = (TextView) findViewById(R.id.textViewGameName);
		_editTextGameName = (EditText) findViewById(R.id.editTextGameName);
		_editTextGamePassword = (EditText) findViewById(R.id.editTextGamePassword);
		_spinnerNumberOfPlayers = (Spinner) findViewById(R.id.spinnerNumberOfPlayers);
		_spinnerNumberOfDistricts = (Spinner) findViewById(R.id.SpinnerDistricts);
		_radioButtonAssasin = (RadioButton) findViewById(R.id.radioButtonAssasain);
		_radioButtonThief = (RadioButton) findViewById(R.id.radioButtonThief);
		_radioButtonMagician = (RadioButton) findViewById(R.id.radioButtonMagician);
		_radioButtonKing = (RadioButton) findViewById(R.id.radioButtonKing);
		_radioButtonBishop = (RadioButton) findViewById(R.id.radioButtonBishop);
		_radioButtonMerchant = (RadioButton) findViewById(R.id.radioButtonMerchant);
		_radioButtonArchitect = (RadioButton) findViewById(R.id.radioButtonArchitect);
		_radioButtonWarlord = (RadioButton) findViewById(R.id.radioButtonWarlord);
		_radioButtonWitch = (RadioButton) findViewById(R.id.radioButtonWitch);
		_radioButtonTaxCollector = (RadioButton) findViewById(R.id.radioButtonTaxCollector);
		_radioButtonWizard = (RadioButton) findViewById(R.id.radioButtonWizard);
		_radioButtonEmperor = (RadioButton) findViewById(R.id.radioButtonEmperor);
		_radioButtonAbbot = (RadioButton) findViewById(R.id.radioButtonAbbot);
		_radioButtonAlchemist = (RadioButton) findViewById(R.id.radioButtonAlchemist);
		_radioButtonNavigator = (RadioButton) findViewById(R.id.radioButtonNavigator);
		_radioButtonDiplomat = (RadioButton) findViewById(R.id.radioButtonDiplomat);
		_radioButtonArtist = (RadioButton) findViewById(R.id.radioButtonArtist);
		_radioButtonQueen = (RadioButton) findViewById(R.id.radioButtonQueen);
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

	private void SetSpinnerAdapters() {
		ArrayAdapter<CharSequence> adapterNumberOfPlayers = ArrayAdapter.createFromResource(this, R.array.numberOfPlayers,
				android.R.layout.simple_spinner_item);
		adapterNumberOfPlayers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spinnerNumberOfPlayers.setAdapter(adapterNumberOfPlayers);

		int spinnerNumberOfPlayersPossition = adapterNumberOfPlayers.getPosition(DEFAULT_NUMBER_OF_PLAYERS);
		_spinnerNumberOfPlayers.setSelection(spinnerNumberOfPlayersPossition);

		ArrayAdapter<CharSequence> adapterDistricts = ArrayAdapter.createFromResource(this, R.array.districts, android.R.layout.simple_spinner_item);
		adapterDistricts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		_spinnerNumberOfDistricts.setAdapter(adapterDistricts);

		int spinnerDistrictsPossition = adapterDistricts.getPosition(DEFAULT_DISTRICTS);
		_spinnerNumberOfDistricts.setSelection(spinnerDistrictsPossition);

	}

	@SuppressWarnings("unchecked")
	private void SetDefaultValues() {

		_textViewGameName.setTextColor(Color.RED);
		ArrayAdapter<CharSequence> adapterNumberOfPlayers = (ArrayAdapter<CharSequence>) _spinnerNumberOfPlayers.getAdapter();
		int spinnerNumberOfPlayersPossition = adapterNumberOfPlayers.getPosition(DEFAULT_NUMBER_OF_PLAYERS);
		_spinnerNumberOfPlayers.setSelection(spinnerNumberOfPlayersPossition);

		ArrayAdapter<CharSequence> adapterDistricts = (ArrayAdapter<CharSequence>) _spinnerNumberOfDistricts.getAdapter();
		int spinnerDistrictsPossition = adapterDistricts.getPosition(DEFAULT_DISTRICTS);
		_spinnerNumberOfDistricts.setSelection(spinnerDistrictsPossition);

		_radioButtonAssasin.setChecked(true);
		_radioButtonThief.setChecked(true);
		_radioButtonMagician.setChecked(true);
		_radioButtonKing.setChecked(true);
		_radioButtonBishop.setChecked(true);
		_radioButtonMerchant.setChecked(true);
		_radioButtonArchitect.setChecked(true);
		_radioButtonWarlord.setChecked(true);
		
		_createButton.setEnabled(false);
	}

	private ArrayList<CitadelsCharacter> GetCharactersFromRadioButtons() {
		ArrayList<CitadelsCharacter> charactersSelected = new ArrayList<CitadelsCharacter>();
		if (_radioButtonAssasin.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Assasin.class));
		}
		if (_radioButtonThief.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Thief.class));
		}
		if (_radioButtonMagician.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Magician.class));
		}
		if (_radioButtonKing.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(King.class));
		}
		if (_radioButtonBishop.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Bishop.class));
		}
		if (_radioButtonMerchant.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Merchant.class));
		}
		if (_radioButtonArchitect.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Architect.class));
		}
		if (_radioButtonWarlord.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Warlord.class));
		}
		if (_radioButtonWitch.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Witch.class));
		}
		if (_radioButtonTaxCollector.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(TaxCollector.class));
		}
		if (_radioButtonWizard.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Wizard.class));
		}
		if (_radioButtonEmperor.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Emperor.class));
		}
		if (_radioButtonAbbot.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Abbot.class));
		}
		if (_radioButtonAlchemist.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Alchemist.class));
		}
		if (_radioButtonNavigator.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Navigator.class));
		}
		if (_radioButtonDiplomat.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Diplomat.class));
		}
		if (_radioButtonArtist.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Artist.class));
		}
		if (_radioButtonQueen.isChecked() == true) {
			charactersSelected.add(CitadelsProxy.getCharacter(Queen.class));
		}

		return charactersSelected;
	}

	public void onCreateButtonClick(View v) {
		String gameName = _editTextGameName.getText().toString();
		String gamePassword = _editTextGamePassword.getText().toString();
		String numberOfDistrictsString = _spinnerNumberOfDistricts.getSelectedItem().toString();
		String numberOfPlayersString = _spinnerNumberOfPlayers.getSelectedItem().toString();
		int numberOfPlayers = Integer.parseInt(numberOfPlayersString);
		int numberOfDistricts = Integer.parseInt(numberOfDistrictsString);

		ArrayList<CitadelsCharacter> characterList = GetCharactersFromRadioButtons();

		IGame newGame = (CitadelsGame) CitadelsProxy.createCitadelsGame(_application.getCurrentUser(), gameName, gamePassword, numberOfPlayers,
				numberOfDistricts, characterList);
		
		CitadelsProxy.saveGame(newGame);

		Intent i = new Intent(this, CitadelsGameWaitingAct.class);
		i.putExtra("game", newGame);
		startActivity(i);
	}

}
