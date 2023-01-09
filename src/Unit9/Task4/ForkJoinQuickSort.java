package Unit9.Task4;

import java.util.ArrayList;
import java.util.concurrent.RecursiveAction;

public class ForkJoinQuickSort<T extends Comparable> implements QuickSort{
    private ArrayList<T> col;

    @Override
    public void sort() {
        if (col.size() == 0)
            return;
        new Swapping(0, col.size()-1).invoke();
    }

    ForkJoinQuickSort(ArrayList<T> c){
        col = c;
    }

    class Swapping extends RecursiveAction{
        int low,high;

        Swapping(int low, int high){
            this.low = low;
            this.high = high;
        }
        @Override
        protected void compute() {
            if (low >= high || low < 0)
                return;
            int p = partition(col, low, high);
            invokeAll(new Swapping(low, p - 1), new Swapping(p+1, high));
        }
    }
}
