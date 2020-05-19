import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MatrixCalc {

	public MatrixCalc() {

	}
	
	Object monitor = new Object();
	private int currentPrint;
	int[][] matrixA;
	int[][] matrixB;
	
	public void calc(int n,int m,int p) {
		 matrixA = initMatrix(n,m);
		 matrixB = initMatrix(m,p);
		 
		System.out.println("Result");
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<p; j++) {
				new Thread(new CalcCell(i*p+j,m, i, j, matrixA, matrixB, j==(p-1))).start();
			}
		}
	}

	private int[][] initMatrix(int rows, int cols) {
		int[][] matrix = new int[rows][cols];
		
		System.out.println("New Matrix:");
		Random rand = new Random();
		
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				matrix[i][j] = rand.nextInt(10);
				System.out.print(" " + matrix[i][j] + " ");
			}
			System.out.println();
		}
		
		return matrix;
	}

	class CalcCell implements Runnable {

		public CalcCell(int id,int m, int row, int col, int[][] matrixA, int[][] matrixB, boolean endRow) {
			this.row = row;
			this.col = col;
			this.m = m;
			this.matrixA = matrixA;
			this.matrixB = matrixB;
			this.endRow = endRow;
			this.id = id;
			this.printed = false;
		}
		
		private int m;
		private int row;
		private int col;
		private int[][] matrixA;
		private int[][] matrixB;
		private boolean endRow;
		private int id;
		private boolean printed;
		
		@Override
		public void run() {
			int sum = 0;
			
			for(int i=0; i< m; i++) {
				sum += matrixA[row][i]*matrixB[i][col];
			}
			try {	
				synchronized(monitor) {
					while(!printed)
					if (id != currentPrint) {
							monitor.wait();
					} else {
						printed = true;
						System.out.print(" " + sum + (endRow ? "\n" : " "));
						currentPrint++;
						monitor.notifyAll();
					}

				} 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
