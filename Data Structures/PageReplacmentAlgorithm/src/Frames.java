import java.util.ArrayList;

public class Frames {
    ArrayList<Integer> frame = new ArrayList<>();
    private int added;
    private int number;
    public int miss = 0;
    public int hit = 0;
    public Frames(int howmany){
        number = howmany;
    }
    public boolean wentFull(){
        return added>=number;
    }
    public void add(int a){
        if(hit(a)){
            hit++;
        }else{
            miss++
        }
        if(!wentFull()){
            frame.add(a);
            added++;
        }
        return true;
    }
    public int change(int pos, int a){
        miss++;
        return frame.set(pos, a).intValue();
    }
    public boolean hit(int a){
        return frame.contains(Integer.valueOf(a));
    }
    public int size(){
        return frame.size();
    }
}
