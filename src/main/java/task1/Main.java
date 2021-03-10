package task1;

import sun.nio.ch.ThreadPool;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Main {
    private static Queue<String> callQueue = new ConcurrentLinkedQueue<String>();
    private static final int NUMBER_OF_OPERATORS = 3;

    public static void main(String[] args) throws InterruptedException {
        Caller caller = new Caller(callQueue);
        caller.start();
        Thread.sleep(3000);
        for (int i = 1; i <= NUMBER_OF_OPERATORS; i++) {
            new Thread(new Operator(callQueue, "оператор" + i)).start();
        }
    }

}
