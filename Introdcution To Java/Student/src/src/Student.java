package src;
import java.util.Vector;
import java.util.HashSet;


public class Student {
	
	int height = 180;
	eyeColour eyeC = eyeColour.BLUE;
	String lastName = "Student";
	int idNumber = 99999;
	boolean isOK = false;
	public boolean setHeight(int height) {
		if(height>=120&&height<=220) {
			this.height = height;
			return true;
		}
		this.height = 180;
		return false;
	}
	public String toString() {
		String str = "Student id: " + Integer.toString(idNumber) + "last name: " + lastName + "eye color: " + eyeC.name();
		return str;
	}
	public void setEyeColor(eyeColour eyeC){
		this.eyeC = eyeC;
	}
	public boolean setLastName(String lastName) {
		if(lastName.matches("[a-zA-Z]+")) {
			this.lastName = lastName;
			return true;
		}
		this.lastName = "Student";
		return false; 
	}
	public boolean setId(int idNumber) {
		if(idNumber>0) {
			this.idNumber = idNumber;
			return true;
		}
		idNumber = 99999;
		return false;
		
	}
	Student(int i, String lastName, eyeColour ecolor, int height){
		isOK = false;
		if(setId(i)==true && setLastName(lastName)==true && setHeight(height)==true) {
			isOK = true;
		}
		setId(i);
		setLastName(lastName);
		setHeight(height);
		setEyeColor(ecolor);
		
	}
	

}
