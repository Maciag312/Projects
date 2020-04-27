package PackAs3;

import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class NumbersWords {

	public static Vector<Integer> readIntegers() {
		Scanner sc = new Scanner(System.in); // gets input from console
		return (readIntegers(sc));
	}


	public static Vector<Integer> readIntegers(Scanner scIn) {
		Vector<Integer> result = new Vector<Integer>();
		while (scIn.hasNextInt()) {
			Integer number = scIn.nextInt();
			result.add(number);
		}
		return (result);

	}

	public static void showNumbers(Vector<Integer> v) {
		int size = v.size();
		for (int k = 0; k < size; k++) {
			System.out.println(v.elementAt(k));
		}
		System.out.print("\n");
	}

	public static void showMaxValue(Vector<Integer> v) {
		int max = Integer.MIN_VALUE;
		System.out.println(max);
	}

	public static Integer[] getGreaterThan(int limit) {
		Integer[] result = null;
		System.out.println("Enter integers to be processed: ");
		Vector<Integer> vInt = readIntegers();

		int countLargers = 0; 
		int size = vInt.size();
		for (int k = 0; k < size; k++) {
			if (vInt.elementAt(k) > limit) {
				countLargers++;
			}
		}

		int resultIndex = 0;
		if (countLargers > 0) { 
			result = new Integer[countLargers];
			for (int i = 0; i < size; i++) {
				if (vInt.elementAt(i) > limit) {
					result[resultIndex] = vInt.elementAt(i);
					resultIndex++;
				}
			}
		}

		return (result);
	}

	// Task3) 2}
	public static void printLargers(int howMany, Vector<Integer> numbers) { // printLarges(3, readintegers());
		Collections.sort(numbers);
		int index = numbers.size();
		System.out.println("The largest " + howMany + " numbers are: ");

		for (int i = 0; (i < howMany && i < numbers.size()); i++) {
			System.out.println(numbers.elementAt((index - i) - 1));
		}
		
	}


	public static Vector<String> readWords() {
		Scanner sc = new Scanner(System.in);
		return (readWords(sc));
	}

	public static Vector<String> readWords(Scanner scIn) {
		Vector<String> result = new Vector<String>();
		while (scIn.hasNext()) {
			String word = scIn.next();
			if (word.toLowerCase().equals("exit"))
				break;
			result.add(word);
		}
		return (result);
	}
	public static Vector<String> readLine(Scanner scIn) {
		Vector<String> result = new Vector<String>();
		while (scIn.hasNextLine()) {
			String line = scIn.nextLine();
			if (line.toLowerCase().equals("exit"))
				break;
			result.add(line);
		}
		return (result);
	}

	public static void showWords(Vector<String> v) {
		int size = v.size();
		for (int k = 0; k < size; k++) {
			System.out.println(v.elementAt(k));
		}
		System.out.print("\n");
	}

	public static void printPalindromes(Vector<String> words) {
		String word = "";
		String reversedWord = "";
		Vector<String> result = new Vector<String>();
		for (int i = 0; i < words.size(); i++) {
			word = words.elementAt(i);
			reversedWord = reverseWord(word);
			if (word.equals(reversedWord)) {
				result.add(word);
			}

		}
		showWords(result);
	}

	public static String reverseWord(String word) {
		String reversedWord = "";
		char[] arrchar;
		Stack<Character> st = new Stack<Character>();
		arrchar = word.toCharArray();

		for (int i = 0; i < word.length(); i++) {
			st.push(arrchar[i]);
		}
		for (int i = 0; i < word.length(); i++) {
			reversedWord += st.pop();
		}

		return reversedWord;
	}


	public static void printAllLinesWith(Vector<String> wordsIneed) {
		
		boolean bContinue = false; 
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a line: ");
		Vector<String> vLines = readLine(sc);
		for(int j = 0; j < wordsIneed.size(); j++) {
			for (int i = 0; i < vLines.size(); i++) {
 				String[] elements = vLines.get(i).split(" ");
 				
 				for(int k=0; k<elements.length; k++) {
 					if(Objects.equals(wordsIneed.get(j),elements[k])) {
 						bContinue = true;
 					}
 				}
 				if(!bContinue) {
 					break;
 				}else{
 					bContinue = false;
 				}
 				if(wordsIneed.get(j)==wordsIneed.lastElement()) {
						System.out.println(vLines.get(i));
				}
 			}
 			
		}
 		
	
	}

	// MAIN
	public static void main(String[] agrs) {
		System.out.print("Enter integers:");
		Vector<Integer> vInteger = readIntegers();
		System.out.print("Enter words:");
		Vector<String> vString = readWords();
		
		//printAllLinesWith(vString);
		
		//printLargers(5, vInteger);
		//printPalindromes(vString);


	}

}