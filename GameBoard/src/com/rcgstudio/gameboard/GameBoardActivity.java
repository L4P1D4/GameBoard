package com.rcgstudio.gameboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.rcgstudio.base.login.LoginAct;
import com.rcgstudio.baseapplication.activities.UserGamesListAct;
import com.rcgstudio.citadels.proxy.CitadelsProxy;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IMessaging;
import com.rcgstudio.diplomacy.proxy.DiplomacyProxy;
import com.rcgstudio.messaging.entities.MessagingProxy;
import com.rcgstudio.tictactoe.proxy.TicTacToeProxy;

public class GameBoardActivity extends Activity {

	GameBoardApplication _application;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		_application = ((GameBoardApplication) getApplicationContext());
		MessagingProxy _messagingProxy = new MessagingProxy();
		_application.addProxy(_messagingProxy);
		_application.addProxy(new DiplomacyProxy((IMessaging) _application.getProxy(MessagingProxy.class)));
		_application.addProxy(new CitadelsProxy((IMessaging) _application.getProxy(MessagingProxy.class)));
		_application.addProxy(new TicTacToeProxy((IMessaging) _application.getProxy(MessagingProxy.class)));
		
		_messagingProxy.populateMockDatabase();

		Intent i = new Intent(this, LoginAct.class);
		startActivityForResult(i, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			finish();
		} else if (resultCode == RESULT_OK && requestCode == 1) {
			Intent i = new Intent(this, UserGamesListAct.class);
			startActivityForResult(i, 2);
		}
	}
}