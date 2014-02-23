package com.rcgstudio.diplomacy.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.diplomacy.R;
import com.rcgstudio.diplomacy.entities.DiplomacyGame;
import com.rcgstudio.diplomacy.entities.DiplomacyMap;
import com.rcgstudio.diplomacy.enums.AnonymousType;
import com.rcgstudio.diplomacy.enums.MapType;
import com.rcgstudio.diplomacy.enums.MessagingType;
import com.rcgstudio.diplomacy.enums.PotType;
import com.rcgstudio.diplomacy.proxy.DiplomacyProxy;

public class DiplomacyGameConfigurationAct extends Activity {

	IUser _user;
	EditText _editTextGameName;
	EditText _editTextGamePassword;
	EditText _editTextBetSize;
	Spinner _spinnerAnonymous;
	Spinner _spinnerMap;
	Spinner _spinnerMessaging;
	Spinner _spinnerPotType;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newdiplomacygameconfiguration);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			_user = (IUser) extras.getSerializable("user");
		}

		_editTextGameName = (EditText) findViewById(R.id.editTextGameName);
		_editTextGamePassword = (EditText) findViewById(R.id.editTextGamePassword);
		_editTextBetSize = (EditText) findViewById(R.id.editTextBetSize);
		_spinnerAnonymous = (Spinner) findViewById(R.id.spinnerAnonymous);
		_spinnerMap = (Spinner) findViewById(R.id.spinnerMap);
		_spinnerMessaging = (Spinner) findViewById(R.id.spinnerMessaging);
		_spinnerPotType = (Spinner) findViewById(R.id.spinnerPotType);

		_spinnerAnonymous.setAdapter(new ArrayAdapter<AnonymousType>(this, android.R.layout.simple_spinner_item, AnonymousType.values()));
		_spinnerMap.setAdapter(new ArrayAdapter<MapType>(this, android.R.layout.simple_spinner_item, MapType.values()));
		_spinnerMessaging.setAdapter(new ArrayAdapter<MessagingType>(this, android.R.layout.simple_spinner_item, MessagingType.values()));
		_spinnerPotType.setAdapter(new ArrayAdapter<PotType>(this, android.R.layout.simple_spinner_item, PotType.values()));
	}

	public void onCreateButtonClick(View v) {
		String gameName = _editTextGameName.getText().toString();
		String gamePassword = _editTextGamePassword.getText().toString();
		// int betSize =
		// Integer.parseInt(_editTextBetSize.getText().toString());
		// AnonymousType _anonymousSelected = (AnonymousType)
		// _spinnerAnonymous.getSelectedItem();
		MapType _mapSelected = (MapType) _spinnerMap.getSelectedItem();
		// MessagingType _messagingSelected = (MessagingType)
		// _spinnerMessaging.getSelectedItem();
		// PotType _potTypeSelected = (PotType)
		// _spinnerPotType.getSelectedItem();

		DiplomacyMap mapSelected = DiplomacyProxy.getDiplomacyMap(_mapSelected.toString());

		IGame newGame = (DiplomacyGame) DiplomacyProxy.createDiplomacyGame(_user, gameName, gamePassword, mapSelected);

		Intent i = new Intent(this, DiplomacyGameAct.class);
		i.putExtra("game", newGame);
		startActivity(i);
	}
}
