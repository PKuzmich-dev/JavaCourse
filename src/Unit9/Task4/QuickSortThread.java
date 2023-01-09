package Unit9.Task4;

import java.util.ArrayList;

public class QuickSortThread<T extends Comparable> implements QuickSort{

    private ArrayList<T> col;
    @Override
    public void sort() {
        if (col.size() == 0)
            return;
        quicksort(0, col.size()-1);
    }

    private void quicksort(int low, int high) {
        if (low >= high || low < 0)
            return;
        int p = partition(col, low, high);
        Thread t1 = new Thread(() -> quicksort(low, p - 1));
        Thread t2 = new Thread(() -> quicksort(p + 1, high));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    QuickSortThread(ArrayList<T> c){
        col = c;
    }
}
