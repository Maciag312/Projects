package src; 

public static class CarDrivers {
	public CarDrivers(){
		
	}
	public static void driveOne(Car car, double kms) {
		car.addDrivenKms(kms);
		
	}
	public static void driveMany(Car[] car, double[] kms) {
		for(int i = 0; i<car.length; i++) {
			car[i].addDrivenKms(kms[i]);
		}
	}
}

