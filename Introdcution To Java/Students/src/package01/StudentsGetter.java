package package01;
import java.util.List;
import java.util.ArrayList;

public class StudentsGetter {
	public StudentsCreator sStud;
	public StudentsGetter() {
		sStud = new StudentsCreator();
		sStud.CreateStudents(100);
	}
	public int[] returnAIndexes(){
		int[] aReturned = new int[sStud.Stud.length];
		for(int i=0; i< sStud.Stud.length; i++) {
			aReturned[i] = sStud.Stud[i].indexNo;
		}
		return aReturned;
	}
	public int returnNumberOfStudentsWHigerScorethan(int nScore) {
		int hold = 0;
		for(int i=0; i< sStud.Stud.length; i++) {
			
			if(sStud.Stud[i].score>nScore){
				hold++;
			}
		}
		return hold;
	}
	public Students[] returnStudentsWHigerScorethan(int nScore) {
		
		List<Students> returnedArrayOfStudents = new ArrayList<Students>();
		for(int i=0; i< sStud.Stud.length; i++) {
					
					if(sStud.Stud[i].score>nScore){
						returnedArrayOfStudents.add(sStud.Stud[i]);
					}
			}
		Students[] rStudA = returnedArrayOfStudents.toArray(new Students[returnedArrayOfStudents.size()]);
		return rStudA;
		
	}
}
