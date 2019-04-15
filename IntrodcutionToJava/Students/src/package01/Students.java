package package01;

import java.util.Scanner;
public class Students {
	String name;
	String surname; 
	int indexNo;
	int score;
	public Students(String Name, String Surname, int IndexNo, int Score) {
		
		this.name = Name;
		this.surname = Surname;
		this.indexNo = IndexNo;
		this.score = Score;
		
		
	}
	public void inputDateAboutStudents() {
		Scanner odczyt = new Scanner(System.in);
		
		

		System.out.println("Set name");
		this.name = odczyt.nextLine();
		System.out.println("Set surname ");
		this.surname = odczyt.nextLine();
		System.out.println("Set index");
		this.indexNo = odczyt.nextInt();
		System.out.println("Set score");
		this.score = odczyt.nextInt();		
	}
	public void CheckIndexNo() {
		if(this.indexNo <= 99999 && this.indexNo >= 0){
			System.out.print("wrong indexNo");
		}
		if(this.indexNo <= 200000 && this.indexNo >= 100000){
			System.out.print("old student");
		}
		if(this.indexNo <= 999999 && this.indexNo >= 200000){
			System.out.print("young student");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StudentsTester st = new StudentsTester();
		st.printHighestScore();

	}

}
