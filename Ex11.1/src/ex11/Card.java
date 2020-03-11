package ex11;

public class Card {

	public Card(EnumCardFace face, EnumCardSuit suit) {
		this.face = face;
		this.suit = suit;
	}
	
	private EnumCardFace face;
	private EnumCardSuit suit;
	
	public int getValue() {
		return this.face.getValue();
	}
	
	public String toString() {
		return this.face.toString() + " of " + this.suit.toString();
	}
	

}
