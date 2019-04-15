package package01;

import java.util.ArrayList;
import java.util.List;

public class StudentsCreator {
	int n; 
	Students[] Stud;
	
	public StudentsCreator() {
		
	}
	public void CreateStudents(int nStudents){

		List<Students> ConvertStudents = new ArrayList<Students>();
		
		
		this.n = nStudents;
		for(int i = 0; i<n; i++) {
			ConvertStudents.add(new Students("Jędrzej", "Chołuj" ,(int)(Math.random()*999999+0),(int)(Math.random()*10+1)));
		}
		Stud = ConvertStudents.toArray(new Students[ConvertStudents.size()]);

	}
	public Students[] ReturnStudents() {
		return this.Stud;
	}
}
