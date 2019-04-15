package package01;

public class StudentsTester {
	public StudentsCreator Stud100;
	public void Add100Stud() {
		Stud100 = new StudentsCreator();
		Stud100.CreateStudents(100);
	}
	public StudentsTester() {
		Stud100 = new StudentsCreator();
		Stud100.CreateStudents(100);
	}
	public boolean ifcount100() {
		if(Stud100.Stud.length==100) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public void printHighestScore() {
		StudentsStats sStats = new StudentsStats();
		System.out.print(sStats.mhighestscore(Stud100.Stud));
	}
}
