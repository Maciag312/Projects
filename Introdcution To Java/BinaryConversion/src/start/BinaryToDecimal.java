package start;
import java.util.List;
import java.util.ArrayList;

public class BinaryToDecimal {
	public static void B2D(String Binary) {
		int Decimal = 0;
		for(int i = 0; i< Binary.length(); i++) {
			Decimal += Character.getNumericValue(Binary.charAt(Binary.length()-1-i))*Math.pow(2,i);
		
		}
		System.out.println(Decimal);
	}
	public static void main(String[] args) {
		System.out.println(rArrayofodd1to99()[5]);
		
	}
	public static int DigitsToSumIntiger(int Number) {
		String sNumb = Integer.toString(Number);
		int Sum = 0;
		for(int i=0; i<sNumb.length(); i++) {
			Sum += Character.getNumericValue((sNumb.charAt(i)));
		}
		return Sum;
		
	}
	public static void printoddf1to99() {
		for(int i = 1; i<=99; i+=2) {
			System.out.println(i);
		}
	}
	public static int[] rArrayofodd1to99() {
	
		ArrayList<Integer> Array = new ArrayList<Integer>();
		for(int i = 1; i<=99; i+=2) {
			Array.add(i);
		}
		int[] rArr =  Array.stream().mapToInt(i->i).toArray();
		
		return rArr; 
	}
	
	

}
