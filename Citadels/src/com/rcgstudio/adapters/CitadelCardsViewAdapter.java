package com.rcgstudio.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.rcgstudio.citadels.R;
import com.rcgstudio.citadels.entities.CitadelsCard;

public class CitadelCardsViewAdapter extends BaseAdapter {

	Context _context;
	List<? extends CitadelsCard> _cardsList;

	public CitadelCardsViewAdapter(Context c, List<? extends CitadelsCard> cardsList) {
		_context = c;
		_cardsList = cardsList;
	}

	public int getCount() {
		if (_cardsList == null)
			return 0;
		return _cardsList.size();
	}

	public Object getItem(int position) {
		if (_cardsList == null) {
			return null;
		}
		return _cardsList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.citadels_card_item, null);
		ImageView cardImageView = (ImageView) rowView.findViewById(R.id.imageViewCard);

		CitadelsCard currentCard = _cardsList.get(position);
		if (currentCard.getIsTurned()) {
			cardImageView.setImageResource(CitadelsCard.IconIdTurned);
		} else {
			cardImageView.setImageResource(currentCard.getIconId());
		}

		return rowView;
	}
}