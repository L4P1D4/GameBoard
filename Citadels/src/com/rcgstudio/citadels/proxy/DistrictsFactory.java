package com.rcgstudio.citadels.proxy;

import java.util.ArrayList;

import com.rcgstudio.citadels.cards.districts.blue.*;
import com.rcgstudio.citadels.cards.districts.green.*;
import com.rcgstudio.citadels.cards.districts.red.Battlefield;
import com.rcgstudio.citadels.cards.districts.red.Fortress;
import com.rcgstudio.citadels.cards.districts.red.Prison;
import com.rcgstudio.citadels.cards.districts.red.WatchTower;
import com.rcgstudio.citadels.cards.districts.yellow.*;
import com.rcgstudio.citadels.entities.CitadelsDistrict;

public class DistrictsFactory {

	static ArrayList<CitadelsDistrict> _allDistricts = new ArrayList<CitadelsDistrict>();

	public static ArrayList<CitadelsDistrict> getAllDistricts() {

		if (_allDistricts.size() > 0) {
			return _allDistricts;
		}

		// YELLOW
		addCard(Manor.class, 5);
		addCard(Castle.class, 5);
		addCard(Palace.class, 2);
		// BLUE
		addCard(Temple.class, 3);
		addCard(Church.class, 3);
		addCard(Monastery.class, 4);
		addCard(Cathedral.class, 2);
		// GREEN
		addCard(Tavern.class, 5);
		addCard(TradingPost.class, 4);
		addCard(Market.class, 4);
		addCard(Docks.class, 3);
		addCard(Harbor.class, 3);
		addCard(TownHall.class, 2);
		// RED
		addCard(WatchTower.class, 3);
		addCard(Prison.class, 3);
		addCard(Battlefield.class, 3);
		addCard(Fortress.class, 3);

		return _allDistricts;
	}

	private static void addCard(Class<? extends CitadelsDistrict> districtClass, int numberOfInstances) {

		
		for (int i = 0; i < numberOfInstances; i++) {
			try {
				_allDistricts.add((CitadelsDistrict) districtClass.newInstance());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		}

	}
}
