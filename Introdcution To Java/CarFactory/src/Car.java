
public class Car {
	private double kmsDriven;
	private int id = 1;
	Car(int id){
		id = id;
		kmsDriven = 1;
	}
	public void addsKms(double addedkms) {
		kmsDriven += addedkms;
	}
	
	public void setKmsDriven(double setKms) {
		kmsDriven = setKms;
	}
	
	public double getKmsDriven() {
		return kmsDriven;
	}
	
	public void setId(int setId) {
		id = setId;
	}
	public int getId() {
		return id;
	}
	public void printKmsDriven() {
		System.out.println(kmsDriven);
	}
	
}



