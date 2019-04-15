package start;
import java.util.Scanner;

public class ArithmeticMean {
	public static void main(String[] args) {
		int sum=0;
		int elemNo=0;
		int currElem=0;
		int nMin=0;
		int nMax=0;
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			String nextIntString = sc.next();
			
			try{
				currElem = Integer.parseInt(nextIntString);
			}
			catch(NumberFormatException e){
				break;
			}
			
			if(elemNo == 0) {
				nMin = currElem;
				nMax = currElem;
			}
			if(elemNo > 0) {
				nMin = Math.min(nMin, currElem);
				nMax = Math.max(nMax, currElem);
			}
    
			
			sum=sum+currElem;
			elemNo=elemNo+1;
			
		}
		if(elemNo==0) {
			System.err.print("No data available\n");
			System.exit(1);
		}
		if(elemNo==1) {
			System.out.println("there is no max and min element in the set");
		}
		else{
			System.out.println("Mid-range in set A is equal " + 0.5*(nMin+nMax));
			
		}
		sc.close();
		System.out.print("The sum of "+elemNo);
		System.out.print(" is "+sum+"\n");
		System.exit(0);
	}

}