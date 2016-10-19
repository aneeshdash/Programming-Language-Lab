import java.util.Random;

/**
 * Created by dash on 15/8/16.
 * Generates random 8-bit binary strings
 */
public class RandomString implements Runnable {
    private String name;
    private Data data;
    private int index;
    public RandomString(int index, Data data) {
        this.name = Integer.toString(index + 1);
        this.data = data;
        this.index = index;
    }

    public void run()
    {
        while(true) {
            StringBuilder binNumber = new StringBuilder(8);
            Random b = new Random();        //class to generate random bits
            for (int i = 0; i < 8; i++) {
                binNumber.append(b.nextInt(2));     //append bits to string
            }
            data.setValue(binNumber.toString(), index);     //synchronized not required as locks are used
            //sleep for some time after producing a number
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String args[]) throws InterruptedException {
        Data data = new Data();
        for (int i=0;i<10;i++)
        {
            Thread t = new Thread(new RandomString(i, data));
            t.start();
        }
        while(true)
        {
            synchronized (data) {
                data.convert();
            }

        }

    }
}
