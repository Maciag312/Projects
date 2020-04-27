import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.random;

public class Main {

    public static void main(String[] args) {
        Memory mem = new Memory();
        mem.generate();

        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Number of frames is: " +  mem.howMnayFrames);
            System.out.println("Page references length is: "+ mem.PRlength);
            System.out.println("Choose algorithm: ");
            System.out.println("1. FIFO");
            System.out.println("2. OPT");
            System.out.println("3. LRU");
            System.out.println("4. SCA");
            System.out.println("5. RAND");
            System.out.println("6. Compare algorithms");

            int algo = sc.nextInt();
            mem.run(algo);
        }

    }
}

class Memory {
    public final int  howMnayFrames = 25;
    public final int PRlength = 1000;
    public final int maxNumb = 25;
    int hits = 0;
    int miss = 0;

    private ArrayList<int[]> framesToread = new ArrayList<>();
    private ArrayList<String> hitormiss = new ArrayList<>();
    Frames frames;

    int[] pagereferences = new int[1000];
    private void generateInner(){
        frames = new Frames(howMnayFrames);
    }
    public void generate(){
        double random = Math.random();
        for(int i = 0; i<pagereferences.length; i++){
            pagereferences[i] = 1+(int)(maxNumb*Math.random());
        }
        frames = new Frames(howMnayFrames);
    }


    public void save(Frames frame){
        int framestoadd[] = new int[howMnayFrames];
        for(int i = 0; i<framestoadd.length; i++) {
            if(frame.size()>i) {
                framestoadd[i] = frame.frame.get(i).intValue();
            }else{
                framestoadd[i] = 0;
            }
        }
        framesToread.add(framestoadd);
        char a = 'H';
        if(!frame.bhit)
            a = 'M';
        hitormiss.add(Character.toString(a));
    }

    public ArrayList<Integer> thits = new ArrayList<>();
    public ArrayList<Integer> tmisses = new ArrayList<>();
    public ArrayList<String> tnames = new ArrayList<>();
    public void showResults(String name){
        System.out.println(name+" algorithm\n");
        System.out.print("PAGE REF: ");
        for(int pr : pagereferences ){
            if(pr<10) System.out.print(pr + "  ");
            if(pr>=10) System.out.print(pr+" ");
        }
        System.out.println();
        for(int i = 0; i<howMnayFrames; i++){
            System.out.print("Frame " + i + " : ");
            for(int j = 0; j<framesToread.size(); j++){
                if(framesToread.get(j)[i]<10){
                    System.out.print(framesToread.get(j)[i]+"  ");

                }
                if(framesToread.get(j)[i]>=10) {
                    System.out.print(framesToread.get(j)[i]+" ");

                }
            }
            System.out.println();

        }
        System.out.print("HIT&MISS: ");

        for(int i = 0; i<hitormiss.size(); i++) {
            System.out.print(hitormiss.get(i)+ "  ");
        }
        System.out.println();
        System.out.println("For " + name + "algorithm has occured");
        System.out.println("Hits: " + hits + " and Miss: "+ miss);
        tnames.add(name);
        thits.add(hits);
        tmisses.add(miss);
        hits = 0;
        miss = 0;
    }
    public void showComparment(){
        int i = 0;
        for (String nam: tnames
             ) {
            System.out.println("\n"+ nam);
            System.out.println("hits: " + thits.get(i));
            System.out.println("misses: " + tmisses.get(i) + "\n");
            i++;
        }
    }
    public void FIFO(){ //when it meets a value, which is in the array it add second chance
        // go one by one - current
        // when it meets value with second chance it takes chance and go to the next
        int last = 0;
        for(int i: pagereferences){
            frames.NewColumn();
            boolean nadded = !frames.add(i);
            if(nadded){
                if(frames.canhit(i)){
                    hits++;
                }else {
                    frames.change(last, i);
                    miss++;
                    if(last<(howMnayFrames-1))
                        last++;
                    else
                        last = 0;
                }
            }else{
                if(frames.bhit)
                    hits++;
                else
                    miss++;

            }
            save(frames);
        }
        showResults("First in first out ");
    }
    private int MathRand(int from, int to){
        return from+(int)(Math.random()*(to-from+1));
    }
    public void OPT(){
        int last = 0;
        int curef = -1;
        for(int i: pagereferences){
            curef++;
            frames.NewColumn();
            boolean nadded = !frames.add(i);
            if(nadded){
                if(frames.canhit(i)){
                    hits++;
                }else {
                    last = findatleastneeded(frames, pagereferences, curef);
                    frames.change(last, i);
                    miss++;
                    if(last<(howMnayFrames-1))
                        last++;
                    else
                        last = 0;
                }
            }else{
                if(frames.bhit)
                    hits++;
                else
                    miss++;

            }
            save(frames);
        }

        showResults("Optimal ");
    }

    private int findTheOldest(Frames frames){
        int theoldest = frames.getFrameTime(0);
        int theoldpos = 0;
        for(int i = 1; i<frames.size(); i++){
            if(theoldest<frames.getFrameTime(i)){
                theoldest = frames.getFrameTime(i);
                theoldpos = i;
            }
        }
        return theoldpos;
    }
    private int findatleastneeded(Frames frames,int[] pageref, int currentposof){
        // iteruje dla kzdego framesa
        // iteruje wewnetrznie pageref
        // dla obecnych frames wuznacza odleglosc do pierwszego elem pageref rownego elem framesowi
        // jezeli nigdy sie nie pojawi to bierzemy go
        // pozycja jest czynnkiem do zwracania
        int thelongestdistnace = 0;
        int smallestpos = 0;
        boolean once = true;
        boolean found = false;
        for(int i = 0; i<frames.frame.size(); i++){
            found = false;
            for(int j = currentposof+1; j<pageref.length; j++){
                if(j==pageref.length-2) {
                    if(!found)
                        smallestpos = i;
                    break;
                }
                if(frames.frame.get(i)==pageref[j]){
                    found = true;
                    if(once){
                        thelongestdistnace = j-currentposof;
                        smallestpos = i;
                        once = false ;
                    }
                    if((j-currentposof)>thelongestdistnace){
                        thelongestdistnace = j-currentposof;
                        smallestpos = i;

                    }
                    break;
                }
            }
        }
        //

        return smallestpos;
    }
        public void LRU(){

        int last = 0;
        for(int i: pagereferences){
            frames.NewColumn();
            boolean nadded = !frames.add(i);
            if(nadded){
                if(frames.canhit(i)){
                    hits++;
                }else {
                    last = findTheOldest(frames);
                    frames.change(last, i);
                    miss++;
                    if(last<(howMnayFrames-1))
                        last++;
                    else
                        last = 0;
                }
            }else{
                if(frames.bhit)
                    hits++;
                else
                    miss++;

            }
            save(frames);
        }

        showResults("LRU ");
    }
    public void SCA(){
        int currelement = 0;
        for(int i: pagereferences){
            frames.NewColumn();
            boolean nadded = !frames.addSC(i);
            if(nadded){
                if(frames.canhit(i)){
                    hits++;
                    frames.secondchance.set(frames.hitpos(i), Boolean.TRUE);
                }else{
                    while(frames.secondchance.get(currelement).equals(Boolean.TRUE)){
                        frames.secondchance.set(currelement, Boolean.FALSE);
                        currelement++;
                        if(currelement>=howMnayFrames) currelement = 0;
                    }
                    frames.change(currelement, i);
                    currelement++;
                    if(currelement>=howMnayFrames) currelement = 0;
                    miss++;
                }
            }else{
                if(frames.bhit)
                    hits++;
                else
                    miss++;
            }
            save(frames);
        }
        save(frames);
        showResults("Second chance Algorithm ");
    }
    public void RAND(){
        int last = 0;
        int curef = -1;
        for(int i: pagereferences){
            curef++;
            frames.NewColumn();
            boolean nadded = !frames.add(i);
            if(nadded){
                if(frames.canhit(i)){
                    hits++;
                }else {
                    last = MathRand(0, frames.frame.size()-1);
                    frames.change(last, i);
                    miss++;
                    if(last<(howMnayFrames-1))
                        last++;
                    else
                        last = 0;
                }
            }else{
                if(frames.bhit)
                    hits++;
                else
                    miss++;

            }
            save(frames);
        }

        showResults("Rand ");
    }
    public void run(int a){
        generateInner();
        switch(a){
            case 1:FIFO();break;
            case 2:OPT();break;
            case 3:LRU();break;
            case 4:SCA();break;
            case 5:RAND();break;
            case 6:showComparment();break;
            default:break;
        }
    }

}
