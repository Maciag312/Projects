//BREAD, MILK, TEA, BUTTER, OTHER
package src; 

public enum Products {
	BREAD,
	MILK,
	TEA,
	BUTTER,
	OTHER;


	public static Products fromString(String s) {
		s=s.toLowerCase().trim();
		switch(s) {
			case "bread": return BREAD;
			case "milk": return MILK;
			case "tea": return TEA;
			case "butter": return BUTTER;
			default: return OTHER;
		}
	}
	
	
}