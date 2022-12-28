package Unit9.Task3.Ex5;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;

public class Leg implements Runnable {
    private final String name;
    private static ArrayBlockingQueue<String> queue;
    public Leg(String name) {
        if (queue == null) {
            queue = new ArrayBlockingQueue<>(1);
            queue.add(name);
        }
        this.name = name;
    }
    @Override
    public void run() {
        String queueEl;
        while (true) {
            queueEl = queue.peek();
            if (queueEl!=null && queueEl.equals(name)) {
                System.out.println(name);
                try {
                    queue.take();
                    queue.put((name.equals("left")) ? "right" : "left");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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