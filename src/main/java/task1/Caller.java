package task1;

import java.util.Queue;

public class Caller extends Thread {
    private static final int DELAY = 1000;
    private static final int NUMBER_OF_CALLS = 60;
    private final Queue<String> callQueue;

    public Caller(Queue<String> callQueue) {
        this.callQueue = callQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= NUMBER_OF_CALLS; i++) {
            callQueue.add("Звонок " + i);
            try {
                sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
