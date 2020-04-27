package com.company;

import java.util.*;

public class Main {

    static Scanner scan; // for input stream

    public static void main(String[] args) {
        System.out.println("START");
        scan=new Scanner(System.in);
        DisjointSetDataStructure []dsds=null;
        int currentPos=0;
        boolean halt=false;
        int maxNo=-1;
        while(!halt) {
            String line=scan.nextLine();
            // empty line and comment line - read next line
            if(line.length()==0 || line.charAt(0)=='#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!"+line);
            String word[]=line.split(" ");
            // go n - start with array of the length n
            if(word[0].equalsIgnoreCase("go") && word.length==2) {
                maxNo=Integer.parseInt(word[1]);
                dsds = new DisjointSetDataStructure[maxNo];
                continue;
            }
            //ch - change index
            if(word[0].equalsIgnoreCase("ch") && word.length==2) {
                currentPos=Integer.parseInt(word[1]);
                continue;
            }
            // show
            if(word[0].equalsIgnoreCase("show") && word.length==1) {
                System.out.println(dsds[currentPos].toString());
                continue;
            }
            // ha
            if(word[0].equalsIgnoreCase("ha") && word.length==1) {
                halt=true;
                continue;
            }
            // ll <size>
            // linkedlist <size>
            if((word[0].equalsIgnoreCase("ll") || word[0].equalsIgnoreCase("linkedlist")) && word.length==2) {
                int size=Integer.parseInt(word[1]);
                dsds[currentPos]=new DisjointSetLinkedList(size);
                for(int i=0;i<size;i++)
                    dsds[currentPos].makeSet(i);
                continue;
            }
            // dsf <size>
            if(word[0].equalsIgnoreCase("dsf") && word.length==2) {
                int size=Integer.parseInt(word[1]);
                dsds[currentPos]=new DisjointSetForest(size);
                for(int i=0;i<size;i++)
                    dsds[currentPos].makeSet(i);
                continue;
            }
            // findset <item>
            if(word[0].equalsIgnoreCase("findset") && word.length==2) {
                int item=Integer.parseInt(word[1]);
                System.out.println(dsds[currentPos].findSet(item));
                continue;
            }
            // union <itemA> <itemB>
            if(word[0].equalsIgnoreCase("union") && word.length==3) {
                int itemA=Integer.parseInt(word[1]);
                int itemB=Integer.parseInt(word[2]);
                System.out.println(dsds[currentPos].union(itemA,itemB));
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }




}

// in the constructor there has to be the number elements N
// the elements are integer values from 0 to N-1
interface DisjointSetDataStructure{
    void makeSet(int item);
    int findSet(int item);
    boolean union(int itemA, int itemB);
}

class DisjointSetLinkedList implements DisjointSetDataStructure {

    private class Element{
        int representant;
        int next;
        int length;
        int last;
    }

    private static final int NULL=-1;

    Element arr[];

    public DisjointSetLinkedList(int size) {

        if(size>0) {
            Element[] elements = new Element[size];
            for(int i = 0; i<size;i++){
                elements[i] = new Element();
            }
            arr = elements;
            for(int i=0; i<elements.length; i++){

                makeSet(i);
            }
        }
    }

    @Override
    public void makeSet(int item) {
        arr[item].representant = item;
        arr[item].next = -1;
        arr[item].last = item;
        arr[item].length = 1;
    }

    @Override
    public int findSet(int item) {
        if(item>=arr.length) return -1;
        return arr[item].representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        if(itemA == 3 && itemB == 5){
            itemA = itemA;
        }
        int represOfA = findSet(itemA);
        int represOfB = findSet(itemB);
        if(arr[represOfA].representant==arr[represOfB].representant) return false;

        arr[arr[represOfA].last].next = represOfB;
        for(int i = 0; i<arr[represOfB].length;i++){
            arr[represOfB+i].representant = arr[represOfA].representant;
            arr[represOfA].length += 1;
            arr[represOfA].last = arr[represOfB].last;
        }
        return true;
    }


    @Override
    public String toString() {
        String str = "Disjoint sets as linked list:";
        int k=0;
        int counter = 0;
        int counter2 = 0;
        while(k<arr.length){
            if(counter>0){
                if(counter2==counter)
                    str += String.valueOf(arr[k].representant);
                else
                    str += ", " + String.valueOf(arr[k-1].next);
                counter--;
                k++;
            }else{
                str+="\n";
                counter2 = counter = arr[k].length;
            }
        }
        return str;
    }

}

class DisjointSetForest implements DisjointSetDataStructure {

    private class Element{
        int rank;
        int parent;
    }

    Element []arr;

    public DisjointSetForest(int size) {
        //TODO
    }

    @Override
    public void makeSet(int item) {
        //TODO
    }

    @Override
    public int findSet(int item) {
        //TODO
        return -1;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        //TODO
        return false;
    }


    @Override
    public String toString() {
        //TODO
        return null;
    }
}