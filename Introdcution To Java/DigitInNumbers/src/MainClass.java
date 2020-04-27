import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class MainClass {
	public static int getIntegerNext(Scanner sc) {		
		int next = 0;
		if(sc.hasNextInt()) {
			next = sc.nextInt();
			return next;
		}
		if(Objects.equals(sc.next(), "close")) {
			return Integer.MAX_VALUE;
		}
		return 0;
	}
	public static void printAllNumbers(Scanner sc) {
		int checkedNumber = 123;// tells me that two last digits arent the same 
		String convertedNumber = "";
		List<String> list = new ArrayList<String>();
		
		while(sc.hasNextInt()) {
			checkedNumber = sc.nextInt();
			convertedNumber = Integer.toString(checkedNumber);
			if(convertedNumber.length()>1) {
				if(convertedNumber.charAt(convertedNumber.length()-1) == convertedNumber.charAt(convertedNumber.length()-2)){
					list.add(convertedNumber);
				}
			}
		}
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	public static int countDigits(int number, int[] frq) {
		int a = 0;
		for(int i = 0; i<frq.length; i++) {
			if(number == frq[i]) {
				a++;
			}
		}
		return a;
	}
	public static int[] DigitsOccuredInNumber(int number) {
		int[] nArr = new int[10];
 		String str1 = Integer.toString(number);
		for(int i=0; i<str1.length(); i++)
		{
			if(i == 10) {
				System.out.println("Out of bounds");
				break;
			}
			nArr[i] = str1.charAt(i);
			
		}
		int[] nArr2 = new int[10];
		for(int i=0; i<10; i++) {
			nArr2[i] = countDigits(i, nArr);
		}
		
		return nArr2;
	}
	public static int whatDigitOccuredMostlyInNumbers(Scanner sc) {
		int nInput;
		int[] nWhichDigtMostly = {0,0,0,0,0,0,0,0,0,0};
		while(sc.hasNextInt()) {
			nInput = sc.nextInt();
			int[] nDigt = new int[10];
			nDigt = DigitsOccuredInNumber(nInput);
			for(int i=0; i<10; i++) {
				nWhichDigtMostly[i] += nDigt[i];
			}
		}
		int nMax = 0;
		int Digt = 0;
		for(int i = 0; i<10; i++) {
			if(nMax<nWhichDigtMostly[i]) {
				nMax = nWhichDigtMostly[i];
				Digt = i;
			}
		}
		return Digt; 
	}
	public static String WhatDigitsOccureMorethanAverage(Scanner sc) {
		int nInput = 0;
		float average = 0;
		String retArr = "";
		int[] nWhichDigtMostly = {0,0,0,0,0,0,0,0,0,0};
		while(sc.hasNextInt()) {
			nInput = sc.nextInt();
			int[] nDigt = new int[10];
			nDigt = DigitsOccuredInNumber(nInput);
			for(int i=0; i<10; i++) {
				nWhichDigtMostly[i] += nDigt[i];
			}
			
		}
		for(int i=0; i<10;i++) {
			average += nWhichDigtMostly[i];
			
		}
		average = average/10;
		for(int i=0; i<10; i++) {
			if(nWhichDigtMostly[i]>average) {
				retArr += (Integer.toString(i) + ", ");
			}
		}
		return retArr;
	}
	
	public static void main(String args[]) {
		Scanner sc1 = new Scanner(System.in);
		int[] freq = {0,1,2,3,4,5,6,7,8,9};
		//System.out.println(getIntegerNext(sc1));
		countDigits(6, freq);
		//printAllNumbers(sc1);
		sc1.close();
		
	}
}
