import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int m=5, n;
		Scanner in = new Scanner(System.in);

		
		System.out.println("Enter number of numbers in array");
		n = in.nextInt();
		System.out.println("Enter number of threads");
		m = in.nextInt();
		
		Controller controller = new Controller(m);
		ArrayManager array = new ArrayManager(n);
		
		// For testing
		System.out.println("Sum: " + array.getSum());

		for(int i=0; i<m; i++) {
			new Thread(new SumRunnable(array,controller)).start();
		}
		
		controller.waitForThreads();
		
		// Real answer
		System.out.println(array.getNumber());
	}

}
