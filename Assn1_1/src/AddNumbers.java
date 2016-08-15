/**
 * Created by dash on 15/8/16.
 */
public class AddNumbers implements Runnable {
    private Data data;
    public AddNumbers(Data data)
    {
        this.data = data;
    }
    public void run()
    {
        long sum = 0;
        for (int i=0;i<data.intData.length;i++)
            sum += data.intData[i];
        data.setSum(sum);
    }
}
