import java.util.ArrayList;

public class Frames {
    ArrayList<Integer> frame = new ArrayList<>();
    ArrayList<Integer> time = new ArrayList<>();

    private int added;
    private int number;
    public ArrayList<Boolean> secondchance = new ArrayList<>();
    public boolean bhit = false;

    public int getFrameTime(int pos){
        return time.get(pos).intValue();
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void NewColumn(){
        bhit = false;
    }
    public Frames(int howmany){
        number = howmany;
        for(int i = 0; i<howmany; i++){
            time.add(0);
            secondchance.add(Boolean.FALSE);
        }
    }
    public boolean wentFull(){
        return added>=number;
    }
    public boolean addSC(int a){
        if(canhit(a)) {
            bhit = true;
            secondchance.set(added-1, Boolean.TRUE);
        }
        boolean wentF = wentFull();
        if(!wentF&&!bhit){
            for(int i = 0;i<this.number; i++){
                time.set(i, (time.get(i).intValue()+1));
            }
            frame.add(a);
            time.set(frame.size()-1, 0);
            added++;
        }
        if(!wentF)
            return true;
        return false;
    }
    public boolean add(int a){
        if(canhit(a))
            bhit = true;
        boolean wentF = wentFull();
        if(!wentF&&!bhit){
            for(int i = 0;i<this.number; i++){
                time.set(i, (time.get(i).intValue()+1));
            }
            frame.add(a);
            time.set(frame.size()-1, 0);
            added++;
        }
        if(!wentF)
            return true;
        return false;
    }
    public int change(int pos, int a){
        bhit = false;
        for(int i = 0;i<this.number; i++){
            time.set(i, (time.get(i).intValue()+1));
        }
        time.set(pos, 0);
        return frame.set(pos, a).intValue();
    }
    public boolean canhit(int a){
        return frame.contains(Integer.valueOf(a));
    }
    public int hitpos(int a){
        return frame.indexOf(Integer.valueOf(a));
    }
    public int size(){
        return frame.size();
    }
}
