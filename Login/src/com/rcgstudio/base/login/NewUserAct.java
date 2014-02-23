package com.rcgstudio.base.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

import com.rcgstudio.core.entities.GameBoardApplication;
import com.rcgstudio.core.entities.User;
import com.rcgstudio.core.interfaces.IUser;
import com.rcgstudio.messaging.entities.MessagingProxy;

public class NewUserAct extends Activity {

	private IUser _user;
	private EditText _editTextUser;
	private EditText _editTextPassword;
	private EditText _editTextEmail;
	private TextView _textViewResult;
	GameBoardApplication _application;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_user);
		_application = ((GameBoardApplication) getApplicationContext());
		_editTextUser = (EditText) findViewById(R.id.editTextUser);
		_editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		_editTextEmail = (EditText) findViewById(R.id.editTextEmail);
		_textViewResult = (TextView) findViewById(R.id.textViewResult);
	}

	public void onSaveButtonClick(View v) {
		_textViewResult.setText("");		
		Random rand = new Random();
		
		_user = new User(rand.nextInt(), _editTextUser.getText().toString(), 
				_editTextPassword.getText().toString(), _editTextEmail.getText().toString());
		
		_user = ((MessagingProxy)_application.getProxy(MessagingProxy.class)).saveUser(_user);
		if (_user != null) {
			_application.setCurrentUser(_user);
			setResult(RESULT_OK);
			finish();
		} else {
			_textViewResult.setText(R.string.user_creation_failed);
		}
	}

	public void onCancelButtonClick(View v) {
		_textViewResult.setText("");		
		setResult(RESULT_CANCELED);
		finish();
	}	
}