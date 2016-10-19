import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by dash on 15/8/16.
 * Sort an array of numbers using Merge Sort and concurrency
 */
public class MergeSort extends RecursiveAction {
    private int[] data;
    private int start, end;
    public MergeSort(int[] data) {
        this.data = data;
    }

    public void compute()
    {
        //base cases
        if(data.length == 1)
            return;
        if(data.length == 2) {
            if(data[0] > data[1]) {
                int temp = data[0];
                data[0] = data[1];
                data[1] = temp;
            }
            return;
        }
        //calculate mid point and split array
        int mid = (data.length - 1)/2;
        int leftData[] = new int[mid + 1];
        int rightData[] = new int[data.length - 1 - mid];
        for (int i=0;i<leftData.length;i++)
            leftData[i] = data[i];
        for (int i=0;i<rightData.length;i++)
            rightData[i] = data[mid + i + 1];
        //recurse
        MergeSort left = new MergeSort(leftData);
        MergeSort right = new MergeSort(rightData);
        //run left in a new thread and right in the same thread
        left.fork();
        right.compute();
        left.join();
        int lidx=0, ridx=0, idx=0;
        //now merge
        while (lidx < leftData.length && ridx < rightData.length)
        {
            if (leftData[lidx] < rightData[ridx]) {
                data[idx] = leftData[lidx];
                lidx++;
                idx++;
            }
            else {
                data[idx] = rightData[ridx];
                ridx++;
                idx++;
            }
        }
        while (lidx < leftData.length) {
            data[idx] = leftData[lidx];
            lidx++;
            idx++;
        }
        while (ridx < rightData.length) {
            data[idx] = rightData[ridx];
            ridx++;
            idx++;
        }
    }

    public static void main(String args[])
    {
        int[] data = new int[]{1, 5, 6, 4, 2, 3};
        ForkJoinPool.commonPool().invoke(new MergeSort(data));
        System.out.println(Arrays.toString(data));
    }
}
