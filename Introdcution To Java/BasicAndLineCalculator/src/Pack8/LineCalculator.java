package Pack8;
import java.util.Scanner;


public class LineCalculator extends BasicCalculator{
	
	
	public void calc(){
		System.out.println("Enter calculations: ");	
		String line = "";
		Scanner scan= new Scanner(System.in);
		
		while(scan.hasNextLine() ){
			line = scan.nextLine();
			if (line.toLowerCase().equals("exit")) {
				break;
			}
			System.out.println(calculate(line));
		}
		
		scan.close();
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LineCalculator Calculation = new LineCalculator();
		Calculation.calc();
	
}
	

}
