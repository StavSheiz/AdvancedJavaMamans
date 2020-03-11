package ex11;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CardsGameMain {

	public static void main(String[] args) {
		CardsGame game = new CardsGame(1,1);
		
		boolean play = true;
		
		while(play) {
			game.initGame();
			ArrayList<Player> winners = game.Play();
			
			String messageTitle ="Game Over";
			String message = game.toString() +"\n";

			message += getWinnersMessage(winners);
			message += "! Play Again?";

			
			int result = JOptionPane.showConfirmDialog(null, message, messageTitle, JOptionPane.YES_NO_OPTION);
			
			play = (result == 0);
		}
		
		System.exit(0);
	}
	
	private static String getWinnersMessage(ArrayList<Player> winners) {
		String message = "";
		
		if(winners.size() == 1) {
			message += "Winner: " + winners.get(0).getName();
			
		} else {
			String winnerNames = "";
			
			for(Player p : winners) {
				winnerNames += p.getName() + ", ";
			}
			
			message += "Tie: " + winnerNames;
		}
			
		return message;
	}

}
