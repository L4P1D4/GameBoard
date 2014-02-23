package com.rcgstudio.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rcgstudio.core.interfaces.IGame;
import com.rcgstudio.core.interfaces.IUser;

public class User extends Entity implements IUser {

	private static final long serialVersionUID = 1L;
	private String _name;
	private String _email;
	private String _password;
	private List<IGame> _userGameList;
	private Map<Class<?>, Integer> _scores = new HashMap<Class<?>, Integer>();

	public User(long id, String name, String password, String email) {
		super(id);
		_userGameList = new ArrayList<IGame>();
		_name = name;
		_password = password;
		_email = email;
	}

	public String getName() {
		return _name;
	}

	public String getEmail() {
		return _email;
	}

	public String getPassword() {
		return _password;
	}

	public List<IGame> getUserGameList() {
		return _userGameList;
	}

	public void addUserGame(IGame gameToAdd) {
		_userGameList.add(gameToAdd);
	}

	public Integer getScore(Class<?> gameClass) {
		if (_scores.containsKey(gameClass)) {
			return _scores.get(gameClass);
		}
		return 0;
	}
	
}
