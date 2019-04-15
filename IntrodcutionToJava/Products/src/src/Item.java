package src;

public class Item{
	private Products prProduct;
	private double weight;
	private double price;
	private String comm;
	private int quantity;
	Item(Products pr, double weight, double price, String comm){
		this.prProduct = pr;
		if(weight>100 || weight < 0.01) {
			this.weight = 1;
		}else {
			this.weight = weight;
		}
		if(price < 1 || price > 10000) {
			this.price = 100;
		}else {
			this.price = price;
		}
		
		this.comm = comm;
	}
	public void add(int quantity) {
		if(quantity < 1) {
			System.out.println("Fail to add");
		}else {
			this.quantity += quantity;
		}
	}
	public boolean remove(int number) {
		if(number > this.quantity) {
			return false;
		}else{
			this.quantity -= number;
			return true;
		}
	}
	public int getQuantity() // the getter for the quantity field
	{
		return this.quantity;
	}
	public String toString() {
		String retr = ""; 
		retr = prProduct.name() + " " + Double.toString(this.price) + "zl " + Double.toString(this.weight) + "kg " + "x" + Double.toString(this.quantity) + " " + this.comm; 
		return retr;
	}
}
