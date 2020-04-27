package start;


import java.util.Scanner;
public class odddigitstointeger {
	public static void main(String[] args) {
		
		String number2Convert;
		String result = "";
		Scanner source = new Scanner(System.in);
		System.out.print("Enter the Decimal Number:");
		number2Convert = source.nextLine(); 
		int reminder = 0;
		for(int i = 0; i < number2Convert.length(); i++) {
			reminder = Character.getNumericValue(number2Convert.charAt(i)) % 2;
			if(reminder == 1) {
				result += Character.toString(number2Convert.charAt(i));
				
			}
		}
		System.out.println(result.length());
		source.close();
	} 
}
