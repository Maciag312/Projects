package SeventhLabs;
import src.Products;

public class Product {
	protected Products type;
	protected double price=10.0;
	protected double weight=1.0;
	public Product(Products productType, double price, double weight) {
		type=productType;
		if (price>0 && price <100)
			this.price=price;
		if (weight>0 && weight<10)
			this.weight=weight;

	}
	public String toString() {
		String result = "["+type+", "+price+" "+weight+"]";
		return (result);
	}
}
