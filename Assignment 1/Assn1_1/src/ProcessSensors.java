import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by dash on 15/8/16.
 * Creates sensors and calcuates the required values
 */
public class ProcessSensors {
    public static void main(String args[]) throws InterruptedException {
        Data data = new Data();                                 //class for handling data
        //set thresholds
        long sumThresh = 10000;
        long avgThresh = 100;
        BigInteger prodThresh = new BigInteger("100000");
        int count;      //count of sensors which returned data
        //create sensor threads and start them
        for (int i=0;i<10;i++)
        {
            Thread t = new Thread(new RandomString(i, data));
            t.start();
        }
        //delay for sensors to start producing output
        Thread.sleep(100);
        while(true)
        {
            //no sensor should access data while reading
            synchronized (data) {
                count = data.convert();
            }
            if(count == 10) {
                ForkJoinPool.commonPool().invoke(new MergeSort(data.intData));
                Thread add = new Thread(new AddNumbers(data));          //threads to add numbers
                add.start();
                Thread mul = new Thread(new MultiplyArray(data));       //thread to multiply numbers
                mul.start();
                add.join();                                             //wait for threads to finish
                data.getAvg();
                mul.join();
                data.check_thresholds(avgThresh, sumThresh, prodThresh);    //check thresholds
                data.printVal();                                            //print outputs
            }
            //if some sensor not producing data
            else
                System.out.println("Some sensor not producing data.");
            Thread.sleep(1000);
        }
    }
}
