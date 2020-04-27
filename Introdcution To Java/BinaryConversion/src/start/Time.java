package start;
import java.util.*;

public class Time {
	public static int nMax;
	public static int nMin;
	public static void findmaxandmin(int[] a) {
		nMax = nMin = a[0];
		for(int i = 1; i < a.length ; i++) {
			
			if(nMax<a[i]) {
				nMax = a[i];
			}
			if(nMin>a[i]) {
				nMin = a[i];
			}
		}
		System.out.print("Max: " + nMax + "\nMin: " + nMin);
	}
	public static void main(String[] args) {
		int c[] = {1,2,-2,19,25};
		findmaxandmin(c);
	}
	
}