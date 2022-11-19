package Unit9.Task1;

import java.util.Date;

public class Job implements Runnable {
    private Counter counter;
    Thread t;
    private int n = 0;
    private int millis;
    Date d = new Date();

    public Job(Counter counter, int millis, String name) {
        this.millis = millis;
        this.counter = counter;
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Запущен поток " + t.getName());
        while(((new Date()).getTime() - d.getTime()) < millis) {
            n++;
            counter.inc();
        }
    }

    public int getN(){
        return n;
    }
}
