import java.util.ArrayList;
import java.util.Random;

public class ArrayManager {

	public ArrayManager(int n) {
		this.initArray(n);
	}
	
	private ArrayList<Integer> myArray;
	
	private void initArray(int n) {
		this.myArray = new ArrayList<Integer>();

		Random rand = new Random();
		
		for(int i=0; i<n; i++) {
			int number = rand.nextInt(100) + 1;

			this.myArray.add(number);
		}
	}
	
	public synchronized PairOfNumbers getNumbers() {
		if(this.myArray.size() <2) {
			return null;
		}
		
		return new PairOfNumbers(this.myArray.remove(this.myArray.size() -1), this.myArray.remove(this.myArray.size() -1));
	}
	
	public synchronized void addNumber(int number) {
		this.myArray.add(number);
	}
	
	public synchronized int getNumber() {
		return this.myArray.get(0);
	}
	
	// For testing
	public int getSum() {
		int sum=0;
		
		for(int i = 0; i < this.myArray.size(); i++)
		    sum += this.myArray.get(i);
		return sum;
	}
}
