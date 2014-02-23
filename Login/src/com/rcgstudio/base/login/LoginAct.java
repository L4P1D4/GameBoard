package com.rcgstudio.base.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.messaging.entities.MessagingProxy;

public class LoginAct extends Activity {

	private IUser _user;
	private EditText _editTextUser;
	private EditText _editTextPassword;
	private TextView _textViewResult;
	GameBoardApplication _application;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		_application = ((GameBoardApplication) getApplicationContext());
		_editTextUser = (EditText) findViewById(R.id.editTextUser);
		_editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		_textViewResult = (TextView) findViewById(R.id.textViewResult);
	}

	public void onLoginButtonClick(View v) {
		_textViewResult.setText("");
		_user = ((MessagingProxy)_application.getProxy(MessagingProxy.class)).getUser(_editTextUser.getText().toString(), _editTextPassword.getText().toString());
		if (_user != null) {
			_application.setCurrentUser(_user);
			setResult(RESULT_OK);
			finish();
		} else {
			_textViewResult.setText(R.string.login_failed);
		}
	}
	
	public void onNewUserButtonClick(View v) {
		_textViewResult.setText("");
		Intent i = new Intent(_application, NewUserAct.class);
		startActivityForResult(i, 1);
	}	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {			
			;
		} else if (resultCode == RESULT_OK && requestCode == 1) {
			setResult(RESULT_OK);
			finish();
		}
	}
}