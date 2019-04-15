	import java.util.ArrayList;
	import java.util.List;
	
	import java.math.BigInteger;

	public class MainClass {
		public static int[][] createRectArr(int rowNo, int colNo){
			int[][] ndArray = new int[rowNo][colNo];
			return ndArray;
		}
		public static int[][] createTriangleArr(int rowNo){
			int[][] ndArray = new int[rowNo][];
			for(int i = 0; i < rowNo; i++){
				ndArray[i] = new int[i+1];
			}
			return ndArray;
		}
		public static int calcFibonacci(int n) {
			int nDig = 1;
			int f1 = 0;
			int f2 = 1;
			for(int i = 0; i<n; i++) {
				nDig = f1 + f2;
				f1 = f2; 
				f2 = nDig;
				
			}
			return nDig; 
		}
		public static void calcFibonacciB() {
			BigInteger f;
			BigInteger count = new BigInteger("0");
			BigInteger a = new BigInteger("20000000000");
			BigInteger nDig = new BigInteger("1");
			BigInteger f1 = new BigInteger("0");
			BigInteger f2 = new BigInteger("1");
			BigInteger f1f2sum = new BigInteger("0");
			BigInteger f1f2sumNEW = new BigInteger("0");

			//int nDig = 1;
			//int f1 = 0;
			//int f2 = 1;
			while(true) {
				count = count.add(a);
				f1f2sum = f1f2sumNEW;
				f1f2sum =f1f2sum.add(f1);
				f1f2sum = f1f2sum.add(f2);
				nDig = f1f2sum;
				f1 = f2; 
				f2 = nDig;
				System.out.print(count);
				//System.out.print(nDig);
				System.out.println();
			}
		}
		public static void fillWithFibonacci(int [][] data) {
			int a = 0;
			for(int i = 0; i<data.length; i++) {
				for(int j = 0; j<data[i].length; j++) {
					data[i][j] = calcFibonacci(a);a++;
				}
			}
		}
		public static int lookup(int [][] data, int val) {
			
			int nHowMany = 0;
			for (int i = 0; i<data.length; i++) {
				for(int j: data[i]) {
					if(val == j) {
						nHowMany++;
					}
				}
			}
			return nHowMany;
		}
		public static void showByRows (int [][] data) {
			for (int i = 0; i<data.length; i++) {
				for(int j: data[i]) {
					System.out.print(j + " ");
				}
				System.out.println();
			}
		}
		public static int getGreatesOdd(int [][] data) {
			int nGreatestOdd = 0;
			boolean bGiveFirstValue = true;
			for(int i = 0; i<data.length; i++) {
				for(int j: data[i]) {
					if(bGiveFirstValue) {
						nGreatestOdd = j;
						bGiveFirstValue = false;
					}
					if(j % 2 == 1){
						nGreatestOdd = Math.max(j, nGreatestOdd);
					}
				}
			}
			return nGreatestOdd;
		}
		public static int getMAxEven(int [][] data) {
			int nGreatestEven = 0;
			boolean bGiveFirstValue = true;
			for(int i = 0; i<data.length; i++) {
				for(int j: data[i]) {
					if(bGiveFirstValue) {
						nGreatestEven = j;
					}
					if(j % 2 == 0){
						nGreatestEven = Math.max(j, nGreatestEven);
					}
				}
			}
			return nGreatestEven;
		}
	 	public static void main(String args[]) {
	 		System.out.println(calcFibonacci(10));
	 		int [][] ra= createRectArr(5, 5);
	 		int [][] rt= createTriangleArr(5);
	 		fillWithFibonacci(rt);
	 		fillWithFibonacci(ra);
	 		//System.out.print(calcFibonacci(Integer.MAX_VALUE));
	 		showByRows(rt);
	 		showByRows(ra);
	 		System.out.println(getGreatesOdd(ra));
	 		calcFibonacciB();
		}
	
	}
