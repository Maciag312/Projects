import java.lang.Thread;
import java.util.concurrent.Semaphore;
public class Reader extends Thread {
    private int Id = 0; 
    RandomGeneratorThread randomGeneratorThread;
    public Reader(int id, RandomGeneratorThread randomGeneratorThread){
        this.Id = id;
        this.randomGeneratorThread = randomGeneratorThread;
    }

    private long waitedTime = 0; 
    private boolean isStopped = false;
    public void run(){
        while(!isStopped){
            try{
                long waited = this.randomGeneratorThread.getValue();
                if(!this.randomGeneratorThread.getBuffer().isEmpty()){
                    System.out.println("Reader <"+this.Id+"> waits for <"+waited+">");
                    try{ Thread.sleep(waited); 
                    } catch (InterruptedException ex){
                        //System.out.println(ex);
                    }
                    System.out.println("Reader <"+this.Id+"> waited <"+(waited)+">");
                }
                    this.randomGeneratorThread.clear();
            }catch(InterruptedException ex){
               // System.out.println(ex);
            }
           
        }
    }
    public void stopReading(){
        isStopped = true;
    }
    public void continueReading(){
        isStopped = false;
    }

}