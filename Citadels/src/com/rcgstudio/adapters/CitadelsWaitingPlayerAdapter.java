package com.rcgstudio.adapters;

import java.util.List;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsGame;
import com.rcgstudio.core.interfaces.IUser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CitadelsWaitingPlayerAdapter extends BaseAdapter {

	private Context _context = null;
	private List<IUser> _usersList;

	public CitadelsWaitingPlayerAdapter(Context context, List<IUser> gameList) {
		_context = context;
		_usersList = gameList;
	}

	public List<IUser> getUsersList() {
		return _usersList;
	}

	public int getCount() {
		if (_usersList == null)
			return 0;
		return _usersList.size();
	}

	public Object getItem(int position) {
		if (_usersList == null) {
			return null;
		}
		return _usersList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		return false;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (_usersList == null)
			return null;

		IUser user = _usersList.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.player_waiting_item, null);
		}

		TextView textViewUserName = (TextView) convertView.findViewById(R.id.textViewUserName);
		TextView textViewUserScore = (TextView) convertView.findViewById(R.id.textViewUserRatingValue);
		textViewUserName.setText(user.getName());
		textViewUserScore.setText(user.getScore(CitadelsGame.class).toString());

		return convertView;
	}
}
