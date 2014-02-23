package com.rcgstudio.core.entities;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;

import com.rcgstudio.core.interfaces.IProxy;
import com.rcgstudio.core.interfaces.IUser;

public class GameBoardApplication extends Application {

	private IUser _currentUser;
	private Map<Class<?>, IProxy> _proxiesList = new HashMap<Class<?>, IProxy>();

	public IUser getCurrentUser() {
		return _currentUser;
	}

	public void setCurrentUser(IUser currentUser) {
		_currentUser = currentUser;
	}

	public void addProxy(IProxy newProxy) {
		if (!_proxiesList.containsKey(newProxy.getClass())) {
			_proxiesList.put(newProxy.getClass(), newProxy);
		}
	}

	public IProxy getProxy(Class<?> proxyClass) {
		if (_proxiesList.containsKey(proxyClass)) {
			return _proxiesList.get(proxyClass);

		}
		return null;
	}
	
	public Map<Class<?>, IProxy> getProxiesList()
	{
		return _proxiesList;
	}
}
