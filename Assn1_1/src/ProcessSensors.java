import java.util.concurrent.ForkJoinPool;

/**
 * Created by dash on 15/8/16.
 */
public class ProcessSensors {
    public static void main(String args[]) throws InterruptedException {
        Data data = new Data();
        long sumThresh = 10000;
        long avgThresh = 100;
        long prodThresh = 100000;
        for (int i=0;i<10;i++)
        {
            Thread t = new Thread(new RandomString(i, data));
            t.start();
        }
        Thread.sleep(100);
        while(true)
        {
            synchronized (data) {
                data.convert();
            }
            ForkJoinPool.commonPool().invoke(new MergeSort(data.intData));
            Thread add = new Thread(new AddNumbers(data));
            add.start();
            Thread mul = new Thread(new MultiplyArray(data));
            mul.start();
            add.join();
            data.getAvg();
            mul.join();
            data.check_thresholds(avgThresh, sumThresh, prodThresh);
//            System.out.println("Main process");
            data.printVal();
            Thread.sleep(1000);
        }
    }
}
