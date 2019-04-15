
public class Car {
	protected double drivenKms; 
	protected String model;
	protected String make;
	protected String size;
	public void drive(double addKms) {
		drivenKms += addKms;
	}
	public Car(String size, String make, String model){
		drivenKms = 0;
		this.size = size;
		this.make = make;
		this.model = model;
	}

}
