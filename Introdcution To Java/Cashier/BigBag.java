package SeventhLabs;

import src.Products;

public class BigBag extends AbstractBag {
	public OnRolls onRolls;
	public BigBag(int size, double maxWeight) {
		super(size, maxWeight);
		onRolls = OnRolls.NO;
		// TODO Auto-generated constructor stub
		if(this.maxWeight>20) {
			this.maxWeight = 20;
			System.out.println("The MaxWeight went out of range");
		}
		if(this.size>15) {
			this.size = 15;
			System.out.println("The size went out of range");
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String strToString = "BigBag is for  " + Integer.toString(this.size) + " elements. Weight is " + Double.toString(this.getTotalWeight()) + "kg in total ";
		strToString += "it is";
		if(onRolls == OnRolls.NO) {
			strToString += "n't";
		}
		strToString += " on the rolls\n";
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
			try { if(content[i].type != toRemove) {
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