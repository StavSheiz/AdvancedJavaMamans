
public class SumRunnable implements Runnable {

	public SumRunnable(ArrayManager array, Controller controller) {
		this.array = array;
		this.controller = controller;
	}
	
	private ArrayManager array;
	private Controller controller;

	@Override
	public void run() {
		PairOfNumbers numbers = this.array.getNumbers();
		
		while(numbers != null) {
			this.array.addNumber(numbers.sum());
			
			numbers = this.array.getNumbers();
		}
		
		this.controller.finished();
	}

}
