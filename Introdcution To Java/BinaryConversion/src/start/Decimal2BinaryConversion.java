package start;

import java.util.Scanner;
public class Decimal2BinaryConversion {
	public static void main(String[] args) {
		
		int number2Convert;
		String result = "";
		Scanner source = new Scanner(System.in);
		System.out.print("Enter the Decimal Number:");
		number2Convert = source.nextInt(); 
		while(number2Convert > 0)
		{
				int reminder = number2Convert % 2; 
				result = reminder + result; 
				number2Convert = number2Convert / 2;
				System.out.println(result); 
				source.close();
		}
	} 
}

