package task1;

import java.util.Queue;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Operator implements Runnable {
    private static final int MIN_PROCESSING_TIME = 3000;
    private final Queue<String> callQueue;
    private final String name;
    private final Random rnd = new Random();

    public Operator(Queue<String> callQueue, String name) {
        this.callQueue = callQueue;
        this.name = name;
    }

    @Override
    public void run() {
        String call;
        while ((call = callQueue.poll()) != null) {
            try {
                sleep(MIN_PROCESSING_TIME + rnd.nextInt(7000));
                System.out.println(call + " обработан " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
