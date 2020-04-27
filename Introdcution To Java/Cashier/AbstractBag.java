package SeventhLabs;

import src.Products;

public abstract class AbstractBag {
	protected Product[] content;
	protected int size; 
	protected double maxWeight; 
	protected double weight; 
	private int counter;
// define necessary fields
	public AbstractBag(int size, double maxWeight) {
		content = new Product[size];
		counter = 0;
		this.size = size; 
		this.maxWeight = maxWeight;
// ent6er your code here
	}
	public abstract String toString();
	public boolean putIn(Product it) {
		boolean breaker = false;
		if(this.size <= counter) {
			System.out.println("There is no room for another products");
			breaker = true;
		}
		if((this.getTotalWeight()+it.weight)>this.maxWeight) {
			System.out.println("Bag is too heavy you can't put this product in");
			breaker = true;
		}
		if(breaker) {
			return false;
		}
		boolean result=true;
		content[counter]=it;
		this.counter++;
		
// put your code here
		return (result);
	}
	public int putIn(Product[] its) {
		int a = 0;
		for(Product pr: its) {
			a++;
			this.putIn(pr);
		}
		int result=a;
// put your code here
// returns the number of (staring) items that were put in
		return (result);
	}
	public double getTotalWeight() {

		double result = 0;
		for(Product j: content) {
			try {
				result += j.weight;
			} 
			catch (NullPointerException e){
				
			}
		}
// put your code here
		return result;
	}
	public double getTotalWeight(Products product) {
		double result=-0.0;
		for(Product j: content) {
			try {
				if(j.type==product) {
					result+=j.weight;
				}
			}
			catch (NullPointerException e){
				
			}
		}
		return result;
		
// put your code here
	}
	public abstract void removeProduct(Products toRemove);
}