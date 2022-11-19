package Unit9.Task1;

public class Counter {
    int n;

    synchronized void inc(){
        n++;
    }

    int get(){
        return n;
    }
}
