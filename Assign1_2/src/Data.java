import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dash on 15/8/16.
 * Class to store and process data
 */
public class Data {
    private String binData[] = new String[10];      //not used any more
    public int intData[] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};     //store integer equivalent of first entry of queue while processing
    public LinkedList<String>[] data = new LinkedList[10];      //queue for storing sensor outputs
    private long sum, avg;      //outputs of post-processing
    private BigInteger prod;
    ReentrantLock lock = new ReentrantLock();           //lock for locking class

    public Data()
    {
        //initialize the queues
        for (int i=0;i<10;i++)
            data[i] = new LinkedList<String >();
    }

    //add value to queue and lock before adding
    public void setValue(String val, int idx) {
        lock.lock();
        try {
            binData[idx] = val;
            data[idx].add(val);
        }
        finally {
            lock.unlock();
        }
    }

    //convert string to integers and count number of sensors which produced data
    //lock is acquired so other sensors cannot add values.
    public int convert() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Thread t[] = new Thread[10];
        int count=0;
        String val;
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                val = data[i].poll();
                if (val != null) {
                    pool.execute(new ConvertBinToInt(val, intData, i));
                    count++;
                } else
                    t[i] = null;
            }
            pool.shutdown();
        }
        finally {
            lock.unlock();
        }
        return count;
    }

    //set sum of values
    public void setSum(long sum)
    {
        this.sum = sum;
    }

    //set product of values
    public void setProd(BigInteger prod)
    {
        this.prod = prod;
    }

    //get average of sensor values
    public void getAvg()
    {
        this.avg = sum/intData.length;
    }

    //check if any value exceeded the thresholds
    public void check_thresholds(long avg, long sum, BigInteger prod)
    {
        if(this.avg > avg)
            System.out.println("State detected from average");
        if(this.sum > sum)
            System.out.println("State detected from sum");
        if(this.prod.compareTo(prod) == 1)
            System.out.println("State detected from multiplication");
    }

    //print values to console
    public void printVal()
    {
        for (int i=0;i<intData.length;i++)
            System.out.print(intData[i] + " ");
        System.out.println();
        System.out.println("Sum: " + sum + ", Avg: " + avg + ", Prod: " + prod);
    }
}
