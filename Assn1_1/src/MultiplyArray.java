/**
 * Created by dash on 15/8/16.
 */
public class MultiplyArray implements Runnable {
    private Data data;
    public  MultiplyArray(Data data) {
        this.data = data;
    }

    public void run()
    {
        long prod = 1;
        for (int i=0;i<data.intData.length;i++)
            prod *= data.intData[i];
        data.setProd(prod);
    }
}
