/**
 * Created by dash on 15/8/16.
 */
public class Data {
    private String binData[] = new String[10];
    public int intData[] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private long sum, prod, avg;
    public void setValue(String val, int idx) {
        binData[idx] = val;
    }

    public void convert() throws InterruptedException {
        Thread t[] = new Thread[10];
        for (int i=0;i<10;i++)
        {
            t[i] = new Thread(new ConvertBinToInt(binData[i], intData, i));
            t[i].start();
        }
        for (int i=0;i<10;i++)
            t[i].join();
    }

    public void setSum(long sum)
    {
        this.sum = sum;
    }

    public void setProd(long prod)
    {
        this.prod = prod;
    }

    public void getAvg()
    {
        this.avg = sum/intData.length;
    }

    public void check_thresholds(long avg, long sum, long prod)
    {
        if(this.avg > avg)
            System.out.println("State detected from average");
        if(this.sum > sum)
            System.out.println("State detected from sum");
        if(this.prod > prod)
            System.out.println("State detected from multiplication");
    }

    public void printVal()
    {
        for (int i=0;i<intData.length;i++)
            System.out.print(intData[i] + " ");
        System.out.println();
        System.out.println("Sum: " + sum + ", Avg: " + avg + ", Prod: " + prod);
    }
}
