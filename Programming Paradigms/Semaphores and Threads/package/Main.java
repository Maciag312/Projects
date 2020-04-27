
import java.util.concurrent.Semaphore;

public class Main{
    public static void main(String[] args) {
        // RandomGeneratorThread randomGeneratorThread2 = new RandomGeneratorThread();
        // randomGeneratorThread2.setBuffer(new Buffer());
        // randomGeneratorThread2.start();
        // Reader reader1 = new Reader(1, randomGeneratorThread2);
        // Reader reader2 = new Reader(2, randomGeneratorThread2);
        // reader1.start();
        // reader2.start();


        BufferSemaphore buf = new BufferSemaphore(new Semaphore(1));
        RandomGeneratorThread randomgeneratorthread = new RandomGeneratorThread(); 
        randomgeneratorthread.setBuffer(buf);
        randomgeneratorthread.start();

        Semaphore semaphore = new Semaphore(1);
        Reader reader3 = new Reader(3, randomgeneratorthread);
        Reader reader4 = new Reader(4, randomgeneratorthread);
        reader3.start();
        reader4.start();

       


    }
}
