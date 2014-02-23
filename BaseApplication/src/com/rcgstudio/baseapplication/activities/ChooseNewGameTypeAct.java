package com.rcgstudio.baseapplication.activities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.rcgstudio.baseapplication.R;
import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IGameProxy;
import com.rcgstudio.core.interfaces.IProxy;
import com.rcgstudio.core.utils.UIUtils;

public class ChooseNewGameTypeAct extends Activity {

	GameBoardApplication _application;
	ArrayList<IGameProxy> _gameProxiesList = new ArrayList<IGameProxy>();
	Context _currentContext;
	Map<Button, Class<?>> _buttonNewConfigurationMap = new HashMap<Button, Class<?>>();
	Map<Button, Class<?>> _buttonGameTypenMap = new HashMap<Button, Class<?>>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choosenewgametype);

		_application = ((GameBoardApplication) getApplicationContext());

		LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.linearLayoutMain);

		Collection<IProxy> proxiesList = _application.getProxiesList().values();
		_currentContext = getApplicationContext();

		for (IProxy iProxy : proxiesList) {
			if (iProxy instanceof IGameProxy) {
				_gameProxiesList.add((IGameProxy) iProxy);
				Button newButton = CreateGameButton((IGameProxy) iProxy);
				mainLinearLayout.addView(newButton);
			}
		}
	}

	View.OnClickListener getOnClickCreateConfigurator(final Button button) {
		return new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(_currentContext, _buttonNewConfigurationMap.get(button));
				i.putExtra("gameType", _buttonGameTypenMap.get(button));
				startActivity(i);
			}
		};
	}

	private Button CreateGameButton(IGameProxy proxy) {
		Button newGameButton = new Button(this);

		newGameButton.setText(proxy.getGameName());

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(UIUtils.dpToPixels(getApplicationContext(), 110),
				LinearLayout.LayoutParams.WRAP_CONTENT);
		newGameButton.setLayoutParams(layoutParams);
		layoutParams.setMargins(UIUtils.dpToPixels(getApplicationContext(), 10), UIUtils.dpToPixels(getApplicationContext(), 10),
				UIUtils.dpToPixels(getApplicationContext(), 10), UIUtils.dpToPixels(getApplicationContext(), 10));
		newGameButton.setOnClickListener(getOnClickCreateConfigurator(newGameButton));
		_buttonGameTypenMap.put(newGameButton, proxy.getGameClass());
		_buttonNewConfigurationMap.put(newGameButton, proxy.getNewGameConfigurationClass());
		return newGameButton;
	}

}
