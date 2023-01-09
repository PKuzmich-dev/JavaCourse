package Unit9.Task4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RecursiveQuickSort<T extends Comparable> implements QuickSort{

    private ArrayList<T> col;

    @Override
    public void sort() {
        if (col.size() == 0)
            return;
        quicksort(0, col.size()-1);
    }

    private void quicksort(int low, int high){
        if (low >= high || low < 0)
            return;
        int p = partition(col, low, high);
        quicksort(low, p - 1);
        quicksort(p + 1, high);
    }
    RecursiveQuickSort(ArrayList<T> c){
        col = c;
    }
}
