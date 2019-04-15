package SeventhLabs;

import src.Products;
public class SmallBag extends AbstractBag {
	public Belt belt = Belt.YES;

	public SmallBag(int size, double maxWeight) {
		super(size, maxWeight);
		// TODO Auto-generated constructor stub
		if(this.maxWeight>2) {
			this.maxWeight = 2;
			System.out.println("The MaxWeight went of of range");
		}
		if(this.size>5) {
			this.size = 5;
			System.out.println("The size went out of range ");
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String strToString = "SmallBag is for " + Integer.toString(this.size) + " elements. Weight is " + Double.toString(this.getTotalWeight()) + "kg in total ";
		strToString += "it has";
		if(belt == Belt.NO) {
			strToString += "n't";
		}
		strToString += " got belt\n";
		
		for(Product k: this.content) {
			if (k != null) {
				strToString += k.toString() + "\n";
			}
		}
		return strToString;
	}

	@Override
	public void removeProduct(Products toRemove) {
		// TODO Auto-generated method stub
		Product[] CopyArray = new Product[content.length];
		int a=0;
		for(int i=0; i < content.length; i++) {
			
			try { 
				if(content[i].type != toRemove) {
					CopyArray[a] = content[i];
					a++;
				}
			}
			
			catch (NullPointerException e) {
					
			}
		}
		content = CopyArray;
		
	}

}
