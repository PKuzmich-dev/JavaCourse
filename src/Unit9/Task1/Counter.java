package Unit9.Task1;

public class Counter {
    private int n;

    synchronized void inc(){
        n++;
    }

    int get(){
        return n;
    }
}
