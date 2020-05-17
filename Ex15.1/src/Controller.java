
public class Controller {

	public Controller(int m) {
		this.num = m;
	}

	private int num;
	private int count = 0;
	
	public synchronized void finished() {
		count ++;
		if(count >= num) {
			notify();
		}
	}
	
	public synchronized void waitForThreads() {
		while(count < num) {
			try {
				wait();
			} catch(InterruptedException e) {
				System.out.println("interrupted while waiting");
			} 
		}
	}
}
