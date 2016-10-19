import java.math.BigInteger;
import java.util.LinkedList;

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

    public Data()
    {
        //initialize the queues
        for (int i=0;i<10;i++)
            data[i] = new LinkedList<String >();
    }

    //add value to queue
    public void setValue(String val, int idx) {
        binData[idx] = val;
        data[idx].add(val);
    }

    //convert string to integers and count number of sensors which produced data
    public int convert() throws InterruptedException {
        Thread t[] = new Thread[10];
        int count=0;
        String val;
        for (int i=0;i<10;i++)
        {
            val = data[i].poll();
            if(val != null) {
                t[i] = new Thread(new ConvertBinToInt(val, intData, i));
                t[i].start();
                count++;
            }
            else
                t[i] = null;
        }
        for (int i=0;i<10;i++)
            t[i].join();
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

    //print calculated values to console
    public void printVal()
    {
        for (int i=0;i<intData.length;i++)
            System.out.print(intData[i] + " ");
        System.out.println();
        System.out.println("Sum: " + sum + ", Avg: " + avg + ", Prod: " + prod);
    }
}
