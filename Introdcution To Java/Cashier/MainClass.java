package SeventhLabs;

import src.Products;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product bread = new Product(Products.BREAD, 3, 1.4);
		Product butter = new Product(Products.BUTTER, 2, 0.1);
		
		Product[] butters = { butter, butter, butter, butter, butter, butter };
		Product heavyButter = new Product(Products.BUTTER, 5, 50);
		BigBag bg = new BigBag(8, 25);
		System.out.println("Big bag now weights " + bg.getTotalWeight());
		Product[] someBag = { new Product(Products.BUTTER, 7.99,0.2), new Product(Products.MILK, 2.1, 1.0), new Product(Products.TEA, 5.49, 0.2) }; 
		bg.putIn(someBag);
		System.out.println("Big bag now weights "+ bg.getTotalWeight());
		
	
		SmallBag smbg = new SmallBag(6, 2.5);
		//overflowing small bag for differrent paraments exciding 6>5 2.5>2
		
		//weight 
		smbg.putIn(bread);
		smbg.putIn(bread);
		
		 // removing product 
		smbg.removeProduct(Products.BREAD);
		System.out.println("Small bag now weights " + smbg.getTotalWeight()); // checking if its empty after using removing
		//quantity
		smbg.putIn(butters);
		smbg.toString();
		//with norolls and with rolls 
		smbg.belt = Belt.NO;
		System.out.println(smbg.toString());
		
		//overflowing Big bag for differrent paraments  
		bg.putIn(heavyButter);
		//weight 
		
		// removing product 
		bg.removeProduct(Products.BUTTER);
		bg.putIn(new Product(Products.TEA, 3 , 1.1));
		bg.removeProduct(Products.TEA);

		//quantity 
		bg.putIn(butters);
		bg.putIn(butters);
		bg.putIn(butters);
		bg.putIn(heavyButter);
		bg.putIn(bread);
		// change default 
		System.out.println(bg.toString());

		// is on rolls change from default  
		bg.onRolls = OnRolls.YES;
		System.out.println(bg.toString());

		
		

	}

}
