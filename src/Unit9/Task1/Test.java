package Unit9.Task1;

import java.util.Date;
import static org.junit.Assert.*;

public class Test {

    @org.junit.Test
    public void testCounter() throws InterruptedException {
        Counter counter = new Counter();
        int millis = 10000;
        Date d = new Date();
        Job j1 = new Job(counter, millis, "№1");
        Job j2 = new Job(counter, millis, "№2");
        Job j3 = new Job(counter, millis, "№3");

        Thread.sleep(millis/2);

        System.out.println("Промежуточные значения:");
        int c1 = j1.getN();
        int c2 = j2.getN();
        int c3 = j3.getN();
        System.out.println("Job №1 сработал " + c1 + " раз");
        System.out.println("Job №2 сработал " + c2 + " раз");
        System.out.println("Job №3 сработал " + c3 + " раз");
        assertNotEquals(0, c1);
        assertNotEquals(0, c2);
        assertNotEquals(0, c3);

        System.out.println();

        j1.t.join();
        j2.t.join();
        j3.t.join();
        System.out.println("Текущее значение счетчика " + counter.get());
        assertEquals(counter.get(), j1.getN()+ j2.getN() + j3.getN());
    }
}
