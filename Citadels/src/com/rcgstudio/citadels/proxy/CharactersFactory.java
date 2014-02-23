package com.rcgstudio.citadels.proxy;

import java.util.HashMap;
import java.util.Map;

import com.rcgstudio.citadels.cards.characters.*;
import com.rcgstudio.citadels.entities.CitadelsCharacter;

public class CharactersFactory {

	public static Map<Class<?>, CitadelsCharacter> getAllCharacters(){
		
		Map<Class<?>, CitadelsCharacter> _availableCharacters = new HashMap<Class<?>, CitadelsCharacter>(18);
		
		_availableCharacters.put(Assasin.class, new Assasin());
		_availableCharacters.put(Thief.class, new Thief());
		_availableCharacters.put(Magician.class,new Magician());
		_availableCharacters.put(King.class,new King());
		_availableCharacters.put(Bishop.class,new Bishop());
		_availableCharacters.put(Merchant.class,new Merchant());
		_availableCharacters.put(Architect.class,new Architect());
		_availableCharacters.put(Warlord.class,new Warlord());
		_availableCharacters.put(Witch.class,new Witch());
		_availableCharacters.put(TaxCollector.class,new TaxCollector());
		_availableCharacters.put(Wizard.class,new Wizard());
		_availableCharacters.put(Emperor.class,new Emperor());
		_availableCharacters.put(Abbot.class,new Abbot());
		_availableCharacters.put(Alchemist.class,new Alchemist());
		_availableCharacters.put(Navigator.class,new Navigator());
		_availableCharacters.put(Diplomat.class,new Diplomat());
		_availableCharacters.put(Artist.class,new Artist());
		_availableCharacters.put(Queen.class,new Queen());
		
		return _availableCharacters;
	}
}
