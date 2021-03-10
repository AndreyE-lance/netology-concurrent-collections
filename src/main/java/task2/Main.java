package task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*Результаты наглядно представлены в выводе терминала. Можно сделать вывод,
        что с увиличением числа записей synchronizedMap начинает обгонять
        ConcurrentHashMap в записи*/

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long step = 10;
        Map<Integer, Integer> conHashMap = new ConcurrentHashMap<>();
        Map<Integer, Integer> syncHashMap = Collections.synchronizedMap(conHashMap);
        System.out.println("Количество ядер " + Runtime.getRuntime().availableProcessors());
        while (step <= 10_000_000) {
            runTest(conHashMap, step, true);
            runTest(conHashMap, step, false);
            runTest(syncHashMap, step, true);
            runTest(syncHashMap, step, false);
            step *= 10;
        }
    }

    private static void runTest(Map<Integer, Integer> map, long step, boolean readWrite) throws InterruptedException {
        long start, finish;
        List<Callable<Boolean>> callableList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        callableList.add(new MapOperations(map, step, readWrite));
        start = System.currentTimeMillis();
        executorService.invokeAll(callableList);
        finish = System.currentTimeMillis();
        executorService.shutdown();
        String mapType;
        if (map instanceof ConcurrentHashMap) {
            mapType = "ConcurrentHashMap";
        } else {
            mapType = "synchronizedMap";
        }
        StringBuilder sBuilder = new StringBuilder("Время работы с ")
                .append(step)
                .append(" элементами в реализации ")
                .append(mapType)
                .append(" равно ")
                .append(finish - start)
                .append(" мс.")
                .append(readWrite ? "Запись." : "Чтение.");

        System.out.println(sBuilder.toString());
    }

}
