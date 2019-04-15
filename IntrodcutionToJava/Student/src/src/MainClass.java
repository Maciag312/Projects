package src;

import java.util.Vector;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
public class MainClass {
	public static Vector<Student> filtrVector(Vector<Student> StudentVector) {
		for(Student st: StudentVector) {
			for(Student st2: StudentVector) {
				if(st2.idNumber == st.idNumber) {
					st2.isOK = false;
				}
			}
		}
		for(int i = 0; i<StudentVector.size(); i++) {
			if(!StudentVector.elementAt(i).isOK) {
				StudentVector.remove(i);
			}
		}
		return StudentVector;
	}
	public static HashSet<Student> filtrHashSet(HashSet<Student> StudentHashSet){
		Iterator<Student> ir1 = StudentHashSet.iterator();
		Iterator<Student> ir2 = StudentHashSet.iterator();
		Student hold2;
		Student hold1;
		if(ir1.hasNext()) {
			hold1 = ir1.next();
			while(ir2.hasNext()) {
				hold2 = ir2.next();
				if(!hold1.equals(hold2)) {
					if(hold1.idNumber==hold2.idNumber) {
						StudentHashSet.remove(hold2);
					}
				}
			}
		}
		Iterator<Student> ir3 = StudentHashSet.iterator();
		while(ir3.hasNext()) {
			Student st03 = ir3.next();
			if(!st03.isOK) {
				StudentHashSet.remove(st03);
			}
		}
		return StudentHashSet;
	}
	public static void printHSByHeight(HashSet<Student> hsStu) {
		Iterator<Student> itr = hsStu.iterator();
		int all = 0;
		Student stHold; 
		Student stNext;
		
		stHold = itr.next();
	
		// 1 2 3 4 5 
		// 1 2 
		// 1 = 1 
		// 
		if(itr.hasNext()) {
			stNext = itr.next();
		}
		Student toPrint;
		while(all!=hsStu.size()) {
			if(itr.hasNext()) {
				stNext = itr.next();
				if(stHold.height>=stNext.height) {
					stHold = stNext;
					all++;
					System.out.println(stHold.toString());
				}
			}
		}
	}
	public static void printHSByNaturalOrder(HashSet<Student> hsStu) {
		Iterator<Student> itr = hsStu.iterator();
		int all = 0;
		Student stHold; 
		Student stNext;
		
		stHold = itr.next();

		if(itr.hasNext()) {
			stNext = itr.next();
		}
		Student toPrint;
		while(all!=hsStu.size()) {
			if(itr.hasNext()) {
				stNext = itr.next();
				if(stHold.idNumber>=stNext.idNumber) {
					stHold = stNext;
					all++;
					System.out.println(stHold.toString());
				}
			}
		}
	}
	public void printVecByHeight(Vector<Student> vecStu) {
		Student st0 = vecStu.firstElement();
		for(Student st: vecStu) {
			if(st0.height>st.height) {
				st0 = st;
				System.out.println(st0.toString());
			}
		
		}
		
	}
	public static void printVecByNaturalOrder(Vector<Student> vecStu) {
		Student hold = vecStu.firstElement();
		int a = 0; 
		while(vecStu.size()!=a) {
			for(Student st: vecStu) {
				System.out.println()
				if(st.idNumber<hold.idNumber) {
					hold = st;a++;
				}
			}
			vecStu.remove(hold);
			System.out.println(hold.toString());
		}
		
	}
	public static void printHSbyEyeColor(HashSet<Student> hsStu) {
		Iterator<Student> itr = hsStu.iterator();
		while(itr.hasNext()) {
			Student holdSt = itr.next();
			if(Objects.equals(holdSt.lastName, holdSt.eyeC.name())){
				System.out.println(holdSt.toString());
			}
			
		}
	}
	public static void printVCByEyeColor(Vector<Student> vecStu) {
		for(Student st: vecStu) {
			if(Objects.equals(st.eyeC.toString(), st.lastName)) {
				System.out.println(st.toString());
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Student> vecStud = new Vector<Student>();
		Student st = new Student(1, "stud", eyeColour.BLACK, 130);
		Student st2 = new Student(2, "stud2", eyeColour.BLUE, 160);
		Student st3 = new Student(3, "stud3", eyeColour.BROWN, 140);
		Student st4 = new Student(4, "stud4", eyeColour.BLACK, 130);
		Student st5 = new Student(5, "stud5", eyeColour.BLUE, 220);
		Student st6 = new Student(6, "stud6", eyeColour.BLACK, 330);
		
		vecStud.add(st2);
		vecStud.add(st3);
		vecStud.add(st4);
		vecStud.add(st5);
		vecStud.add(st6);
		vecStud = filtrVector(vecStud);
		printVecByNaturalOrder(vecStud);
		System.out.print("dsada");
		HashSet<Student> hsStud = new HashSet<Student>();
	}

}
