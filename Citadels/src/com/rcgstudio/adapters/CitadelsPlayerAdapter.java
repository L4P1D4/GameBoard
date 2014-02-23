package com.rcgstudio.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsGame;
import com.rcgstudio.citadels.entities.CitadelsPlayerStatus;

public class CitadelsPlayerAdapter extends BaseAdapter {

	private Context _context = null;
	private List<CitadelsPlayerStatus> _playerStatusList;
	Gallery _districtsHorizontalListView;
	CitadelCardsViewAdapter _citadelsCardAdapter;

	public CitadelsPlayerAdapter(Context context, List<CitadelsPlayerStatus> playerStatusList) {
		_context = context;
		_playerStatusList = playerStatusList;
	}

	public List<CitadelsPlayerStatus> getUsersList() {
		return _playerStatusList;
	}

	public int getCount() {
		if (_playerStatusList == null)
			return 0;
		return _playerStatusList.size();
	}

	public Object getItem(int position) {
		if (_playerStatusList == null) {
			return null;
		}
		return _playerStatusList.get(position);
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
		if (_playerStatusList == null)
			return null;

		CitadelsPlayerStatus playerStatus = _playerStatusList.get(position);

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.player_item, null);
		}

		TextView textViewUserName = (TextView) convertView.findViewById(R.id.textViewUserName);
		TextView textViewUserScore = (TextView) convertView.findViewById(R.id.textViewRating);
		TextView textViewCards = (TextView) convertView.findViewById(R.id.textViewCards);
		TextView textViewGold = (TextView) convertView.findViewById(R.id.textViewGold);
		TextView textViewPoints = (TextView) convertView.findViewById(R.id.textViewPoints);
		LinearLayout layoutMain = (LinearLayout) convertView.findViewById(R.id.mainLayout);

		textViewUserName.setText(playerStatus.getUser().getName());
		String score = playerStatus.getUser().getScore(CitadelsGame.class).toString();
		textViewUserScore.setText("(" + score + ")");
		
		textViewCards.setText(Integer.valueOf(playerStatus.getHandCards().size()).toString());
		textViewGold.setText(playerStatus.getCurrentGold().toString());
		textViewPoints.setText(playerStatus.getPoints().toString());

		if (playerStatus.hasTurn()) {
			layoutMain.setBackgroundResource(R.color.gold);
		} else {
			layoutMain.setBackgroundResource(R.color.grey);
		}

		_districtsHorizontalListView = (Gallery) convertView.findViewById(R.id.horizontallistviewDistricts);

		_citadelsCardAdapter = new CitadelCardsViewAdapter(convertView.getContext(), playerStatus.getHandCards());
		_districtsHorizontalListView.setAdapter(_citadelsCardAdapter);

		_districtsHorizontalListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString() + " Clicked", Toast.LENGTH_LONG).show();

			}

		});

		return convertView;
	}
}
