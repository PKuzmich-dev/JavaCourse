package Unit9.Task4;

import java.util.ArrayList;

public interface QuickSort<T extends Comparable> {
    void sort();

    public default int partition(ArrayList<T> col, int low, int high){
        T pivot = col.get(high);
        int i = low - 1;
        T tmp;
        for (int j = low; j < high; j++){
            if (col.get(j).compareTo(pivot) < 0){
                i++;
                tmp = col.get(j);
                col.set(j, col.get(i));
                col.set(i, tmp);
            }
        }
        i++;
        tmp = col.get(high);
        col.set(high, col.get(i));
        col.set(i, tmp);
        return i;
    }
}