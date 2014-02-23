package com.rcgstudio.baseapplication.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcgstudio.baseapplication.R;
import com.rcgstudio.core.interfaces.IGame;

public class OpenedGameItemAdapter extends BaseAdapter {

	private Context _context = null;
	private List<IGame> _gamesList;

	public List<IGame> getGamesList() {
		return _gamesList;
	}

	public OpenedGameItemAdapter(Context context, List<IGame> gameList) {
		_context = context;
		_gamesList = gameList;
	}

	public int getCount() {
		if (_gamesList == null)
			return 0;
		return _gamesList.size();
	}

	public Object getItem(int position) {
		if (_gamesList == null) {
			return null;
		}
		return _gamesList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return true;
	}

	@Override
	public boolean isEnabled(int position) {
		return true;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (_gamesList == null)
			return null;

		IGame game = _gamesList.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.openedgameitem, null);
		}

		TextView gameNameTextView = (TextView) convertView.findViewById(R.id.textViewGameName);
		gameNameTextView.setText(game.getName());
		ImageView gameTypeImageView = (ImageView) convertView.findViewById(R.id.imageViewGameIcon);
		gameTypeImageView.setImageResource(game.getIconId());

		ImageView imageViewLock = (ImageView) convertView.findViewById(R.id.imageViewLock);
		int lockVisibleVisibility = View.INVISIBLE;
		if (game.hasPassword())
		{
			lockVisibleVisibility = View.VISIBLE;
		}
		imageViewLock.setVisibility(lockVisibleVisibility);
		
		TextView currentPlayersTextView = (TextView) convertView.findViewById(R.id.textViewCurrentPlayers);
		currentPlayersTextView.setText(game.getCurrentNumberOfPlayers().toString());
		TextView maxPlayersTextView = (TextView) convertView.findViewById(R.id.textViewMaxPlayers);
		maxPlayersTextView.setText(game.getMaxPlayers().toString());

		return convertView;
	}
}
