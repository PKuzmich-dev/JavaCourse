package Unit9.Task4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;
import org.junit.After;
import org.junit.Before;

public class Test {
    ArrayList<Integer> col = new ArrayList<>();
    @Before
    public void initCollection() {
        Random r = new Random();
        int tmp;
        for(int i=0; i< 100; i++) {
            col.add(r.nextInt());
        }

        System.out.println("До сортировки:");
        //System.out.println(col);
        System.out.println(new Date());
    }

    @After
    public void printCollection() {
        System.out.println("После сортировки:");
        System.out.println(new Date());
        //System.out.println(col);
    }

    @org.junit.Test
    public void testRecursiveQuickSort() {
        RecursiveQuickSort rqs = new RecursiveQuickSort(col);
        rqs.sort();
        assertTrue(sorted());
    }

    @org.junit.Test
    public void QuickSortThread(){
        QuickSortThread rqs = new QuickSortThread(col);
        rqs.sort();
        assertTrue(sorted());
    }

    @org.junit.Test
    public void ForkJoinQuickSort(){
        ForkJoinQuickSort rqs = new ForkJoinQuickSort(col);
        rqs.sort();
        assertTrue(sorted());
    }

    private boolean sorted(){
        for(int i=1; i < col.size(); i++)
            if (col.get(i) < col.get(i-1))
                return false;
        return true;
    }
}
