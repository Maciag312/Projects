package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
	     Matrix mx = new Matrix();
	     while(true) {
	         System.out.println("Enter size of n x n matrix: ");
	         Scanner sc = new Scanner(System.in);
	         mx.setSize(sc.nextInt());
	         System.out.println("Enter a matrix: ");
             System.out.println("There are " + mx.findPaths()+ " paths");
         }


    }


}
class Matrix{

    public void setSize(int size) {
        sizeN = size;
        matrix = new int[sizeN][sizeN];
    }
    int sizeN;
    int[][] matrix;
    Stack<Cell> Back = new Stack<>();
    int findPaths(){
        setMatrix();
        int nPaths = 0;
        boolean first =true;
        Back.add(new Cell(0,0));
        int a = 0;
        int b = 0;
        int val = 0;

        while(Back.size()>0||first){
            Cell cell = Back.pop();
            a = cell.x;
            b = cell.y;
            val = matrix[a][b];
            if(!first)
                a+=val;
            first = false;

           while(true){
               val = matrix[a][b];
               if(matrix[a][b]==0){
                   nPaths++; break;
               }
               if(b+val<sizeN){
                   if(a+val<sizeN)
                       Back.add(new Cell(a,b));
                   b += val;
               }
               else if (a+val<sizeN){
                   a += val;
               }
               else if(a+val>=sizeN&&b+val>=sizeN)break;

           }

        }
        return nPaths;
    }
    public void setMatrix() {
        Scanner scanner = new Scanner(System.in);
        for(int i=0; i<sizeN;i++){
            for(int j = 0; j<sizeN;j++){
                if(scanner.hasNextInt()){
                    this.matrix[i][j] = scanner.nextInt();
                }
            }
        }
        
    }
}
class Cell {
    int x;
    int y;
    public Cell(int a, int b){
        x=a;
        y=b;
    }
}