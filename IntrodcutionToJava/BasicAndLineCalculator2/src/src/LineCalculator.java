package src;
import java.util.*;
import java.util.Vector;
public class LineCalculator extends BasicCalculator{
	
	private String getRound(String line) {
	    // this is very simple and interesting 
	    double a  = Double.parseDouble(line);

	    //  round  val is  :  1.6666666666666667
	    // if you want to only two precision point with double we 
	            //  can use formate option in String 
	           // which takes 2 parameters one is formte specifier which 
	           // shows dicimal places another double value 
	    String s = String.format("%.15f", a);
	    return s;
	    // now out put will be : val is :1.67
	}
	
	public String calculateLine(String line) {
//		if(!line.substring(0, 0).matches("[0-9]+") || !line.substring(line.length()-1, line.length()-1).matches("[0-9]+")){
//			System.out.println("On beggining should be a number not an operator");
//			return "";
//		}
		String result ="";
		line = line.trim();
		String[] Numbers = line.split("-|\\+|\\*");
		String[] oper = line.split("[0-9]+|\\.");
		int howmanyblanks = 0; 
		for(int i = 0; i<oper.length; i++) {
			if(oper[i].isBlank()) {
				howmanyblanks++;
			}
		}
		String[] Operators = new String[oper.length-howmanyblanks];

		for(int i = 0; i<Operators.length; i++) {
			Operators[i] = oper[i+howmanyblanks];
		}
		String[] results = new String[Operators.length];
//		for(int i = 0; i<Numbers.length; i++) {
//			System.out.println(Numbers[i]);
//		}
//		for(int i = 0; i<Operators.length; i++) {
//			System.out.println(Operators[i]);
//		}
//		System.out.println(Operators.length+1);
//		System.out.println(Numbers.length);
		if(Operators.length+1 != Numbers.length) {
			System.out.println("Invalid using of operators");
		}else {
			int a = -1;
			for(int i = 0; i<Operators.length; i++) {
				a++;
				if(Operators[i].toCharArray()[0] == '*') {
					Operators[i] = "+";
					Numbers[i] = this.calculate((Numbers[i]+ "*" + Numbers[i+1]));
					Numbers[i+1] = "0";
				}

			}
			for(int j=0;j<Operators.length; j++) {
					
				Numbers[j+1] = this.calculate((Numbers[j]+ Operators[j] + Numbers[j+1]));
			}
			result = Numbers[Operators.length];
		}
		
		return getRound(result); 

	}
	
	public void calc(){
		System.out.println("Calculate: ");	
		String line = "";
		Scanner scan= new Scanner(System.in);
		
		while(scan.hasNextLine() ){
			line = scan.nextLine();
			if (line.toLowerCase().equals("exit")) {
				break;
			}
			System.out.println(calculateLine(line));
		}
		
		scan.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LineCalculator Calculation = new LineCalculator();
		Calculation.calc();
	
}
	

}