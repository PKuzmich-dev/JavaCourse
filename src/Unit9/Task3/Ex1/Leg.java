package Unit9.Task3.Ex1;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class Leg implements Runnable {
    private final String name;
    private volatile static String nameEnabled = "left";
    public Leg(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        while (true) {
            if (name.equals(nameEnabled)) {
                System.out.println(name);
                nameEnabled = (name.equals("left")) ? "right" : "left";
            }
        }
    }
    public static void main(String[] args) {
        CompletableFuture.allOf(
            CompletableFuture.runAsync(new Leg("left")),
            CompletableFuture.runAsync(new Leg("right"))
        ).join();
    }
}
