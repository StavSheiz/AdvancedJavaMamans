package ex11;

import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

	public DeckOfCards() {
		this.cards = new ArrayList<Card>();
		
		for(int suit = 0; suit < 4; suit++) {
			for(int face = 0; face < 13; face++) {
				Card c = new Card(EnumCardFace.values()[face], EnumCardSuit.values()[suit]);
				this.cards.add(c);
			}
		}
	}
	
	private ArrayList<Card> cards;
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
	
	public Card popCard() {
		if(!this.cards.isEmpty()) {
			return this.cards.remove(0);
		} else {
			return null;
		}
	}

}
