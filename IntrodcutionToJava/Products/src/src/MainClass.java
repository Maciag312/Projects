package src;

import java.util.*;


public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item it = new Item(Products.TEA, 0.5, 12.3, "my personal choice");
		Item bread = new Item(Products.BREAD, 1.0, 4.19, "wholegrain");
		Item butter = new Item(Products.BUTTER, 1.0, 4.19, "82% Fat");

		it.add(3);
		bread.add(3);
		bread.remove(2);
		System.out.println(bread.getQuantity());
		System.out.println(bread.toString());
		System.out.println(butter.toString());
		System.out.println(butter.remove(1));
		System.out.println("adding, should be 3 is: " +it.getQuantity());
		System.out.println("removing, should be false, is: " +it.remove(10));
		
		long startTime = System.nanoTime();    
		ArrayList<Item> alist=new ArrayList<Item>();
		for(int i = 0; i <  1000000; i++) {
			alist.add(it);
		}
		long estimatedTime = System.nanoTime() - startTime;
		System.out.println(estimatedTime);
	}

}
