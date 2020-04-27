
import java.lang.Thread;
import java.util.*;
import java.util.concurrent.Semaphore;

interface BufferInterface{
    boolean isEmpty();
    void setValue(long val);
    long getValue() throws InterruptedException; 
    void clear();

}
class Buffer implements BufferInterface{
    private long value = 0; 
    private boolean empty = true; 
    Buffer(){
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public void setValue(long val) {
        value = val;
        this.empty = false; 
    }

    @Override
    public long getValue() {
        return this.value;
    }

    @Override
    public void clear() {
       this.value = 0;
       this.empty = true;
    }

}
class BufferSemaphore implements BufferInterface{
    private long value = 0; 
    private boolean empty = true; 
    private Semaphore semaphore;
    BufferSemaphore(Semaphore semaphore){
        this.semaphore = semaphore;
    }

    @Override
    public boolean isEmpty() {
        return empty;
    }

    @Override
    public void setValue(long val) {
        value = val;
        this.empty = false; 
    }

    @Override
    public long getValue() throws InterruptedException {
        if(this.value==0) throw new InterruptedException();
        this.semaphore.acquire();
        return this.value;
    }

    @Override
    public void clear() {
        this.semaphore.release();
        this.value = 0;
        this.empty = true;
    }
    
}
public class RandomGeneratorThread extends Thread {
    Random random; 
    private long generated = 0;
    public RandomGeneratorThread(){
        this.random = new Random();
    }
    private int randomValue(){
        return random.nextInt(4000)+1000;
    }
    
    private BufferInterface buffer;

    public void setBuffer(BufferInterface buffer){
        this.buffer = buffer;
    }
    public BufferInterface getBuffer(){
        return this.buffer;
    }

    public void clear(){
        buffer.clear();
    }
    private boolean isStopped = false;
    public void run(){
            while(!isStopped){
                try {Thread.sleep(1);
                    if(generated==0){
                        generated = randomValue();
                        System.out.println("Generated value: <"+generated+">");
                    }
                }
                catch(InterruptedException ex){
                    System.out.println(ex);
                }
            if(buffer.isEmpty()){
                buffer.setValue(generated);
                System.out.println("Value  <"+generated+"> has been added");
                generated = 0;
            }
        }
    }

    public void continueGenerating(){
        isStopped = false;
    }
    public void stopGenerating(){
        isStopped = true;
    }
    public long getValue() throws InterruptedException{
        return buffer.getValue();
    }
}