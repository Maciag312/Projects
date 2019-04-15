
public class Rectangle extends Trapezoid{
	public double area() {
		return super.area();
	}
	Rectangle(int width, int height){
		super(height, width, width, width);
	}
}
