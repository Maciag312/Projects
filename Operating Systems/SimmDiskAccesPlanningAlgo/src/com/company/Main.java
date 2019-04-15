package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        while(true) {
            System.out.println("\n1. Run for FCFS, SSTF, SCAN and C-SCAN");
            System.out.println("2. Run for EDF");
            System.out.println("3. Run for FD-SCAN");
            Scanner sc = new Scanner(System.in);
            String readed = "";
            if (sc.hasNext()) {
                readed = sc.next();
            }
            int a = Integer.parseInt(readed);
            if(a==0){
                break;
            }
            Disk disk = new Disk(a);
        }


    }
}
