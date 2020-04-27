package com.maciag;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

public class Main {

	static Scanner scan;

	public static void loadDocument(String name) {
		String strlin = "";
		try {

			scan = new Scanner(new FileReader(name + ".txt"));
			while(in.hasNext()){
				strlin = in.next();
				if(strlin.startsWith("eod")){
					break;
				}
				if(strlin.startsWith("link=") && strlin.length()>5){

					if (Character.isLetter(strlin.charAt(5))){
						strlin.toLowerCase();
						System.out.println(" " + strlin.substring(5));
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}


	}

	
	
	// accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
	private static boolean correctLink(String link) {

		return true;
	}

	private static void drawLine(int n, char ch) {
		for(int i = 0; i<n; i++) {
	
			System.out.print(ch);
		}
	}


	private static void drawPyramid(int n) {
		for(int i = 0; i<n; i++) {
			drawLine(n-i, ' ');
			drawLine(1+2*i, 'X');
			drawLine(n-i, ' ');
			System.out.print("\n");
		}
	}
	private static void drawPyramid(int n, int s) {
		int k = s+n;
		for(int i = 0; i<n; i++) {
			drawLine(n-i+s, ' ');
			drawLine(1+2*i, 'X');
			drawLine(n-i, ' ');
			System.out.print("\n");
		}
	}

	
	private static void drawChristmassTree(int n) {
		for(int i = 0; i<n; i++) {
			
			drawPyramid(i, n-i);
			
		}
	}
	 



	/***
	 * commands:
	 * py size
	 *   draw a pyramid with size
	 * ct size
	 *   draw a christmas tree with size
	 * ld documentName
	 *   load document from standard input line by line. Last line consists of only sequence "eod",
	 *   which means end of document
	 * ha
	 *   halt program and finish execution
	 * @param args
	 */
	

	public static void main(String[] args) {
		System.out.println("START");
		scan=new Scanner(System.in);
		boolean halt=false;
		while(!halt) {
			String line=scan.nextLine();
			// empty line and comment line - read next line
			if(line.length()==0 || line.charAt(0)=='#')
				continue;
			// copy line to output (it is easier to find a place of a mistake)
			System.out.println("!"+line);
			String word[]=line.split(" ");
			if(word[0].equalsIgnoreCase("py") && word.length==2) {
				int value=Integer.parseInt(word[1]);
				drawPyramid(value);
				continue;
			}
			if(word[0].equalsIgnoreCase("ct") && word.length==2) {
				int value=Integer.parseInt(word[1]);
				drawChristmassTree(value);
				continue;
			}
			// ld documentName
			if(word[0].equalsIgnoreCase("ld") && word.length==2) {
				loadDocument(word[1]);
				continue;
			}
			// ha
			if(word[0].equalsIgnoreCase("ha") && word.length==1) {
				halt=true;
				continue;
			}
			System.out.println("Wrong command");			
		}
		System.out.println("END OF EXECUTION");
		scan.close();

	}




}