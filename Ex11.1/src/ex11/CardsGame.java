package ex11;

import java.util.ArrayList;

public class CardsGame {

	public CardsGame(int humanPlayers, int AIPlayers) {
		this.players = new ArrayList<Player>();
		for(int i = 0; i < humanPlayers; i++) {
			players.add(new Player("player " + i));
		}
		
		for(int j = humanPlayers; j < AIPlayers + humanPlayers; j++) {
			players.add(new AIPlayer("AIplayer" + j));
		}
	}
	
	private ArrayList<Player> players;
	private DeckOfCards deck;
	
	public void initGame() {
		this.deck = new DeckOfCards();
		this.deck.shuffle();		

		players.forEach(player -> {
			player.resetHand();
			player.addCard(deck.popCard());
			player.addCard(deck.popCard());
		});
	}
	
	public ArrayList<Player> Play() {
		players.forEach(player -> {
			boolean isPlay = true;
			
			while(isPlay && !this.deck.isEmpty()) {
				isPlay = player.playNextMove();
				
				if(isPlay) {
					player.addCard(deck.popCard());
				}
			}
		});
		
		return this.getWinners();
	}
	
	public String toString() {
		String str = "";
		for(Player p : this.players) {
			str += p.toString() + " \n";
		}
		
		return str;
	}
	

	
	private ArrayList<Player> getWinners() {
		ArrayList<Player> winners = new ArrayList<Player>();
		winners.add(new Player("none"));
		
		for(Player p : this.players) {
			int currValue = p.getHandValue();
			int winnerValue = winners.get(0).getHandValue();
			if(currValue <= 21 && 21 - currValue < 21 - winnerValue) {
				winners.clear();
				winners.add(p);
			} else if (currValue == winnerValue) {
				winners.add(p);
			}
		}
		
		return winners;
	}
}
