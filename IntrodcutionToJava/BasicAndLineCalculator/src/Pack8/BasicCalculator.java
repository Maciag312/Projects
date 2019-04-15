package Pack8;

import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class BasicCalculator extends AbstractCalc {
	private enum opTypeEnum {
		STRINGTYPE, INTTYPE, FLOATTYPE
	}
	private opTypeEnum opType;
	
	@Override
	public String calculate(String argument) {
			String result = "";
			String operator = "";

			//Add operators to string
			for (char entry: argument.toCharArray()) {
		
			if (entry == '+') {
				operator += "+";
			} 
			else if (entry == '-') {
				operator += "-";
			}
			else if (entry == '*') {
				operator += "*";
			}
			}
			
			if (operator.isEmpty()) {
				return "WRONG FORMAT -unknown operator";
			}
			
//split operators and tokens into arrays
			char[] opArray = operator.toCharArray();
			String[] tokenArray = argument.split("[-+*]");
			

			
			//check if token types are the same throughout;
			
			String token1 = tokenArray[0];
			if (isInteger(token1)) {
				opType = opTypeEnum.INTTYPE;
			} else if (isFloatingPoint(token1)) {
				opType = opTypeEnum.FLOATTYPE;
			} else {
				opType = opTypeEnum.STRINGTYPE;
			}
			
			
			
			opTypeEnum tokenArType;
			for (int i = 1; i< tokenArray.length; i++) {
				
				if (isInteger(tokenArray[i])) {
					tokenArType = opTypeEnum.INTTYPE;
				} else if (isFloatingPoint(token1)) {
					tokenArType = opTypeEnum.FLOATTYPE;
				} else {
					tokenArType = opTypeEnum.STRINGTYPE;
				}
				
				if (!(tokenArType.equals(opType))) {
					return "Operands are not the same type";
				}
				
				
			}
			 
			//check number of operands is correct in relation to number of tokens (noOfTokens == noOfOperators +1)
			
			if (!(opArray.length == tokenArray.length - 1)) {
				return "WRONG FORMAT - incorrect number of tokens to operators";
			}
			
			
			
			//go through each operand and apply operators to them:
			
			
		result = tokenArray[0];
			for (int i = 0 ; i < opArray.length; i++) {
				switch (opArray[i]) {
				case '+': {
					if (opType.equals(opTypeEnum.INTTYPE)) {
						result = Integer.toString(sum(Integer.parseInt(result), Integer.parseInt(tokenArray[i+1])));
					} else if (opType.equals(opTypeEnum.FLOATTYPE)) {
						result = sum(Double.parseDouble(result), Double.parseDouble(tokenArray[i+1])).toString();
					} else {
						result = sum(result, tokenArray[i+1]);
					}
				}
				break;
				case '-':{
					if (opType.equals(opTypeEnum.INTTYPE)) {
						result = Integer.toString(difference(Integer.parseInt(result), Integer.parseInt(tokenArray[i+1])));
					} else if (opType.equals(opTypeEnum.FLOATTYPE)) {
						result = difference(Double.parseDouble(result), Double.parseDouble(tokenArray[i+1])).toString();
					} else {
						result = difference(result, tokenArray[i+1]);
					}
				}
				
				break;
				case '*':{
					if (opType.equals(opTypeEnum.INTTYPE)) {
						result = Integer.toString(product(Integer.parseInt(result), Integer.parseInt(tokenArray[i+1])));
					} else if (opType.equals(opTypeEnum.FLOATTYPE)) {
						result = product(Double.parseDouble(result), Double.parseDouble(tokenArray[i+1])).toString();
					} else {
						result = product(result, tokenArray[i+1]);
					}
				break;
				}
				}
				
			}
			
			
			
			
			
			
			
			
			
			/*
			if (isInteger(k1) == isInteger(k2) && isFloatingPoint(k1) == isFloatingPoint(k2)) {

				
				switch (operator) {
				case "+": {
					if (isInteger(k1)) {
						result += sum(Integer.parseInt(k1), Integer.parseInt(k2));
					} else if (isFloatingPoint(k1)) {
						result += sum(Double.parseDouble(k1), Double.parseDouble(k2));
					} else {
						result += sum(k1, k2);
					}

				}
					break;
				case "*": {
					if (isInteger(k1)) {
						result += product(Integer.parseInt(k1), Integer.parseInt(k2));
					} else if (isFloatingPoint(k1)) {
						result += product(Double.parseDouble(k1), Double.parseDouble(k2));
					} else {
						result += product(k1, k2);
					}

				}
					break;
				case "-": {
					if (isInteger(k1)) {
						result += difference(Integer.parseInt(k1), Integer.parseInt(k2));
					} else if (isFloatingPoint(k1)) {
						result += difference(Double.parseDouble(k1), Double.parseDouble(k2));
					} else {
						result += difference(k1, k2);
					}

				}
					break;
				default:
					result += "Unknown operator";
				}
			} else {
				result += "Operands are not the same type";
			}*/

			return result;
		}

	@Override //DONE
	public int sum(int k1, int k2) {
		// TODO Auto-generated method stub
		return k1+k2;
	}

	@Override //DONE
	public BigDecimal sum(double k1, double k2) {
		// TODO Auto-generated method stub
		BigDecimal kb1 = BigDecimal.valueOf(k1);
		BigDecimal kb2 = BigDecimal.valueOf(k2);
		BigDecimal res = kb1.add(kb2);
		return res;
	}

	@Override //DONE
	public String sum(String k1, String k2) { 

		//check for null as the method 
        if(k1 == null || k2 == null) {
            return null;
        }

        //split the given String to some list
        List<String> k1List = Arrays.asList(k1.split(" "));
        List<String> k2List = Arrays.asList(k2.split(" "));

        //get a Set and add the list items to it. LinkedHashSet
        //is used to maintain the given order.
        Set<String> stringSet = new LinkedHashSet<>(k1List);
        stringSet.addAll(k2List);

        //Then join them using 
        return String.join(" ", stringSet);
    }
	

	@Override //DONE
	public int product(int k1, int k2) {
		// TODO Auto-generated method stub
		return k1*k2;
	}

	@Override //DONE
	public BigDecimal product(double k1, double k2) {
		BigDecimal kb1 = BigDecimal.valueOf(k1);
		BigDecimal kb2 = BigDecimal.valueOf(k2);
		BigDecimal result = kb1.multiply(kb2);
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public String product(String k1, String k2) {
		// TODO Auto-generated method stub
		if(k1 == null || k2 == null) {
            return null;
        }

        //split the given String to some list
        List<String> k1List = Arrays.asList(k1.split(" "));
        List<String> k2List = Arrays.asList(k2.split(" "));

		ArrayList<String> intersectionList = new ArrayList<String>();
		
		for (int i =0; i < k1List.size();i++) {
			for (int j =0; j < k2List.size();j++) {
				if(!(k1List.get(i)=="") && !(k2List.get(j)=="") );
				if (k1List.get(i).equals(k2List.get(j) )) {
					intersectionList.add(k2List.get(j));
					k1List.set(i, "");
					k2List.set(j, "");
				}
			}
		}
			
		return String.join(" ", intersectionList);
	}

	@Override //DONE
	public int difference(int k1, int k2) {
		// TODO Auto-generated method stub
		
		return Math.abs(k1 -k2);
	}

	@Override //DONE
	public BigDecimal difference(double k1, double k2) {
		
		// TODO Auto-generated method stub
		BigDecimal kb1 = BigDecimal.valueOf(k1);
		BigDecimal kb2 = BigDecimal.valueOf(k2);
		BigDecimal res = kb1.subtract(kb2);
		
		
		return res;
	}

	@Override
	public String difference(String k1, String k2) {
		if(k1 == null || k2 == null) {
            return null;
        }

        List<String> k1List = Arrays.asList(k1.split(" "));
        List<String> k2List = Arrays.asList(k2.split(" "));

          
        
        
        
		ArrayList<String> DifferenceList = new ArrayList<String>();
		
		
		for (int i =0; i < k1List.size();i++) {
			for (int j =0; j < k2List.size();j++) {
				if(!(k1List.get(i)=="") && !(k2List.get(j)=="") );
				if (k1List.get(i).equals( k2List.get(j))) {
					k1List.set(i, "");
					k2List.set(j, "");
				}
			}
		}
		
		for (int i =0; i < k1List.size();i++) {
			if (!(k1List.get(i)== "")) {
				DifferenceList.add(k1List.get(i));
			}
		}
					
		return String.join(" ", DifferenceList);
	}

}
