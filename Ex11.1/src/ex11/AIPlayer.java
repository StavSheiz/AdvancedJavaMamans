package ex11;

public class AIPlayer extends Player {

	public AIPlayer(String name) {
		super(name);
	}
	
	public boolean playNextMove() {
		return this.getHandValue() < 18;
	}

}
