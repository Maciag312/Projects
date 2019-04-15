
public class Trapezoid {
	int height, width, sideTop, sideBottom;
	public double area(){ 
		double ar = (sideTop+sideBottom)*0.5*height;
		return ar;
	}
	
	Trapezoid(int height, int width, int sideTop, int sideBottom){
		this.width = width;
		this.height = height;
		this.sideTop = sideTop;
		this.sideBottom = sideBottom;
	}
}
