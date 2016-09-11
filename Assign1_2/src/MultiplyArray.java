import java.math.BigInteger;

/**
 * Created by dash on 15/8/16.
 * Multiply elements of an integer array
 */
public class MultiplyArray implements Runnable {
    private Data data;
    public  MultiplyArray(Data data) {
        this.data = data;
    }

    public void run()
    {
        BigInteger prod = BigInteger.valueOf(1);
        for (int i=0;i<data.intData.length;i++)
            prod = prod.multiply(BigInteger.valueOf(data.intData[i]));
        data.setProd(prod);
    }
}
