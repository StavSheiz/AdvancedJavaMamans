package ex11;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Player {

	public Player(String name) {
		this.hand = new ArrayList<Card>();
		this.name = name;
	}
	
	private ArrayList<Card> hand;
	private String name;
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public String toString() {
		return this.name + ": " + this.getHandString();
	}
	
	public void resetHand() {
		this.hand.clear();
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean playNextMove() {
		String message = this.getHandString();
		String messageTitle = this.name + "'s turn";
		int result = 1;
		if(this.getHandValue() < 21) {
			result = JOptionPane.showConfirmDialog(null, message, messageTitle, JOptionPane.YES_NO_OPTION);			
		}

		return result == 0; // 0=yes, 1=no
	}
		
	
	protected int getHandValue() {
		int count = 0;
		int countAce = 0;
		
		for(Card c : this.hand) {
			count += c.getValue();
			
			if(c.getValue() == 1) {
				countAce++;
			}
		}
		
		while(countAce > 0 && Math.abs(count + 10 - 21) < Math.abs(count - 21)) {
			countAce--;
			count += 10;
		}
		
		return count;
	}

	private String getHandString() {
		return this.hand.toString() + ", Current Score: " + this.getHandValue();
	}
}
