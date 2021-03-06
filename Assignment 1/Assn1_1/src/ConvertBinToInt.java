/**
 * Created by dash on 15/8/16.
 * Class to convert String to bits to integer
 */
public class ConvertBinToInt implements Runnable {
    private int[] data;
    private int index;
    private String binStr;
    public ConvertBinToInt(String binStr, int[] data, int index)
    {
        this.binStr = binStr;
        this.index = index;
        this.data = data;
    }
    public void run()
    {
        data[index] = Integer.parseInt(binStr,2);
    }
}
