public class seventhlist {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car car1 = new Car(1);
		car1.addsKms(12.3);
		getAverage("123341412332412");
		

	}
	static int[] howMany = new int[10];
	
	public static void getAverage(String sT) {
		for(int i=0; i<10; i++) {
			howMany[i] = 0;
		}
		for(int i=0; i<sT.length(); i++) {
			
			howMany[Integer.valueOf(sT.indexOf(i))] += 1;
		}
		double a = 0; 
		double sum = 0;
		for(int i=0; i<10; i++) {
			if(howMany[i]>0) {
				a++; 
				sum += howMany[i];
			}
		}
		double average = sum/a;
		System.out.println(average);
		for(int i=0; i<10; i++) {
			if(average<howMany[i]) {
				System.out.print(i + "Is over the average" );
			}
		}
	}

}
